"""
This module contains the Python3ErrorListener class.
"""
from antlr4.error.ErrorListener import ErrorListener


class Python3ErrorListener(ErrorListener):
    """
    Python3ErrorListener class is responsible for handling syntax errors in the input pyttern file.
    """
    def __init__(self, input, source = ""):
        self.input = input
        self.source = source

    def syntaxError(self, recognizer, offendingSymbol, line, column, msg, e):
        """
        Syntax error handler.
        """
        raise PytternSyntaxException(line, column, offendingSymbol.text, msg, self.source)

class PytternSyntaxException(Exception):
    """PytternSyntaxError class."""
    def __init__(self, line, column, symbol, msg, source):
        self.line = line
        self.column = column
        self.symbol = symbol
        self.msg = msg
        self.source = source

    def __str__(self):
        return f"Syntax error in {self.source} at {self.line}:{self.column} ({self.symbol}) : {self.msg}"
