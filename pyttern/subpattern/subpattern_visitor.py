"""ANTLR parse tree visitor for parsing subpattern specifications."""

from __future__ import annotations

from typing import Any, Optional
from antlr4.tree.Tree import TerminalNodeImpl
from loguru import logger

from .SubPattern import AndSubPattern, BaseSubPattern, NotSubPattern, OrSubPattern
from ..antlr.python import Python3ParserVisitor, Python3Parser


def flatten(lst: list[Any]) -> list[Any]:
    """Recursively flatten a nested list into a single flat list.

    Args:
        lst: The list containing nested lists or elements.

    Returns:
        list[Any]: A flat list containing all terminal elements.
    """
    flat_list = []
    for el in lst:
        flatten_el = flatten(el) if isinstance(el, list) else [el]
        flat_list.extend(flatten_el)
    return flat_list


def get_original_text(ctx: Any) -> str:
    """Extract the raw source text corresponding to an ANTLR parse tree context.

    Args:
        ctx: ANTLR ParseTree context node.

    Returns:
        str: Source text snippet corresponding to the token span.
    """
    if ctx is None:
        return ""
    token_source = ctx.start.source[1]
    return token_source.getText(ctx.start.start, ctx.stop.stop)


class SubPattern_Visitor(Python3ParserVisitor):
    """ANTLR visitor that traverses subpattern ASTs and constructs SubPattern instances.

    Attributes:
        override: Whether existing subpatterns should be overwritten when encountered.
        current_subpattern: Reference to the subpattern currently being constructed.
    """

    def __init__(self, override: bool = True):
        """Initialize the visitor with configuration flags.

        Args:
            override: Whether to overwrite existing subpatterns in the registry.
        """
        super().__init__()
        self.override = override
        self.current_subpattern: Optional[BaseSubPattern] = None

    def visitSubpattern_input(self, ctx: Python3Parser.Subpattern_inputContext) -> list[BaseSubPattern]:
        """Visit top-level subpattern input and collect all parsed SubPattern objects.

        Args:
            ctx: Top-level subpattern input parse context.

        Returns:
            list[BaseSubPattern]: List of parsed SubPattern instances.
        """
        results = self.visitChildren(ctx)
        return [res for res in results if isinstance(res, BaseSubPattern)]

    def visitSubpattern_stmts(self, ctx: Python3Parser.Subpattern_stmtsContext) -> BaseSubPattern:
        """Visit subpattern statement definitions and construct the SubPattern instance.

        Args:
            ctx: Subpattern statement block parse context.

        Returns:
            BaseSubPattern: Constructed concrete subpattern instance.
        """
        vals = flatten(self.visitChildren(ctx))
        name, type_cls, args = vals[0]
        args_order = list(args.keys())
        self.current_subpattern = type_cls(
            name, args, args_order, code=get_original_text(ctx).strip()
        )
        transformations = vals[1:]
        for transformation in transformations:
            t_name, t_pda = transformation
            self.current_subpattern.add_transformation(t_name, t_pda)
        logger.trace(self.current_subpattern)
        return self.current_subpattern

    def visitSimple_subpattern(self, ctx: Python3Parser.Simple_subpatternContext) -> tuple[str, type, dict]:
        """Visit the subpattern header (e.g. $|Name(?arg)) and parse name, type, and args.

        Args:
            ctx: Subpattern header parse context.

        Returns:
            tuple[str, type, dict]: 3-tuple of (subpattern_name, subpattern_class, args_dict).

        Raises:
            ValueError: If an unrecognized subpattern operator symbol is encountered.
        """
        name = ctx.NAME().accept(self)
        type_str = ctx.getChild(1).getText().upper()
        if type_str == "&":
            type_cls = AndSubPattern
        elif type_str == "|":
            type_cls = OrSubPattern
        elif type_str == "!":
            type_cls = NotSubPattern
        else:
            raise ValueError(f"Unknown subpattern operator: {type_str}")

        if ctx.subpattern_args():
            arg_list = self.visitChildren(ctx.subpattern_args())
        else:
            arg_list = []

        args = {}
        for arg in arg_list:
            args.update(arg)
        logger.trace(f"Subpattern {name} with args {args}")
        return name, type_cls, args

    def visitSubpattern_arg(self, ctx: Python3Parser.Subpattern_argContext) -> dict[str, Any]:
        """Visit a formal argument definition in a subpattern header.

        Args:
            ctx: Formal argument parse context.

        Returns:
            dict[str, Any]: Mapping of parameter name to its default binding expression node.
        """
        name = flatten(ctx.getChild(0).accept(self))
        if isinstance(name, list):
            name = "".join(name)
        name = name.replace('?', '')

        bind = ctx.getChild(2)
        return {name: bind}

    def visitTransformation(self, ctx: Python3Parser.TransformationContext) -> tuple[str, Any]:
        """Visit a transformation branch ($# TransformationName).

        Args:
            ctx: Transformation branch parse context.

        Returns:
            tuple[str, Any]: Tuple of (branch_name, statement_parse_tree).
        """
        name = ctx.NAME().accept(self)
        parse_tree = ctx.stmt()

        logger.trace(f"Transformation {name} with stmt {parse_tree}")
        return name, parse_tree

    def visitTerminal(self, node: TerminalNodeImpl) -> str:
        """Return raw text representation of a terminal AST node.

        Args:
            node: Terminal token leaf node.

        Returns:
            str: Token text string.
        """
        return node.getText()

    def defaultResult(self) -> list:
        """Return the default accumulator value for unvisited branches.

        Returns:
            list: Empty list.
        """
        return []

    def aggregateResult(self, aggregate: list, nextResult: Any) -> list:
        """Aggregate intermediate branch evaluation results into a list.

        Args:
            aggregate: Accumulator list.
            nextResult: Child branch evaluation result.

        Returns:
            list: Appended results list.
        """
        aggregate.append(nextResult)
        return aggregate