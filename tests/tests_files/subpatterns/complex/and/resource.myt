$&ResourceFlow(?res, ?buf)

$# InitResource
?res = open(?)

$# ReadBuffer
?buf = ?res.read()

$# CloseResource
?res.close()
