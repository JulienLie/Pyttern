"""
Java language processor for Pyttern.
"""

import io

from antlr4 import CommonTokenStream, InputStream
from loguru import logger

from .base_processor_interface import BaseProcessor
from ..antlr.java.JavaLexer import JavaLexer
from ..antlr.java.JavaParser import JavaParser
from ..pyttern_error_listener import Python3ErrorListener
from ..pytternfsm.java.java_to_pda import Java_to_PDA
from ..pytternfsm.java.tree_pruner import TreePruner


class JavaProcessor(BaseProcessor):
    """
    Language processor implementation for Java and Jattern (Java patterns).
    """

    def generate_tree_from_stream(self, stream: InputStream):
        """
        Parses Java source from an ANTLR stream and returns a pruned parse tree.

        Args:
            stream (InputStream): The ANTLR stream containing Java source code.

        Returns:
            ParserRuleContext: The pruned Java compilation unit parse tree.
        """
        logger.debug("Generating Java parse tree")
        lexer = JavaLexer(stream)
        token_stream = CommonTokenStream(lexer)
        java_parser = JavaParser(token_stream)

        error = io.StringIO()
        java_parser.removeErrorListeners()
        error_listener = Python3ErrorListener(error)
        java_parser.addErrorListener(error_listener)

        tree = java_parser.compilationUnit()
        pruned_tree = TreePruner().visit(tree)
        return pruned_tree

    def create_pda(self, pattern_tree):
        """
        Compiles a Java pattern parse tree into a Pushdown Automaton.

        Args:
            pattern_tree (ParserRuleContext): The Java pattern parse tree.

        Returns:
            dict[str, PDA]: A dictionary containing compiled PDA instances.
        """
        return Java_to_PDA().visit(pattern_tree)

    def get_language_extensions(self) -> list[str]:
        """
        Returns supported file extensions for Java and Jattern files.

        Returns:
            list[str]: Supported extensions ['java', 'jav', 'jat'].
        """
        return ["java", "jav", "jat"]
