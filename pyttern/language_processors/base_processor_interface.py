"""Base language processor interface for Pyttern."""
from __future__ import annotations

from pathlib import Path
from typing import TYPE_CHECKING, Any

from antlr4 import FileStream, InputStream, ParserRuleContext
from antlr4.tree.Tree import Tree

from ..pytternfsm.PDA import PDA

if TYPE_CHECKING:
    from ..Pyttern_listener import ConsolePytternListener
    from ..subpattern.SubPattern import BaseSubPattern, SubPatternCallContext
    from ..subpattern.catalog import SubPatternCatalog


class BaseProcessor:
    """
    Abstract singleton base processor handling parsing, tree generation,
    and PDA compilation for a specific programming language.
    """

    _instances: dict[type, BaseProcessor] = {}

    def __new__(cls) -> BaseProcessor:
        if cls not in BaseProcessor._instances:
            BaseProcessor._instances[cls] = super(BaseProcessor, cls).__new__(cls)
        return BaseProcessor._instances[cls]

    def generate_tree_from_code(self, code: str) -> ParserRuleContext | Tree:
        """
        Generates a pruned parse tree from a source code string.

        Args:
            code (str): Source code or pattern string.

        Returns:
            ParserRuleContext | Tree: The root node of the pruned parse tree.
        """
        code = code.strip()
        code += "\n"
        stream = InputStream(code)
        return self.generate_tree_from_stream(stream)

    def generate_tree_from_stream(self, stream: InputStream) -> ParserRuleContext | Tree:
        """
        Parses an ANTLR input stream and returns a pruned parse tree.

        Args:
            stream (InputStream): The ANTLR input stream.

        Returns:
            ParserRuleContext | Tree: The pruned parse tree.

        Raises:
            NotImplementedError: Subclasses must implement this method.
        """
        raise NotImplementedError

    def generate_tree_from_file(self, file: str | Path) -> ParserRuleContext | Tree:
        """
        Generates a pruned parse tree from a source file.

        Args:
            file (str | Path): Path to the source file.

        Returns:
            ParserRuleContext | Tree: The pruned parse tree.
        """
        file_input = FileStream(str(file), encoding="utf-8")
        return self.generate_tree_from_stream(file_input)

    def create_pda(
        self,
        pattern_tree: ParserRuleContext | Tree,
        catalog: SubPatternCatalog | None = None,
    ) -> dict[str, PDA] | PDA:
        """
        Compiles a pattern parse tree into a Pushdown Automaton (PDA).

        Args:
            pattern_tree (ParserRuleContext | Tree): The root of the pattern parse tree.
            catalog (SubPatternCatalog | None): Optional catalog for subpatterns.

        Returns:
            dict[str, PDA] | PDA: A mapping of automaton names to PDA instances, or a PDA.

        Raises:
            NotImplementedError: Subclasses must implement this method.
        """
        raise NotImplementedError

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
            context: The call context (SubPatternCallContext or AST node).
            catalog: Optional subpattern catalog to resolve nested subpatterns.

        Returns:
            dict[str, PDA]: A dictionary mapping transformation names to compiled PDA instances.
        """
        raise NotImplementedError

    def create_matcher(self, fsm: Any, code_tree: ParserRuleContext | Tree) -> Any:
        """
        Creates a Matcher instance for the given compiled PDA and code tree.

        Args:
            fsm: Compiled pushdown automaton.
            code_tree: Target code parse tree.

        Raises:
            NotImplementedError: Subclasses must implement this method.
        """
        raise NotImplementedError

    def create_listener(self) -> ConsolePytternListener:
        """
        Creates a default event listener for pattern matching execution.

        Returns:
            ConsolePytternListener: The listener instance.
        """
        from ..Pyttern_listener import ConsolePytternListener

        return ConsolePytternListener()

    def get_language_extensions(self) -> list[str]:
        """
        Returns the list of file extensions supported by this language processor.

        Returns:
            list[str]: Supported file extensions (without leading dot).

        Raises:
            NotImplementedError: Subclasses must implement this method.
        """
        raise NotImplementedError


BaseProcessorInterface = BaseProcessor

__all__ = ["BaseProcessor", "BaseProcessorInterface"]
