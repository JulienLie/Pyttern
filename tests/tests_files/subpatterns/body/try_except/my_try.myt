$|MyTryExcept(?err): ?body

$# TryExcept
try:
    ?body
except ?err:
    pass

$# TryExceptElse
try:
    ?body
except ?err:
    pass
else:
    pass
