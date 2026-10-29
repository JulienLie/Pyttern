"""
Editor support module for Pyttern.

Provides reusable auto-completion and syntax diagnostic services for
pattern files and subpattern definitions.
"""

from __future__ import annotations

import io
import re
from typing import Any
from antlr4 import InputStream, CommonTokenStream
from loguru import logger

from .antlr.python import Python3Parser
from .antlr.python.Python3Lexer import Python3Lexer
from .pyttern_error_listener import PytternErrorListener

# Pyttern-specific wildcard completion items
PYTTERN_WILDCARDS: list[dict[str, str]] = [
    {
        "label": "?",
        "kind": "keyword",
        "detail": "simple wildcard",
        "documentation": "Matches exactly 1 element (variable, expression, statement, etc.).",
    },
    {
        "label": "?name",
        "kind": "keyword",
        "detail": "named wildcard",
        "documentation": "Matches 1 element and binds it to `name`.",
    },
    {
        "label": "?*",
        "kind": "keyword",
        "detail": "sequence wildcard",
        "documentation": "Matches zero or more elements.",
    },
    {
        "label": "?:",
        "kind": "keyword",
        "detail": "body wildcard",
        "documentation": "Matches a node that has a block/body (e.g., `if`, `for`).",
    },
    {
        "label": "?:*",
        "kind": "keyword",
        "detail": "deep body wildcard",
        "documentation": "Matches a body at any level of indentation.",
    },
    {
        "label": "?<...>",
        "kind": "keyword",
        "detail": "contains wildcard",
        "documentation": "Matches if the inner pattern is found anywhere within the node.",
    },
    {
        "label": "?$(...)",
        "kind": "keyword",
        "detail": "sub-pattern call",
        "documentation": "Calls a sub-pattern defined elsewhere.",
    },
    {
        "label": "?{n, m}",
        "kind": "keyword",
        "detail": "range wildcard",
        "documentation": "Matches between `n` and `m` elements in a sequence.",
    },
]

# Subpattern inline call structures for pattern code
PATTERN_SUBPATTERNS: list[dict[str, str]] = [
    {
        "label": "$&Name(args)",
        "kind": "keyword",
        "detail": "AND sub-pattern",
        "documentation": "AND operator. All internal transformations must match.",
    },
    {
        "label": "$|Name(args)",
        "kind": "keyword",
        "detail": "OR sub-pattern",
        "documentation": "OR operator. At least one internal transformation must match.",
    },
    {
        "label": "$#",
        "kind": "keyword",
        "detail": "transformation label",
        "documentation": "A label for a specific transformation branch/version of the pattern logic.",
    },
]

# Subpattern definition skeletons for subpattern files (.myt)
SUBPATTERN_SKELETONS: list[dict[str, str]] = [
    {
        "label": "$&SubPatternName(?arg)\n\n$# TransformationName\n?arg",
        "kind": "keyword",
        "detail": "default AND sub-pattern implementation",
        "documentation": "A default skeleton for an AND sub-pattern where all transformations must match.",
    },
    {
        "label": "$|SubPatternName(?arg)\n\n$# TransformationName\n?arg",
        "kind": "keyword",
        "detail": "default OR sub-pattern implementation",
        "documentation": "A default skeleton for an OR sub-pattern where at least one transformation must match.",
    },
    {
        "label": "$!SubPatternName(?arg)\n\n$# TransformationName\n?arg",
        "kind": "keyword",
        "detail": "default NOT sub-pattern implementation",
        "documentation": "A default skeleton for a NOT sub-pattern where no transformations should match.",
    },
    {
        "label": "$#",
        "kind": "keyword",
        "detail": "transformation label",
        "documentation": "A label for a specific transformation branch/version of the pattern logic.",
    },
]

# Pyttern API keywords
PYTTERN_API: list[dict[str, str]] = [
    {
        "label": "match_files",
        "kind": "function",
        "detail": "pyttern.match_files",
        "documentation": "Matches patterns against files.\n\n`match_files(pattern_file, code_file, lang='python')`",
    },
    {
        "label": "PytternMatcher",
        "kind": "class",
        "detail": "pyttern.PytternMatcher",
        "documentation": "Main matcher class for matching trees.\n\n`matcher = PytternMatcher(match_details=True)`",
    },
    {
        "label": "get_processor",
        "kind": "function",
        "detail": "pyttern.language_processors.get_processor",
        "documentation": "Retrieves the processor instance for a specific language (e.g. 'python', 'java').",
    },
    {
        "label": "parse_diagnostics",
        "kind": "function",
        "detail": "PythonProcessor.parse_diagnostics",
        "documentation": "Parses code and returns a list of diagnostic dictionaries for syntax errors.",
    },
    {
        "label": "generate_tree_from_code",
        "kind": "function",
        "detail": "PythonProcessor.generate_tree_from_code",
        "documentation": "Generates a pruned parse tree from Python/Pyttern code.",
    },
]

