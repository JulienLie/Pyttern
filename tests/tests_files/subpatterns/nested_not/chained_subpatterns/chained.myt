$&Pipeline(?data)

$# ValidateData
?data = validate(?)

$# ProcessData
?$ProcessChoice(?data)

$|ProcessChoice(?d)

$# SafeProcessing
?$NoUnsafeProcessing(?d)

$!NoUnsafeProcessing(?x)

$# CheckForbidden
?$ForbiddenOp(?x)

$!ForbiddenOp(?v)

$# EvalOp
eval(?v)
