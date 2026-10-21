$&LoggedMethod(?name): ?body

$# DefBody
def ?name(self):
    ?body

$# HasLog
def ?name(self):
    ?body
    self.log()
