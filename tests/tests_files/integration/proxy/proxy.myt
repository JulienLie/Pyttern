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

$|Operation()

$# call
self.?service.?operation(?*)

$# cache
?:*
    if ?input not in self.?cache:
        

$# if
if ?:
    self.?service.?operation(?*)

$# try
try ?:
    self.?service.?operation(?*)