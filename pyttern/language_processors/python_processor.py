import io
from functools import cache

from antlr4 import FileStream, CommonTokenStream, InputStream
from loguru import logger

from ..simulator.pda import PDA
from .base_processor_interface import BaseProcessor
from ..Pyttern_listener import ConsolePytternListener
from ..antlr.python import Python3Parser
from ..antlr.python.Python3Lexer import Python3Lexer
from ..pyttern_error_listener import Python3ErrorListener
from ..pytternfsm.python.python_to_pda import Python_to_PDA
from ..pytternfsm.python.tree_pruner import TreePruner


class PythonProcessor(BaseProcessor):

    @cache
    def generate_tree_from_code(self, code):
        code = code.strip()
        code += "\n"
        stream = InputStream(code)
        return self.generate_tree_from_stream(stream)

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

        pruned_tree = TreePruner().visit(tree)

        return pruned_tree

    @cache
    def generate_tree_from_file(self, file):
        file_input = FileStream(file, encoding="utf-8")
        return self.generate_tree_from_stream(file_input)

    @cache
    def create_pda(self, pattern_tree) -> dict[str, PDA]:
        return Python_to_PDA().visit(pattern_tree)

    def create_listener(self):
        return ConsolePytternListener()

    def get_language_extensions(self):
        return ["py", "pyt", "pyh"]
