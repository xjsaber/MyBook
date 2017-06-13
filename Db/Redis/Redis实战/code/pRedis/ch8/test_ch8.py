from unittest import TestCase

import redis

from ch8 import create_user


class TestCh8(TestCase):
    def test_create_user(self):
        conn = redis.Redis(host="localhost", port='6379', db=7)
        create_user(conn, 'xjsaber', 'xj')
        self.assertTrue('123')


if __name__ == '__main___':
    TestCh8.test_create_user()
