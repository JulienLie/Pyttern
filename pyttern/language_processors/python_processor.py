import io
from functools import cache

from antlr4 import FileStream, CommonTokenStream, InputStream
from loguru import logger

from .base_processor_interface import BaseProcessor
from ..PytternListener import ConsolePytternListener
from ..antlr.python import Python3Parser
from ..antlr.python.Python3Lexer import Python3Lexer
from ..pyttern_error_listener import Python3ErrorListener
from ..pytternfsm.python.python_to_pda import Python_to_PDA
from ..pytternfsm.python.tree_pruner import TreePruner

class PythonProcessor(BaseProcessor):

    @cache
    def generate_tree_from_code(self, code):
        code = code.strip()
        code += '\n'
        stream = InputStream(code)
        return self.generate_tree_from_stream(stream)

    @cache
    def generate_tree_from_file(self, file):
        file_input = FileStream(file, encoding="utf-8")
        return self.generate_tree_from_stream(file_input)

    @cache
    def generate_tree_from_stream(self, stream):
        logger.info("Generating tree")
        lexer = Python3Lexer(stream)
        stream = CommonTokenStream(lexer)
        py_parser = Python3Parser(stream)

        error = io.StringIO()

        py_parser.removeErrorListeners()
        error_listener = Python3ErrorListener(error)
        py_parser.addErrorListener(error_listener)

        tree = py_parser.file_input()
        """if len(error_listener.symbol) > 0:
            raise IOError(
                f"Syntax error in {stream} at line {error_listener.line} "
                f"({repr(error_listener.symbol)}) : {error.getvalue()}")"""

        pruned_tree = TreePruner().visit(tree)

        return pruned_tree

    @cache
    def create_fsm(self, pattern_tree):
        return Python_to_PDA().visit(pattern_tree)

    def create_listener(self):
        return ConsolePytternListener()

    def get_language_extensions(self):
        return ["py", "pyt", "pyh"]