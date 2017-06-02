import redis


conn = redis.Redis()
pipe = conn.pipeline()


def list_item(conn, itemid, sellerid, price):
    pipe.watch()
    if not pipe.sismember(inv, itemid):
        pipe.unwatch()
        return None

    pipe.multi()
    pipe.zadd("market:", item, price)
    pipe.srem(inv, itemid)
    pipe.execute()
    return True
