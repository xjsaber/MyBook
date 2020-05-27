from unittest import TestCase

from ch7 import tokenize
from ch7 import index_document

import redis


class TestTokenize(TestCase):
    conn = redis.Redis(host='localhost', port=6379, db=6)

    def test_tokenize(self):
        content = "12312414124142124"
        for result in tokenize(content):
            print(result)
        self.assertTrue("123")

    def test_index_document(self):
        conn = redis.Redis(host='localhost', port=6379, db=6)
        docid = '1'
        content = 'you are pig'
        index_document(conn, docid, content)
        self.assertTrue("123")


if __name__ == '__main__':
    # TestTokenize.test_tokenize()
    TestTokenize.test_index_document()
