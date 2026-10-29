"""Execution engine and state simulation for Pushdown Automata."""
from __future__ import annotations

from collections.abc import Mapping
from typing import Any

from antlr4.ParserRuleContext import ParserRuleContext
from antlr4 import RuleContext
from antlr4.tree.Tree import TerminalNode, Tree
from antlr4.tree.Trees import Trees
from loguru import logger

from pyttern.simulator.configuration import Configuration, Environment
from .pda.PDA import PDA
from .pda.PDA_alphabets import NavigationAlphabet
from .pda.transition import CallTransition, NamedTransition, NodeTransition
from ..antlr.python import Python3Parser
from ..pytternfsm.python.match_set import Match, MatchSet
from ..subpattern.catalog import SubPatternCatalog


def _to_symbol_str(val: Any) -> str:
    """Helper to convert a transition alpha or beta symbol sequence to string."""
    if isinstance(val, str):
        return val
    return "".join(getattr(x, "value", str(x)) for x in val)


def _is_trace_enabled() -> bool:
    core = getattr(logger, "_core", None)
    return any(h._levelno <= 5 for h in core.handlers.values()) if core and hasattr(core, "handlers") else False


def _is_debug_enabled() -> bool:
    core = getattr(logger, "_core", None)
    return any(h._levelno <= 10 for h in core.handlers.values()) if core and hasattr(core, "handlers") else False


def _describe_node(node: Any) -> str:
    """Helper to format AST nodes cleanly in debug logs."""
    if node is None:
        return "None"
    if isinstance(node, TerminalNode):
        return f"Terminal('{node}')"
    text = node.getText() if hasattr(node, "getText") else str(node)
    text_short = (text[:30] + "...") if len(text) > 30 else text
    text_short = text_short.replace("\n", "\\n")
    return f"{node.__class__.__name__}['{text_short}']"


