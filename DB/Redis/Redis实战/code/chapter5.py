import logging
import redis
import time

conn = redis.Redis()


# 将最新的日志记录到Redis里面
SEVERITY = {
    logging.DEBUG: 'debug',
    logging.INFO: 'info',
    logging.WARNING: 'warning',
    logging.ERROR: 'error',
    logging.CRITICAL: 'cirtical',
}
SEVERITY.update((name, name) for name in SEVERITY.values())


def log_recent(conn, name, message, serverity=logging.INFO, pipe=None):
    serverity = str(SEVERITY.get(serverity, serverity)).lower()
    destination = 'recent:%s:%s'%(name, serverity)
    message = time.asctime() + ' ' + message
    pipe = pipe or conn.pipeline()
    pipe.lpush(destination, message)
    pipe.ltrim(destination, 0, 99)
    pipe.execute()


# 函数在刚开始执行会先休眠，让订阅者有足够的时间来连接服务器并监听消息
def publisher(n):
    time.sleep(1)
    for i in range(n):
        conn.publish('channel', i)
        time.sleep(1)


# 启动发送者线程，并让他发送三条消息
def run_pubsub():
    threading.Thread(target=publisher, args=(3,)).start()
    pubsub = conn.pubsub()
    pubsub.subscribe(['channel'])
    count = 0
    for item in pubsub.listen():
        # 打印接收到的每条消息
        print(item)
        count += 1
        if count == 4:
            # 接收到一条订阅反馈消息和三条发布者发送的消息之后，执行退订操作，停止监听新消息
            pubsub.unsubscribe()
        if count == 5:
            # 客户端在接收到退订反馈消息之后就不再接收消息
            break


# 根据字母表顺序对元素进行排序
conn.sort('sort-input', alpha=True)
# 对散列的域（field）用作权重，对sort-input列表进行排序
conn.sort('sort-input', by='d-*->field')
# 对散列的域（field）用作权重，对sort-input列表进行排序
conn.sort('sort-input', by='d-*->field', get='d-*->field')


# 基本的Redis事务
# 无事务 结果为1,2,3
def notrans():
    print(conn.incr('notrans:'))
    time.sleep(.1)
    conn.incr('notrans:', -1)

if 1:
    for i in range(3):
        threading.Thread(target=notrans).start()
    time.sleep(.5)


# 有事务 结果为1,1,1
def trains():
    pipeline = conn.pipeline()
    pipeline.incr('trans:')
    time.sleep(.1)

    pipeline.incr('trans:', -1)
    print(pipeline.execute()[0])


if 1:
    for i in range(3):
        threading.Thread(target=trains).start()
    time.sleep(.5)