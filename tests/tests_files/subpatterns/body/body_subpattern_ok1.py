def accumulate(lst, pred):
    cnt = 0
    for el in lst:
        if pred(el):
            cnt += 1
    return cnt