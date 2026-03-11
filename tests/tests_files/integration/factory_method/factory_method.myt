$&FactoryMethod(?Creator, ?createProduct, ?Product, ?ProductA, ?ProductB, ?productMethod)

$# Creator
class ?Creator:
    def ?(self, ?*):
        ?<self.?createProduct(?*)>

    def ?createProduct(self, ?*):
        ?

$# ConcreteCreatorA
class ?ConcreteCreatorA(?Creator):
    def ?createProduct(self, ?*):
        return ProductA(?*)

$# ConcreteCreatorB
class ?ConcreteCreatorB(?Creator):
    def ?createProduct(self, ?*):
        return ProductB(?*)

$# Product
class ?Product:
    def ?productMethod(self, ?*):
        ?

$# ProductA
class ?ProductA(?Product):
    def ?productMethod(self, ?*):
        ?

$# ProductB
class ?ProductB(?Product):
    def ?productMethod(self, ?*):
        ?
