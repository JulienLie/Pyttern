import io
from functools import lru_cache

from antlr4 import CommonTokenStream, InputStream
from loguru import logger

from .base_processor_interface import BaseProcessor
from ..Pyttern_listener import ConsolePytternListener
from ..antlr.python import Python3Parser
from ..antlr.python.Python3Lexer import Python3Lexer
from ..pyttern_error_listener import Python3ErrorListener, PytternErrorListener
from ..pytternfsm.python.python_to_pda import Python_to_PDA
from ..pytternfsm.python.tree_pruner import TreePruner


class PythonProcessor(BaseProcessor):

    @lru_cache(maxsize=128)
    def generate_tree_from_code(self, code):
        code = code.strip()
        code += "\n"
        stream = InputStream(code)
        return self.generate_tree_from_stream(stream)

    @lru_cache(maxsize=128)
    def generate_tree_from_stream(self, stream):
        logger.debug("Generating tree")
        lexer = Python3Lexer(stream)
        stream = CommonTokenStream(lexer)
        py_parser = Python3Parser(stream)

        error = io.StringIO()

        py_parser.removeErrorListeners()
        error_listener = Python3ErrorListener(error)
        py_parser.addErrorListener(error_listener)

        tree = py_parser.file_input()

        pruned_tree = TreePruner().visit(tree)

        return pruned_tree

    @lru_cache(maxsize=128)
    def generate_tree_from_file(self, file):
        with open(file, 'r', encoding="utf-8") as f:
            return self.generate_tree_from_code(f.read())

    @lru_cache(maxsize=128)
    def create_pda(self, pattern_tree):
        return Python_to_PDA().visit(pattern_tree)

    def create_listener(self):
        return ConsolePytternListener()

    def get_language_extensions(self):
        return ["py", "pyt", "pyh"]

    @staticmethod
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

        logger.debug("Parsing for debugging purpose")
        lexer = Python3Lexer(stream)
        stream = CommonTokenStream(lexer)
        py_parser = Python3Parser(stream)

        py_parser.removeErrorListeners()
        error_listener = PytternErrorListener()
        py_parser.addErrorListener(error_listener)
        logger.debug(f"Parsing done, error found: {error_listener.errors}")

        return error_listener.errors
    
    @staticmethod
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

        return []