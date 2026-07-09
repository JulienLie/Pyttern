import io

from antlr4 import InputStream, CommonTokenStream
from loguru import logger

from .SubPattern import BaseSubPattern
from .subpattern_visitor import SubPattern_Visitor
from ..antlr.python import Python3Parser
from ..antlr.python.Python3Lexer import Python3Lexer
from ..language_processors import Languages
from ..pyttern_error_listener import Python3ErrorListener, PytternErrorListener
from ..pytternfsm.python.tree_pruner import TreePruner

def string_to_subpattern_tree(subpattern_string):
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

def parse_subpattern_from_string(code: str, language: Languages, override: bool=False) -> list[BaseSubPattern]:
    """
    Parses subpatterns from a string and returns the last parsed subpattern.

    :param code: The string containing subpattern definitions.
    :param language: The programming language of the subpatterns.
    :param override: Whether to override existing subpatterns with the same name.
    :return: The list of parsed subpatterns.
    :raises ValueError: If the code format is invalid or no subpattern definition is found.
    """
    logger.trace("Parsing subpattern from string")

    subpattern_tree = string_to_subpattern_tree(code)
    subpatterns = SubPattern_Visitor().visit(subpattern_tree)

    return subpatterns

def parse_subpattern_from_file(file: str, language: Languages, override: bool=False) -> list[BaseSubPattern]:
    """
    Parses subpatterns from a file and returns the last parsed subpattern.

    :param file: The file containing subpattern definitions.
    :param language: The programming language of the subpatterns.
    :param override: Whether to override existing subpatterns with the same name.
    :return: The list of parsed subpatterns.
    :raises ValueError: If the file format is invalid or no subpattern definition is found.
    """
    logger.debug(f"Parsing subpattern from file: {file}")

    with open(file, 'r', encoding="UTF-8") as f:
        code = f.read()
        return parse_subpattern_from_string(code, language, override)


def parse_diagnostics(code: str) -> list[dict]:
    """
    * **Arguments:**
    - `code` (`str`): The full text content of the active document.
    * **Return Value:**
    - A list of diagnostic dictionaries, representing parsing errors, syntax warnings, or validation issues.
    - Return an empty list `[]` if no errors are found.

    #### Diagnostic Dictionary Format:
    Each dictionary in the returned list should have the following fields:

    | Field Name | Type | Description |
    | :--- | :--- | :--- |
    | `message` | `str` | The error description or description of the issue. |
    | `line` | `int` | **0-indexed** starting line number of the diagnostic. |
    | `character` | `int` | **0-indexed** starting character offset of the diagnostic on that line. |
    | `severity` | `str` | The severity of the diagnostic. Must be one of: `'error'`, `'warning'`, `'info'`, `'hint'`. |
    | `end_line` | `int` | *(Optional)* **0-indexed** ending line number. Defaults to `line` if omitted. |
    | `end_character`| `int` | *(Optional)* **0-indexed** ending character offset. Defaults to `character + 1` if omitted. |
    """
    code = code.strip() + "\n"
    stream = InputStream(code)

    logger.debug(f"Parsing subpattern {code} for debugging purpose")
    lexer = Python3Lexer(stream)
    stream = CommonTokenStream(lexer)
    py_parser = Python3Parser(stream)

    error = io.StringIO()

    py_parser.removeErrorListeners()
    error_listener = PytternErrorListener(error)
    py_parser.addErrorListener(error_listener)

    py_parser.subpattern_input()

    logger.debug(f"Parsing done, error found: {error_listener.errors}")

    return error_listener.errors


