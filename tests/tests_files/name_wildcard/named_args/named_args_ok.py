def foo(val1, val2, val3 = 0):
    if val3 == 0:
        val3 = 1
    b = -val3 * val1 + val2
    return b