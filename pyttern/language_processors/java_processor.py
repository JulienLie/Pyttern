import io

from antlr4 import CommonTokenStream
from loguru import logger

from .base_processor_interface import BaseProcessor
from ..antlr.java.JavaLexer import JavaLexer
from ..antlr.java.JavaParser import JavaParser
from ..pyttern_error_listener import Python3ErrorListener
from ..pytternfsm.java.java_to_pda import Java_to_PDA
from ..pytternfsm.java.tree_pruner import TreePruner


class JavaProcessor(BaseProcessor):
    def generate_tree_from_stream(self, stream):
        logger.debug("Generating tree")
        lexer = JavaLexer(stream)
        stream = CommonTokenStream(lexer)
        java_parser = JavaParser(stream)


        java_parser.removeErrorListeners()
        error_listener = Python3ErrorListener()
        java_parser.addErrorListener(error_listener)

        tree = java_parser.compilationUnit()
        
        pruned_tree = TreePruner().visit(tree)

        return pruned_tree

    def create_pda(self, pattern_tree):
        return Java_to_PDA().visit(pattern_tree)

    def get_language_extensions(self):
        return ["java", "jav", "jat"]
