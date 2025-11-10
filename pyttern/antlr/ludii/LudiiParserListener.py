# Generated from LudiiParser.g4 by ANTLR 4.13.2
from antlr4 import *
if "." in __name__:
    from .LudiiParser import LudiiParser
else:
    from LudiiParser import LudiiParser

# This class defines a complete listener for a parse tree produced by LudiiParser.
class LudiiParserListener(ParseTreeListener):

    # Enter a parse tree produced by LudiiParser#file_input.
    def enterFile_input(self, ctx:LudiiParser.File_inputContext):
        pass

    # Exit a parse tree produced by LudiiParser#file_input.
    def exitFile_input(self, ctx:LudiiParser.File_inputContext):
        pass


    # Enter a parse tree produced by LudiiParser#function.
    def enterFunction(self, ctx:LudiiParser.FunctionContext):
        pass

    # Exit a parse tree produced by LudiiParser#function.
    def exitFunction(self, ctx:LudiiParser.FunctionContext):
        pass


    # Enter a parse tree produced by LudiiParser#args.
    def enterArgs(self, ctx:LudiiParser.ArgsContext):
        pass

    # Exit a parse tree produced by LudiiParser#args.
    def exitArgs(self, ctx:LudiiParser.ArgsContext):
        pass


    # Enter a parse tree produced by LudiiParser#named_argument.
    def enterNamed_argument(self, ctx:LudiiParser.Named_argumentContext):
        pass

    # Exit a parse tree produced by LudiiParser#named_argument.
    def exitNamed_argument(self, ctx:LudiiParser.Named_argumentContext):
        pass


    # Enter a parse tree produced by LudiiParser#argument.
    def enterArgument(self, ctx:LudiiParser.ArgumentContext):
        pass

    # Exit a parse tree produced by LudiiParser#argument.
    def exitArgument(self, ctx:LudiiParser.ArgumentContext):
        pass


    # Enter a parse tree produced by LudiiParser#lst.
    def enterLst(self, ctx:LudiiParser.LstContext):
        pass

    # Exit a parse tree produced by LudiiParser#lst.
    def exitLst(self, ctx:LudiiParser.LstContext):
        pass



del LudiiParser