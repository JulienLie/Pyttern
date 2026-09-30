$|ErrorHandling(?exc, ?msg)

$# TryExcept
try:
    ?
except ?exc as ?msg:
    ?

$# GuardIf
if ?msg is not None:
    raise ?exc(?msg)

$# AssertCheck
assert ?, ?msg

$# ExplicitRaise
raise ?exc(?msg)
