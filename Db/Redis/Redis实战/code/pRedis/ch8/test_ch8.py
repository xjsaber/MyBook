from unittest import TestCase

import redis
import time

from ch8 import create_user, create_status, get_status_messages


class TestCh8(TestCase):
    def test_create_user(self):
        conn = redis.Redis(host="localhost", port='6379', db=7)
        create_user(conn, '139960061', 'xj')
        self.assertTrue('123')

    def test_create_status(self):
        conn = redis.Redis(host="localhost", port='6379', db=7)
        uid = 5
        create_status(conn, uid, "Hello, sb")
        self.assertTrue("123")

    def test_get_status_messages(self):
        conn = redis.Redis(host="localhost", port='6379', db=7)
        uid = '139960061'
        print(get_status_messages(conn, uid))
        self.assertTrue("123")

    # 主页面内容
    def test_set_timeline(self):
        conn = redis.Redis(host="localhost", port='6379', db=7)
        timeline = 'home'
        id = 139960061
        conn.zadd('%s:%s' % (timeline, id), '227138379358277633', time.time())
        conn.zadd('%s:%s' % (timeline, id), '227140001668935680', time.time())
        conn.zadd('%s:%s' % (timeline, id), '227143088878014464', time.time())

if __name__ == '__main__':
    t = TestCh8()
    # t.test_create_user()
    # t.test_create_status()
    # t.test_set_timeline()
    t.test_get_status_messages()
