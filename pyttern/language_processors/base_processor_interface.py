from functools import lru_cache
from antlr4 import FileStream, InputStream
from ..Pyttern_listener import ConsolePytternListener

class BaseProcessor:
    def __new__(cls):
        if not hasattr(cls, 'instance'):
            cls.instance = super(BaseProcessor, cls).__new__(cls)
        return cls.instance

    def generate_tree_from_code(self, code):
        code = code.strip()
        code += "\n"
        stream = InputStream(code)
        return self.generate_tree_from_stream(stream)

    def generate_tree_from_stream(self, stream):
        raise NotImplementedError

    def generate_tree_from_file(self, file):
        file_input = FileStream(file, encoding="utf-8")
        return self.generate_tree_from_stream(file_input)

    def create_pda(self, pattern_tree):
        raise NotImplementedError

    def create_matcher(self, fsm, code_tree):
        raise NotImplementedError
    
    def create_listener(self):
        return ConsolePytternListener()

    def get_language_extensions(self):
        raise NotImplementedError
