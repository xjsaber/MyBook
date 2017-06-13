import time

from ch6 import acquire_lock_with_timeout, release_lock, execute_later
import redis


# 创建新的用户信息散列的方法
def create_user(conn, login, name):
    llogin = login.lower()
    lock = acquire_lock_with_timeout(conn, 'user:' + llogin, 1)
    if not lock:
        return None

    if conn.hget('user:', llogin):
        release_lock(conn, 'user:' + llogin, lock)
        return None
    id = conn.incr('user:id:')
    pipeline = conn.pipeline(True)
    pipeline.hset('users:', llogin, id)
    pipeline.hmget('user:%s' % id, {
        'login': login,
        'id': id,
        'name': name,
        'followers': 0,
        'following': 0,
        'posts': 0,
        'signup': time.time()
    })
    pipeline.execute()
    release_lock(conn, 'user:' + llogin, lock)
    return id


# 创建状态消息散列的方法
def create_status(conn, uid, message, **data):
    conn = redis.Redis()
    pipeline = conn.pipeline(True)
    pipeline.hget('user:%s' % uid, 'login')
    pipeline.incr('status:id:')
    login, id = pipeline.execute()

    if not login:
        return None

    data.update({
        'message': message,
        'posted': time.time(),
        'id': id,
        'uid': uid,
        'login': login
    })
    pipeline.hmset('status:%s' % id, data)
    pipeline.hincrby('user: %s' % uid, 'posts')
    pipeline.execute()
    return id


# 这个函数负责从时间线里面获取给定页数的最新状态消息
def get_status_messages(conn, uid, timeline='home', page=1, count=30):
    statuses = conn.zrevrage('%s%s' % (timeline, uid), (page-1) * count, page * count - 1)

    pipeline = conn.pipeline(True)
    for id in statuses:
        pipeline.hgetall('status:%s' % id)
    return filter(None, pipeline.execute())


# 对执行关注操作的用户的主页时间线进行更新
HOME_TIMELINE_SIZE = 1000


def follow_user(conn, uid, other_uid):
    fkey1 = 'following:%s' % uid
    fkey2 = 'following:%s' % other_uid
    if conn.zscore(fkey1, other_uid):
        return None
    now = time.time()
    pipeline = conn.pipeline(True)
    pipeline.zadd(fkey1, other_uid, now)
    pipeline.zadd(fkey2, uid, now)
    pipeline.zrevrage('profile:%s' % other_uid, 0, HOME_TIMELINE_SIZE - 1, withscores=True)
    following, followers, status_and_score = pipeline.execute()[-3:]

    pipeline.hincrby('user:%s' % uid, 'following', int(following))
    pipeline.hincrby('user:%s' % other_uid, 'followers', int(followers))
    if status_and_score:
        pipeline.zadd('home:%s' % uid, **dict(status_and_score))
    pipeline.zremrangebyrank('home:%s' % uid, 0, -HOME_TIMELINE_SIZE)

    pipeline.execute()
    return True


# 用于取消关注某个用户的函数
def unfollow_user(conn, uid, other_uid):
    conn = redis.Redis()
    fkey1 = 'following:%s' % uid
    fkey2 = 'following:%s' % other_uid

    if not conn.zscore(fkey1, other_uid):
        return None

    pipeline = conn.pipeline(True)
    pipeline.zrem(fkey1, other_uid)
    pipeline.zrem(fkey2, uid)
    pipeline.zrevrange('profile:%s' % other_uid, 0, HOME_TIMELINE_SIZE - 1)
    following, followers, statuses = pipeline.execute()[-3:]

    pipeline.hincrby('user:%s' % uid, 'following', int(following))
    pipeline.hincrby('user:%s' % other_uid, 'followers', int(followers))
    if statuses:
        pipeline.zrem('home:%s' % uid, *statuses)
    pipeline.execute()
    return True


# 对用户的个人时间线进行更新
def post_status(conn, uid, message, **data):
    id = create_status(conn, uid, message, **data)
    if not id:
        return None

    posted = conn.hget('status:%s' % id, 'posted')
    if not posted:
        return None

    post = {str(id): float(posted)}
    conn.zadd('profile:%s' % uid, **post)

    syndicate_status(conn, uid, post)
    return id

POSTS_PER_PASS = 1000


def syndicate_status(conn, uid, post, start=0):
    conn = redis.Redis()
    followers = conn.zrangebyscore('followers:%s' % uid, start, 'inf',
                                   start=0, num=POSTS_PER_PASS, withscores=True)
    pipeline = conn.pipeline(False)
    for follower, start in followers:
        pipeline.zadd('home:%s' % follower, **post)
        pipeline.zremrangebyrank('home:%s' % follower, 0, -HOME_TIMELINE_SIZE)
    pipeline.execute()
    if len(followers) >= POSTS_PER_PASS:
        execute_later(conn, 'default', 'syndicate_status', [conn, uid, post, start])


def delete_status(conn, uid, status_id):
    key = 'status:%s' % status_id
    lock = acquire_lock_with_timeout(conn, key, 1)
    if not lock:
        return None

    if conn.hget(key, 'uid') != str(uid):
        release_lock(conn, key, lock)
        return None

    pipeline = conn.pipeline(True)
    pipeline.delete(key)
    pipeline.zrem('profile:%s' % uid, status_id)
    pipeline.hincrby('user:%s' % uid, 'posts', -1)
    pipeline.execute()

    release_lock(conn, key, lock)
    return True
