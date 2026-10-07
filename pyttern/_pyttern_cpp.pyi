from __future__ import annotations
import typing
__all__: list[str] = ['Node', 'parse_code', 'parse_subpattern']
class Node:
    def __repr__(self) -> str:
        ...
    def __str__(self) -> str:
        ...
    def accept(self, arg0: typing.Any) -> typing.Any:
        ...
    def getChild(self, i: typing.SupportsInt | typing.SupportsIndex, rule_type: str = '') -> Node:
        ...
    def getChildCount(self) -> int:
        ...
    def getChildren(self) -> list[Node]:
        ...
    def getText(self) -> str:
        ...
    @property
    def children(self) -> list[Node]:
        ...
    @property
    def is_terminal(self) -> bool:
        ...
    @property
    def parentCtx(self) -> Node:
        ...
    @property
    def rule_name(self) -> str:
        ...
def parse_code(arg0: str) -> Node:
    """
    Parse and prune Python code in C++
    """
def parse_subpattern(arg0: str) -> Node:
    """
    Parse and prune Pyttern subpattern in C++
    """
