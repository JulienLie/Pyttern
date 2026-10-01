"""
Python language processor for Pyttern.
"""

import io

from antlr4 import CommonTokenStream, InputStream
from loguru import logger

from .base_processor_interface import BaseProcessor
from ..Pyttern_listener import ConsolePytternListener
from ..antlr.python import Python3Parser
from ..antlr.python.Python3Lexer import Python3Lexer
from ..pyttern_error_listener import Python3ErrorListener
from ..pytternfsm.python.python_to_pda import Python_to_PDA
from ..pytternfsm.python.tree_pruner import TreePruner


class PythonProcessor(BaseProcessor):
    """
    Language processor implementation for Python source and Pyttern pattern files.
    """

    def generate_tree_from_code(self, code: str):
        """
        Parses Python code or pattern string into a pruned parse tree.

        Args:
            code (str): Source code or pattern content.

        Returns:
            ParserRuleContext: The pruned ANTLR file input parse tree.
        """
        code = code.strip() + "\n"
        stream = InputStream(code)
        return self.generate_tree_from_stream(stream)

    def generate_tree_from_stream(self, stream: InputStream):
        """
        Parses an ANTLR stream of Python code and returns a pruned parse tree.

        Args:
            stream (InputStream): The ANTLR input stream.

        Returns:
            ParserRuleContext: The pruned ANTLR parse tree.
        """
        logger.debug("Generating Python parse tree")
        lexer = Python3Lexer(stream)
        token_stream = CommonTokenStream(lexer)
        py_parser = Python3Parser(token_stream)

        input_err = io.StringIO()
        py_parser.removeErrorListeners()
        error_listener = Python3ErrorListener(input_err)
        py_parser.addErrorListener(error_listener)

        tree = py_parser.file_input()
        pruned_tree = TreePruner().visit(tree)
        return pruned_tree

    def generate_tree_from_file(self, file: str):
        """
        Reads a Python or Pyttern file and returns its pruned parse tree.

        Args:
            file (str): Path to the source file.

        Returns:
            ParserRuleContext: The pruned parse tree.
        """
        with open(file, 'r', encoding="utf-8") as f:
            return self.generate_tree_from_code(f.read())

    def create_pda(self, pattern_tree):
        """
        Compiles a Python pattern parse tree into a Pushdown Automaton.

        Args:
            pattern_tree (ParserRuleContext): The pattern parse tree.

        Returns:
            dict[str, PDA]: A dictionary containing compiled PDA instances.
        """
        return Python_to_PDA().visit(pattern_tree)

    def create_listener(self):
        """
        Creates a console listener for Python pattern matching.

        Returns:
            ConsolePytternListener: The listener instance.
        """
        return ConsolePytternListener()

    def get_language_extensions(self) -> list[str]:
        """
        Returns supported file extensions for Python and Pyttern files.

        Returns:
            list[str]: Supported extensions ['py', 'pyt', 'pyh'].
        """
        return ["py", "pyt", "pyh"]

    @staticmethod
    def parse_diagnostics(code: str) -> list[dict]:
        """
        Parses code and returns a list of diagnostic dictionaries for syntax errors.

        Args:
            code (str): Source code or pattern text to inspect.

        Returns:
            list[dict]: List of diagnostic error objects with line, column, and message.
        """
        from ..editor_support import run_diagnostics
        return run_diagnostics(code, entry_rule="file_input")

    @staticmethod
    def get_completions(code: str, line: int, character: int) -> list[dict]:
        """
        Returns auto-completion suggestion dictionaries at the given document position.

        Args:
            code (str): The document text.
            line (int): 0-indexed line number.
            character (int): 0-indexed character offset on that line.

        Returns:
            list[dict]: Completion items matching the prefix.
        """
        from ..editor_support import get_editor_completions
        return get_editor_completions(code, line, character, mode="pattern")