def ?func(n):
    if n < 0:
        return 0
    return ?func(n - 1) + n