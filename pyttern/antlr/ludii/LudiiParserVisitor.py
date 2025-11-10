# Generated from LudiiParser.g4 by ANTLR 4.13.2
from antlr4 import *
if "." in __name__:
    from .LudiiParser import LudiiParser
else:
    from LudiiParser import LudiiParser

# This class defines a complete generic visitor for a parse tree produced by LudiiParser.

class LudiiParserVisitor(ParseTreeVisitor):

    # Visit a parse tree produced by LudiiParser#file_input.
    def visitFile_input(self, ctx:LudiiParser.File_inputContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by LudiiParser#function.
    def visitFunction(self, ctx:LudiiParser.FunctionContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by LudiiParser#args.
    def visitArgs(self, ctx:LudiiParser.ArgsContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by LudiiParser#named_argument.
    def visitNamed_argument(self, ctx:LudiiParser.Named_argumentContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by LudiiParser#argument.
    def visitArgument(self, ctx:LudiiParser.ArgumentContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by LudiiParser#lst.
    def visitLst(self, ctx:LudiiParser.LstContext):
        return self.visitChildren(ctx)



del LudiiParser