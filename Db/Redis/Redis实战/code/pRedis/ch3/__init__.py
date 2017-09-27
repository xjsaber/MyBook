import redis
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

if __name__ == "__main__":
    redis_str()
