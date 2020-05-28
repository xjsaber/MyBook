import json
import uuid
import math
import redis
import time

conn = redis.Redis()
pipe = conn.pipeline()


# 代码清单6-1 add_update_contact()函数
def add_update_contact(user, contact):
    ac_list = 'recent:' + user
    pipeline = conn.pipeline(True)
    # 如果联系人已经存在，那么移除他
    pipeline.lrem(ac_list, contact)
    # 将联系人推入列表的最前端
    pipeline.lpush(ac_list, contact)
    # 只保留列表里面的前100个联系人
    pipeline.ltrim(ac_list, 0, 99)
    pipeline.execute()


# 获取锁
def acquire_lock(conn, lockname, acquire_timeout=10):
    identifier = str(uuid.uuid4())

    end = time.time() + acquire_timeout
    while time.time() < end:
        if conn.setnx('lock:' + lockname, identifier):
            return identifier
        time.sleep(.001)
    return False


def purchase_item_with_lock(conn, buyerid, itemid, sellerid):
    buyer = "users:%s" % buyerid
    seller = "users:%s" % sellerid
    item = "%s.%s" % (itemid, sellerid)
    inventory = "inventory:%s" % buyerid

    locked = acquire_lock(conn, market)
    if not locked:
        return False

    pipe = conn.pipeline(True)
    try:
        pipe.zscore("market:", item)
        pipe.hget(buyer, "funds")
        price, funds = pipe.execute()
        if price is None or price > funds:
            return None

        pipe.hincrby(seller, 'funds', int(price))
        pipe.hincrby(buyer, 'funds', int(-price))
        pipe.sadd(inventory, itemid)
        pipe.zrem("market:", item)
        pipe.execute()
        return True
    finally:
        release_lock(conn, market, locked)


def release_lock(conn, lockname, identifier):
    # 检查进程是否仍然持有锁
    pipe = conn.pipeline(True)
    lockname = 'lock:' + lockname

    while True:
        try:
            pipe.watch(lockname)
            # 释放锁
            if pipe.get(lockname) == identifier:
                pipe.multi()
                pipe.delete(lockname)
                pipe.execute()
                return True
            pipe.unwatch()
            break
        # 有其他客户端修改了锁，重试
        except redis.exceptions.WatchError:
            pass
    # 进程已经失去了锁
    return False


# 获得锁超时限制特性
def acquire_lock_with_timeout(conn, lockname, acquire_timeout=10, lock_timeout=10):
    # 128位随机标识符
    identifier = str(uuid.uuid4())
    lockname = 'lock:' + lockname
    # 确保传给EXPIRE的都是整数
    lock_timeout = int(math.ceil(lock_timeout))

    end = time.time() + acquire_timeout
    while time.time() < end:
        # 获取锁并设置过期时间
        if conn.setnx(lockname, identifier):
            conn.expire(lockname, lock_timeout)
            return identifier
        # 检查过期时间，并在有需要时对其进行更新
        elif not conn.ttl(lockname):
            conn.expire(lockname, lock_timeout)

        time.sleep(.001)
    return False


# 获取信号量
def acquire_semaphore(conn, semname, limit, timeout=10):
    identifier = str(uuid.uuid4())
    now = time.time()

    pipeline = conn.pipeline(True)
    pipeline.zremrangebyscore(semname, '-inf', now - timeout)
    pipeline.zadd(semname, identifier, now)
    pipeline.zrank(semname, identifier)
    if pipeline.execute()[-1] < limit:
        return identifier

    conn.zrem(semname, identifier)
    return None


# 释放信号量
def release_semaphore(conn, semname, identifier):
    return conn.zrem(semname, identifier)


def acquire_fair_semaphore(conn, semname, limit, timeout=10):
    # 128位随机标识符
    identifier = str(uuid.uuid4())
    czset = semname + ':owner'
    ctr = semname + ':counter'

    now = time.time()
    pipeline = conn.pipeline(True)
    # 删除超时的信号量
    pipeline.zremrangebyscore(semname, '-inf', now - timeout)
    pipeline.zinterstore(czset, {czset: 1, semname: 0})
    # 对计数器执行自增擦欧总，并获取计数器在执行自增操作之后的值
    pipeline.incr(ctr)
    counter = pipeline.execute()[-1]
    # 尝试获取信号量
    pipeline.zadd(semname, identifier, now)
    pipeline.zadd(czset, identifier, counter)
    # 通过检查排名来判断客户端是否取得了信号量
    pipeline.zrank(czset, identifier)
    if pipeline.execute()[-1] < limit:
        # 客户端成功取得了信号量
        return identifier

    pipeline.zrem(semname, identifier)
    pipeline.zrem(czset, identifier)
    pipeline.execute()
    return None


# 释放信号量
def release_fair_semaphore(conn, semname, identifier):
    pipeline = conn.pipeline(True)
    pipeline.zrem(semname, identifier)
    pipeline.zrem(semname + ':owner', identifier)
    return pipeline.execute()[0]


# 延迟任务
def execute_later(conn, queue, name, args, delay=0):
    identifier = str(uuid.uuid4())
    item = json.dumps([identifier, queue, name, args])
    if delay > 0:
        conn.zadd('delayed:', item, time.time() + delay)
    else:
        conn.rpush('queue:' + queue, item)
    return identifier
