$&Composite(?Component, ?execute)

$# Component
class ?Component:
    def ?execute(?*):
        ?

$# Leaf
class ?Leaf(?Component):
    def ?execute(?*):
        ?

$# Composite
class ?Composite(?Component):
    ?$CompositeClass(?children)



$&CompositeClass(?children)

$# init
def __init__(self, ?*):
    self.?children = []

$# add
def ?add(?self, ?child):
    self.?children.append(?child)

$# remove
def ?remove(?self, ?child):
    self.?children.remove(?child)

$# execute
def ?execute(?*):
    for ?child in self.?children:
        ?child.execute(?*)