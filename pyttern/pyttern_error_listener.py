"""ANTLR error listeners and syntax error formatting utilities."""

import re
from typing import Any

from antlr4.error.ErrorListener import ErrorListener
from loguru import logger


def clean_token(t: str) -> str:
    """Format and clean an ANTLR raw token symbol into human-readable text.

    Args:
        t: Raw ANTLR token name or text.

    Returns:
        str: Human-readable token description (e.g. 'newline', 'wildcard ("?")').
    """
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
    """Format a comma-delimited set of expected tokens into natural prose.

    Args:
        expected_set_str: Expected tokens string formatted like '{TOKEN1, TOKEN2}'.

    Returns:
        str: Readable alternatives string (e.g. 'foo or bar', 'foo, bar, or baz').
    """
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
    """Translate cryptic ANTLR syntax error messages into helpful diagnostic text.

    Args:
        msg: Raw ANTLR error message.

    Returns:
        str: Improved user-facing syntax error message.
    """
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
    """ANTLR error listener that raises PytternSyntaxException immediately upon syntax error."""

    def __init__(self, input: Any) -> None:
        """Initialize the listener.

        Args:
            input: Underlying stream or buffer.
        """
        self.input = input

    def syntaxError(
        self,
        recognizer: Any,
        offendingSymbol: Any,
        line: int,
        column: int,
        msg: str,
        e: Any,
    ) -> None:
        """Format the error message and raise a PytternSyntaxException.

        Args:
            recognizer: Parser or lexer instance.
            offendingSymbol: Offending token node.
            line: 1-indexed line number.
            column: 0-indexed column offset.
            msg: Raw ANTLR error message.
            e: Associated RecognitionException.

        Raises:
            PytternSyntaxException: Always raised to stop parsing.
        """
        improved = improve_message(msg)
        logger.error(f"Syntax error: {improved}")
        raise PytternSyntaxException(max(0, line - 1), column, offendingSymbol.text, improved)


class PytternSyntaxException(Exception):
    """Exception raised when pattern syntax cannot be parsed.

    Attributes:
        line: 0-indexed line number.
        column: 0-indexed column offset.
        symbol: The offending token symbol text.
        msg: Human-readable error description.
    """

    def __init__(self, line: int, column: int, symbol: str, msg: str) -> None:
        """Initialize the syntax exception.

        Args:
            line: 0-indexed line number.
            column: 0-indexed column offset.
            symbol: Text of the offending symbol.
            msg: Formatted error message.
        """
        self.line = line
        self.column = column
        self.symbol = symbol
        self.msg = msg

    def __str__(self) -> str:
        """Return formatted 1-indexed error string.

        Returns:
            str: Error description including line and column.
        """
        return f"Syntax error at {self.line + 1}:{self.column} ({self.symbol}) : {self.msg}"


class PytternErrorListener(ErrorListener):
    """Error listener that accumulates diagnostic error dictionaries without aborting."""

    def __init__(self, input: Any) -> None:
        """Initialize the diagnostic error listener.

        Args:
            input: Target input stream.
        """
        self.errors: list[dict[str, Any]] = []
        self.input = input

    def syntaxError(
        self,
        recognizer: Any,
        offendingSymbol: Any,
        line: int,
        column: int,
        msg: str,
        e: Any,
    ) -> None:
        """Record a diagnostic syntax error dictionary.

        Args:
            recognizer: Parser or lexer instance.
            offendingSymbol: Offending token.
            line: 1-indexed line number.
            column: 0-indexed column offset.
            msg: Raw error message.
            e: Recognition exception.
        """
        improved = improve_message(msg)
        error = {
            "message": improved,
            "line": max(0, line - 1),  # Convert 1-indexed to 0-indexed
            "character": column,
            "severity": "error",
        }
        logger.error(f"New syntax error: {error}")
        self.errors.append(error)

    def reportAmbiguity(self, recognizer: Any, dfa: Any, startIndex: int, stopIndex: int, exact: bool, ambigAlts: Any, configs: Any) -> None:
        """Report ambiguity event. No-op."""
        pass

    def reportAttemptingFullContext(self, recognizer: Any, dfa: Any, startIndex: int, stopIndex: int, conflictingAlts: Any, configs: Any) -> None:
        """Report full context attempt. No-op."""
        pass

    def reportContextSensitivity(self, recognizer: Any, dfa: Any, startIndex: int, stopIndex: int, prediction: int, configs: Any) -> None:
        """Report context sensitivity event. No-op."""
        pass