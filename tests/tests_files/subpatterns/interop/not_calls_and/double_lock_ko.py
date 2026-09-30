def safe_sync(a, b):
    a.acquire()
    b.acquire()
