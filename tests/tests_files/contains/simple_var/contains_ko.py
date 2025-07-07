def pi(n):
    x = 0
    for i in range(0, n+1):
        x += ((-1)**n)/(2*n+1)
    return 4*x
