def foo(n):
    if n < 0:
        return 0
    return foo(n - 1) + n