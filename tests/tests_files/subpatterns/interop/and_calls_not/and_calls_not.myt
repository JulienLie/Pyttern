$&CleanFunction(?func)

$# FuncDef
def ?func(?*):
    ?*

$# NoGlobalCheck
def ?func(?*):
    ?$NoGlobalStmt()

$!NoGlobalStmt()

$# GlobalDeclaration
global ?
