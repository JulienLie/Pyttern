from antlr4.tree.Tree import TerminalNodeImpl
from loguru import logger

from .Macro import Macro
from ..antlr.python import Python3ParserVisitor, Python3Parser
from ..pytternfsm.python.python_to_pda import Python_to_PDA


def flatten(lst: list) -> list:
    flat_list = []
    for el in lst:
        flatten_el = flatten(el) if isinstance(el, list) else [el]
        flat_list += flatten_el
    return flat_list

class Macro_Visitor(Python3ParserVisitor):
    def __init__(self):
        self.current_macro = None

    def visitMacro_input(self, ctx:Python3Parser.Macro_inputContext):
        return self.visitChildren(ctx)[:-1]

    def visitMacro_stmts(self, ctx:Python3Parser.Macro_stmtsContext):
        vals = flatten(self.visitChildren(ctx))
        name, type, args = vals[0]
        args_order = list(args.keys())
        self.current_macro = Macro(name, args, args_order, code=ctx.getText(), type=type)
        transformations = vals[1:]
        for transformation in transformations:
            t_name, t_pda = transformation
            self.current_macro.add_transformation(t_name, t_pda)
        logger.debug(self.current_macro)
        return self.current_macro

    def visitMacro_def(self, ctx:Python3Parser.Macro_defContext):
        return ctx.macro().accept(self)

    def visitSimple_macro(self, ctx:Python3Parser.Simple_macroContext):
        name = ctx.NAME().accept(self)
        type = "AND" if ctx.getChild(1).getText().upper() == "&" else "OR"
        arg_list = self.visitChildren(ctx.macro_args())
        args = {}
        for arg in arg_list:
            args.update(arg)
        logger.debug(f"Macro {name} with args {args}")
        return name, type, args

    def visitMacro_arg(self, ctx:Python3Parser.Macro_argContext):
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