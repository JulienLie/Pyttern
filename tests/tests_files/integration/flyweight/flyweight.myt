$&Flyweight()

$# Factory
class ?Factory:
    def __init__(self, ?*):
        self.?cache = []

    def ?getFlyweight(self, ?repeatingState):
        if self.?cache[?repeatingState] is None:
            self.?cache[?repeatingState] = ?Flyweight(?repeatingState)
        return self.?cache[?repeatingState]

$# Flyweight
class ?Flyweight:
    def ?operation(self, ?uniqueState):
        ?

$# Context
class ?Context:
    def __init__(?rState, ?uState):
        self.?uniqueState = ?uState
        self.?flyweight = 