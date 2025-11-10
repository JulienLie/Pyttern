def multiplications():
    count = 0
    for i in range (1, a+1):
        for j in range (1, b+1, 2):
            if n==i*j :
                count+=1
    return (count)

def multiplications(n):
    count = 0
    for i in range (n+1):
        for j in range (1, n+1, 2):
            if n==i*j :
                count+=1
    return (count)

def multiplications(n):
    count = 0
    for i in range (1, n+1):
        for j in range (1, n+1):
            if n==i*j :
                count+=1
    return (count)
