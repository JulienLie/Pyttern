def multiplications(n):
    """
    pre:  n est un nombre entier positif
    post: Retourne le nombre de décompositions a,b distinctes
          telles que n == a*b == b*a
    """
    # A COMPLETER #
    print("ok")
    b=n
    a=1
    c=0

    if a or b or a and c: print("ok")
    elif not a and (not c or b):
        print("maybe")
    elif a > c != b:
        print("MMmmh")
    else:
        print("no")

    while a*b==n:
        c=a*(b/a)
        a+=1
    return c

