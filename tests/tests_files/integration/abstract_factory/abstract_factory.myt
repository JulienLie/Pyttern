$&AbstractFactory(?AbstractFactory, ?CreateProductA, ?CreateProductB, ?ProductA, ?ConcreteProductA1, ?ConcreteProductA2, ?ProductB, ?ConcreteProductB1, ?CreateProductB2)

$# AbstractFactory
class ?AbstractFactory:
    def ?CreateProductA(self, ?*):
        ?

    def ?CreateProductB(self, ?*):
        ?

$# ConcreteFactory1
class ?ConcreteFactory1(?AbstractFactory):
    def ?CreateProductA(self, ?*):
        return ?ConcreteProductA1(?*)

    def ?CreateProductB(self, ?*):
        return ?ConcreteProductB1(?*)

$# ConcreteFactory2
class ?ConcreteFactory2(?AbstractFactory):
    def ?CreateProductA(self, ?*):
        return ?ConcreteProductA2(?*)

    def ?CreateProductB(self, ?*):
        return ?ConcreteProductB2(?*)

$# ProductA
class ?ProductA:
    ?

$# ProductB
class ?ProductB:
    ?

$# ConcreteProductA1
class ?ConcreteProductA1(?ProductA):
    ?

$# ConcreteProductA2
class ?ConcreteProductA2(?ProductA):
    ?

$# ConcreteProductB1
class ?ConcreteProductB1(?ProductA):
    ?

$# ConcreteProductB2
class ?ConcreteProductB2(?ProductA):
    ?