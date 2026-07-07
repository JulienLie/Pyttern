def foo(n):
    count = 0
    for i in range(n):
        for j in range(n):
            if n == i * j:
                count += 1
        i += 1
    return count