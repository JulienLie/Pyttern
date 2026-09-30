$!NoDeadlock(?l1, ?l2)

$# CheckDoubleAcquire
?$DoubleAcquire(?l1, ?l2)

$&DoubleAcquire(?l1, ?l2)

$# FirstAcquire
?l1.acquire()

$# SecondAcquire
?l2.acquire()
