import json
import redis
import time


# 尝试获取并返回令牌对应的用户
def check_token(conn, token):
    return conn.hget('login', token)


# 修改令牌
def update_token(conn, token, user, item=None):
    # 获取时间搓
    timestamp = time.time()
    # 维持令牌与已登录用户之间的映射
    conn.hset('login', token, user)
    # 记录令牌最后一次出现的时间
    conn.zadd('recent:', token, timestamp)
    if item:
        # 记录用户浏览过的商品
        conn.zadd('viewed:' + token, item, timestamp)
        # 移除旧的记录，只保留用户最近浏览过的25个商品。
        conn.zremrangebyrank('viewed:' + token, 0, -26)
        conn.zincrby('viewed', item, -1) #排序


QUIT = False
LIMIT = 1000000


# 清理所有的session
def clean_sessions(conn):
    while not QUIT:
        # 找出目前已有令牌的数量
        size = conn.zcard('recent:')
        # 令牌数量未超过限制，休眠并在之后重新检查。
        if size <= LIMIT:
            time.sleep(1)
            continue
        # 获取需要移除的令牌ID
        end_index = min(size - LIMIT, 100)
        tokens = conn.zrange('recent:', 0, end_index-1)

        # 为那些将要被删除的令牌构建键名
        session_keys = []
        for token in tokens:
            session_keys.append('viewed:' + token)

        # 移除最旧得到那些令牌
        conn.delete(*session_keys)
        conn.hdel('login:', *tokens)
        conn.zrem('recent:', *tokens)


# 添加到购物车
def add_to_cart(conn, session, item, count):
    if count:
        # 从购物车里面移除指定的商品
        conn.hrem('cart:' + session, item)
    else:
        # 将指定的商品添加到购物车
        conn.hset('cart:' + session. item, count)


# 清除所有的缓存
def clean_full_sessions(conn):
    while not QUIT:
        size = conn.zcard('recent:')
        if size <= LIMIT:
            time.sleep(1)

        end_index = min(size - LIMIT, 100)
        sessions = conn.zrange('recent:', 0, end_index-1)

        session_keys = []
        for session in sessions:
            session_keys.append('viewed:' + session)
            session_keys.append('cart:' + session)

        conn.delete(*session_keys)
        conn.hdel('login:', *sessions)
        conn.zrem('recent:', *sessions)
    pass


def schedule_row_cache(conn, row_id, delay):
    conn.zadd('delay:', row_id, delay)
    conn.zadd('schedule:', row_id, time.time())


# 守护进程函数
def cache_rows(conn):
    while not QUIT:
        next = conn.zrange('schedule:', 0, 0, withscores=True)
        now = time.time()
        # 尝试获取下一个需要被缓存的数据行以及该行的调度时间戳，命令会返回一个包含零个或一个元祖（tuple）的列表
        # 暂时没有行需要被缓存，休眠50毫秒
        if not next or next[0][1] > now:
            time.sleep(.05)
            continue

        row_id = next[0][0]

        # 提前获取下一次调度的延迟时间
        delay = conn.zscore('delay:', row_id)
        if delay <= 0:
            # 不必再缓存这个行，将它从缓存中移除
            conn.zrem('delay:', row_id)
            conn.zrem('schedule:', row_id)
            conn.delete('inv:' + row_id)
            continue

        # 读取数据行
        row = Inventory.get(row_id)
        conn.zadd('schedule:', row_id, now + delay)
        conn.set('inv:' + row_id, json.dumps(row.to_dict()))


def rescale_viewed(conn):
    while not QUIT:
        conn.zremrangebyrank('viewed', 0, -20001)
        conn.zinterstore('viewed:', {'viewed:': .5})
        time.sleep(300)


def can_cache(conn, request):
    # 尝试从页面取出商品ID
    item_id = extract_item_id(request)
    # 检查这个页面能否被缓存以及这个页面是否为商品页面
    if not item_id or is_dynamic(request):
        return False
    # 取得商品的浏览次数排名
    rank = conn.zrank('viewed:', item_id)
    # 根据商品的浏览次数排名来判断是否需要缓存这个页面
    return rank is not None and rank < 10000