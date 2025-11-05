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


def get_all_nodes(tree, rule_index):
    """
    Recursively retrieves all nodes from a parse tree that match a specific rule index.

    :param tree: The root of the parse tree to search.
    :param rule_index: The rule index to match nodes against.
    :return: A list of nodes matching the specified rule index.
    """

    nodes = []
    for node in Trees.getChildren(tree):
        # check this node (the root) first
        if isinstance(node, TerminalNode):
            if node.symbol.type == rule_index:
                nodes.append(node)
        elif isinstance(node, ParserRuleContext):
            if node.getRuleIndex() == rule_index:
                nodes.append(node)
        nodes += get_all_nodes(node, rule_index)
    return nodes

def define_new_macro(tree, override, code) -> Macro:
    """
    Defines a new macro based on the provided parse tree.

    :param tree: The parse tree containing the macro definition.
    :param override: Whether to override an existing macro with the same name.
    :return: A Macro object representing the defined macro.
    :raises ValueError: If the macro name already exists and override is False.
    """
    macro_name = Trees.findAllTokenNodes(tree, Python3Parser.MACRO_NAME)[0].getText()[1:]  # Remove the leading '?'

    if override and macro_name in loaded_macros:
        logger.error(f"Macro {macro_name} is already defined. Overwriting the existing macro.")
        raise ValueError(f"Macro {macro_name} is already defined.")

    logger.debug(f"Defining new macro: {macro_name}")
    arg_nodes = get_all_nodes(tree, Python3Parser.RULE_macro_arg)
    args = {}
    args_order = []
    for arg_node in arg_nodes:
        arg_name = arg_node.atom_wildcard().getText()[1:]  # Remove the leading '?'
        arg_default = arg_node.getChild(2)
        args[arg_name] = arg_default
        args_order.append(arg_name)

    return Macro(macro_name, code, args, args_order)

def handle_new_transformation(tree: Python3Parser.File_inputContext):
    new_root: ParserRuleContext = tree.stmt(0).getChild(0)
    new_root.parent = None
    """end_ctx = TerminalMacroNode()
    end_ctx.parentCtx = new_root
    new_root.addChild(end_ctx)
    logger.debug(end_ctx)"""
    return new_root

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
