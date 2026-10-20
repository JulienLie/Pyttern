from __future__ import annotations

import os
from abc import ABC, abstractmethod
from dataclasses import dataclass
from pathlib import Path
from typing import Any, Optional

from antlr4.ParserRuleContext import ParserRuleContext
from antlr4.tree.Tree import Tree

from pyttern.language_processors.base_processor_interface import BaseProcessor
from pyttern.pattern.result import MatchResult
from pyttern.simulator.Matcher import Matcher
from pyttern.subpattern.catalog import SubPatternCatalog


@dataclass(frozen=True)
class CompiledPattern(ABC):
    """
    Abstract base class representing an immutable compiled structural pattern.
    """
    name: str

    @abstractmethod
    def matches(self, target: Any, stop_at_first: Optional[bool] = None) -> MatchResult:
        """
        Executes structural matching against target code or syntax tree.

        :param target: Pre-parsed CST node, file path (str or Path), or raw code string.
        :param stop_at_first: Optional override to short-circuit upon finding the first match.
        :return: An immutable MatchResult instance.
        """
        pass


@dataclass(frozen=True)
class LeafPattern(CompiledPattern):
    """
    An elementary compiled pattern wrapping a Pushdown Automaton.
    """
    pda: Any
    processor: BaseProcessor
    catalog: Optional[SubPatternCatalog] = None

    def matches(self, target: Any, stop_at_first: Optional[bool] = None) -> MatchResult:
        """
        Executes matching of this leaf pattern against target code.
        """
        if isinstance(target, (ParserRuleContext, Tree)):
            code_tree = target
        elif isinstance(target, Path):
            code_tree = self.processor.generate_tree_from_file(str(target))
        elif isinstance(target, str):
            if "\n" not in target and os.path.isfile(target):
                code_tree = self.processor.generate_tree_from_file(target)
            else:
                code_tree = self.processor.generate_tree_from_code(target)
        else:
            raise TypeError(
                f"Target must be a CST node, file path, or code string, got {type(target).__name__}"
            )

        stop = False if stop_at_first is None else bool(stop_at_first)
        match_set = Matcher.match(
            self.pda,
            code_tree,
            stop_at_first=stop,
            catalog=self.catalog,
        )
        matched = match_set.count() > 0
        return MatchResult(
            matched=matched,
            name=self.name,
            matches=match_set,
            children=(),
        )

    def __repr__(self) -> str:
        return f"LeafPattern(name={self.name!r})"
