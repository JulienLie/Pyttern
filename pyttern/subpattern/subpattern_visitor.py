from antlr4.tree.Tree import TerminalNodeImpl
from loguru import logger

from .SubPattern import AndSubPattern, BaseSubPattern, NotSubPattern, OrSubPattern
from ..antlr.python import Python3ParserVisitor, Python3Parser


def flatten(lst: list) -> list:
    flat_list = []
    for el in lst:
        flatten_el = flatten(el) if isinstance(el, list) else [el]
        flat_list += flatten_el
    return flat_list

def get_original_text(ctx):
    if ctx is None:
        return ""
    token_source = ctx.start.source[1]
    return token_source.getText(ctx.start.start, ctx.stop.stop)

class SubPattern_Visitor(Python3ParserVisitor):
    def __init__(self):
        self.current_subpattern = None

    def visitSubpattern_input(self, ctx:Python3Parser.Subpattern_inputContext):
        results = self.visitChildren(ctx)
        return [res for res in results if isinstance(res, BaseSubPattern)]

    def visitSubpattern_stmts(self, ctx:Python3Parser.Subpattern_stmtsContext):
        vals = flatten(self.visitChildren(ctx))
        name, type, args = vals[0]
        args_order = list(args.keys())
        self.current_subpattern = type(name, args, args_order, code=get_original_text(ctx).strip())
        transformations = vals[1:]
        for transformation in transformations:
            t_name, t_pda = transformation
            self.current_subpattern.add_transformation(t_name, t_pda)
        logger.debug(self.current_subpattern)
        return self.current_subpattern

    def visitSimple_subpattern(self, ctx:Python3Parser.Simple_subpatternContext):
        name = ctx.NAME().accept(self)
        type_str = ctx.getChild(1).getText().upper()
        if type_str == "&": type_cls = AndSubPattern
        elif type_str == "|": type_cls = OrSubPattern
        elif type_str == "!": type_cls = NotSubPattern
        
        if ctx.subpattern_args():
            arg_list = self.visitChildren(ctx.subpattern_args())
        else:
            arg_list = []
            
        args = {}
        for arg in arg_list:
            args.update(arg)
        logger.debug(f"Subpattern {name} with args {args}")
        return name, type_cls, args

    def visitSubpattern_arg(self, ctx:Python3Parser.Subpattern_argContext):
        name = flatten(ctx.getChild(0).accept(self))
        if isinstance(name, list):
            name = "".join(name)
        name = name.replace('?', '')

        bind = ctx.getChild(2)

        return {name: bind}

    def visitTransformation(self, ctx:Python3Parser.TransformationContext):
        name = ctx.NAME().accept(self)
        parse_tree = ctx.stmt().getChild(0)
        # pda = Python_to_PDA().visit(ctx.stmt().getChild(0))

        logger.debug(f"Transformation {name} with stmt {parse_tree}")
        return name, parse_tree

    def visitTerminal(self, node: TerminalNodeImpl):
        return node.getText()

    def defaultResult(self):
        return []

    def aggregateResult(self, aggregate, nextResult):
        return aggregate + [nextResult]