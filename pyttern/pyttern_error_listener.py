"""
This module contains the Python3ErrorListener class.
"""

from antlr4.error.ErrorListener import ErrorListener


class Python3ErrorListener(ErrorListener):
    """
    Python3ErrorListener class is responsible for handling syntax errors in the input pyttern file.
    """
    def __init__(self, input):
       self.input = input

    def syntaxError(self, recognizer, offendingSymbol, line, column, msg, e):
        """
        Syntax error handler.
        """
        raise PytternSyntaxException(line, column, offendingSymbol.text, msg)

class PytternSyntaxException(Exception):
    """PytternSyntaxError class."""
    def __init__(self, line, column, symbol, msg):
        self.line = line
        self.column = column
        self.symbol = symbol
        self.msg = msg

    def __str__(self):
        return f"Syntax error at {self.line}:{self.column} ({self.symbol}) : {self.msg}"