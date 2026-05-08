$&Adapter(?Target, ?Adaptee, ?Adapter, ?method, ?method_to_adapt)

$# Target
class ?Target:
    def ?method(self, ?*):
        ?

$# Adaptee
class ?Adaptee:
    def ?method_to_adapt(self, ?*):
        ?

$# Adapter
class ?Adapter(?Target, ?Adaptee):
    def ?method(self, ?*):
        ?<self.?method_to_adapt(?*)>