class Matcher:
    """Core pattern matching execution engine simulating Pushdown Automata."""

    configurations: list[Configuration]
    _listeners: list[Any]
    n_step: int
    pda: PDA
    callable: dict[str, PDA]
    parse_tree: Tree
    catalog: SubPatternCatalog
    match_set: MatchSet

    def __init__(
        self,
        pdas: dict[str, PDA] | PDA,
        parse_tree: Tree,
        catalog: SubPatternCatalog | None = None,
    ) -> None:
        """Initialize matcher with compiled PDAs and target parse tree.

        Args:
            pdas: Pushdown Automaton dictionary or single PDA.
            parse_tree: Target Concrete Syntax Tree root to match against.
            catalog: Optional SubPatternCatalog for resolving subpattern calls.
        """
        if isinstance(pdas, dict):
            self.pda = pdas["__main__"]
            self.callable = pdas
        else:
            self.pda = pdas
            self.callable = {"__main__": pdas}
        self.parse_tree = parse_tree
        self.catalog = catalog if catalog is not None else SubPatternCatalog()
        self.match_set = MatchSet()
        self.configurations = []
        self.n_step = 0
        self._listeners = []

    def add_listener(self, listener: Any) -> Matcher:
        """Register an event listener for simulation callbacks.

        Args:
            listener: Listener instance conforming to Pyttern_listener interface.

        Returns:
            Matcher: Self instance for chaining.
        """
        self._listeners.append(listener)
        return self

    def remove_listener(self, listener: Any) -> None:
        """Unregister an event listener.

        Args:
            listener: Listener instance to remove.
        """
        self._listeners.remove(listener)

    def remove_all_listeners(self) -> None:
        """Clear all registered event listeners."""
        self._listeners.clear()

    @staticmethod
    def match(
        pda: dict[str, PDA] | PDA,
        parse_tree: ParserRuleContext | Tree,
        stop_at_first: bool = False,
        bindings: Mapping[str, Any] | Environment | None = None,
        catalog: SubPatternCatalog | None = None,
    ) -> MatchSet:
        """Matches a given parse tree against a Pushdown Automaton (PDA) and returns matches.

        Args:
            pda: Pushdown Automaton mapping with '__main__' as entrypoint or single PDA.
            parse_tree: The target parse tree to match against.
            stop_at_first: Whether to stop after the first match. Defaults to False.
            bindings: Initial variable bindings. Defaults to None.
            catalog: Optional SubPatternCatalog for resolving subpattern calls.

        Returns:
            MatchSet: Collection of match results.
        """
        matcher = Matcher(pda, parse_tree, catalog=catalog)
        if _is_debug_enabled():
            logger.debug(f"Starting match on root node {_describe_node(parse_tree)}")
        matcher.start(bindings)
        while len(matcher.configurations) > 0:
            matcher.step()
            if stop_at_first and matcher.match_set.count() > 0:
                break

        if _is_debug_enabled():
            logger.debug(f"Match finished with {matcher.match_set.count()} matches")
        return matcher.match_set

    def start(self, initial_bindings: Mapping[str, Any] | Environment | None = None) -> Matcher:
        """Initializes the matcher with the initial configuration and variable bindings.

        Args:
            initial_bindings: Initial variable bindings.

        Returns:
            Matcher: Self instance for chaining.
        """
        bindings: dict[str, Any] = {t: None for t in self.pda.named_wildcards}
        if initial_bindings is not None:
            if isinstance(initial_bindings, Environment):
                bindings.update(initial_bindings.mapping)
            else:
                bindings.update(initial_bindings)

        initial_env = Environment.from_dict(bindings)
        self.configurations.append(
            Configuration(
                state=self.pda.initial_state,
                parse_tree=self.parse_tree,
                action="",
                environment=initial_env,
                history=[],
            )
        )
        for listener in self._listeners:
            listener.on_start(self, self.pda.initial_state, self.parse_tree)
        return self

    def step(self) -> Matcher:
        """Executes a single step of simulation across active configurations."""
        if _is_trace_enabled():
            logger.trace(f"Step {self.n_step}")
        if len(self.configurations) == 0:
            raise Warning("No more configurations to process")
        current_config = self.configurations.pop()

        current_fsm, current_node, stack, variables, matches = (
            current_config.state,
            current_config.parse_tree,
            current_config.action,
            current_config.environment,
            current_config.history,
        )

        for listener in self._listeners:
            listener.step(self, current_fsm, current_node, stack, variables, matches)

        transitions = self.pda.get_transitions(current_fsm)
        for transition in transitions:
            alpha = _to_symbol_str(transition.alpha)
            A = transition.A
            t = transition.t
            q_prime = transition.q_prime
            beta = _to_symbol_str(transition.beta)

            if not stack.endswith(alpha):
                if _is_trace_enabled():
                    logger.trace(f"Wrong stack elements: expecting {alpha} but was {stack[-len(alpha):]}")
                continue
            new_stack = stack.removesuffix(alpha)

            new_vars: list[tuple[Environment, int]] = []
            if isinstance(A, NodeTransition):
                if not self._match_node(current_node, A):
                    if _is_trace_enabled():
                        logger.trace(f"Wrong node: {current_node.__class__.__name__} != {A.name}")
                    continue
                new_vars = [(variables, 0)]
            elif isinstance(A, NamedTransition):
                var_name = A.name
                if variables.get(var_name) is None:
                    bound_variables = variables.bind(var_name, current_node)
                    new_vars = [(bound_variables, 0)]
                    for listener in self._listeners:
                        listener.on_new_variable(self, current_fsm, current_node, var_name)
                elif self._match_tree(variables.get(var_name), current_node):
                    new_vars = [(variables, 0)]
                else:
                    if _is_trace_enabled():
                        logger.trace(
                            f"Mismatch in variable {var_name}: {variables[var_name]} != {current_node}"
                        )
                    continue
            elif isinstance(A, CallTransition):
                new_vars = self.call_subpattern(A, current_node, variables)
                if not new_vars:
                    if _is_trace_enabled():
                        logger.trace(f"Subpattern call {A.subpattern_name} returned no valid configurations")
                    continue
            else:
                logger.error(f"Unknown transition type: {A}")
                raise ValueError(f"Unknown transition type: {A} ({type(A)})")

            if _is_trace_enabled():
                logger.trace(f"Taking {transition} and generating {len(new_vars)} new configuration(s)")

            new_stack += beta
            new_matches = matches + [(transition, current_node)]

            for variables, skip in new_vars:
                if q_prime == self.pda.final_states:
                    match = Match(self.n_step, variables, new_matches)
                    self.match_set.record(match)
                    for listener in self._listeners:
                        listener.on_match(self, match)

                curr = current_node
                if skip > 0:
                    curr = self._skip_siblings(curr, skip)
                    if curr is None:
                        continue
                next_node = self._get_next_node(curr, t)
                if next_node is None:
                    if _is_trace_enabled():
                        logger.trace(f"Wrong direction: cannot get next node at {t}")
                    continue
                new_config = Configuration(
                    state=q_prime,
                    parse_tree=next_node,
                    action=new_stack,
                    environment=variables,
                    history=new_matches,
                )
                self.configurations.append(new_config)

        self.n_step += 1
        return self

    def call_subpattern(
        self,
        transition: CallTransition,
        current_node: RuleContext | Tree,
        bindings: Environment,
    ) -> list[tuple[Environment, int]]:
        """Executes a subpattern call transition against the current AST node.

        Args:
            transition: Subpattern call transition specification.
            current_node: Active node in the target AST.
            bindings: Current variable bindings.

        Returns:
            list[tuple[Environment, int]]: Valid (environment, sibling_skip) pairs.
        """
        from . import subpatterns

        subpattern_name = transition.subpattern_name
        args = transition.args
        call_id = getattr(transition, "call_id", None)

        subp = self.catalog.get(subpattern_name)
        if not subp:
            from ..subpattern.SubPattern import loaded_subpatterns

            subp = loaded_subpatterns.get(subpattern_name)
        if not subp:
            raise ValueError(
                f"Subpattern {subpattern_name} not found. Available: {list(self.catalog.keys())}"
            )

        return subpatterns.call_subpattern(
            subp,
            current_node,
            bindings,
            args,
            catalog=self.catalog,
            context_type=getattr(transition, "context_type", None),
            call_id=call_id,
        )

    def _skip_siblings(self, node: Any, skip: int) -> Any:
        """Skip forward `skip` siblings in the parse tree."""
        if skip == 0:
            return node
        parent = getattr(node, "parentCtx", None)
        if parent is None:
            return None
        children = parent.children if getattr(parent, "children", None) is not None else list(parent.getChildren())
        try:
            index = children.index(node)
            return children[index + skip]
        except (IndexError, ValueError):
            return None

    def _get_next_node(self, node: Any, directions: list[NavigationAlphabet]) -> Any:
        """Navigates from `node` along the sequence of `directions`."""
        current_node = node
        for direction in directions:
            match direction:
                case NavigationAlphabet.RIGHT_SIBLING:
                    if current_node is self.parse_tree:
                        return None
                    parent = getattr(current_node, "parentCtx", None)
                    if parent is None:
                        return None
                    siblings = parent.children if getattr(parent, "children", None) is not None else list(parent.getChildren())
                    try:
                        index = siblings.index(current_node)
                        current_node = siblings[index + 1]
                    except (IndexError, ValueError):
                        return None
                case NavigationAlphabet.LEFT_CHILD:
                    children = getattr(current_node, "children", None)
                    if children:
                        current_node = children[0]
                    else:
                        return None
                case NavigationAlphabet.PARENT:
                    if current_node is self.parse_tree:
                        return None
                    current_node = getattr(current_node, "parentCtx", None)
                    if current_node is None:
                        return None
        return current_node

    @staticmethod
    def _to_symbol_str(val: Any) -> str:
        return _to_symbol_str(val)

    @staticmethod
    def _match_node(input: Any, A: NodeTransition) -> bool:
        """Checks whether an AST node matches the given node transition condition.

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

        child_count = (
            len(input.children)
            if hasattr(input, "children") and input.children is not None
            else (input.getChildCount() if hasattr(input, "getChildCount") else 0)
        )
        return input.__class__.__name__ == name and down <= child_count <= up

    @staticmethod
    def _unwrap(tree: Any) -> Any:
        """Unwraps single-child AST wrapper nodes down towards NameContext.

        Args:
            tree (Tree): Starting parse tree node.

        Returns:
            Tree: Deepest single-child descendant or NameContext.
        """
        from ..antlr.python import Python3Parser

        while (
            tree is not None
            and not isinstance(tree, TerminalNode)
            and hasattr(tree, "children")
            and len(tree.children) == 1
        ):
            if isinstance(tree, Python3Parser.NameContext):
                break
            tree = tree.children[0]
        return tree

    @staticmethod
    def _match_tree(tree1: Any, tree2: Any) -> bool:
        """Recursively checks structural and value equivalence between two AST trees.

        Args:
            tree1 (Tree or object): First node or tree.
            tree2 (Tree or object): Second node or tree.

        Returns:
            bool: True if the two trees are structurally identical.
        """
        if tree1 is tree2:
            return True
        if _is_trace_enabled():
            logger.trace(f"Matching {tree1} and {tree2}")
        if tree1 is None or tree2 is None:
            return False
        if not isinstance(tree1, Tree) or not isinstance(tree2, Tree):
            return bool(tree1 == tree2)
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
        children1 = getattr(tree1, "children", None)
        children2 = getattr(tree2, "children", None)
        if children1 is None or children2 is None:
            return False
        if len(children1) != len(children2):
            return False
        for child1, child2 in zip(children1, children2):
            if not Matcher._match_tree(child1, child2):
                return False
        return True


__all__ = ["Matcher"]
