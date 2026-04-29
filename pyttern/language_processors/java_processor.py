import io

from antlr4 import FileStream, CommonTokenStream, InputStream
from loguru import logger

from .base_processor_interface import BaseProcessor
from ..Pyttern_listener import ConsolePytternListener
from ..antlr.java.JavaLexer import JavaLexer
from ..antlr.java.JavaParser import JavaParser
from ..pyttern_error_listener import Python3ErrorListener


class JavaProcessor(BaseProcessor):
    def generate_tree_from_code(self, code):
        code = code.strip()
        stream = InputStream(code)
        return self.generate_tree_from_stream(stream)

    def generate_tree_from_stream(self, stream):
        logger.info("Generating tree")
        lexer = JavaLexer(stream)
        stream = CommonTokenStream(lexer)
        java_parser = JavaParser(stream)

        error = io.StringIO()

        java_parser.removeErrorListeners()
        error_listener = Python3ErrorListener(error)
        java_parser.addErrorListener(error_listener)

        tree = java_parser.compilationUnit()
        # if len(error_listener.symbol) > 0:
        #     raise IOError(
        #         f"Syntax error in {stream} at line {error_listener.line} "
        #         f"({repr(error_listener.symbol)}) : {error.getvalue()}")

        # pruned_tree = TreePruner().visit(tree)

        # return pruned_tree
        return tree

    def generate_tree_from_file(self, file):
        file_input = FileStream(file, encoding="utf-8")
        return self.generate_tree_from_stream(file_input)

    def create_pda(self, pattern_tree):
        #return Java_Visitor().visit(pattern_tree)
        raise NotImplementedError("Java PDA creation not implemented yet.")
    
    def create_listener(self):
        return ConsolePytternListener()

    def get_language_extensions(self):
        return ["java", "jav", "jat"]
