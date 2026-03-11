$&Decorator(?Component, ?execute, ?BaseDecorator, ?ConcreteDecorator)

$# Component
class ?Component:
    def ?execute(self, ?*):
        ?

$# ConcreteComponent
class ?ConcreteComponent(?Component):
    def ?execute(self, ?*):
        ?

$# BaseDecorator
class ?BaseDecorator(?Component):
    def __init__(self, ?c):
        self.?wrappee = ?c

    def ?execute(self, ?*):
        ?<self.?wrappee.execute(?*)>

$# ConcreteDecorator
class ?ConcreteDecorator(?BaseDecorator):
    def ?execute(self, ?*):
        ?<super().?execute()>