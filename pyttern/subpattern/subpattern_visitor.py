"""ANTLR parse tree visitor for parsing subpattern specifications."""
from __future__ import annotations

from typing import Any
from antlr4.tree.Tree import ParseTree, TerminalNodeImpl
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
    flat_list: list[Any] = []
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

    def __init__(self, override: bool = True) -> None:
        """Initialize the visitor with configuration flags.

        Args:
            override: Whether to overwrite existing subpatterns in the registry.
        """
        super().__init__()
        self.override = override
        self.current_subpattern: BaseSubPattern | None = None

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
        header = self.visitSubpattern(ctx.subpattern())
        if len(header) == 4:
            name, type_cls, args, body_var = header
        else:
            name, type_cls, args = header
            body_var = None

        args_order = list(args.keys())
        self.current_subpattern = type_cls(
            name, args, args_order, code=get_original_text(ctx).strip(), body_var=body_var
        )
        for t_ctx in ctx.transformation():
            t_name, t_pda = self.visitTransformation(t_ctx)
            self.current_subpattern.add_transformation(t_name, t_pda)
        logger.trace(self.current_subpattern)
        return self.current_subpattern

    def visitSubpattern(self, ctx: Python3Parser.SubpatternContext) -> Any:
        """Visit subpattern wrapper node and dispatch to simple or compound subpattern.

        Args:
            ctx: Subpattern context node.

        Returns:
            Any: Header tuple from simple or compound subpattern.
        """
        if ctx.compound_subpattern():
            return self.visitCompound_subpattern(ctx.compound_subpattern())
        elif ctx.simple_subpattern():
            return self.visitSimple_subpattern(ctx.simple_subpattern())
        return self.visitChildren(ctx)

    def visitCompound_subpattern(
        self, ctx: Python3Parser.Compound_subpatternContext
    ) -> tuple[str, type[BaseSubPattern], dict[str, Any], str]:
        """Visit compound subpattern header with body wildcard.

        Args:
            ctx: Compound subpattern context node.

        Returns:
            tuple: (name, subpattern_class, args_dict, body_var).
        """
        name, type_cls, args = self.visitSimple_subpattern(ctx.simple_subpattern())
        body_var_node = ctx.atom_wildcard()
        body_var = body_var_node.getText().replace("?", "")
        return name, type_cls, args, body_var

    def visitBlockEnd(self, ctx: Any) -> None:
        """Ignore BlockEnd tokens in subpattern visitor."""
        return None

    def visitSimple_subpattern(
        self, ctx: Python3Parser.Simple_subpatternContext
    ) -> tuple[str, type[BaseSubPattern], dict[str, Any]]:
        """Visit the subpattern header (e.g. $|Name(?arg)) and parse name, type, and args.

        Args:
            ctx: Subpattern header parse context.

        Returns:
            tuple[str, type, dict]: 3-tuple of (subpattern_name, subpattern_class, args_dict).

        Raises:
            ValueError: If an unrecognized subpattern operator symbol is encountered.
        """
        name: str = ctx.NAME().accept(self)
        type_str = ctx.getChild(1).getText().upper()
        type_cls: type[BaseSubPattern]
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

        args: dict[str, Any] = {}
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
        raw_name = ctx.getChild(0).accept(self)
        if isinstance(raw_name, list):
            name_parts = flatten(raw_name)
            name = "".join(str(p) for p in name_parts)
        else:
            name = str(raw_name)
        name = name.replace("?", "")

        bind = ctx.getChild(2)
        return {name: bind}

    def visitTransformation(self, ctx: Python3Parser.TransformationContext) -> tuple[str, ParseTree]:
        """Visit a transformation branch ($# TransformationName).

        Args:
            ctx: Transformation branch parse context.

        Returns:
            tuple[str, ParseTree]: Tuple of (branch_name, statement_parse_tree).
        """
        name: str = ctx.NAME().accept(self)
        parse_tree: ParseTree = ctx.stmt()

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

    def defaultResult(self) -> list[Any]:
        """Return the default accumulator value for unvisited branches.

        Returns:
            list[Any]: Empty list.
        """
        return []

    def aggregateResult(self, aggregate: list[Any], nextResult: Any) -> list[Any]:
        """Aggregate intermediate branch evaluation results into a list.

        Args:
            aggregate: Accumulator list.
            nextResult: Child branch evaluation result.

        Returns:
            list[Any]: Appended results list.
        """
        aggregate.append(nextResult)
        return aggregate
