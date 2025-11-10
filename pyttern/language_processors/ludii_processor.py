import io

from antlr4 import CommonTokenStream
from antlr4.InputStream import InputStream
from loguru import logger

from .base_processor_interface import BaseProcessor
from ..antlr.ludii.LudiiLexer import LudiiLexer
from ..antlr.ludii.LudiiParser import LudiiParser
from ..pyttern_error_listener import Python3ErrorListener


class LudiiProcessor(BaseProcessor):
    def generate_tree_from_code(self, code):
        code = code.strip()
        code += "\n"
        stream = InputStream(code)
        return self.generate_tree_from_stream(stream)

    def generate_tree_from_stream(self, stream):
        logger.info("Generating Ludii tree")
        lexer = LudiiLexer(stream)
        stream = CommonTokenStream(lexer)
        lud_parser = LudiiParser(stream)

        error = io.StringIO()

        lud_parser.removeErrorListeners()
        error_listener = Python3ErrorListener(error)
        lud_parser.addErrorListener(error_listener)

        tree = lud_parser.file_input()
        """if len(error_listener.symbol) > 0:
            raise IOError(
                f"Syntax error in {stream} at line {error_listener.line} "
                f"({repr(error_listener.symbol)}) : {error.getvalue()}")"""

        return tree

    def generate_tree_from_file(self, file):
        file_input = io.open(file, encoding="utf-8")
        return self.generate_tree_from_stream(file_input)

    def create_pda(self, pattern_tree):
        pass

    def create_matcher(self, fsm, code_tree):
        pass

    def create_listener(self):
        pass

    def get_language_extensions(self):
        return ['ludii', 'lud', 'lut']