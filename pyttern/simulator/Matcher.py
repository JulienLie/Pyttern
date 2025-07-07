import dataclasses
import math

from antlr4.ParserRuleContext import ParserRuleContext
from antlr4.tree.Tree import TerminalNode
from loguru import logger

from .pda.PDA import PDA
from .pda.navigation_direction import NavigationDirection
from ..pytternfsm.python.match_set import MatchSet


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
    def match(pda: PDA, parse_tree: ParserRuleContext, stop_at_first=False) -> MatchSet:
        matcher = Matcher(pda, parse_tree)
        matcher.start()
        while len(matcher.configurations) > 0:
            matcher.step()
            if stop_at_first and matcher.match_set.count() > 0:
                break
        return matcher.match_set

    def start(self):
        logger.info("Starting match")
        first_config = (self.pda.initial_state, self.parse_tree, "", {}, [])
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
            self.match_set.record(self.n_step)
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

            if len(a) > 0 and a[0] == "?":
                logger.debug("Handling variable")
                name = a[1:]
                if name not in var:
                    logger.debug(f"New variable: {name}")
                    var[name] = current_node
                elif not self._match_tree(var[name], current_node):
                    logger.debug(f"Wrong variable: {name} expecting {var[name]} but was {current_node}")
                    continue
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
            new_var = var.copy()
            new_config = (q_prime, next_node, new_stack, new_var, new_matches)
            self.configurations.append(new_config)

        self.n_step += 1
        return self



    @staticmethod
    def _get_next_node(node, directions):
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