def accumulate(lst, pred):
    cnt = 0
    i = 0
    while i < len(lst):
        el = lst[i]
        if pred(el):
            cnt += 1
    return cnt