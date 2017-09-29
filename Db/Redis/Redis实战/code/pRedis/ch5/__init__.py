import csv
import json
import logging
import threading
import redis
import time

conn = redis.Redis()


# 代码清单5-1 将最新的日志记录到Redis里面
SEVERITY = {
    # 设置一个字典，将大部分日志的安全级别映射为字符串
    logging.DEBUG: 'debug',
    logging.INFO: 'info',
    logging.WARNING: 'warning',
    logging.ERROR: 'error',
    logging.CRITICAL: 'cirtical',
}
SEVERITY.update((name, name) for name in SEVERITY.values())


# 记录最近的日志
def log_recent(conn, name, message, serverity=logging.INFO, pipe=None):
    # 尝试将日志的安全级别转换为简单的字符串
    serverity = str(SEVERITY.get(serverity, serverity)).lower()
    # 创建负责存储消息的键
    destination = 'recent:%s:%s'%(name, serverity)
    # 将当前时间添加到消息里面，用于记录消息的发送时间
    message = time.asctime() + ' ' + message
    # 使用流水线来将通信往返次数降低为一次
    pipe = pipe or conn.pipeline()
    pipe.lpush(destination, message)
    # 对日志列表进行修剪，让它只包含最新的100条消息
    pipe.ltrim(destination, 0, 99)
    pipe.execute()


# 代码清单5-2 log_common()函数
def log_common(conn, name, message, serverity=logging.INFO, timeout=5):
    print(123)


# 代码清单5-3 update_counter()函数
# 以秒为单位的计数器精度，分别为1秒、5秒、1分钟、5分钟
PRECISION = [1, 5, 60, 300, 3600, 18000, 86400]


# 代码清单5-3 update_counter()函数
def update_counter(conn, name, count=1, now=None):
    now = now or time.time()  # 通过取得当前时间来判断应该对哪个时间片执行自增操作
    pipe = conn.pipeline()  # 为了保证之后的清理工作可以正确地执行，这里需要创建一个事务型流水线
    for prec in PRECISION:  # 记录的每种精度都创建一个计数器
        pnow = int(now / prec) * prec  # 取得当前时间片的开始时间
        hash = '%s:%s' % (prec, name)  # 创建负责存储计数信息的散列
        pipe.zadd('known:', hash, 0)  # 将计数器的引用信息添加到有序集合里面，并将其分值设置为0，以便在之后执行清理操作
        pipe.hincrby('count:' + hash, pnow, count)  # 对给定名字和精度的计数器进行更新
    pipe.execute()


# 代码清单 5-4 get_counter()函数
def get_counter(name, precision):
    hash = '%s:%s' % (precision, name)
    data = conn.hgetall('count:' + hash)
    to_return = []


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


# 代码清单5-9 ip_to_score()函数
def ip_to_score(ip_address):
    score = 0
    for v in ip_address.split('.'):
        score = score * 256 + int(v, 10)
    return score


# 代码清单5-10 import_ips_to_redis函数
def import_ips_to_redis(conn, filename):  # GeoLiteCity-Blocks.csv文件
    csv_file = csv.reader(open(filename, 'rb'))
    for count, row in enumerate(csv_file):
        start_ip = row[0] if row else ''
        if 'i' in start_ip:
            start_ip = ip_to_score(start_ip)
        elif start_ip.isdigit():
            start_ip = int(start_ip, 10)
        else:
            continue

        city_id = row[2] + '_' + str(count)  # 构建唯一城市ID
        conn.zadd('ip2cityid:', city_id, start_ip)  # 将城市ID及其对应的IP地址分值添加到有序集合里面


# 代码清单5-11 import_cities_to_redis()函数
def import_cities_to_redis(conn, filename):
    for row in csv.reader(open(filename, 'rb')):
        if len(row) < 4 or not row[0].isdigit():
            continue
        row = [i.decode('latin-1') for i in row]
        city_id = row[0]
        country = row[1]
        region = row[2]
        city = row[3]
        conn.hset('citid2city:', city_id, json.dumps([country, region, city]))
