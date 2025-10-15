from antlr4.ParserRuleContext import ParserRuleContext
from antlr4.tree.Tree import TerminalNode
from antlr4.tree.Trees import Trees
from loguru import logger

from .Macro import Macro, loaded_macros
from ..antlr.python import Python3Parser
from ..language_processors import get_processor, Languages


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


def parse_macro_from_string(code: str, language: Languages, override: bool=False):
    """
    Parses macros from a string and returns the last parsed macro.

    :param code: The string containing macro definitions.
    :param language: The programming language of the macros.
    :param override: Whether to override existing macros with the same name.
    :return: The list of parsed Macros.
    :raises ValueError: If the code format is invalid or no macro definition is found.
    """
    logger.debug("Parsing macro from string")

    parsed_macros = []

    macros = code.split("?#")
    if len(macros) < 2:
        raise ValueError("Invalid macro format. Expected at least one macro definition.")

    processor = get_processor(language)
    current_macro = None
    for macro in macros:
        if len(macro.strip()) == 0:
            continue

        logger.debug(macro.strip())
        macro_name = macro.split("\n")[0].strip()
        macro_code = "\n".join(macro.split("\n")[1:]).strip()

        tree = processor.generate_tree_from_code(macro_code)

        if macro_name == "DEFINE":
            if current_macro is not None:
                parsed_macros.append(current_macro)
                logger.debug(f"Completed macro: {current_macro.name} with tranformations: "
                             f"{current_macro.transformations.keys()}")
            current_macro = define_new_macro(tree, override, code)
            logger.debug(f"Defined macro: {current_macro.name} with args: {current_macro.args}")
            continue

        if current_macro is None:
            raise ValueError("No macro definition found in the string.")

        pruned_tree = handle_new_transformation(tree)
        logger.debug(f"Pruned tree for macro {macro_name}: {pruned_tree.toStringTree()}")
        macro_pda = processor.create_pda(pruned_tree)
        current_macro.add_transformation(macro_name, macro_pda)

    if current_macro is not None:
        parsed_macros.append(current_macro)
        logger.debug(f"Completed macro: {current_macro.name} with transformations: "
                     f"{current_macro.transformations.keys()}")

    return parsed_macros

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
