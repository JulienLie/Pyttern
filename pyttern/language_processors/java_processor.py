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
    def generate_tree_from_stream(self, stream: InputStream) -> ParserRuleContext | Tree:
        logger.debug("Generating tree")
        lexer = JavaLexer(stream)
        token_stream = CommonTokenStream(lexer)
        java_parser = JavaParser(token_stream)

        error = io.StringIO()

        java_parser.removeErrorListeners()
        error_listener = Python3ErrorListener(error)
        java_parser.addErrorListener(error_listener)

        tree = java_parser.compilationUnit()

        pruned_tree: ParserRuleContext | Tree = TreePruner().visit(tree)

        return pruned_tree

    def create_pda(
        self,
        pattern_tree: ParserRuleContext | Tree,
        catalog: SubPatternCatalog | None = None,
    ) -> dict[str, PDA]:
        return Java_to_PDA().visit(pattern_tree)

    def compile_subpattern(
        self,
        subpattern: BaseSubPattern,
        context: SubPatternCallContext | ParserRuleContext | Tree | None,
        catalog: SubPatternCatalog | None = None,
    ) -> dict[str, PDA]:
        raise NotImplementedError("Subpatterns are not yet supported for Java.")

    def get_language_extensions(self) -> list[str]:
        return ["java", "jav", "jat"]


__all__ = ["JavaProcessor"]
