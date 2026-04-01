?:*
    class ?Mediator(?*):
        ?:*
            def ?notify(self, ?sender, ?event, ?*):
                ?*

?:*
    class ?BaseComponent(?*):
        ?:*
            def __init__(self, ?*):
                ?*
                self.?m = ?
                ?*
        ?:*
            def ?(?*):
                ?*
                self.?m = ?
                ?*

?:*
    class ?ConcreteMediator(?Mediator):
        ?:*
            def __init__(self, ?*):
                ?*
                self.?c1 = ?
                ?*
                self.?c1.?any_name = self
                ?*
        ?:*
            def ?notify(self, ?sender, ?event, ?*):
                ?:*
                    if ?<event == ?>:
                        ?:*
                            ?<?.?()(?*)>

?:*
    class ?(?BaseComponent):
        ?:*
            def ?(self, ?*):
                ?:*
                    self.?m.?notify(self, ?*, ?*)
