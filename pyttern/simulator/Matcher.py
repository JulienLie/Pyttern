from antlr4.ParserRuleContext import ParserRuleContext
from antlr4.tree.Tree import TerminalNode, Tree
from loguru import logger

from pyttern.simulator.configuration import Environment

from .pda.PDA import PDA
from .pda.PDA_alphabets import NavigationAlphabet
from .pda.transition import NodeTransition, NamedTransition, CallTransition
from ..subpattern.SubPattern import loaded_subpatterns
from ..pytternfsm.python.match_set import MatchSet, Match

def _describe_node(node) -> str:
    """Helper to format AST nodes cleanly in debug logs."""
    if node is None:
        return "None"
    if isinstance(node, TerminalNode):
        return f"Terminal('{node}')"
    text = node.getText() if hasattr(node, 'getText') else str(node)
    text_short = (text[:30] + "...") if len(text) > 30 else text
    text_short = text_short.replace("\n", "\\n")
    return f"{node.__class__.__name__}['{text_short}']"


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
        Matches a given parse tree against a Pushdown Automaton (PDA) and returns matches.

        Args:
            pda (dict[str, PDA]): Pushdown Automaton mapping with '__main__' as entrypoint.
            parse_tree (ParserRuleContext): The target parse tree to match against.
            stop_at_first (bool, optional): Whether to stop after the first match. Defaults to False.
            bindings (dict or Environment, optional): Initial variable bindings. Defaults to None.

        Returns:
            MatchSet: Collection of match results.
        """
        matcher = Matcher(pda, parse_tree)
        logger.debug(f"Starting match on root node {_describe_node(parse_tree)}")
        matcher.start(bindings)
        while len(matcher.configurations) > 0:
            matcher.step()
            if stop_at_first and matcher.match_set.count() > 0:
                break
        logger.debug(f"Match finished with {matcher.match_set.count()} matches")
        return matcher.match_set

    def start(self, initial_bindings=None):
        """
        Initializes the matcher with the initial configuration and variable bindings.

        Args:
            initial_bindings (dict or Environment, optional): Initial variable bindings.

        Returns:
            Matcher: Self instance for chaining.
        """
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
        if len(self.configurations) == 0:
            raise Warning("No more configurations to process")
        current_config = self.configurations.pop()
        current_state, current_node, stack, var, matches = current_config
        logger.trace(
            f"Step {self.n_step}: state={current_state}, node={_describe_node(current_node)}, "
            f"stack='{stack}', bindings={var}"
        )
        for listener in self._listeners:
            listener.step(self, current_state, current_node, stack, var, matches)

        if current_state == self.pda.final_states:
            logger.debug(f"Match found at step {self.n_step} with bindings: {var}")
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
        Executes a subpattern call transition against the current AST node.

        Args:
            transition (CallTransition): Subpattern call transition specification.
            current_node (ParserRuleContext): Active node in the target AST.
            bindings (Environment): Current variable bindings.

        Returns:
            list[tuple[Environment, int]]: Valid (environment, sibling_skip) pairs.
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
        """
        Navigates from the given AST node along the specified sequence of directions.

        Args:
            node (ParserRuleContext): Starting AST node.
            directions (list[NavigationAlphabet]): Navigation directions (PARENT, LEFT_CHILD, RIGHT_SIBLING).

        Returns:
            ParserRuleContext or None: The destination AST node, or None if navigation fails.
        """
        current_node = node
        for direction in directions:
            match direction:
                case NavigationAlphabet.RIGHT_SIBLING:
                    if current_node is self.parse_tree:
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
                    if current_node is self.parse_tree:
                        return None
                    current_node = current_node.parentCtx
        return current_node

    @staticmethod
    def _match_node(input, A: NodeTransition) -> bool:
        """
        Checks whether an AST node matches the given node transition condition.

        Args:
            input (Tree or TerminalNode): Candidate node from the AST.
            A (NodeTransition): Expected node label and child count bounds.

        Returns:
            bool: True if input matches transition condition A.
        """
        name = A.name
        down, up = A.down, A.up
        if name == "":
            return True
        if isinstance(input, TerminalNode):
            return str(input) == name

        return input.__class__.__name__ == name and down <= input.getChildCount() <= up

    @staticmethod
    def _unwrap(tree):
        """
        Unwraps single-child AST wrapper nodes down towards NameContext.

        Args:
            tree (Tree): Starting parse tree node.

        Returns:
            Tree: Deepest single-child descendant or NameContext.
        """
        from ..antlr.python import Python3Parser
        while tree is not None and not isinstance(tree, TerminalNode) and hasattr(tree, 'children') and len(tree.children) == 1:
            if isinstance(tree, Python3Parser.NameContext):
                break
            tree = tree.children[0]
        return tree

    @staticmethod
    def _match_tree(tree1, tree2) -> bool:
        """
        Recursively checks structural and value equivalence between two AST trees.

        Args:
            tree1 (Tree or object): First node or tree.
            tree2 (Tree or object): Second node or tree.

        Returns:
            bool: True if the two trees are structurally identical.
        """
        logger.trace(f'Matching {tree1} and {tree2}')
        if tree1 is None or tree2 is None:
            return False
        from antlr4.tree.Tree import Tree
        if not isinstance(tree1, Tree) or not isinstance(tree2, Tree):
            return tree1 == tree2
        if isinstance(tree1, TerminalNode) and isinstance(tree2, TerminalNode):
            return str(tree1) == str(tree2)
        if isinstance(tree1, TerminalNode) or isinstance(tree2, TerminalNode):
            return False
        from ..antlr.python import Python3Parser
        if isinstance(tree1, Python3Parser.NameContext) and isinstance(tree2, Python3Parser.ExprContext):
            unwrapped = Matcher._unwrap(tree2)
            if isinstance(unwrapped, Python3Parser.NameContext):
                return Matcher._match_tree(tree1, unwrapped)
        if tree1.__class__.__name__ != tree2.__class__.__name__:
            return False
        if len(tree1.children) != len(tree2.children):
            return False
        for child1, child2 in zip(tree1.children, tree2.children):
            if not Matcher._match_tree(child1, child2):
                return False
        return True

