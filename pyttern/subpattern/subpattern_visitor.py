from __future__ import annotations

from typing import Any, Optional
from antlr4.tree.Tree import TerminalNodeImpl
from loguru import logger

from .SubPattern import AndSubPattern, BaseSubPattern, NotSubPattern, OrSubPattern
from ..antlr.python import Python3ParserVisitor, Python3Parser


def flatten(lst: list[Any]) -> list[Any]:
    """
    Recursively flattens a nested list into a single flat list.

    :param lst: The list containing nested lists or elements.
    :return: A flat list of elements.
    """
    flat_list = []
    for el in lst:
        flatten_el = flatten(el) if isinstance(el, list) else [el]
        flat_list.extend(flatten_el)
    return flat_list


def get_original_text(ctx: Any) -> str:
    """
    Extracts the raw source text corresponding to an ANTLR parse tree context.

    :param ctx: ANTLR ParseTree context.
    :return: Source text snippet.
    """
    if ctx is None:
        return ""
    token_source = ctx.start.source[1]
    return token_source.getText(ctx.start.start, ctx.stop.stop)


class SubPattern_Visitor(Python3ParserVisitor):
    """
    ANTLR visitor that traverses subpattern ASTs and constructs concrete SubPattern instances.
    """

    def __init__(self, override: bool = True):
        super().__init__()
        self.override = override
        self.current_subpattern: Optional[BaseSubPattern] = None

    def visitSubpattern_input(self, ctx: Python3Parser.Subpattern_inputContext) -> list[BaseSubPattern]:
        """
        Visits top-level subpattern input and collects all parsed SubPattern objects.
        """
        results = self.visitChildren(ctx)
        return [res for res in results if isinstance(res, BaseSubPattern)]

    def visitSubpattern_stmts(self, ctx: Python3Parser.Subpattern_stmtsContext) -> BaseSubPattern:
        """
        Visits subpattern statement definitions and constructs the SubPattern instance.
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

    def visitSubpattern(self, ctx: Python3Parser.SubpatternContext):
        if ctx.compound_subpattern():
            return self.visitCompound_subpattern(ctx.compound_subpattern())
        elif ctx.simple_subpattern():
            return self.visitSimple_subpattern(ctx.simple_subpattern())
        return self.visitChildren(ctx)

    def visitCompound_subpattern(self, ctx: Python3Parser.Compound_subpatternContext) -> tuple[str, type, dict, str]:
        name, type_cls, args = self.visitSimple_subpattern(ctx.simple_subpattern())
        body_var_node = ctx.atom_wildcard()
        body_var = body_var_node.getText().replace('?', '')
        return name, type_cls, args, body_var

    def visitBlockEnd(self, ctx: Any) -> None:
        return None

    def visitSimple_subpattern(self, ctx: Python3Parser.Simple_subpatternContext) -> tuple[str, type, dict]:
        """
        Visits the subpattern header (e.g. $|Name(?arg)) and returns (name, subpattern_class, args).
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
        """
        Visits a formal argument definition in a subpattern header.
        """
        name = flatten(ctx.getChild(0).accept(self))
        if isinstance(name, list):
            name = "".join(name)
        name = name.replace('?', '')

        bind = ctx.getChild(2)
        return {name: bind}

    def visitTransformation(self, ctx: Python3Parser.TransformationContext) -> tuple[str, Any]:
        """
        Visits a transformation branch ($# TransformationName).
        """
        name = ctx.NAME().accept(self)
        parse_tree = ctx.stmt()

        logger.trace(f"Transformation {name} with stmt {parse_tree}")
        return name, parse_tree

    def visitTerminal(self, node: TerminalNodeImpl) -> str:
        """
        Returns text representation of a terminal AST node.
        """
        return node.getText()

    def defaultResult(self) -> list:
        return []

    def aggregateResult(self, aggregate: list, nextResult: Any) -> list:
        return aggregate + [nextResult]