PYTHON_KEYWORDS_LIST: list[str] = [
    "False", "None", "True", "and", "as", "assert", "async", "await", "break",
    "class", "continue", "def", "del", "elif", "else", "except", "finally",
    "for", "from", "global", "if", "import", "in", "is", "lambda", "nonlocal",
    "not", "or", "pass", "raise", "return", "try", "while", "with", "yield"
]

PYTHON_KEYWORDS: list[dict[str, str]] = [
    {
        "label": kw,
        "kind": "keyword" if kw not in ("True", "False", "None") else "constant",
        "detail": "python keyword" if kw not in ("True", "False", "None") else "python constant",
        "documentation": f"Standard Python {'constant' if kw in ('True', 'False', 'None') else 'keyword'}.",
    }
    for kw in PYTHON_KEYWORDS_LIST
]


def extract_local_definitions(lines: list[str]) -> list[dict[str, str]]:
    """Extract local function and variable names from document lines."""
    local_functions: set[str] = set()
    local_variables: set[str] = set()

    for line_str in lines:
        func_match = re.search(r'\bdef\s+([a-zA-Z_]\w*)', line_str)
        if func_match:
            local_functions.add(func_match.group(1))
            continue

        class_match = re.search(r'\bclass\s+([a-zA-Z_]\w*)', line_str)
        if class_match:
            local_variables.add(class_match.group(1))
            continue

        var_matches = re.findall(r'\b([a-zA-Z_]\w*)\s*=(?!=)', line_str)
        for var in var_matches:
            if var not in PYTHON_KEYWORDS_LIST:
                local_variables.add(var)

    suggestions: list[dict[str, str]] = []
    for func in sorted(local_functions):
        suggestions.append({
            "label": func,
            "kind": "function",
            "detail": "local function",
            "documentation": f"Function `{func}` defined in the current document.",
        })
    for var in sorted(local_variables):
        suggestions.append({
            "label": var,
            "kind": "variable",
            "detail": "local variable/class",
            "documentation": f"Variable/class `{var}` defined in the current document.",
        })

    return suggestions


def get_editor_completions(
    code: str,
    line: int,
    character: int,
    mode: str = "pattern",
) -> list[dict[str, str]]:
    """
    Computes code completion items at a specific cursor position.

    :param code: Full text of document.
    :param line: 0-indexed line.
    :param character: 0-indexed character offset.
    :param mode: 'pattern' for pattern files or 'subpattern' for subpattern files.
    :return: Filtered list of completion items.
    """
    lines = code.split('\n')
    if line < 0 or line >= len(lines):
        return []

    current_line = lines[line]
    if character < 0 or character > len(current_line):
        return []

    prefix_line = current_line[:character]
    if mode == "subpattern":
        match = re.search(r'(\?[:*<{,a-zA-Z0-9$]*|\$[&|!#a-zA-Z0-9]*|[a-zA-Z_]\w*)$', prefix_line)
    else:
        match = re.search(r'(\?[:*<{,a-zA-Z0-9$]*|\$[&|#a-zA-Z0-9]*|[a-zA-Z_]\w*)$', prefix_line)
    prefix = match.group(1) if match else ""

    local_suggestions = extract_local_definitions(lines)

    if mode == "subpattern":
        candidates = PYTTERN_WILDCARDS + SUBPATTERN_SKELETONS + PYTHON_KEYWORDS + local_suggestions
    else:
        candidates = PYTTERN_WILDCARDS + PATTERN_SUBPATTERNS + PYTTERN_API + PYTHON_KEYWORDS + local_suggestions

    prefix_lower = prefix.lower()
    filtered: list[dict[str, str]] = []
    for item in candidates:
        label = item["label"]
        if label.startswith('?') or label.startswith('$'):
            if label.startswith(prefix):
                filtered.append(item)
        else:
            if prefix.startswith('?') or prefix.startswith('$'):
                continue
            if label.lower().startswith(prefix_lower):
                filtered.append(item)

    return filtered


def run_diagnostics(code: str, entry_rule: str = "file_input") -> list[dict[str, Any]]:
    """
    Runs ANTLR syntax diagnostics on the given code.

    :param code: Source code.
    :param entry_rule: ANTLR start rule ('file_input' or 'subpattern_input').
    :return: List of diagnostic dictionaries.
    """
    code_normalized = code.strip() + "\n"
    stream = InputStream(code_normalized)

    logger.debug(f"Parsing code for diagnostics (rule={entry_rule})")
    lexer = Python3Lexer(stream)
    token_stream = CommonTokenStream(lexer)
    parser = Python3Parser(token_stream)

    error_buf = io.StringIO()
    parser.removeErrorListeners()
    error_listener = PytternErrorListener(error_buf)
    parser.addErrorListener(error_listener)

    if entry_rule == "subpattern_input":
        parser.subpattern_input()
    else:
        parser.file_input()

    logger.debug(f"Diagnostics complete, errors found: {len(error_listener.errors)}")
    return error_listener.errors
