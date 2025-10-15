def foo():
    a = 0
    b = 1
    c = 2
    d = 3
    n = 4
    for i in range(n):
        a = a + i
        b = b + 1
        if i % 2 == 0:
            c = c + i
        else:
            d = d + i
            ?*
    return a, b, c, d