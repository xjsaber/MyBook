import json
import redis
import time


conn = redis.Redis()
print(conn.set("hello", "world"))
print(conn.get("hello"))


# 尝试获取并返回令牌对应的用户
def check_token(conn, token):
    return conn.hget('login', token)


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


QUIT = False
LIMIT = 1000000


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


def schedule_row_cache(conn, row_id, delay):
    conn.zadd('delay:', row_id, delay)
    conn.zadd('schedule:', row_id, time.time())


# 通过组合使用调度函数和持续运行缓存函数，实现一种重复调度的自动缓存机制，
# 并且可以随心所欲地控制数据行缓存的更新频率
# def cache_rows(conn):
#     while not QUIT:
#         next = conn.zrange('schedule:', 0, 0, withscores=True)
#         now = time.time()
#         if not next or next[0][1] > now:
#             time.sleep(.05)
#             continue
#         row_id = next[0][0]
#
#         delay = conn.zscore('delay', row_id)
#         if delay <= 0:
#             conn.zrem('delay:', row_id)
#             conn.zrem('schedule:', row_id)
#             conn.delete('inv:' + row_id)
#             continue
#
#         row = Inventory.get(row_id)
#         conn.zadd('schedule:', row_id, now + delay)
#         conn.set('inv:' + row_id, json.dumps(row.to_dict()))


print(check_token(conn, ""))
