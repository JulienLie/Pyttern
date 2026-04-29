class Clazz:

    def __new__(clazz):
        return clazz()

    def __init__(self):
        self.foo = 0
        self.bar = 0

    def set_foo(self, foo):
        self.foo = foo
    
    def get_foo(self):
        return self.foo

    def get_bar(self):
        return self.bar
    
    def set_bar(self, bar):
        self.bar = bar

    def __str__(self):
        return ""