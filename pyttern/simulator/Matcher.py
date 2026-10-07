try:
    from antlr4.ParserRuleContext import ParserRuleContext
    from antlr4.tree.Tree import TerminalNode, Tree
except ImportError:
    ParserRuleContext = object
    TerminalNode = ()
    Tree = ()
from loguru import logger

from pyttern.simulator.configuration import Environment

from .pda.PDA import PDA
from .pda.PDA_alphabets import NavigationAlphabet
from .pda.transition import NodeTransition, NamedTransition, CallTransition
from ..subpattern.SubPattern import loaded_subpatterns
from ..pytternfsm.python.match_set import MatchSet, Match

class Matcher:
    def __init__(self, pdas: dict[str, PDA], parse_tree: Tree):
        self.pda = pdas["__main__"]
        self.callable = pdas
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
    def match(pda: dict[str, PDA], parse_tree: ParserRuleContext, stop_at_first=False, bindings=None) -> MatchSet:
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

    def start(self, initial_bindings=None):
        bindings = {t: None for t in self.pda.named_wildcards}
        if initial_bindings is not None:
            if isinstance(initial_bindings, Environment):
                bindings.update(initial_bindings.mapping)
            else:
                bindings.update(initial_bindings)
        first_config = (self.pda.initial_state, self.parse_tree, "", Environment.from_dict(bindings), [])
        self.configurations.append(first_config)
        for listener in self._listeners:
            listener.on_start(self, self.pda.initial_state, self.parse_tree)
        return self

    def step(self):
        logger.trace(f"Step {self.n_step}")
        if len(self.configurations) == 0:
            raise Warning("No more configurations to process")
        current_config = self.configurations.pop()
        logger.trace(f"Checking config: {current_config}")
        current_state, current_node, stack, var, matches = current_config
        for listener in self._listeners:
            listener.step(self, current_state, current_node, stack, var, matches)

        if current_state == self.pda.final_states:
            logger.debug("Match found")
            match = Match(self.n_step, var, matches)
            self.match_set.record(match)
            for listener in self._listeners:
                listener.on_match(self, match)
            return self


        for transition in self.pda.get_transitions(current_state):
            # Transition components
            alpha = transition.alpha
            A = transition.A
            t = transition.t
            q_prime = transition.q_prime
            beta = transition.beta

            if not stack.endswith(alpha):
                logger.trace(f"Wrong stack elements: expecting {alpha} but was {stack[-len(alpha):]}")
                continue
            new_stack = stack.removesuffix(alpha)

            class_name = current_node.__class__.__name__

            new_var: Environment = var
            new_vars = []

            # Default terminal node
            if isinstance(A, NodeTransition):
                if not self._match_node(current_node, A):
                    if isinstance(current_node, TerminalNode):
                        logger.trace(f"Wrong input: expecting {A.name} but was {str(current_node)}")
                    else:
                        logger.trace(f"Wrong input: expecting {A.name} but was {class_name}")
                    continue
                new_vars.append((new_var, 0))

            # Handle Variables
            elif isinstance(A, NamedTransition):
                name = A.name
                if new_var[name] is None:
                    logger.trace(f"New variable: {name}")
                    new_var = new_var.bind(name, current_node)
                elif not self._match_tree(new_var[name], current_node):
                    logger.trace(f"Wrong variable: {name} expecting {new_var[name]} but was {current_node}")
                    continue
                new_vars.append((new_var, 0))

            # Handle subpatterns
            elif isinstance(A, CallTransition):
                logger.trace(f"Handling subpattern transition: {A}")
                possible_bindings = self.call_subpattern(A, current_node, new_var)
                if len(possible_bindings) < 1:
                    continue
                logger.debug(possible_bindings)
                new_vars += possible_bindings

            else:
                logger.error(f"Unknown transition type: {A}")
                raise ValueError(f"Unknown transition type: {A} ({type(A)})")


            logger.trace(f"Taking {transition} and generating {len(new_vars)} new configuration(s)")

            new_stack += beta
            new_matches = matches + [(transition, current_node)]

            for variables, k in new_vars:
                curr = current_node
                valid_skip = True
                for _ in range(k):
                    parent = curr.parentCtx
                    if parent is None:
                        valid_skip = False
                        break
                    sibs = list(parent.getChildren())
                    try:
                        idx = sibs.index(curr)
                        curr = sibs[idx + 1]
                    except (IndexError, ValueError):
                        valid_skip = False
                        break
                if not valid_skip:
                    continue
                next_node = self._get_next_node(curr, t)
                if next_node is None:
                    logger.trace(f"Wrong direction: cannot get next node at {t}")
                    continue
                new_config = (q_prime, next_node, new_stack, variables, new_matches)
                self.configurations.append(new_config)

        self.n_step += 1
        return self

    def call_subpattern(self, transition: CallTransition, current_node: ParserRuleContext, bindings):
        """
        Calls a subpattern transition (CallTransition) against the current node.

        :param transition: The transition object.
        :param current_node: The current node in the parse tree.
        :param bindings: The current variable bindings.
        :return: A list of binding dicts.
        """
        from . import subpatterns

        subpattern_name = transition.subpattern_name
        args = transition.args

        subp = loaded_subpatterns.get(subpattern_name)
        if not subp:
            logger.warning(f"subpattern not found: {subpattern_name}")
            logger.debug(f"Loaded subpatterns: {loaded_subpatterns.keys()}, callable subpatterns: {self.callable.keys()}")
            return []
        
        return subpatterns.call_subpattern(subp, current_node, bindings, args)


    def _get_next_node(self, node, directions):
        current_node = node
        for direction in directions:
            match direction:
                case NavigationAlphabet.RIGHT_SIBLING:
                    if current_node == self.parse_tree:
                        return None
                    try:
                        parent = current_node.parentCtx
                        if parent is None:
                            return None
                        siblings = list(parent.getChildren())
                        index = siblings.index(current_node)
                        current_node = siblings[index + 1]
                    except IndexError:
                        return None
                case NavigationAlphabet.LEFT_CHILD:
                    try:
                        children = list(current_node.getChildren())
                        current_node = children[0]
                    except AttributeError:
                        return None
                    except IndexError:
                        return None
                case NavigationAlphabet.PARENT:
                    if current_node == self.parse_tree:
                        return None
                    current_node = current_node.parentCtx
        return current_node

    @staticmethod
    def _match_node(input, A: NodeTransition):
        name = A.name
        down, up = A.down, A.up
        if name == "":
            return True
        is_term = getattr(input, "is_terminal", False) or (isinstance(TerminalNode, type) and isinstance(input, TerminalNode))
        if is_term:
            input_text = input.getText() if hasattr(input, "getText") else str(input)
            return input_text == name

        input_name = getattr(input, "rule_name", input.__class__.__name__)
        matches_name = (input_name == name) or (input_name + "Context" == name) or (name + "Context" == input_name) or (input_name.replace("Context", "") == name.replace("Context", ""))
        return matches_name and down <= input.getChildCount() <= up

    @staticmethod
    def _unwrap(tree):
        while tree is not None and not getattr(tree, "is_terminal", False) and hasattr(tree, 'children') and len(tree.children) == 1:
            rule = getattr(tree, "rule_name", tree.__class__.__name__)
            if rule in ("Name", "NameContext"):
                break
            tree = tree.children[0]
        return tree

    @staticmethod
    def _match_tree(tree1, tree2):
        logger.trace(f'Matching {tree1} and {tree2}')
        if tree1 is None or tree2 is None:
            return False
        is_term1 = getattr(tree1, "is_terminal", False) or (isinstance(TerminalNode, type) and isinstance(tree1, TerminalNode))
        is_term2 = getattr(tree2, "is_terminal", False) or (isinstance(TerminalNode, type) and isinstance(tree2, TerminalNode))
        if is_term1 and is_term2:
            t1_text = tree1.getText() if hasattr(tree1, "getText") else str(tree1)
            t2_text = tree2.getText() if hasattr(tree2, "getText") else str(tree2)
            return t1_text == t2_text
        if is_term1 or is_term2:
            return False
        r1 = getattr(tree1, "rule_name", tree1.__class__.__name__).replace("Context", "")
        r2 = getattr(tree2, "rule_name", tree2.__class__.__name__).replace("Context", "")
        if r1 == "Name" and r2 == "Expr":
            unwrapped = Matcher._unwrap(tree2)
            if getattr(unwrapped, "rule_name", unwrapped.__class__.__name__).replace("Context", "") == "Name":
                return Matcher._match_tree(tree1, unwrapped)
        if r1 != r2:
            return False
        if len(tree1.children) != len(tree2.children):
            return False
        for child1, child2 in zip(tree1.children, tree2.children):
            if not Matcher._match_tree(child1, child2):
                return False
        return True

