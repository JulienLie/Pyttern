$|ServiceClass(?Service): ?body

$# SimpleService
class ?Service:
    ?body

$# SubService
class ?Service(?):
    ?body


$&AuditedMethod(?method, ?service): ?body

$# DefBody
def ?method(self, ?*):
    ?body

$# HasAudit
def ?method(self, ?*):
    ?body
    self.audit(?service)


$|SafeTransaction(?tx): ?body

$# PlainWith
with ?tx:
    ?body

$# AsWith
with ? as ?tx:
    ?body


$!NoDirectCommit(?tx)

$# DirectCommit
?tx.commit()
