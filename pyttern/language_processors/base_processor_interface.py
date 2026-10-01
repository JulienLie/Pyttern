"""
Base language processor interface for Pyttern.
"""

from antlr4 import FileStream, InputStream
from ..Pyttern_listener import ConsolePytternListener


class BaseProcessor:
    """
    Abstract singleton base processor handling parsing, tree generation,
    and PDA compilation for a specific programming language.
    """

    def __new__(cls):
        if not hasattr(cls, 'instance'):
            cls.instance = super(BaseProcessor, cls).__new__(cls)
        return cls.instance

    def generate_tree_from_code(self, code: str):
        """
        Generates a pruned parse tree from a source code string.

        Args:
            code (str): Source code or pattern string.

        Returns:
            ParserRuleContext: The root node of the pruned parse tree.
        """
        code = code.strip() + "\n"
        stream = InputStream(code)
        return self.generate_tree_from_stream(stream)

    def generate_tree_from_stream(self, stream: InputStream):
        """
        Parses an ANTLR input stream and returns a pruned parse tree.

        Args:
            stream (InputStream): The ANTLR input stream.

        Returns:
            ParserRuleContext: The pruned parse tree.

        Raises:
            NotImplementedError: Subclasses must implement this method.
        """
        raise NotImplementedError

    def generate_tree_from_file(self, file: str):
        """
        Generates a pruned parse tree from a source file.

        Args:
            file (str): Path to the source file.

        Returns:
            ParserRuleContext: The pruned parse tree.
        """
        file_input = FileStream(file, encoding="utf-8")
        return self.generate_tree_from_stream(file_input)

    def create_pda(self, pattern_tree):
        """
        Compiles a pattern parse tree into a Pushdown Automaton (PDA).

        Args:
            pattern_tree (ParserRuleContext): The root of the pattern parse tree.

        Returns:
            dict[str, PDA]: A mapping of automaton names to PDA instances.

        Raises:
            NotImplementedError: Subclasses must implement this method.
        """
        raise NotImplementedError

    def create_matcher(self, fsm, code_tree):
        """
        Creates a Matcher instance for the given compiled PDA and code tree.

        Args:
            fsm: Compiled pushdown automaton.
            code_tree: Target code parse tree.

        Raises:
            NotImplementedError: Subclasses must implement this method.
        """
        raise NotImplementedError

    def create_listener(self):
        """
        Creates a default event listener for pattern matching execution.

        Returns:
            ConsolePytternListener: The listener instance.
        """
        return ConsolePytternListener()

    def get_language_extensions(self) -> list[str]:
        """
        Returns the list of file extensions supported by this language processor.

        Returns:
            list[str]: Supported file extensions (without leading dot).

        Raises:
            NotImplementedError: Subclasses must implement this method.
        """
        raise NotImplementedError
