from __future__ import annotations

from abc import ABC
from dataclasses import dataclass
from typing import Any, Optional, Sequence, Union

from pyttern.language_processors.base_processor_interface import BaseProcessor
from pyttern.pattern.compiled import CompiledPattern
from pyttern.pattern.result import MatchResult


@dataclass(frozen=True, init=False)
class CompositePattern(CompiledPattern, ABC):
    """
    Abstract base class for immutable composite pattern combinations (AND, OR, NOT).
    """
    children: tuple[CompiledPattern, ...]
    _processor: Optional[BaseProcessor]

    def __init__(
        self,
        children: Sequence[CompiledPattern] = (),
        name: str = "composite",
        processor: Optional[BaseProcessor] = None,
    ) -> None:
        object.__setattr__(self, "name", name)
        object.__setattr__(self, "children", tuple(children))
        object.__setattr__(self, "_processor", processor)

    @property
    def processor(self) -> Optional[BaseProcessor]:
        """
        Exposes the language processor injected into this composite, or directly
        from its first child pattern.
        """
        if self._processor is not None:
            return self._processor
        if self.children:
            return self.children[0].processor
        return None

    def __repr__(self) -> str:
        return f"{self.__class__.__name__}(name={self.name!r}, children={list(self.children)!r})"


@dataclass(frozen=True, init=False)
class AndPattern(CompositePattern):
    """
    Logical AND composite pattern: matches if and only if all child patterns match.
    Short-circuits on the first failing child when stop_at_first=True.
    """

    def __init__(
        self,
        children: Sequence[CompiledPattern] = (),
        name: str = "and",
        processor: Optional[BaseProcessor] = None,
    ) -> None:
        super().__init__(children=children, name=name, processor=processor)

    def matches(self, target: Any, stop_at_first: Optional[bool] = None) -> MatchResult:
        code_tree = self._resolve_target(target)
        stop = False if stop_at_first is None else bool(stop_at_first)
        child_results: list[MatchResult] = []
        matched = True

        for child in self.children:
            res = child.matches(code_tree, stop_at_first=stop_at_first)
            child_results.append(res)
            if not res.matched:
                matched = False
                if stop:
                    break

        return MatchResult(
            matched=matched,
            name=self.name,
            matches=None,
            children=tuple(child_results),
        )


@dataclass(frozen=True, init=False)
class OrPattern(CompositePattern):
    """
    Logical OR composite pattern: matches if any child pattern matches.
    Short-circuits on the first successful match by default or when stop_at_first=True.
    """

    def __init__(
        self,
        children: Sequence[CompiledPattern] = (),
        name: str = "or",
        processor: Optional[BaseProcessor] = None,
    ) -> None:
        super().__init__(children=children, name=name, processor=processor)

    def matches(self, target: Any, stop_at_first: Optional[bool] = None) -> MatchResult:
        code_tree = self._resolve_target(target)
        child_results: list[MatchResult] = []
        matched = False

        for child in self.children:
            res = child.matches(code_tree, stop_at_first=stop_at_first)
            child_results.append(res)
            if res.matched:
                matched = True
                if stop_at_first is None or stop_at_first is True:
                    break

        return MatchResult(
            matched=matched,
            name=self.name,
            matches=None,
            children=tuple(child_results),
        )


@dataclass(frozen=True, init=False)
class NotPattern(CompositePattern):
    """
    Logical NOT composite pattern: inverts the match outcome of its child pattern(s).
    When evaluating multiple children, represents NOT-any (fails if any child matches).
    Short-circuits on the first matching child when stop_at_first=True.
    """

    def __init__(
        self,
        child_or_children: Union[CompiledPattern, Sequence[CompiledPattern], None] = None,
        name: str = "not",
        processor: Optional[BaseProcessor] = None,
        *,
        child: Optional[CompiledPattern] = None,
        children: Optional[Sequence[CompiledPattern]] = None,
    ) -> None:
        resolved_children: tuple[CompiledPattern, ...]
        if child is not None:
            resolved_children = (child,)
        elif children is not None:
            resolved_children = tuple(children)
        elif child_or_children is not None:
            if isinstance(child_or_children, CompiledPattern):
                resolved_children = (child_or_children,)
            else:
                resolved_children = tuple(child_or_children)
        else:
            resolved_children = ()
        super().__init__(children=resolved_children, name=name, processor=processor)

    @property
    def child(self) -> Optional[CompiledPattern]:
        """Convenience property for accessing the single child pattern."""
        return self.children[0] if self.children else None

    def matches(self, target: Any, stop_at_first: Optional[bool] = None) -> MatchResult:
        code_tree = self._resolve_target(target)
        stop = False if stop_at_first is None else bool(stop_at_first)
        child_results: list[MatchResult] = []
        any_matched = False

        for child in self.children:
            res = child.matches(code_tree, stop_at_first=stop_at_first)
            child_results.append(res)
            if res.matched:
                any_matched = True
                if stop:
                    break

        matched = not any_matched
        return MatchResult(
            matched=matched,
            name=self.name,
            matches=None,
            children=tuple(child_results),
        )
