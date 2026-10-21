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
    _instances: dict[type, BaseProcessor] = {}

    def __new__(cls) -> BaseProcessor:
        if cls not in BaseProcessor._instances:
            BaseProcessor._instances[cls] = super(BaseProcessor, cls).__new__(cls)
        return BaseProcessor._instances[cls]

    def generate_tree_from_code(self, code: str) -> ParserRuleContext | Tree:
        code = code.strip()
        code += "\n"
        stream = InputStream(code)
        return self.generate_tree_from_stream(stream)

    def generate_tree_from_stream(self, stream: InputStream) -> ParserRuleContext | Tree:
        raise NotImplementedError

    def generate_tree_from_file(self, file: str | Path) -> ParserRuleContext | Tree:
        file_input = FileStream(str(file), encoding="utf-8")
        return self.generate_tree_from_stream(file_input)

    def create_pda(
        self,
        pattern_tree: ParserRuleContext | Tree,
        catalog: SubPatternCatalog | None = None,
    ) -> dict[str, PDA] | PDA:
        raise NotImplementedError

    def compile_subpattern(
        self,
        subpattern: BaseSubPattern,
        context: SubPatternCallContext | ParserRuleContext | Tree | None,
        catalog: SubPatternCatalog | None = None,
    ) -> dict[str, PDA]:
        """
        Compiles a subpattern definition into a Pushdown Automaton given a call context.

        :param subpattern: The subpattern definition to compile.
        :param context: The call context (SubPatternCallContext or AST node).
        :param catalog: Optional subpattern catalog to resolve nested subpatterns.
        :return: A dictionary mapping transformation names to compiled PDA instances.
        """
        raise NotImplementedError

    def create_matcher(self, fsm: Any, code_tree: ParserRuleContext | Tree) -> Any:
        raise NotImplementedError

    def create_listener(self) -> ConsolePytternListener:
        from ..Pyttern_listener import ConsolePytternListener

        return ConsolePytternListener()

    def get_language_extensions(self) -> list[str]:
        raise NotImplementedError


BaseProcessorInterface = BaseProcessor

__all__ = ["BaseProcessor", "BaseProcessorInterface"]
