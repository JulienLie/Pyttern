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