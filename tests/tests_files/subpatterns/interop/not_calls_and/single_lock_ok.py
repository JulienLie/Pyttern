def safe_sync(a, b):
    a.acquire()
    a.release()
