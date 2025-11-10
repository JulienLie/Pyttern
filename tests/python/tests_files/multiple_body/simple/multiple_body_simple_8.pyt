def multiplications(n):
    count = 0
    for i in range (1,n+1):
        for j in range (1,n+1):
            ?:*
                if n==i*j :
                    count+=1
    return (count)

def test_multiplications():
    assert multiplications(1) == 1
    assert multiplications(2) == 1
    assert multiplications(3) == 1
    assert multiplications(4) == 2
    assert multiplications(5) == 1
    assert multiplications(6) == 4
    assert multiplications(7) == 1
    assert multiplications(8) == 2
    assert multiplications(9) == 3
    assert multiplications(10) == 4


