import redis
import time
import config

config = config.Config
conn = redis.Redis(host=config.host, password=config.password)
ONE_WEEK_IN_SECONDS = 7 * 86400
VOTE_SCORE = 432


# 文章投票功能
def article_vote(conn, user, article):
    cutoff = time.time() - ONE_WEEK_IN_SECONDS
    if conn.zscore('time:', article) < cutoff:
        return

    # 从article:id标识符（identifier）里面取出id
    article_id = article.partition(':')[-1]
    if conn.sadd('vote:' + article_id, user):
        conn.zincrby('score:', article, VOTE_SCORE)


def post_article(conn, user, title, link):
    article_id = str(conn.incr('article:'))
    # 1.自增长
    voted = 'voted:' + article_id
    # 2.添加文章发布者的ID到记录文章
    conn.sadd(voted, user)
    # 3.设置过期时间
    conn.expire(voted, ONE_WEEK_IN_SECONDS)

    now = time.time()
    article = 'article:' + article_id
    # 4.使用HMSET存储文章相关信息
    conn.hmset(article, {
        'title': title,
        'link': link,
        'poster': user,
        'time': now,
        'votes': 1
    })
    # 5.添加文章初始评分
    conn.zadd('score:', article, now + VOTE_SCORE)
    # 6.添加发布时间
    conn.zadd('time:', article, now)
    # 7.返回key：文章+自增长数字
    return article_id


ARTICLES_PER_PAGE = 25


# 读取所有的文章
def get_articles(conn, page, order='score:'):
    # 页码，开始结束
    start = (page-1) * ARTICLES_PER_PAGE
    end = start + ARTICLES_PER_PAGE - 1

    # zrevrange 返回有序集key中，指定区间内的成员。其中成员的位置按score值递减(从大到小)来排列。
    ids = conn.zrevrange(order, start, end)
    articles = []
    for id in ids:
        article_data = conn.hgetall(id)
        article_data['id'] = id
        articles.append(article_data)

    return articles


def add_remove_groups(conn, article_id, to_add=[], to_remove=[]):
    article = 'article:' + article_id
    for group in to_add:
        conn.sadd('group:' + group, article)
    for group in to_remove:
        conn.srem('group:' + group, article)


def get_group_articles(conn, group, page, order='score:'):
    key = order + group
    if not conn.exists(key):
        conn.zinterstore(key,
                         ['group:' + group, order],
                         agregate='max',
                         )
        conn.expire(key, 60)
    return get_articles(conn, page, key)



