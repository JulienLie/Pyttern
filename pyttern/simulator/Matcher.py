import dataclasses
import math
import re

from antlr4.ParserRuleContext import ParserRuleContext
from antlr4.tree.Tree import TerminalNode
from loguru import logger

from .pda.PDA import PDA
from .pda.navigation_direction import NavigationDirection
from ..macro.Macro import loaded_macros
from ..pytternfsm.python.match_set import MatchSet, Match


class Matcher:
    def __init__(self, pda, parse_tree):
        self.pda = pda
        self.parse_tree = parse_tree
        self.match_set = MatchSet()
        self.configurations = []
        self.n_step = 0
        self._listeners = []

    def add_listener(self, listener):
        self._listeners.append(listener)

    def remove_listener(self, listener):
        self._listeners.remove(listener)

    def remove_all_listeners(self):
        self._listeners.clear()

    @staticmethod
    def match(pda: PDA, parse_tree: ParserRuleContext, stop_at_first=False, bindings=None) -> MatchSet:
        """
        Matches a given parse tree against a Pushdown Automaton (PDA) and returns the resulting matches.

        :param pda: The Pushdown Automaton (PDA) to use for matching.
        :param parse_tree: The parse tree to match against the PDA.
        :param stop_at_first: A boolean indicating whether to stop after the first match is found. Defaults to False.
        :param bindings: An optional dictionary of initial variable bindings. Defaults to None.
        :return: A MatchSet object containing the results of the matching process.
        """

        matcher = Matcher(pda, parse_tree)
        logger.debug("Starting match")
        matcher.start(bindings)
        while len(matcher.configurations) > 0:
            matcher.step()
            if stop_at_first and matcher.match_set.count() > 0:
                break
        logger.debug(f"Match finished with {matcher.match_set.count()} matches")
        return matcher.match_set

    def start(self, bindings=None):
        if bindings is None:
            bindings = {}
        first_config = (self.pda.initial_state, self.parse_tree, "", bindings, [])
        self.configurations.append(first_config)
        for listener in self._listeners:
            listener.on_start(self, self.pda.initial_state, self.parse_tree)
        return self

    def step(self):
        logger.debug(f"Step {self.n_step}")
        if len(self.configurations) == 0:
            raise Warning("No more configurations to process")
        current_config = self.configurations.pop()
        logger.debug(f"Checking config: {current_config}")
        current_state, current_node, stack, var, matches = current_config
        for listener in self._listeners:
            listener.step(self, current_state, current_node, stack, var, matches)

        if current_state in self.pda.final_states:
            logger.info("Match found")
            match = Match(self.n_step, var.copy())
            self.match_set.record(match)
            for listener in self._listeners:
                listener.on_match(self)
            return self


        for transition in self.pda.get_transitions(current_state):
            _, a, A, t, q_prime, alpha = dataclasses.astuple(transition)
            if not stack.endswith(A):
                logger.debug(f"Wrong stack elements: expecting {A} but was {stack[-len(A):]}")
                continue
            new_stack = stack.removesuffix(A)

            class_name = current_node.__class__.__name__

            new_var = var.copy()

            # Check if the current node matches the macro format
            match = re.match(r"([A-Z].*):(.+)\((\w*(?:,\w+)*)?\)", a)

            # Handle Variables
            if len(a) > 0 and a[0] == "?":
                logger.debug("Handling variable")
                name = a[1:]
                if name not in new_var:
                    logger.debug(f"New variable: {name}")
                    new_var[name] = current_node
                elif not self._match_tree(new_var[name], current_node):
                    logger.debug(f"Wrong variable: {name} expecting {new_var[name]} but was {current_node}")
                    continue

            # Handle Macros
            elif match:
                macro_name = match.group(1)
                trnsf_name = match.group(2)
                args = match.group(3).split(",") if match.group(3) else []

                logger.debug(f"Handling macro: {macro_name} with transformation {trnsf_name} and args {args}")

                macro_match = self.call_macro(macro_name, trnsf_name, args, current_node, new_var)
                if macro_match is None or macro_match.count() == 0:
                    continue

            # Default terminal node
            elif not self._match_node(current_node, a):
                if isinstance(current_node, TerminalNode):
                    logger.debug(f"Wrong input: expecting {a} but was {str(current_node)}")
                else:
                    logger.debug(f"Wrong input: expecting {a} but was {class_name}")
                continue

            next_node = self._get_next_node(current_node, t)
            if next_node is None:
                logger.debug(f"Wrong direction: cannot get next node at {t}")
                continue

            logger.debug(f"Taking {transition}")

            new_stack += alpha
            new_matches = matches + [(current_state, current_node)]

            new_config = (q_prime, next_node, new_stack, new_var.copy(), new_matches)
            self.configurations.append(new_config)

        self.n_step += 1
        return self

    def call_macro(self, macro_name, trnsf_name, args, current_node, bindings):
        """
        Calls a macro with the given name and transformation name, matching it against the current node.

        :param args:
        :param macro_name: The name of the macro to call.
        :param trnsf_name: The name of the transformation within the macro.
        :param current_node: The current node in the parse tree.
        :param bindings: The current variable bindings.
        :return: A MatchSet containing the results of the macro call.
        """
        macro = loaded_macros.get(macro_name)
        if not macro or trnsf_name not in macro.transformations:
            logger.warning(f"Macro or transformation not found: {macro_name}:{trnsf_name}")
            return MatchSet()

        macro_params = {}
        logger.debug(f"Using macro {macro_name}:{trnsf_name} with args {macro.args}")

        for i, arg in enumerate(args):
            if arg in bindings:
                macro_params[macro.args_order[i]] = bindings[arg]

        for param in macro.args:
            if param in macro_params:
                continue
            if macro.args[param] is not None:
                macro_params[param] = macro.args[param]
            else:
                logger.error(f"Macro argument {param} not found in bindings and has no default value")

        logger.debug(f"Calling macro {macro_name}:{trnsf_name} on node {current_node} with bindings {macro_params}")

        #macro_pda = get_processor(Languages.PYTHON).create_fsm(macro.transformations[trnsf_name])
        macro_pda = macro.transformations[trnsf_name]
        match_set = Matcher.match(macro_pda, current_node, stop_at_first=True, bindings=macro_params)
        if match_set.count() == 0:
            logger.debug(f"Macro {macro_name}:{trnsf_name} did not match")
            return None

        sub_bindings = match_set.matches[0].bindings
        for i, arg in enumerate(macro.args_order):
            if arg not in sub_bindings:
                logger.error(f"Macro argument {arg} not found in sub_bindings")
                return None
            binding_name = args[i]
            if binding_name not in bindings:
                bindings[binding_name] = sub_bindings[arg]

        return match_set


    def _get_next_node(self, node, directions):
        current_node = node
        for direction in directions:
            match direction:
                case NavigationDirection.RIGHT_SIBLING:
                    try:
                        parent = current_node.parentCtx
                        if parent is None:
                            return None
                        siblings = list(parent.getChildren())
                        index = siblings.index(current_node)
                        current_node = siblings[index + 1]
                    except IndexError:
                        return None
                case NavigationDirection.LEFT_CHILD:
                    try:
                        children = list(current_node.getChildren())
                        current_node = children[0]
                    except AttributeError:
                        return None
                    except IndexError:
                        return None
                case NavigationDirection.PARENT:
                    if current_node == self.parse_tree:
                        return None
                    current_node = current_node.parentCtx
        return current_node

    @staticmethod
    def _match_node(input, a):
        to_match = a.split("/") if len(a) > 1 else [a]
        name = to_match[0]
        down, up = to_match[1].split(",") if len(to_match) > 1 else (0, math.inf)
        down = float(down)
        up = float(up)
        if name == "":
            return True
        if isinstance(input, TerminalNode):
            return str(input) == name

        return input.__class__.__name__ == name and down <= input.getChildCount() <= up

    @staticmethod
    def _match_tree(tree1, tree2):
        logger.debug(f'Matching {tree1} and {tree2}')
        if tree1 is None or tree2 is None:
            return False
        if isinstance(tree1, TerminalNode) and isinstance(tree2, TerminalNode):
            return str(tree1) == str(tree2)
        if tree1.__class__.__name__ != tree2.__class__.__name__:
            return False
        if len(tree1.children) != len(tree2.children):
            return False
        for child1, child2 in zip(tree1.children, tree2.children):
            if not Matcher._match_tree(child1, child2):
                return False
        return True