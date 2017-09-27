import threading

import redis
import time

import config

config = config.Config
conn = redis.Redis(host=config.host, password=config.password)


def redis_str():
    print(conn.get('key'))
    print(conn.incr('key'))
    print(conn.incr('key', 15))
    print(conn.decr('key', 5))
    print(conn.decr('key'))
    print(conn.get('key'))
    print(conn.set('key', 13))
    print(conn.incr('key'))


def redis_str_ext():
    print(conn.append('new-string-key', 'hello'))
    print(conn.append('new-string-key', 'world!'))
    print(conn.substr('new-string-key', 3, 7))
    print(conn.setrange('new-string-key', 0, 'H'))
    print(conn.append('new-string-key', 'hello'))


def redis_linked_list():
    print(conn.rpush('list-key', 'last'))
    print(conn.lpush('list-key', 'first'))
    print(conn.rpush('list-key', 'new last'))
    print(conn.lrange('list-key', 0, -1))
    print(conn.lpop('list-key'))
    print(conn.lpop('list-key'))
    print(conn.lrange('list-key', 0, -1))
    print(conn.rpush('list-key', 'a', 'b', 'c'))
    print(conn.lrange('list-key', 2, -1))
    print(conn.ltrim('list-key', 2, -1))
    print(conn.lrange('list-key', 0, -1))


def redis_linked_pop():
    conn.rpush('list', 'item1')
    conn.rpush('list', 'item2')
    conn.rpush('list2', 'item3')
    conn.brpoplpush('list2', 'list', 1)
    conn.brpoplpush('list2', 'list', 1)
    conn.lrange('list', 0, -1)
    conn.brpoplpush('list', 'list2', 1)
    conn.blpop(['list', 'list2'], 1)
    conn.blpop(['list', 'list2'], 1)
    conn.blpop(['list', 'list2'], 1)
    conn.blpop(['list', 'list2'], 1)


def redis_set():
    print(conn.sadd('set-key', 'a', 'b', 'c'))
    print(conn.srem('set-key', 'c', 'd'))
    print(conn.srem('set-key', 'c', 'd'))
    print(conn.scard('set-key'))
    print(conn.smembers('set-key'))
    print(conn.smove('set-key', 'set-key2', 'a'))
    print(conn.smove('set-key', 'set-key2', 'c'))
    print(conn.smembers('set-key2'))


def redis_set_diff():
    print(conn.sadd('skey1', 'a', 'b', 'c', 'd'))
    print(conn.sadd('skey2', 'c', 'd', 'e', 'f'))
    print(conn.sdiff('skey1', 'skey2'))
    print(conn.sinter('skey1', 'skey2'))
    print(conn.sunion('skey1', 'skey2'))


def redis_hash():
    print(conn.hmset('hash-key', {'k1': 'v1', 'k2': 'v2', 'k3': 'v3'}))
    print(conn.hmget('hash-key', ['k2', 'k3']))
    print(conn.hlen('hash-key'))
    print(conn.hdel('hash-key', 'k1', 'k3'))


def redis_hash_advance():
    print(conn.hmset('hash-key2', {'short': 'hello', 'long': 1000*'1'}))
    print(conn.hkeys('hash-key2'))
    print(conn.hexists('hash-key2', 'num'))
    print(conn.hincrby('hash-key2', 'num'))
    print(conn.hexists('hash-key2', 'num'))


def redis_zset():
    print(conn.zadd('zset-key', 'a', 3, 'b', 2, 'c', 1))
    print(conn.zcard('zset-key'))
    print(conn.zincrby('zset-key', 'c', 3))
    print(conn.zscore('zset-key', 'b'))
    print(conn.zrank('zset-key', 'c'))
    print(conn.zcount('zset-key', 0, 3))


def redis_zset_range():
    print(conn.zadd('zset-1', 'a', 1, 'b', 2, 'c', 3))
    print(conn.zadd('zset-2', 'b', 4, 'c', 1, 'd', 0))
    print(conn.zinterstore('zset-i', ['zset-1', 'zset-2']))
    print(conn.zrange('zset-i', 0, -1, withscores=True))
    print(conn.zunionstore('zset-u', ['zset-1', 'zset-2'], aggregate='min'))
    print(conn.zrange('zset-u', 0, -1, withscores=True))
    print(conn.sadd('set-1', 'a', 'd'))
    print(conn.zunionstore('zset-u2', ['zset-1', 'zset-2', 'set-1']))
    print(conn.zrange('zset-u2', 0, -1, withscores=True))


def publisher(n):
    time.sleep(1)
    for i in range(n):
        conn.publish('channel', i)
        time.sleep(1)


def run_pubsub():
    # 启动发送者线程，并让它发送三条消息
    threading.Thread(target=publisher, args=(3, )).start()
    # 创建发布与订阅对象，并让它订阅给定的频道
    pubsub = conn.pubsub()
    pubsub.subscribe(['channel'])
    count = 0
    # 通过遍历函数pubsub.listen()的执行结果来监听订阅消息
    for item in pubsub.listen():
        print(item)
        count += 1
        if count == 4:
            pubsub.unsubscribe()
        if count == 5:
            break


def redis_sort():
    print(conn.rpush('sort-input', 23, 15, 110, 7))
    print(conn.sort('sort-input'))
    print(conn.sort('sort-input', alpha=True))
    print(conn.hset('d-7', 'field', 5))
    print(conn.hset('d-15', 'field', 1))
    print(conn.hset('d-23', 'field', 9))
    print(conn.hset('d-110', 'field', 3))
    print(conn.sort('sort-input', by='d-*->field'))
    print(conn.sort('sort-input', by='d-*->field', get='d-*->field'))


def notrans():
    print(conn.incr('notrans:'))
    time.sleep(1)
    conn.incr('notrans:', -1)
# if 1:
#     for i in range(3):
#         threading.Thread(target=notrans).start()
#     time.sleep(.5)


def trans():
    pipeline = conn.pipeline()
    pipeline.incr('trans:')
    time.sleep(1)

    pipeline.incr('trans:', -1)
    print(pipeline.execute()[0])
# if 1:
#     for i in range(3):
#         threading.Thread(target=trans).start()
#     time.sleep(.5)


def redis_out_time():
    print(conn.set('key', 'value'))
    print(conn.get('key'))
    print(conn.expire('key', 2))
    print(time.sleep(2))
    print(conn.get('key'))
    print(conn.set('key', 'value2'))
    print(conn.expire('key', 100))
    print(conn.ttl('key'))

if __name__ == "__main__":
    redis_out_time()
