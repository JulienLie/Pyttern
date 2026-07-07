"""
This module contains the Python3ErrorListener class.
"""

import re
from antlr4.error.ErrorListener import ErrorListener
from loguru import logger


def clean_token(t: str) -> str:
    t = t.strip()
    if not t:
        return t
    # Strip quotes if they surround the token
    if (t.startswith("'") and t.endswith("'")) or (t.startswith('"') and t.endswith('"')):
        val = t[1:-1]
    else:
        val = t
        
    if val == r'\n' or val == '\n' or val == 'NEWLINE':
        return 'newline'
    if val == '<EOF>' or val == 'EOF':
        return 'end of file'
    if val == 'INDENT':
        return 'indentation'
    if val == 'DEDENT':
        return 'dedent'
    if val == 'NAME':
        return 'name/identifier'
    if val == 'WILDCARD':
        return 'wildcard ("?")'
    if val == 'SUB_PATTERN':
        return 'subpattern ("$")'
    if val == 'BALISE':
        return 'tag ("$#")'
    
    return f"'{val}'"


def format_alternatives(expected_set_str: str) -> str:
    s = expected_set_str.strip('{} ')
    parts = [p.strip() for p in s.split(',')]
    cleaned = []
    for p in parts:
        if p:
            cleaned.append(clean_token(p))
    # Deduplicate
    cleaned = list(dict.fromkeys(cleaned))
    if not cleaned:
        return "something else"
    if len(cleaned) == 1:
        return cleaned[0]
    elif len(cleaned) == 2:
        return f"{cleaned[0]} or {cleaned[1]}"
    else:
        return ", ".join(cleaned[:-1]) + f", or {cleaned[-1]}"


def improve_message(msg: str) -> str:
    msg = msg.strip()
    
    # 1. mismatched input '<offending>' expecting <expected>
    m1 = re.match(r"mismatched input\s+(.+?)\s+expecting\s+(.+)", msg)
    if m1:
        offending = clean_token(m1.group(1))
        expected = m1.group(2)
        if expected.startswith('{') and expected.endswith('}'):
            expected_formatted = format_alternatives(expected)
        else:
            expected_formatted = clean_token(expected)
        return f"expected {expected_formatted} but found {offending}"

    # 2. extraneous input '<offending>' expecting <expected>
    m2 = re.match(r"extraneous input\s+(.+?)\s+expecting\s+(.+)", msg)
    if m2:
        offending = clean_token(m2.group(1))
        expected = m2.group(2)
        if expected.startswith('{') and expected.endswith('}'):
            expected_formatted = format_alternatives(expected)
        else:
            expected_formatted = clean_token(expected)
        return f"unexpected {offending} (expected {expected_formatted})"

    # 3. missing <expected> at <offending>
    m3 = re.match(r"missing\s+(.+?)\s+at\s+(.+)", msg)
    if m3:
        expected = clean_token(m3.group(1))
        offending = clean_token(m3.group(2))
        return f"missing {expected} before {offending}"

    # 4. no viable alternative at input <offending>
    m4 = re.match(r"no viable alternative at input\s+(.+)", msg)
    if m4:
        offending = clean_token(m4.group(1))
        return f"invalid syntax at or near {offending}"

    # 5. token recognition error at: <text>
    m5 = re.match(r"token recognition error at:\s+(.+)", msg)
    if m5:
        text = m5.group(1).strip("'\"")
        return f"unexpected character '{text}'"

    return msg


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
        improved = improve_message(msg)
        logger.error(f"Syntax error: {improved}")
        raise PytternSyntaxException(max(0, line - 1), column, offendingSymbol.text, improved)


class PytternSyntaxException(Exception):
    """PytternSyntaxError class."""
    def __init__(self, line, column, symbol, msg):
        self.line = line
        self.column = column
        self.symbol = symbol
        self.msg = msg

    def __str__(self):
        return f"Syntax error at {self.line + 1}:{self.column} ({self.symbol}) : {self.msg}"


class PytternErrorListener(ErrorListener):
    def __init__(self, input):
        self.errors = []
        self.input = input

    def syntaxError(self, recognizer, offendingSymbol, line, column, msg, e):
        improved = improve_message(msg)
        error = {
            "message": improved,
            "line": max(0, line - 1),  # Convert 1-indexed to 0-indexed
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