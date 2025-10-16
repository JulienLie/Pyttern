def factors(n):
    l = [2,3,5,7,11,13,17,19]
    i = 0
    t = []
    for i in range(len(l)):
        if n%l[i] == 0:
            t.append(l[i])
            i -= 1
    nbr = len(t)
    return (nbr)
