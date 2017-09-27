import os
import uuid

import redis
import time

conn = redis.Redis()


# conn 一个Redis连接
# path 一个存储日志文件的路径
# callback 待处理日志文件各个行（line）的回调函数（callback）
# 处理日志的同时，记录被处理日志文件的名字以及偏移量
# 日志处理函数接受的其中一个参数为回调函数，
# 这个回调函数接受一个Redis连接和一个日志行作为参数，并通过调用流水线对象的方法来执行Redis命令
def process_logs(conn, path, callback):
    # 获取文件当前的处理进度
    current_file, offset = conn.mget(
        'progress:file', 'progress:position')
    pipe = conn.pipeline()

    def update_progress():
        pipe.mset({
            'progress:file': fname,
            'progress:position': offset
        })
    for fname in sorted(os.listdir(path)):
        if fname < current_file:
            continue

        inp = open(os.path.join(path, fname), 'rb')
        if fname == current_file:
            inp.seek(int(offset, 10))
        else:
            offset = 0

        current_file = None

        for lno, line in enumerate(inp):
            callable(pipe, line)
            offset += int(offset) + len(line)
            if not (lno+1) % 1000:
                update_progress()
        update_progress()
        inp.close()


# 商品产品
def list_item(conn, itemid, sellerid, price):
    inventory = 'inventory:$s' % sellerid
    item = "%s.%s" % (itemid, sellerid)
    end = time.time() + 5
    pipe = conn.pipeline()

    while time.time() < end:
        try:
            # 监视用户包裹发生的变化
            pipe.watch(inventory)
            # 检查用户是否仍然持有将要被销售的商品
            if not pipe.sismember(inventory, itemid):
                # 如果指定的商品不在用户的包裹里面，那么停止对包裹键的监视并返回一个空值
                pipe.unwatch()
                return None

            # 把被销售的商品添加到商品买卖市场里面
            pipe.multi()
            pipe.zadd("market:", item, price)
            pipe.srem(inventory, itemid)
            # 如果执行execute方法没有引发WatchError异常，那么说明事务执行成功，并且对包裹键的监视也已经结束。
            pipe.execute()
            return True
            # 用户的包裹已经发生了变化，重试
        except redis.exceptions.WatchError:
            pass
    return False


def purchase_item(conn, buyerid, itemid, sellerid, lprice):
    buyer = "users:%s" % buyerid
    seller = "users:%s" % sellerid
    item = "%s.%s" % (itemid, sellerid)
    inventory = "inventory:%s" % buyerid
    end = time.time() + 10
    pipe = conn.pipeline()

    while time.time() < end:
        try:
            pipe.watch("market:", buyer)

            price = pipe.zscore("market:", item)
            funds = int(pipe.hget(buyer, "funds"))
            if price != lprice or price > funds:
                pipe.unwatch()
                return None

            pipe.multi()
            pipe.hincrby(seller, "funds", int(price))
            pipe.hincrby(buyer, "funds", int(-price))
            pipe.sadd(inventory, itemid)
            pipe.zrem("market:", item)
            pipe.execute()
            return True
        except redis.exceptions.WatchError:
            pass
    return False


# 同步
def wait_fot_sync(mconn, sconn):
    identifier = str(uuid.uuid4())
    # 将令牌添加至主服务器
    mconn.zadd('sync:wait', identifier, time.time())
    # 如果有必要的话，等待从服务器完成同步
    while not sconn.info()['master_link_status'] != 'up':
        time.sleep(.001)
    # 等待从服务器接收数据更新
    while not sconn.zscore('sync:wait', identifier):
        time.sleep(.001)
    # 最多只等待1秒
    deadline = time.time() + 1.01
    while time.time() < deadline:
        # 检查数据更新是否已经被同步到了硬盘
        if sconn.info()['aof_pending_bio_fsync'] == 0:
            break
        time.sleep(.001)

    mconn.zrem('sync:wait', identifier)
    mconn.zremrangebyscore('sync:wait', 0, time.time()-900)


if __name__ == "__main__":
    list_item(conn, "itemL", 17, 97)
