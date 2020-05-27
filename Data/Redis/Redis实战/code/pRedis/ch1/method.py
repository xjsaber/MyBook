import redis
import time

conn = redis.Redis(host="182.254.136.245", password="redis1234")
ONE_WEEK_IN_SECONDS = 7 * 86400
VOTE_SCORE = 432


# redis string功能
def redis_string():
    conn.set("hello", "world")
    print(conn.get("hello"))
    conn.delete("hello")
    print(conn.get("hello"))


# redis linked-list 功能
def redis_linked():
    print(conn.rpush("list-key", "item"))
    print(conn.rpush("list-key", "item2"))
    print(conn.rpush("list-key", "item"))
    print(conn.lrange("list-key", 0, -1))
    print(conn.lindex("list-key", 1))
    print(conn.lpop("list-key"))
    print(conn.lrange("list-key", 0, -1))


# redis map 功能
def redis_smembers():
    print(conn.sadd("set-key", "item"))
    print(conn.sadd("set-key", "item2"))
    print(conn.sadd("set-key", "item3"))
    print(conn.sadd("set-key", "item"))
    print(conn.smembers("set-key"))
    print(conn.sismember("set-key", "item2"))
    print(conn.sismember("set-key", "item4"))
    print(conn.srem("set-key", "item2"))
    print(conn.srem("set-key", "item2"))
    print(conn.srem("set-key"))


# redis HashSet功能
def redis_hash():
    print(conn.hset("hash-key", "sub-key1", "value1"))
    print(conn.hset("hash-key", "sub-key2", "value2"))
    print(conn.hset("hash-key", "sub-key3", "value3"))
    print(conn.hgetall("hash-key"))
    print(conn.hdel("hash-key", "sub-key2"))
    print(conn.hdel("hash-key", "sub-key2"))
    print(conn.hget("hash-key", "sub-key1"))


# redis ZSet功能
def redis_zset():
    print(conn.zadd("zset-key", "member1", 1.0))
    print(conn.zadd("zset-key", "member0", 2))
    print(conn.zadd("zset-key", "member0", 2))
    print(conn.zrange("zset-key", 0, -1, withscores=True))
    print(conn.zrangebyscore("zset-key", 0, 800, withscores=True))
    print(conn.zrem("zset-key", "member1"))
    print(conn.zrem("zset-key", "member1"))
    print(conn.zrange("zrange", "zset-key", 0, -1, score_cast_func=0))


if __name__ == "__main__":
    redis_zset()
