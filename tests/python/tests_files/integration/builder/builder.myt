$&Builder(?Builder, ?buildStepA, ?buildStepB, ?getResult)

$# Director
class ?Director:
    def __init__(self, ?*, ?b, ?*):
        self.?builder = ?b

    def ?change_builder(?b):
        self.?builder = ?b

    def ?make(?*):
        ?<?getResult()>

$# Builder
class ?Builder:
    def ?reset(self):
        ?
    
    ?$BuildSteps(?buildStepA, ?buildStepB, ?getResult)

$# ConcreteBuilder1
class ?ConcreteBuilder1(?Builder):
    def __init__(self, ?*):
        self.?result = ?

    ?$BuildSteps(?buildStepA, ?buildStepB, ?getResult)

$# ConcreteBuilder2
class ?ConcreteBuilder2(?Builder):
    def __init__(self, ?*):
        self.?result = ?

    ?$BuildSteps(?buildStepA, ?buildStepB, ?getResult)



$&BuildSteps(?buildStepA, ?buildStepB, ?getResult)

$# buildStepA
def ?buildStepA(self):
    ?

$# buildStepB
def ?buildStepB(self):
    ?

$# getResult
def ?getResult(self):
    ?