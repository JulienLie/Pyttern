class BaseProcessor:
    def __new__(cls):
        if not hasattr(cls, 'instance'):
            cls.instance = super(BaseProcessor, cls).__new__(cls)
        return cls.instance
    
    def generate_tree_from_code(self, code):
        raise NotImplementedError

    def generate_tree_from_stream(self, stream):
        raise NotImplementedError

    def generate_tree_from_file(self, file):
        raise NotImplementedError

    def create_fsm(self, pattern_tree):
        raise NotImplementedError

    def create_simulator(self, fsm, code_tree):
        raise NotImplementedError

    def create_listener(self):
        raise NotImplementedError
