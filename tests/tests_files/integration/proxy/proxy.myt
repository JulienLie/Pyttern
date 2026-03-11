$&Proxy(?ServiceInterface, ?operation)

$# Interface
class ?ServiceInterface:
    def ?operation(self, ?*):
        ?

$# Service
class ?Service(?ServiceInterface):
    def ?operation(self, ?*):
        ?

$# Proxy
class ?Proxy(?ServiceInterface):
    ?$ProxyBody(?operation, ?service)


$&ProxyBody(?operation, ?service)

$# init
def __init__(self, ?s):
    self.?service = ?s

$# operation
def ?operation(self, ?*):
    ?<self.?service.?operation(?*)>
