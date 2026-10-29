class Builder:
    def reset(self):
        pass

    def build_step_a(self):
        pass

    def build_step_b(self):
        pass

    def get_result(self):
        pass


class ConcreteBuilder1(Builder):
    def __init__(self):
        self.result = []

    def reset(self):
        self.result = []

    def build_step_a(self):
        self.result.append("A1")

    def build_step_b(self):
        self.result.append("B1")

    def get_result(self):
        return self.result


class ConcreteBuilder2(Builder):
    def __init__(self):
        self.result = []

    def reset(self):
        self.result = []

    def build_step_a(self):
        self.result.append("A2")

    def build_step_b(self):
        self.result.append("B2")

    def get_result(self):
        return self.result


class Director:
    def __init__(self, builder):
        self.builder = builder

    def change_builder(self, builder):
        self.builder = builder

    def make(self):
        self.builder.build_step_a()
        self.builder.build_step_b()
        return self.builder.get_result()
