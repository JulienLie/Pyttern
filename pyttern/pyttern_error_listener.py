"""
This module contains the Python3ErrorListener class.
"""

from typing import override

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


class PytternErrorListener(ErrorListener):
    def __init__(self):
        self.errors = []

    @override
    def syntaxError(self, recognizer, offendingSymbol, line, column, msg, e):
        self.errors.append({
            "message": msg,
            "line": line,
            "character": column,
            "severity": "error",
        })

    @override
    def reportAmbiguity(self, recognizer, dfa, startIndex, stopIndex, exact, ambigAlts, configs):
        pass

    @override
    def reportAttemptingFullContext(self, recognizer, dfa, startIndex, stopIndex, conflictingAlts, configs):
        pass

    @override
    def reportContextSensitivity(self, recognizer, dfa, startIndex, stopIndex, prediction, configs):
        pass