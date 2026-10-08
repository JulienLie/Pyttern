"""Parser and editor support helpers for subpattern definitions."""

import io
from typing import Any

from antlr4 import CommonTokenStream, InputStream
from loguru import logger

from .SubPattern import BaseSubPattern
from .subpattern_visitor import SubPattern_Visitor
from ..antlr.python import Python3Parser
from ..antlr.python.Python3Lexer import Python3Lexer
from ..language_processors import Languages
from ..pyttern_error_listener import Python3ErrorListener
from ..pytternfsm.python.tree_pruner import TreePruner


def string_to_subpattern_tree(subpattern_string: str) -> Any:
    """Parse a raw subpattern string into a pruned ANTLR ParseTree.

    Args:
        subpattern_string: Raw subpattern code snippet.

    Returns:
        Any: Pruned ANTLR ParseTree root.
    """
    logger.info("Generating subpattern tree")
    stream = InputStream(subpattern_string)
    lexer = Python3Lexer(stream)
    stream = CommonTokenStream(lexer)
    py_parser = Python3Parser(stream)

    error = io.StringIO()

    py_parser.removeErrorListeners()
    error_listener = Python3ErrorListener(error)
    py_parser.addErrorListener(error_listener)

    tree = py_parser.subpattern_input()
    pruned_tree = TreePruner().visit(tree)

    return pruned_tree


def parse_subpattern_from_string(
    code: str, language: Languages, override: bool = True
) -> list[BaseSubPattern]:
    """Parse subpattern definitions from a source string.

    Args:
        code: String containing subpattern definitions.
        language: Target programming language enum.
        override: Whether to override existing registered subpatterns.

    Returns:
        list[BaseSubPattern]: List of parsed SubPattern objects.
    """
    logger.trace("Parsing subpattern from string")

    subpattern_tree = string_to_subpattern_tree(code)
    subpatterns = SubPattern_Visitor(override=override).visit(subpattern_tree)

    return subpatterns


import os

_subpattern_file_cache: dict[tuple[str, float, Languages, bool], list[BaseSubPattern]] = {}


def parse_subpattern_from_file(file: str, language: Languages, override: bool = True) -> list[BaseSubPattern]:
    """Parse subpatterns from a file on disk.

    Args:
        file: Path to the file containing subpattern definitions.
        language: Target programming language enum.
        override: Whether to override existing subpatterns with identical names.

    Returns:
        list[BaseSubPattern]: List of parsed subpattern objects.

    Raises:
        ValueError: If the file format is invalid or no subpattern is found.
    """
    logger.debug(f"Parsing subpattern from file: {file}")
    try:
        mtime = os.path.getmtime(file)
    except OSError:
        mtime = 0.0
    cache_key = (os.path.abspath(file), mtime, language, override)
    if cache_key in _subpattern_file_cache:
        subpatterns = _subpattern_file_cache[cache_key]
        if override:
            from .SubPattern import loaded_subpatterns
            for sp in subpatterns:
                loaded_subpatterns[sp.name] = sp
        return subpatterns

    with open(file, 'r', encoding="UTF-8") as f:
        code = f.read()
        res = parse_subpattern_from_string(code, language, override)
        _subpattern_file_cache[cache_key] = res
        return res


def parse_diagnostics(code: str) -> list[dict]:
    """Parse subpattern code and return syntax error diagnostic items for editors.

    Args:
        code: Subpattern source code string.

    Returns:
        list[dict]: List of diagnostic dictionaries with range, message, and severity.
    """
    from ..editor_support import run_diagnostics
    return run_diagnostics(code, entry_rule="subpattern_input")


def get_completions(code: str, line: int, character: int) -> list[dict]:
    """Return auto-completion suggestions at the given document position.

    Args:
        code: Subpattern source code string.
        line: 0-indexed line number in the document.
        character: 0-indexed column offset on the line.

    Returns:
        list[dict]: List of LSP-style completion item dictionaries.
    """
    from ..editor_support import get_editor_completions
    return get_editor_completions(code, line, character, mode="subpattern")
