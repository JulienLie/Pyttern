"""Python pattern-to-PDA compiler translating Python AST patterns into Pushdown Automata."""

import math
from typing import Any

from loguru import logger

from .tree_pruner import BlockEndContext, TreePruner
from ...antlr.python import Python3Parser, Python3ParserVisitor
from ...simulator.pda.PDA_alphabets import NavigationAlphabet
from ...simulator.pda.transition import NamedTransition, NodeTransition, Transition
from ...subpattern.SubPattern import SubPatternCallContext, loaded_subpatterns
from ..generic_to_pda import Generic_to_PDA


class Python_to_PDA(Generic_to_PDA, Python3ParserVisitor):
    """Compiles Python AST patterns into Pushdown Automata (PDAs)."""

    def __init__(self) -> None:
        """Initialize the Python compiler with grammar rules, skippable nodes, and pruner."""
        grammar = Python3Parser
        skippable_nodes = [
            "StmtContext"
        ]
        remove_double_wildcard = [
            grammar.List_wildcardContext,
            grammar.Double_wildcardContext
        ]
        tree_pruner = TreePruner()

        super().__init__(grammar, skippable_nodes, remove_double_wildcard, tree_pruner)

    def define_boundaries(self, ctx: Any) -> tuple[int, int | float]:
        """Compute the minimum down and maximum up traversal bounds for an AST context.

        Args:
            ctx: Python AST rule context.

        Returns:
            tuple[int, int | float]: (down_limit, up_limit) bounds.
        """
        logger.trace(f"Defining boundaries for {ctx.__class__.__name__} {hash(ctx)}: {ctx.getText()}")
        down = up = 0
        if isinstance(ctx, (self.grammar.File_inputContext, self.grammar.BlockContext)):
            logger.trace(f"Context {ctx.__class__.__name__} is a file input or block, setting boundaries to 1 and inf")
            down = 1
            up = math.inf
        elif isinstance(ctx, self.grammar.If_stmtContext):
            logger.trace(f"Context {ctx.__class__.__name__} is an if statement, setting boundaries to 1 and inf")
            down = 1
            up = math.inf
        elif isinstance(ctx, self.grammar.ExprContext):  # TODO: generalize this probably
            logger.trace(f"Context {ctx.__class__.__name__} is an Expression context, setting boundaries to 1 and inf")
            down = 1
            up = math.inf
        else:
            for child in ctx.children:
                if self.lookahead(
                    child,
                    (self.grammar.Double_wildcardContext, self.grammar.List_wildcardContext, self.grammar.Subpattern_callContext),
                ) is not None:
                    logger.trace(f"Child {child.__class__.__name__} is a double wildcard or subpattern call, setting boundaries to 0 and inf")
                    up = math.inf
                    continue

                only_wildcard = lambda c: "wildcard" in c.__class__.__name__
                everything = lambda _: True
                predicate = everything if "list" in ctx.__class__.__name__ else only_wildcard

                simple_node = self.lookahead(child, self.grammar.Number_wildcardContext, predicate)
                if simple_node is not None:
                    numbers_node = simple_node.getChild(0, self.grammar.Wildcard_numberContext)
                    if numbers_node is not None:
                        logger.trace(f"Child {child.__class__.__name__} has wildcard numbers, visiting numbers node")
                        min_n, max_n = numbers_node.accept(self)
                        up += max_n
                        down += min_n
                        continue
                down += 1
                up += 1

        return down, up

    def _find_direct_subpattern_calls(self, node: Any) -> list[Any]:
        """Find subpattern call nodes directly contained within a subtree without descending into blocks.

        Args:
            node: Target parse tree node.

        Returns:
            list[Any]: List of direct Subpattern_callContext nodes found.
        """
        if isinstance(node, Python3Parser.Subpattern_callContext):
            return [node]
        if isinstance(node, Python3Parser.BlockContext):
            return []
        if not hasattr(node, 'children') or node.children is None:
            return []
        calls = []
        for child in node.children:
            calls.extend(self._find_direct_subpattern_calls(child))
        return calls

    def visitBlockEnd(self, ctx: BlockEndContext) -> Any:
        """Visit synthetic BlockEnd context and emit right-sibling self loop.

        Args:
            ctx: Synthetic BlockEnd marker context.

        Returns:
            Any: Child visit result.
        """
        logger.debug("Visiting BlockEnd")
        node = self.current_state
        self_transition = Transition(node, "", NodeTransition(""), [NavigationAlphabet.RIGHT_SIBLING], node, "")
        self.pda.add_transition(self_transition)
        return self.visitChildren(ctx)

    @staticmethod
    def _extract_subpattern_args(subpattern_call: Any) -> list[str]:
        """Extract argument variable names from a subpattern call node.

        Args:
            subpattern_call: Subpattern_callContext AST node.

        Returns:
            list[str]: Cleaned argument parameter names without leading '$' or '?'.
        """
        args_container = subpattern_call.subpattern_args()
        if args_container is not None and args_container.subpattern_arg() is not None:
            return [arg_node.getChild(0).getText()[1:] for arg_node in args_container.subpattern_arg()]
        return []

    def visitFile_input(self, ctx: Python3Parser.File_inputContext) -> Any:
        """Visit top-level file input and handle file-level subpattern invocations.

        Args:
            ctx: File_input context.

        Returns:
            Any: Compiled PDA entry transition result or standard child visit.
        """
        subpattern_call = self.lookahead(ctx, Python3Parser.Subpattern_callContext)
        logger.trace(f"Checking for subpatterns in {ctx.getText()} -> {subpattern_call}")

        if subpattern_call:
            name = subpattern_call.NAME().getText()
            subpattern = loaded_subpatterns.get(name)
            logger.debug(f"Handling {subpattern} at block level")

            context = SubPatternCallContext(ctx, None)
            transformations = subpattern.compile(context)
            self.dict_pda.update(transformations)

            args_names = self._extract_subpattern_args(subpattern_call)
            new_state = subpattern.generate_pda(self.pda, args_names, self.current_state)
            self.current_state = new_state
            return self._add_up_transition(NodeTransition(ctx.__class__.__name__))

        return super().visitFile_input(ctx)

    def visitStmt(self, ctx: Python3Parser.StmtContext) -> Any:
        """Visit statement context and route subpattern calls or double wildcards.

        Args:
            ctx: Stmt context node.

        Returns:
            Any: Target state index.

        Raises:
            ValueError: If a called subpattern is not registered.
        """
        logger.trace(f"Visiting Stmt {hash(ctx)}: {ctx.getText()}")


        lookahead_double_wildcard = self.lookahead(ctx, self.grammar.Double_wildcardContext)
        if lookahead_double_wildcard:
            return self.visitDouble_wildcard(lookahead_double_wildcard)

        lookahead_call_transition = self.lookahead(ctx, Python3Parser.Subpattern_callContext)
        if lookahead_call_transition:
            name = lookahead_call_transition.NAME().getText()
            subpattern = loaded_subpatterns.get(name)
            if subpattern is None:
                logger.error(f"Calling {name} subpattern, but was not loaded. Loaded subpattern: {list(loaded_subpatterns.keys())}")
                raise ValueError(f"Calling {name} subpattern, but was not loaded. Loaded subpattern: {list(loaded_subpatterns.keys())}")

            context = SubPatternCallContext(ctx, None)
            transformations = subpattern.compile(context)
            self.dict_pda.update(transformations)

            args_names = self._extract_subpattern_args(lookahead_call_transition)
            new_state = subpattern.generate_pda(self.pda, args_names, self.current_state)
            self.current_state = new_state

            self._restrict_stmt = True
            logger.trace("Restricting self transition on next stmt")
            return self.current_state

        return super().visitStatement(ctx)

    def visitAtom_wildcard(self, ctx: Python3Parser.Atom_wildcardContext) -> Any:
        """Visit atomic wildcard and delegate to child wildcard node.

        Args:
            ctx: Atom wildcard context.

        Returns:
            Any: Target state or child visitor result.
        """
        return ctx.getChild(0).accept(self)

    def visitExpr(self, ctx: Python3Parser.ExprContext) -> Any:
        """Visit expression context and check for embedded number wildcards or subpattern calls.

        Args:
            ctx: Expression context.

        Returns:
            Any: Result of wildcard/subpattern handling or standard child visit.
        """
        wildcard = self.lookahead(ctx, (Python3Parser.Number_wildcardContext,))
        if wildcard is not None:
            return wildcard.accept(self)

        subpattern_call = self.lookahead(ctx, Python3Parser.Subpattern_callContext)
        if subpattern_call is not None:
            name = subpattern_call.NAME().getText()
            subpattern = loaded_subpatterns.get(name)
            if subpattern is not None:
                context = SubPatternCallContext(ctx, None)
                transformations = subpattern.compile(context)
                self.dict_pda.update(transformations)

                args_names = self._extract_subpattern_args(subpattern_call)
                new_state = subpattern.generate_pda(self.pda, args_names, self.current_state)
                self.current_state = new_state
                return self._add_up_transition(ctx)

        return self.visitChildren(ctx)

    def visitSimple_compound_wildcard(self, ctx: Python3Parser.Simple_compound_wildcardContext) -> Any:
        """Visit simple compound wildcard ($*) and set up child navigation into block body.

        Args:
            ctx: Simple compound wildcard context.

        Returns:
            Any: Result of visiting block body.
        """
        # Go to children
        child_state = self.pda.new_state()
        child_transition = Transition(
            self.current_state, "", NodeTransition(''), [NavigationAlphabet.LEFT_CHILD], child_state, 'I'
        )
        self.pda.add_transition(child_transition)
        self.current_state = child_state
        self.depth += 1

        # Find body node
        self_transition = Transition(
            child_state, "", NodeTransition(''), [NavigationAlphabet.RIGHT_SIBLING], child_state, ''
        )
        self.pda.add_transition(self_transition)

        # Explore body
        return ctx.getChild(0, self.grammar.BlockContext).accept(self)

    def visitDouble_wildcard(self, ctx: Python3Parser.Double_wildcardContext) -> int:
        """Visit double wildcard ($$) and add upward transition if it terminates parent block.

        Args:
            ctx: Double wildcard context.

        Returns:
            int: Current state index.
        """
        # Handle a case when the double wildcard is the only statement
        parent_block = self.lookbehind(ctx, self.grammar.BlockContext)
        if parent_block is None:
            logger.error("Double wildcard not in a block")
            return self.current_state

        last_child = parent_block.getChild(parent_block.getChildCount() - 1)
        maybe_this = self.lookahead(last_child, self.grammar.Double_wildcardContext)
        if maybe_this is not None and maybe_this == ctx:
            # If the double wildcard is the last statement of the block, we need to add a transition to the end of the
            # block
            self._add_up_transition(ctx)
        return self.current_state

    def visitParameters(self, ctx: Python3Parser.ParametersContext) -> Any:
        """Visit formal parameters context and handle empty list wildcard cases.

        Args:
            ctx: Parameters context.

        Returns:
            Any: Target state or child visit result.
        """
        return self.handle_empty_list(ctx)

    def visitVarargslist(self, ctx: Python3Parser.VarargslistContext) -> Any:
        """Visit variable argument list context and handle empty list cases.

        Args:
            ctx: Varargslist context.

        Returns:
            Any: Target state or child visit result.
        """
        return self.handle_empty_list(ctx)

    def visitArgument(self, ctx: Python3Parser.ArgumentContext) -> Any:
        """Visit call argument context and handle empty list cases.

        Args:
            ctx: Argument context.

        Returns:
            Any: Target state or child visit result.
        """
        return self.handle_empty_list(ctx)

    def visitVar_wildcard(self, ctx: Python3Parser.Var_wildcardContext) -> int:
        """Visit named wildcard variable ($var) and record variable binding in PDA.

        Args:
            ctx: Named variable wildcard context.

        Returns:
            int: Current state index.
        """
        label = ctx.NAME().getText()
        self.pda.named_wildcards.add(label)
        self._add_up_transition(ctx, NamedTransition(f"{label}"))
        return self.current_state

    def visitMultiple_compound_wildcard(self, ctx: Python3Parser.Multiple_compound_wildcardContext) -> Any:
        """Visit multiple compound wildcard ($**) and delegate to generic handler.

        Args:
            ctx: Multiple compound wildcard context.

        Returns:
            Any: Target state index.
        """
        blockChild = ctx.getChild(0, self.grammar.BlockContext)
        return super().visitGenericMultiple_compound_wildcard(ctx, blockChild)

    def visitSubpattern_call(self, ctx: Python3Parser.Subpattern_callContext) -> int:
        """Compile a subpattern call invocation into PDA transitions.

        Args:
            ctx: Subpattern call context node.

        Returns:
            int: Newly generated target state index.

        Raises:
            ValueError: If subpattern is not found or arguments count does not match requirements.
        """
        subpattern_name = ctx.NAME().getText()
        logger.debug(f"Compiling subpattern call '{subpattern_name}'")
        if subpattern_name not in loaded_subpatterns:
            raise ValueError(f"SubPattern {subpattern_name} is not defined. Available subpatterns: {list(loaded_subpatterns.keys())}")

        args_names = self._extract_subpattern_args(ctx)
        body = ctx.block()
        subpattern = loaded_subpatterns[subpattern_name]

        ast_ctx = ctx.parentCtx
        while "wildcard" in ast_ctx.__class__.__name__:
            ast_ctx = ast_ctx.parentCtx
        context = SubPatternCallContext(ast_ctx, body=body)

        transformations = subpattern.compile(context)
        self.dict_pda.update(transformations)

        n_args_req = sum(1 for key in subpattern.args if subpattern.args[key] is None)
        if len(args_names) < n_args_req:
            logger.error(f"Subpattern {subpattern_name} requires at least {n_args_req} arguments, but got {len(args_names)}")
            raise ValueError(f"Subpattern {subpattern_name} requires at least {n_args_req} arguments, but got {len(args_names)}")

        self.current_state = subpattern.generate_pda(self.pda, args_names, self.current_state)
        return self._add_up_transition(ctx)