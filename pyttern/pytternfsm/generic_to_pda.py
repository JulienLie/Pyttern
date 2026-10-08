"""Generic AST-to-Pushdown Automaton (PDA) compiler base class."""

import abc
import math
from typing import Any, TypeVar

from antlr4.tree.Tree import TerminalNode
from loguru import logger

from ..simulator.pda.PDA import PDA
from ..simulator.pda.PDA_alphabets import NavigationAlphabet
from ..simulator.pda.transition import NamedTransition, NodeTransition, Transition, TransitionCondition

T = TypeVar('T')


def _is_trace_enabled() -> bool:
    return any(h._levelno <= 5 for h in logger._core.handlers.values()) if logger._core.handlers else False


def _is_debug_enabled() -> bool:
    return any(h._levelno <= 10 for h in logger._core.handlers.values()) if logger._core.handlers else False


class Generic_to_PDA(metaclass=abc.ABCMeta):
    """Abstract compiler translating an ANTLR parse tree pattern into a PDA graph.

    Attributes:
        pda: Pushdown Automaton being built.
        current_state: Currently active state index during traversal.
        depth: Current indentation / tree depth level.
        move_to_B: Stack tracking depths for compound body transitions.
        dict_pda: Mapping of compilation units (e.g. '__main__', subpatterns) to PDAs.
        grammar: ANTLR parser grammar class.
        skippable_nodes: Node class names that can be skipped via right-sibling transitions.
        remove_double_wildcard: Tuple of node contexts where trailing wildcards are pruned.
        tree_pruner: Tree pruner visitor instance.
    """

    def __init__(
        self,
        grammar: Any,
        skippable_nodes: list[str],
        remove_double_wildcard: list[Any] | tuple[Any, ...],
        tree_pruner: Any,
    ) -> None:
        """Initialize the compiler with target grammar rules and configuration.

        Args:
            grammar: Parser class containing grammar rules and tokens.
            skippable_nodes: List of AST node rule names that support self-loop skipping.
            remove_double_wildcard: Collection of context classes for double wildcard elimination.
            tree_pruner: Pruner visitor instance for preprocessing pattern trees.
        """
        self.pda = PDA()
        self.current_state = self.pda.initial_state
        self.depth = 0
        self.move_to_B: list[int] = []
        self.dict_pda: dict[str, PDA] = {}
        self.grammar = grammar
        self.skippable_nodes = skippable_nodes
        self.remove_double_wildcard = tuple(remove_double_wildcard)
        self.tree_pruner = tree_pruner
        self._restrict_stmt = False
        self.__var_names: dict[str, Any] = {}
        self.__is_last_branch = True

    def visit(self, tree: Any) -> dict[str, PDA]:
        """Compile a pattern parse tree into a dictionary of PDAs.

        Args:
            tree: Root of the pattern parse tree.

        Returns:
            dict[str, PDA]: Dictionary containing compiled PDAs with '__main__' as entrypoint.
        """
        if _is_debug_enabled():
            logger.debug(f"Visiting tree: {tree}")
        self.dict_pda = {}
        self.__var_names = {}
        super().visit(tree)
        self.depth = 0
        self.pda.final_states = self.current_state
        if _is_trace_enabled():
            logger.trace(f"var_names: {self.__var_names}")
        self.dict_pda["__main__"] = self.pda
        return self.dict_pda

    @abc.abstractmethod
    def define_boundaries(self, ctx: Any) -> tuple[int, int | float]:
        """Compute minimum down and up tree traversal boundaries for a given context.

        Args:
            ctx: Current AST rule context.

        Returns:
            tuple[int, int | float]: A pair (down_bound, up_bound).
        """
        pass

    def visitChildren(self, node: Any) -> int:
        """Visit child nodes of an AST rule and generate corresponding PDA transitions.

        Args:
            node: Current parse tree rule context.

        Returns:
            int: Resulting next state index after traversing children.
        """
        if _is_trace_enabled():
            logger.trace(f"Visiting {node.__class__.__name__} {hash(node)}: {node.getText()}")

        children = node.children
        if len(children) == 0:
            return self.visitTerminal(node)

        down, up = self.define_boundaries(node)

        # Handle the double wildcard case
        while len(children) > 1 and self.lookahead(children[-1], self.remove_double_wildcard):
            children.pop()
            logger.trace("Remove double wildcard")

        # Add self-transition to be able to skip statements
        if node.__class__.__name__ in self.skippable_nodes:
            if not self._restrict_stmt:
                self_transition = Transition(
                    self.current_state,
                    "",
                    NodeTransition(''),
                    [NavigationAlphabet.RIGHT_SIBLING],
                    self.current_state,
                    '',
                )
                self.pda.add_transition(self_transition)
            else:
                self._restrict_stmt = False

        next_state = self.pda.new_state()
        transition = Transition(
            self.current_state,
            "",
            NodeTransition(node.__class__.__name__, down, up),
            [NavigationAlphabet.LEFT_CHILD],
            next_state,
            'I',
        )
        self.pda.add_transition(transition)
        self.current_state = next_state

        # Visit every child
        is_last_branch = self.__is_last_branch
        self.__is_last_branch = False
        old_move_to_B = self.move_to_B
        self.move_to_B = []

        old_depth = self.depth
        self.depth = 0
        for i, child in enumerate(children):
            if i == len(children) - 1:
                self.depth = old_depth + 1
                self.move_to_B = old_move_to_B
                self.__is_last_branch = is_last_branch
            child.accept(self)

        return next_state

    def visitStatement(self, ctx: Any) -> Any:
        """Visit statement context and route compound or number wildcards appropriately.

        Args:
            ctx: Statement rule context.

        Returns:
            Any: Target state or child visitation result.
        """
        logger.trace(f"Visiting Stmt {hash(ctx)}: {ctx.getText()}")

        # Handle multiple compound wildcard
        lookahead_multiple_body = self.lookahead(ctx, self.grammar.Multiple_compound_wildcardContext)
        if lookahead_multiple_body:
            return self.visitMultiple_compound_wildcard(lookahead_multiple_body)

        # Handle simple compound wildcard
        lookahead_simple_wildcard = self.lookahead(ctx, self.grammar.Simple_wildcardContext)
        if lookahead_simple_wildcard:
            return self.visitSimple_wildcard(lookahead_simple_wildcard)

        # Handle number wildcard
        lookahead_number_wildcard = self.lookahead(ctx, self.grammar.Number_wildcardContext)
        if lookahead_number_wildcard:
            return self.visitNumber_wildcard(lookahead_number_wildcard)

        return self.visitChildren(ctx)

    def visitExpr_wildcard(self, ctx: Any) -> Any:
        """Visit expression wildcard wrapper node.

        Args:
            ctx: Expression wildcard context.

        Returns:
            Any: Child visitor result.
        """
        return ctx.getChild(0).accept(self)

    def visitStmt_wildcard(self, ctx: Any) -> Any:
        """Visit statement wildcard wrapper node.

        Args:
            ctx: Statement wildcard context.

        Returns:
            Any: Child visitor result.
        """
        return ctx.getChild(0).accept(self)

    def visitCompound_wildcard(self, ctx: Any) -> Any:
        """Visit compound wildcard wrapper node.

        Args:
            ctx: Compound wildcard context.

        Returns:
            Any: Child visitor result.
        """
        return ctx.getChild(0).accept(self)

    def visitSimple_wildcard(self, ctx: Any) -> int:
        """Visit simple wildcard node and generate navigation transitions.

        Args:
            ctx: Simple wildcard context.

        Returns:
            int: Resulting state index.
        """
        return self._add_up_transition(ctx)

    def visitNumber_wildcard(self, ctx: Any) -> int:
        """Visit bounded numeric wildcard node ($[min, max]) and create repetition paths.

        Args:
            ctx: Number wildcard context.

        Returns:
            int: Target state index.

        Raises:
            ValueError: If the lower bound exceeds the upper bound.
        """
        numbers_node = ctx.getChild(0, self.grammar.Wildcard_numberContext)
        low, high = self.visitWildcard_number(numbers_node)
        logger.trace(f"Visiting Simple_wildcard with numbers: low={low}, high={high}")

        if low > high:
            logger.error(f"Invalid simple wildcard: low={low} > high={high}")
            raise ValueError(f"Invalid simple wildcard: low={low} > high={high}")

        for _ in range(1, low):
            # Add transitions for low - 1
            next_state = self.pda.new_state()
            transition = Transition(
                self.current_state,
                '',
                NodeTransition(''),
                [NavigationAlphabet.RIGHT_SIBLING],
                next_state,
                '',
            )
            self.pda.add_transition(transition)
            self.current_state = next_state

        # We don't have optional nodes -> we fall back to basic behavior
        if high <= low or high == math.inf:
            return self._add_up_transition(ctx)

        dummy_state = self.pda.new_state()
        dummy_transition = Transition(self.current_state, "", NodeTransition(''), [], dummy_state, '')
        self.pda.add_transition(dummy_transition)

        for i in range(low, high):
            # Add transitions for high - low
            next_state = self.pda.new_state()

            # There is a sibling
            transition = Transition(
                self.current_state,
                '',
                NodeTransition(''),
                [NavigationAlphabet.RIGHT_SIBLING],
                next_state,
                '',
            )
            self.pda.add_transition(transition)

            # No more siblings
            up_transition = Transition(next_state, '', NodeTransition(''), [], dummy_state, '')
            self.pda.add_transition(up_transition)
            self.current_state = next_state

        self.current_state = dummy_state
        return self._add_up_transition(ctx)


    def visitWildcard_number(self, ctx: Any) -> tuple[int, int | float]:
        """Extract low and high integer range bounds from a wildcard number context.

        Args:
            ctx: Wildcard number rule context.

        Returns:
            tuple[int, int | float]: (low_bound, high_bound) limit tuple.
        """
        low = int(ctx.getChild(1).getText())
        high = int(ctx.getChild(3).getText()) if ctx.getChild(3) and ctx.getChild(3).getText().isdigit() else math.inf

        if ctx.COMMA() is None:
            high = low

        logger.trace(f"Visiting Wildcard_number: low={low}, high={high}")
        if low > high:
            logger.error(f"Invalid wildcard number: low={low} > high={high}")
            return 1, 1

        return low, high

    def visitList_wildcard(self, ctx: Any) -> int:
        """Add a right-sibling self-loop transition to match arbitrary sibling list items.

        Args:
            ctx: List wildcard context.

        Returns:
            int: Current state index.
        """
        self_transition = Transition(
            self.current_state,
            '',
            NodeTransition(''),
            [NavigationAlphabet.RIGHT_SIBLING],
            self.current_state,
            '',
        )
        self.pda.add_transition(self_transition)
        return self.current_state

    def visitTerminal(self, node: Any) -> int:
        """Visit terminal AST leaf node and emit transition matching the node text/name.

        Args:
            node: Terminal node or context acting as a terminal.

        Returns:
            int: Resulting state index.
        """
        if isinstance(node, TerminalNode):
            logger.trace(f"Visiting terminal {node}")
            node_text = str(node).strip()
            node_transition = NodeTransition(node_text)
        else:
            logger.trace(f"Visiting {node.__class__.__name__} as terminal")
            node_text = f"{node.__class__.__name__}/0,0"
            node_transition = NodeTransition(node.__class__.__name__, 0, 0)

        logger.trace(f"is last branch: {self.__is_last_branch}, current node: {node}, node text: {node_text}")

        return self._add_up_transition(node, node_transition)

    def visitVar_wildcard(self, ctx: Any) -> int:
        """Visit named wildcard variable ($variable) and register variable binding.

        Args:
            ctx: Named variable wildcard context.

        Returns:
            int: Resulting state index.
        """
        label = ctx.getText()
        self.pda.named_wildcards.add(label)
        self._add_up_transition(ctx, NamedTransition(f"{label}"))
        return self.current_state

    def visitContains_wildcard(self, ctx: Any) -> Any:
        """Visit containment wildcard ($contains(...)) with body traversal transitions.

        Args:
            ctx: Contains wildcard context.

        Returns:
            Any: Target state index after visiting body.
        """
        self.add_body_transition()

        logger.trace(f"Type of contains wildcard: {ctx.getChild(2).__class__.__name__}")
        prune_tree = self.tree_pruner.visit(ctx)
        return prune_tree.getChild(2).accept(self)

    @abc.abstractmethod
    def visitSimple_compound_wildcard(self, ctx: Any) -> Any:
        """Visit simple compound wildcard ($*). Must be implemented by subclasses.

        Args:
            ctx: Simple compound wildcard context.
        """
        pass

    @abc.abstractmethod
    def visitMultiple_compound_wildcard(self, ctx: Any) -> Any:
        """Visit multiple compound wildcard ($**). Must be implemented by subclasses.

        Args:
            ctx: Multiple compound wildcard context.
        """
        pass

    def visitGenericMultiple_compound_wildcard(self, ctx: Any, blockChild: Any) -> int:
        """Compile a multi-compound body wildcard with pushdown stack frame markers.

        Args:
            ctx: Multi-compound wildcard context.
            blockChild: Child AST block statement to traverse within the compound scope.

        Returns:
            int: Target state index.

        Raises:
            Exception: If blockChild is None.
        """
        # Transition to push B on the stack
        dummy_state = Generic_to_PDA.add_body_transition(self)

        # Explore
        if blockChild is None:
            raise Exception("Body of multiple compound wildcard cannot be empty")
        ret = blockChild.accept(self)

        skip_transition = Transition(dummy_state, "B", NodeTransition(''), [], ret, 'B')
        self.pda.add_transition(skip_transition)

        return ret

    def add_body_transition(self, allow_multiple_compound: bool = True) -> int:
        """Push a block frame symbol 'B' on the stack and create compound navigation loops.

        Args:
            allow_multiple_compound: Whether to add full sibling/child exploratory cycles.

        Returns:
            int: State index representing the compound body entry.
        """
        dummy_state = self.pda.new_state()
        dummy_transition = Transition(self.current_state, "", NodeTransition(''), [], dummy_state, 'B')
        self.pda.add_transition(dummy_transition)
        self.current_state = dummy_state

        self.move_to_B.append(self.depth)

        # If we are in multiple compound mode, allow any combination of Right Sibling & Left Child transitions
        if allow_multiple_compound:
            next_state = self.pda.new_state()
            child_transition = Transition(self.current_state, "", NodeTransition(''), [], next_state, '')
            self.pda.add_transition(child_transition)

            self_transition = Transition(
                next_state, "", NodeTransition(''), [NavigationAlphabet.RIGHT_SIBLING], next_state, ''
            )
            self.pda.add_transition(self_transition)
            back_transition = Transition(
                next_state, "", NodeTransition(''), [NavigationAlphabet.LEFT_CHILD], self.current_state, 'I'
            )
            self.pda.add_transition(back_transition)
            self.current_state = next_state

        return dummy_state

    def _add_up_transition(self, node: Any, label: TransitionCondition | None = None) -> int:
        """Add ascend/parent navigation transitions matching current stack indentation depth.

        Args:
            node: Current parse tree node being ascended from.
            label: Transition condition symbol or None for an empty node transition.

        Returns:
            int: Newly created target state index.
        """
        if label is None:
            label = NodeTransition('')

        if self._is_last_node():
            logger.debug(f"Node {node} is the last node in the tree, adding transition to the end")
            self_transition = Transition(
                self.current_state,
                '',
                NodeTransition(''),
                [NavigationAlphabet.RIGHT_SIBLING],
                self.current_state,
                '',
            )
            self.pda.add_transition(self_transition)

            last_state = self.pda.new_state()
            transition = Transition(self.current_state, '', label, [], last_state, '')
            self.pda.add_transition(transition)
            self.current_state = last_state
            return last_state

        if len(self.move_to_B) > 0:
            return self._add_up_to_B_transition(label)

        return self._add_up_default_transition(label)

    def _add_up_default_transition(self, label: TransitionCondition) -> int:
        """Add standard parent ascent popping 'I' symbols based on current depth.

        Args:
            label: Transition condition to evaluate.

        Returns:
            int: Target state index.
        """
        next_state = self.pda.new_state()
        to_pop = 'I' * self.depth
        to_up = [NavigationAlphabet.PARENT] * self.depth
        self.depth = 0
        transition = Transition(
            self.current_state,
            to_pop,
            label,
            to_up + [NavigationAlphabet.RIGHT_SIBLING],
            next_state,
            '',
        )
        self.pda.add_transition(transition)
        self.current_state = next_state
        return next_state

    def _add_up_to_B_transition(self, label: TransitionCondition) -> int:
        """Add upward navigation popping to the nearest compound body 'B' stack frame.

        Args:
            label: Transition condition to evaluate.

        Returns:
            int: Intermediate match state index.
        """
        depth = self.move_to_B.pop()
        self.depth = depth

        q_before_end = self.current_state

        # Commit to an intermediate state
        match_state = self.pda.new_state()
        match_transition = Transition(self.current_state, '', label, [], match_state, '')
        self.pda.add_transition(match_transition)
        self.current_state = match_state

        # Move up as many times as there I on the stack and consume them
        up_transition = Transition(
            self.current_state, 'I', NodeTransition(''), [NavigationAlphabet.PARENT], self.current_state, ''
        )
        self.pda.add_transition(up_transition)

        # Consume the B from the stack
        next_state = self.pda.new_state()
        next_transition = Transition(self.current_state, 'B', NodeTransition(''), [], next_state, '')
        self.pda.add_transition(next_transition)
        self.current_state = next_state

        self._add_up_transition(None)
        q_after = self.current_state

        # Add a bypass transition for depth 0 (where B is on top of stack)
        bypass_transition = Transition(q_before_end, 'B', NodeTransition(''), [], q_after, '')
        self.pda.add_transition(bypass_transition)

        return match_state

    def _is_last_node(self) -> bool:
        """Check whether the current branch is the final branch of the pattern tree.

        Returns:
            bool: True if on the terminal branch.
        """
        return self.__is_last_branch

    def handle_empty_list(self, ctx: Any) -> Any:
        """Handle zero-element matching when list wildcard is present in list context.

        Args:
            ctx: Parse tree context containing a potential list.

        Returns:
            Any: Target state index or child visit result.
        """
        list_wildcard = self.lookahead(ctx, self.grammar.List_wildcardContext)
        if list_wildcard is not None:
            # If the list wildcard is the only statement in the list, we need to add a transition to handle 0 elements
            logger.trace("Handling empty list")
            return self._add_up_transition(ctx)
        return self.visitChildren(ctx)

    @staticmethod
    def lookahead(ctx: Any, clazz: type[T] | tuple[type[T], ...], predicate: Any = None) -> T | None:
        """Search descendants of ctx for an instance of clazz along a single-child chain.

        Args:
            ctx: Root context node to search downward from.
            clazz: Expected class type or tuple of class types.
            predicate: Optional filter callable returning bool.

        Returns:
            T | None: First matching descendant node, or None if not found.
        """
        curr = ctx
        while curr is not None:
            if isinstance(curr, clazz):
                return curr
            children = getattr(curr, 'children', None)
            if children is None or len(children) != 1:
                return None
            if predicate is not None and not predicate(curr):
                return None
            curr = children[0]
        return None

    @staticmethod
    def lookbehind(ctx: Any, clazz: type[T] | tuple[type[T], ...]) -> T | None:
        """Traverse ancestor parentCtx references to find an instance of clazz.

        Args:
            ctx: Starting context node.
            clazz: Expected ancestor class type or tuple of types.

        Returns:
            T | None: First matching ancestor node, or None if root reached.
        """
        if isinstance(ctx, clazz):
            return ctx
        if not hasattr(ctx, 'parentCtx'):
            return None
        return Generic_to_PDA.lookbehind(ctx.parentCtx, clazz)

