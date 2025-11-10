def foo(?x, ?y, ?z = 0):
    if ?z == 0:
        ?z = 1
    b = -?z * ?x + ?y
    return b