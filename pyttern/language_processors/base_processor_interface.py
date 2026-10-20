from antlr4 import FileStream, InputStream
from ..Pyttern_listener import ConsolePytternListener

class BaseProcessor:
    _instances: dict[type, "BaseProcessor"] = {}

    def __new__(cls):
        if cls not in BaseProcessor._instances:
            BaseProcessor._instances[cls] = super(BaseProcessor, cls).__new__(cls)
        return BaseProcessor._instances[cls]

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

    def create_pda(self, pattern_tree, catalog=None):
        raise NotImplementedError

    def compile_subpattern(self, subpattern, context, catalog=None):
        """
        Compiles a subpattern definition into a Pushdown Automaton given a call context.

        :param subpattern: The subpattern definition to compile.
        :param context: The call context (SubPatternCallContext or AST node).
        :param catalog: Optional subpattern catalog to resolve nested subpatterns.
        :return: A dictionary mapping transformation names to compiled PDA instances.
        """
        raise NotImplementedError

    def create_matcher(self, fsm, code_tree):
        raise NotImplementedError
    
    def create_listener(self):
        return ConsolePytternListener()

    def get_language_extensions(self):
        raise NotImplementedError
