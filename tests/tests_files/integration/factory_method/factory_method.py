class Dialog():
    def createButton(self):
        pass

    def render(self):
        okButton = self.createButton()
        okButton.onClick(lambda x: print(x))
        okButton.render()

class WindowsDialog(Dialog):
    def createButton(self):
        return WindowsButton()
    
class WebDialog(Dialog):
    def createButton(self):
        return WebButton()
    
class Button:
    def render(self):
        pass

    def onClick(self, f):
        pass

class WindowsButton(Button):
    def render(self):
        print("new windows button rendered")

    def onClick(self, f):
        f()

class WebButton(Button):
    def render(self):
        print("new web button rendered")

    def onClick(self, f):
        f()