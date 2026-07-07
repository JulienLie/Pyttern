"""
This module contains the Python3ErrorListener class.
"""

from antlr4.error.ErrorListener import ErrorListener
from loguru import logger


class Python3ErrorListener(ErrorListener):
    def __init__(self, input):
        self.input = input

    """
    Python3ErrorListener class is responsible for handling syntax errors in the input pyttern file.
    """
    def syntaxError(self, recognizer, offendingSymbol, line, column, msg, e):
        """
        Syntax error handler.
        """
        logger.error(f"Syntax error: {msg}")
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


class PytternErrorListener(ErrorListener):
    def __init__(self, input):
        self.errors = []
        self.input = input

    def syntaxError(self, recognizer, offendingSymbol, line, column, msg, e):
        error = {
            "message": msg,
            "line": line,
            "character": column,
            "severity": "error",
        }
        logger.error(f"New syntax error: {error}")
        self.errors.append(error)

    def reportAmbiguity(self, recognizer, dfa, startIndex, stopIndex, exact, ambigAlts, configs):
        pass

    def reportAttemptingFullContext(self, recognizer, dfa, startIndex, stopIndex, conflictingAlts, configs):
        pass

    def reportContextSensitivity(self, recognizer, dfa, startIndex, stopIndex, prediction, configs):
        pass