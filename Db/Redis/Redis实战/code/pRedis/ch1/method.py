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


if __name__ == "__main__":
    redis_smembers()
