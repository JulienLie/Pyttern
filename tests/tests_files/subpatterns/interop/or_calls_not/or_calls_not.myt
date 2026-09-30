$|NoSideEffects(?x)

$# CheckNoPrint
?$NoPrint(?x)

#$# CheckNoWrite
#?$NoWrite(?x)

$!NoPrint(?v)

$# PrintStmt
print(?v, ?*)

$!NoWrite(?v)

$# WriteMethod
?v.write(?*)
