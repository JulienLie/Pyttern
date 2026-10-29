"""Java language processor for Pyttern."""
from __future__ import annotations

import io
from typing import TYPE_CHECKING

from antlr4 import CommonTokenStream, InputStream, ParserRuleContext
from antlr4.tree.Tree import Tree
from loguru import logger

from .base_processor_interface import BaseProcessor
from ..antlr.java.JavaLexer import JavaLexer
from ..antlr.java.JavaParser import JavaParser
from ..pyttern_error_listener import Python3ErrorListener
from ..pytternfsm.PDA import PDA
from ..pytternfsm.java.java_to_pda import Java_to_PDA
from ..pytternfsm.java.tree_pruner import TreePruner

if TYPE_CHECKING:
    from ..subpattern.SubPattern import BaseSubPattern, SubPatternCallContext
    from ..subpattern.catalog import SubPatternCatalog


class JavaProcessor(BaseProcessor):
    """
    Language processor implementation for Java and Jattern (Java patterns).
    """

    def generate_tree_from_stream(self, stream: InputStream) -> ParserRuleContext | Tree:
        """
        Parses Java source from an ANTLR stream and returns a pruned parse tree.

        Args:
            stream (InputStream): The ANTLR stream containing Java source code.

        Returns:
            ParserRuleContext | Tree: The pruned Java compilation unit parse tree.
        """
        logger.debug("Generating Java parse tree")
        lexer = JavaLexer(stream)
        token_stream = CommonTokenStream(lexer)
        java_parser = JavaParser(token_stream)

        fake_error_output = io.StringIO()
        error_listener = Python3ErrorListener(fake_error_output)
        java_parser.removeErrorListeners()
        java_parser.addErrorListener(error_listener)

        tree = java_parser.compilationUnit()
        pruned_tree: ParserRuleContext | Tree = TreePruner().visit(tree)
        return pruned_tree

    def create_pda(
        self,
        pattern_tree: ParserRuleContext | Tree,
        catalog: SubPatternCatalog | None = None,
    ) -> dict[str, PDA]:
        """
        Compiles a Java pattern parse tree into a Pushdown Automaton.

        Args:
            pattern_tree (ParserRuleContext | Tree): The Java pattern parse tree.
            catalog (SubPatternCatalog | None): Optional subpattern catalog.

        Returns:
            dict[str, PDA]: A dictionary containing compiled PDA instances.
        """
        return Java_to_PDA().visit(pattern_tree)

    def compile_subpattern(
        self,
        subpattern: BaseSubPattern,
        context: SubPatternCallContext | ParserRuleContext | Tree | None,
        catalog: SubPatternCatalog | None = None,
    ) -> dict[str, PDA]:
        """
        Compiles a subpattern definition into a Pushdown Automaton given a call context.

        Args:
            subpattern: The subpattern definition to compile.
            context: The call context.
            catalog: Optional subpattern catalog.

        Raises:
            NotImplementedError: Subpatterns are not yet supported for Java.
        """
        raise NotImplementedError("Subpatterns are not yet supported for Java.")

    def get_language_extensions(self) -> list[str]:
        """
        Returns supported file extensions for Java and Jattern files.

        Returns:
            list[str]: Supported extensions ['java', 'jav', 'jat'].
        """
        return ["java", "jav", "jat"]


__all__ = ["JavaProcessor"]
