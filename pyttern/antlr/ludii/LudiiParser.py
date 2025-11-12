# Generated from LudiiParser.g4 by ANTLR 4.13.2
# encoding: utf-8
import sys

from antlr4 import *

if sys.version_info[1] > 5:
	from typing import TextIO
else:
	from typing.io import TextIO

def serializedATN():
    return [
        4,1,15,51,2,0,7,0,2,1,7,1,2,2,7,2,2,3,7,3,2,4,7,4,2,5,7,5,1,0,5,
        0,14,8,0,10,0,12,0,17,9,0,1,0,1,0,1,1,1,1,1,1,1,1,1,1,1,2,1,2,5,
        2,28,8,2,10,2,12,2,31,9,2,1,3,1,3,1,3,1,3,1,4,1,4,1,4,3,4,40,8,4,
        1,5,1,5,5,5,44,8,5,10,5,12,5,47,9,5,1,5,1,5,1,5,0,0,6,0,2,4,6,8,
        10,0,0,50,0,15,1,0,0,0,2,20,1,0,0,0,4,29,1,0,0,0,6,32,1,0,0,0,8,
        39,1,0,0,0,10,41,1,0,0,0,12,14,3,2,1,0,13,12,1,0,0,0,14,17,1,0,0,
        0,15,13,1,0,0,0,15,16,1,0,0,0,16,18,1,0,0,0,17,15,1,0,0,0,18,19,
        5,0,0,1,19,1,1,0,0,0,20,21,5,1,0,0,21,22,5,11,0,0,22,23,3,4,2,0,
        23,24,5,2,0,0,24,3,1,0,0,0,25,28,3,6,3,0,26,28,3,8,4,0,27,25,1,0,
        0,0,27,26,1,0,0,0,28,31,1,0,0,0,29,27,1,0,0,0,29,30,1,0,0,0,30,5,
        1,0,0,0,31,29,1,0,0,0,32,33,5,11,0,0,33,34,5,3,0,0,34,35,3,8,4,0,
        35,7,1,0,0,0,36,40,3,2,1,0,37,40,3,10,5,0,38,40,5,11,0,0,39,36,1,
        0,0,0,39,37,1,0,0,0,39,38,1,0,0,0,40,9,1,0,0,0,41,45,5,6,0,0,42,
        44,3,8,4,0,43,42,1,0,0,0,44,47,1,0,0,0,45,43,1,0,0,0,45,46,1,0,0,
        0,46,48,1,0,0,0,47,45,1,0,0,0,48,49,5,7,0,0,49,11,1,0,0,0,5,15,27,
        29,39,45
    ]