def get_completions(code: str, line: int, character: int) -> list[dict]:
    """
    * **Arguments:**
    - `code` (`str`): The full text content of the active document.
    - `line` (`int`): The **0-indexed** line number where the cursor is currently positioned.
    - `character` (`int`): The **0-indexed** character offset on that line where the cursor is positioned.
    * **Return Value:**
    - A list of completion suggestion dictionaries.
    - Return `[]` if no suggestions are available.

    #### Completion Dictionary Format:
    Each dictionary in the returned list should have the following fields:

    | Field Name | Type | Description |
    | :--- | :--- | :--- |
    | `label` | `str` | The text proposal to display in the autocomplete dropdown and insert when selected. |
    | `kind` | `str` | The type of item. Must be one of: `'keyword'`, `'variable'`, `'function'`, `'text'`, `'constant'`. |
    | `detail` | `str` | *(Optional)* A short description shown next to the label (e.g. `wildcard operator`). |
    | `documentation`| `str` | *(Optional)* Detailed documentation shown in the expandable info card (can be markdown). |
    """
    import re

    lines = code.split('\n')
    if line < 0 or line >= len(lines):
        return []

    current_line = lines[line]
    if character < 0 or character > len(current_line):
        return []

    prefix_line = current_line[:character]

    # Extract the prefix matching word characters, or wildcard starts (? or $)
    match = re.search(r'(\?[:*<{,a-zA-Z0-9$]*|\$[&|!#a-zA-Z0-9]*|[a-zA-Z_]\w*)$', prefix_line)
    prefix = match.group(1) if match else ""

    # Pyttern-specific wildcard patterns
    pyttern_wildcards = [
        {
            "label": "?",
            "kind": "keyword",
            "detail": "simple wildcard",
            "documentation": "Matches exactly 1 element (variable, expression, statement, etc.)."
        },
        {
            "label": "?name",
            "kind": "keyword",
            "detail": "named wildcard",
            "documentation": "Matches 1 element and binds it to `name`."
        },
        {
            "label": "?*",
            "kind": "keyword",
            "detail": "sequence wildcard",
            "documentation": "Matches zero or more elements."
        },
        {
            "label": "?:",
            "kind": "keyword",
            "detail": "body wildcard",
            "documentation": "Matches a node that has a block/body (e.g., `if`, `for`)."
        },
        {
            "label": "?:*",
            "kind": "keyword",
            "detail": "deep body wildcard",
            "documentation": "Matches a body at any level of indentation."
        },
        {
            "label": "?<...>",
            "kind": "keyword",
            "detail": "contains wildcard",
            "documentation": "Matches if the inner pattern is found anywhere within the node."
        },
        {
            "label": "?$(...)",
            "kind": "keyword",
            "detail": "sub-pattern call",
            "documentation": "Calls a sub-pattern defined elsewhere."
        },
        {
            "label": "?{n, m}",
            "kind": "keyword",
            "detail": "range wildcard",
            "documentation": "Matches between `n` and `m` elements in a sequence."
        },
    ]

    # Sub-pattern default implementations and headers
    pyttern_subpatterns = [
        {
            "label": "$&SubPatternName(?arg)\n\n$# TransformationName\n?arg",
            "kind": "keyword",
            "detail": "default AND sub-pattern implementation",
            "documentation": "A default skeleton for an AND sub-pattern where all transformations must match."
        },
        {
            "label": "$|SubPatternName(?arg)\n\n$# TransformationName\n?arg",
            "kind": "keyword",
            "detail": "default OR sub-pattern implementation",
            "documentation": "A default skeleton for an OR sub-pattern where at least one transformation must match."
        },
        {
            "label": "$!SubPatternName(?arg)\n\n$# TransformationName\n?arg",
            "kind": "keyword",
            "detail": "default NOT sub-pattern implementation",
            "documentation": "A default skeleton for a NOT sub-pattern where no transformations should match."
        },
        {
            "label": "$#",
            "kind": "keyword",
            "detail": "transformation label",
            "documentation": "A label for a specific transformation branch/version of the pattern logic."
        },
    ]

    # Python keywords
    python_keywords_list = [
        "False", "None", "True", "and", "as", "assert", "async", "await", "break", 
        "class", "continue", "def", "del", "elif", "else", "except", "finally", 
        "for", "from", "global", "if", "import", "in", "is", "lambda", "nonlocal", 
        "not", "or", "pass", "raise", "return", "try", "while", "with", "yield"
    ]

    python_keywords = [
        {
            "label": kw,
            "kind": "keyword" if kw not in ("True", "False", "None") else "constant",
            "detail": "python keyword" if kw not in ("True", "False", "None") else "python constant",
            "documentation": f"Standard Python {'constant' if kw in ('True', 'False', 'None') else 'keyword'}."
        }
        for kw in python_keywords_list
    ]

    # Extract local definitions
    local_functions = set()
    local_variables = set()

    for line_str in lines:
        # Check function definitions
        func_match = re.search(r'\bdef\s+([a-zA-Z_]\w*)', line_str)
        if func_match:
            local_functions.add(func_match.group(1))
            continue
        
        # Check class definitions
        class_match = re.search(r'\bclass\s+([a-zA-Z_]\w*)', line_str)
        if class_match:
            local_variables.add(class_match.group(1))
            continue

        # Check variable assignments
        var_matches = re.findall(r'\b([a-zA-Z_]\w*)\s*=(?!=)', line_str)
        for var in var_matches:
            if var not in python_keywords_list:
                local_variables.add(var)

    local_suggestions = []
    for func in sorted(local_functions):
        local_suggestions.append({
            "label": func,
            "kind": "function",
            "detail": "local function",
            "documentation": f"Function `{func}` defined in the current document."
        })
    for var in sorted(local_variables):
        local_suggestions.append({
            "label": var,
            "kind": "variable",
            "detail": "local variable/class",
            "documentation": f"Variable/class `{var}` defined in the current document."
        })

    # Combine all candidates
    all_candidates = pyttern_wildcards + pyttern_subpatterns + python_keywords + local_suggestions

    # Filter candidates based on prefix
    prefix_lower = prefix.lower()
    filtered = []
    for item in all_candidates:
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