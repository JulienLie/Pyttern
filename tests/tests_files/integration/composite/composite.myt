$&Composite(?Component, ?execute)

$# Component
class ?Component:
    def ?execute(self, ?*):
        ?

$# Leaf
class ?Leaf(?Component):
    def ?execute(self, ?*):
        ?

$# Composite
class ?Composite(?Component):
    ?$CompositeBody(?children, ?execute)



$&CompositeBody(?children, ?execute)

$# init
def __init__(self, ?*):
    self.?children = []

$# add
def ?add(self, ?component):
    self.?children.append(?component)

$# remove
def ?remove(self, ?component):
    self.?children.remove(?component)

$# getChildren
def ?getChildren(self):
    return self.?children

$# execute
def ?execute(self, ?*):
    for ?c in self.?children:
        ?<?c.?execute(?*)>