class LudiiParser ( Parser ):

    grammarFileName = "LudiiParser.g4"

    atn = ATNDeserializer().deserialize(serializedATN())

    decisionsToDFA = [ DFA(ds, i) for i, ds in enumerate(atn.decisionToState) ]

    sharedContextCache = PredictionContextCache()

    literalNames = [ "<INVALID>", "'('", "')'", "':'", "','", "';'", "'{'", 
                     "'}'" ]

    symbolicNames = [ "<INVALID>", "LPAREN", "RPAREN", "COLON", "COMMA", 
                      "SEMI_COLON", "LBRACE", "RBRACE", "WS", "COMMENT", 
                      "LINE_COMMENT", "NAME", "STRING", "LITERAL", "NUMBER", 
                      "REFERENCE" ]

    RULE_file_input = 0
    RULE_function = 1
    RULE_args = 2
    RULE_named_argument = 3
    RULE_argument = 4
    RULE_lst = 5

    ruleNames =  [ "file_input", "function", "args", "named_argument", "argument", 
                   "lst" ]

    EOF = Token.EOF
    LPAREN=1
    RPAREN=2
    COLON=3
    COMMA=4
    SEMI_COLON=5
    LBRACE=6
    RBRACE=7
    WS=8
    COMMENT=9
    LINE_COMMENT=10
    NAME=11
    STRING=12
    LITERAL=13
    NUMBER=14
    REFERENCE=15

    def __init__(self, input:TokenStream, output:TextIO = sys.stdout):
        super().__init__(input, output)
        self.checkVersion("4.13.2")
        self._interp = ParserATNSimulator(self, self.atn, self.decisionsToDFA, self.sharedContextCache)
        self._predicates = None




    class File_inputContext(ParserRuleContext):
        __slots__ = 'parser'

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser

        def EOF(self):
            return self.getToken(LudiiParser.EOF, 0)

        def function(self, i:int=None):
            if i is None:
                return self.getTypedRuleContexts(LudiiParser.FunctionContext)
            else:
                return self.getTypedRuleContext(LudiiParser.FunctionContext,i)


        def getRuleIndex(self):
            return LudiiParser.RULE_file_input

        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterFile_input" ):
                listener.enterFile_input(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitFile_input" ):
                listener.exitFile_input(self)

        def accept(self, visitor:ParseTreeVisitor):
            if hasattr( visitor, "visitFile_input" ):
                return visitor.visitFile_input(self)
            else:
                return visitor.visitChildren(self)




    def file_input(self):

        localctx = LudiiParser.File_inputContext(self, self._ctx, self.state)
        self.enterRule(localctx, 0, self.RULE_file_input)
        self._la = 0 # Token type
        try:
            self.enterOuterAlt(localctx, 1)
            self.state = 15
            self._errHandler.sync(self)
            _la = self._input.LA(1)
            while _la==1:
                self.state = 12
                self.function()
                self.state = 17
                self._errHandler.sync(self)
                _la = self._input.LA(1)

            self.state = 18
            self.match(LudiiParser.EOF)
        except RecognitionException as re:
            localctx.exception = re
            self._errHandler.reportError(self, re)
            self._errHandler.recover(self, re)
        finally:
            self.exitRule()
        return localctx


    class FunctionContext(ParserRuleContext):
        __slots__ = 'parser'

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser

        def LPAREN(self):
            return self.getToken(LudiiParser.LPAREN, 0)

        def NAME(self):
            return self.getToken(LudiiParser.NAME, 0)

        def args(self):
            return self.getTypedRuleContext(LudiiParser.ArgsContext,0)


        def RPAREN(self):
            return self.getToken(LudiiParser.RPAREN, 0)

        def getRuleIndex(self):
            return LudiiParser.RULE_function

        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterFunction" ):
                listener.enterFunction(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitFunction" ):
                listener.exitFunction(self)

        def accept(self, visitor:ParseTreeVisitor):
            if hasattr( visitor, "visitFunction" ):
                return visitor.visitFunction(self)
            else:
                return visitor.visitChildren(self)




    def function(self):

        localctx = LudiiParser.FunctionContext(self, self._ctx, self.state)
        self.enterRule(localctx, 2, self.RULE_function)
        try:
            self.enterOuterAlt(localctx, 1)
            self.state = 20
            self.match(LudiiParser.LPAREN)
            self.state = 21
            self.match(LudiiParser.NAME)
            self.state = 22
            self.args()
            self.state = 23
            self.match(LudiiParser.RPAREN)
        except RecognitionException as re:
            localctx.exception = re
            self._errHandler.reportError(self, re)
            self._errHandler.recover(self, re)
        finally:
            self.exitRule()
        return localctx


    class ArgsContext(ParserRuleContext):
        __slots__ = 'parser'

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser

        def named_argument(self, i:int=None):
            if i is None:
                return self.getTypedRuleContexts(LudiiParser.Named_argumentContext)
            else:
                return self.getTypedRuleContext(LudiiParser.Named_argumentContext,i)


        def argument(self, i:int=None):
            if i is None:
                return self.getTypedRuleContexts(LudiiParser.ArgumentContext)
            else:
                return self.getTypedRuleContext(LudiiParser.ArgumentContext,i)


        def getRuleIndex(self):
            return LudiiParser.RULE_args

        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterArgs" ):
                listener.enterArgs(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitArgs" ):
                listener.exitArgs(self)

        def accept(self, visitor:ParseTreeVisitor):
            if hasattr( visitor, "visitArgs" ):
                return visitor.visitArgs(self)
            else:
                return visitor.visitChildren(self)




    def args(self):

        localctx = LudiiParser.ArgsContext(self, self._ctx, self.state)
        self.enterRule(localctx, 4, self.RULE_args)
        self._la = 0 # Token type
        try:
            self.enterOuterAlt(localctx, 1)
            self.state = 29
            self._errHandler.sync(self)
            _la = self._input.LA(1)
            while (((_la) & ~0x3f) == 0 and ((1 << _la) & 2114) != 0):
                self.state = 27
                self._errHandler.sync(self)
                la_ = self._interp.adaptivePredict(self._input,1,self._ctx)
                if la_ == 1:
                    self.state = 25
                    self.named_argument()
                    pass

                elif la_ == 2:
                    self.state = 26
                    self.argument()
                    pass


                self.state = 31
                self._errHandler.sync(self)
                _la = self._input.LA(1)

        except RecognitionException as re:
            localctx.exception = re
            self._errHandler.reportError(self, re)
            self._errHandler.recover(self, re)
        finally:
            self.exitRule()
        return localctx


    class Named_argumentContext(ParserRuleContext):
        __slots__ = 'parser'

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser

        def NAME(self):
            return self.getToken(LudiiParser.NAME, 0)

        def COLON(self):
            return self.getToken(LudiiParser.COLON, 0)

        def argument(self):
            return self.getTypedRuleContext(LudiiParser.ArgumentContext,0)


        def getRuleIndex(self):
            return LudiiParser.RULE_named_argument

        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterNamed_argument" ):
                listener.enterNamed_argument(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitNamed_argument" ):
                listener.exitNamed_argument(self)

        def accept(self, visitor:ParseTreeVisitor):
            if hasattr( visitor, "visitNamed_argument" ):
                return visitor.visitNamed_argument(self)
            else:
                return visitor.visitChildren(self)




    def named_argument(self):

        localctx = LudiiParser.Named_argumentContext(self, self._ctx, self.state)
        self.enterRule(localctx, 6, self.RULE_named_argument)
        try:
            self.enterOuterAlt(localctx, 1)
            self.state = 32
            self.match(LudiiParser.NAME)
            self.state = 33
            self.match(LudiiParser.COLON)
            self.state = 34
            self.argument()
        except RecognitionException as re:
            localctx.exception = re
            self._errHandler.reportError(self, re)
            self._errHandler.recover(self, re)
        finally:
            self.exitRule()
        return localctx


    class ArgumentContext(ParserRuleContext):
        __slots__ = 'parser'

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser

        def function(self):
            return self.getTypedRuleContext(LudiiParser.FunctionContext,0)


        def lst(self):
            return self.getTypedRuleContext(LudiiParser.LstContext,0)


        def NAME(self):
            return self.getToken(LudiiParser.NAME, 0)

        def getRuleIndex(self):
            return LudiiParser.RULE_argument

        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterArgument" ):
                listener.enterArgument(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitArgument" ):
                listener.exitArgument(self)

        def accept(self, visitor:ParseTreeVisitor):
            if hasattr( visitor, "visitArgument" ):
                return visitor.visitArgument(self)
            else:
                return visitor.visitChildren(self)




    def argument(self):

        localctx = LudiiParser.ArgumentContext(self, self._ctx, self.state)
        self.enterRule(localctx, 8, self.RULE_argument)
        try:
            self.state = 39
            self._errHandler.sync(self)
            token = self._input.LA(1)
            if token in [1]:
                self.enterOuterAlt(localctx, 1)
                self.state = 36
                self.function()
                pass
            elif token in [6]:
                self.enterOuterAlt(localctx, 2)
                self.state = 37
                self.lst()
                pass
            elif token in [11]:
                self.enterOuterAlt(localctx, 3)
                self.state = 38
                self.match(LudiiParser.NAME)
                pass
            else:
                raise NoViableAltException(self)

        except RecognitionException as re:
            localctx.exception = re
            self._errHandler.reportError(self, re)
            self._errHandler.recover(self, re)
        finally:
            self.exitRule()
        return localctx


    class LstContext(ParserRuleContext):
        __slots__ = 'parser'

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser

        def LBRACE(self):
            return self.getToken(LudiiParser.LBRACE, 0)

        def RBRACE(self):
            return self.getToken(LudiiParser.RBRACE, 0)

        def argument(self, i:int=None):
            if i is None:
                return self.getTypedRuleContexts(LudiiParser.ArgumentContext)
            else:
                return self.getTypedRuleContext(LudiiParser.ArgumentContext,i)


        def getRuleIndex(self):
            return LudiiParser.RULE_lst

        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterLst" ):
                listener.enterLst(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitLst" ):
                listener.exitLst(self)

        def accept(self, visitor:ParseTreeVisitor):
            if hasattr( visitor, "visitLst" ):
                return visitor.visitLst(self)
            else:
                return visitor.visitChildren(self)




    def lst(self):

        localctx = LudiiParser.LstContext(self, self._ctx, self.state)
        self.enterRule(localctx, 10, self.RULE_lst)
        self._la = 0 # Token type
        try:
            self.enterOuterAlt(localctx, 1)
            self.state = 41
            self.match(LudiiParser.LBRACE)
            self.state = 45
            self._errHandler.sync(self)
            _la = self._input.LA(1)
            while (((_la) & ~0x3f) == 0 and ((1 << _la) & 2114) != 0):
                self.state = 42
                self.argument()
                self.state = 47
                self._errHandler.sync(self)
                _la = self._input.LA(1)

            self.state = 48
            self.match(LudiiParser.RBRACE)
        except RecognitionException as re:
            localctx.exception = re
            self._errHandler.reportError(self, re)
            self._errHandler.recover(self, re)
        finally:
            self.exitRule()
        return localctx





