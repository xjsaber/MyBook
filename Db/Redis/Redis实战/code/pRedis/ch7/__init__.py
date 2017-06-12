import re
import uuid

STOP_WORDS = set('''able about across after all almost also am among an 
and any are as at be because been but by can cannot could dear did do dose 
either else ever every for from get got had has have he her hers him his how however if in into
is it its just least let like likely may me might most must my neither no nor not of off 
often on only or other our own rather said say says she should since so some than that the 
their them then there these they this tis to too twas us wants was we were what when where
which while who whom why whom why will with would yet you your'''.split())

WORDS_RE = re.compile("[a-z']{2,}")


def tokenize(content):
    words = set()
    for match in WORDS_RE.finditer(content.lower()):
        word = match.group().strip("'")
        if len(word) >= 2:
            words.add(word)
    return words - STOP_WORDS


def index_document(conn, docid, content):
    words = tokenize(content)

    pipeline = conn.pipeline(True)
    for word in words:
        pipeline.sadd('idx:' + word, docid)
    return len(pipeline.execute())


def _set_common(conn, method, names, ttl=30, execute=True):
    id = str(uuid.uuid4())
    pipeline = conn.pipeline(True) if execute else conn
    names = ['idx:' + name for name in names]
    getattr(pipeline, method)('idx:' + id, *names)
    pipeline.expire('idx:' + id, ttl)
    if execute:
        pipeline.execute()
    return id

