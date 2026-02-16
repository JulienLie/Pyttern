import io

from antlr4 import InputStream, CommonTokenStream
from antlr4.ParserRuleContext import ParserRuleContext
from antlr4.tree.Tree import TerminalNode
from antlr4.tree.Trees import Trees
from loguru import logger

from .Macro import Macro, loaded_macros
from .macro_visitor import Macro_Visitor
from ..antlr.python import Python3Parser
from ..antlr.python.Python3Lexer import Python3Lexer
from ..language_processors import Languages
from ..pyttern_error_listener import Python3ErrorListener
from ..pytternfsm.python.tree_pruner import TreePruner

def string_to_macro_tree(macro_string):
    logger.info("Generating macro tree")
    stream = InputStream(macro_string)
    lexer = Python3Lexer(stream)
    stream = CommonTokenStream(lexer)
    py_parser = Python3Parser(stream)

    error = io.StringIO()

    py_parser.removeErrorListeners()
    error_listener = Python3ErrorListener(error)
    py_parser.addErrorListener(error_listener)

    tree = py_parser.macro_input()

    pruned_tree = TreePruner().visit(tree)

    return pruned_tree

def parse_macro_from_string(code: str, language: Languages, override: bool=False):
    """
    Parses macros from a string and returns the last parsed macro.

    :param code: The string containing macro definitions.
    :param language: The programming language of the macros.
    :param override: Whether to override existing macros with the same name.
    :return: The list of parsed Macros.
    :raises ValueError: If the code format is invalid or no macro definition is found.
    """
    logger.trace("Parsing macro from string")

    macro_tree = string_to_macro_tree(code)
    macros = Macro_Visitor().visit(macro_tree)

    return macros

def parse_macro_from_file(file: str, language: Languages, override: bool=False):
    """
    Parses macros from a file and returns the last parsed macro.

    :param file: The file containing macro definitions.
    :param language: The programming language of the macros.
    :param override: Whether to override existing macros with the same name.
    :return: The list of parsed Macros.
    :raises ValueError: If the file format is invalid or no macro definition is found.
    """
    logger.debug(f"Parsing macro from file: {file}")

    with open(file, 'r', encoding="UTF-8") as f:
        code = f.read()
        return parse_macro_from_string(code, language, override)
