import math

from antlr4.tree.Tree import TerminalNode
from loguru import logger

from .tree_pruner import TreePruner
from ...antlr.python import Python3ParserVisitor, Python3Parser
from ...subpattern.SubPattern import loaded_subpatterns, SubPattern
from ...simulator.pda.PDA import PDA
from ...simulator.pda.PDA_alphabets import NavigationAlphabet
from ...simulator.pda.transition import NodeTransition, CallTransition, TransitionCondition, NamedTransition, Transition
from ..generic_to_pda import Generic_to_PDA, rightmost_terminal

class Python_to_PDA(Generic_to_PDA, Python3ParserVisitor):
    def __init__(self):
        grammar = Python3Parser
        skippable_nodes = [
                "StmtContext"
            ]
        remove_double_wildcard = [
            grammar.List_wildcardContext,
            grammar.Double_wildcardContext
        ]
        
        super().__init__(grammar, skippable_nodes, remove_double_wildcard)

    def visit(self, tree) -> dict[str, PDA]:
        logger.debug(f"Visiting tree: {tree}")
        self.__dict_pda = {}
        self.__var_names = {}
        self.__last_node = rightmost_terminal(tree)
        super().visit(tree)
        self.depth = 0
        self.pda.final_states = self.current_state
        logger.debug(f"var_names: {self.__var_names}")
        self.__dict_pda["__main__"] = self.pda
        return self.__dict_pda

    def define_boundaries(self, ctx):
        """
        Define the boundaries for the current context.
        :param ctx: The context to define boundaries for.
        :return: A tuple of (down, up) boundaries.
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
        else:
            for child in ctx.children:
                if self.lookahead(child, (self.grammar.Double_wildcardContext, self.grammar.List_wildcardContext)) is not None:
                    logger.trace(f"Child {child.__class__.__name__} is a double wildcard, setting boundaries to 0 and inf")
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

    def visitStmt(self, ctx:Python3Parser.StmtContext):
        # Handle double wildcard as Stmt
        logger.trace(f"Visiting Stmt {hash(ctx)}: {ctx.getText()}")

        lookahead_double_wildcard = self.lookahead(ctx, self.grammar.Double_wildcardContext)
        if lookahead_double_wildcard:
            return self.visitDouble_wildcard(lookahead_double_wildcard)

        self_transition = Transition(self.current_state, "", NodeTransition(''), [NavigationAlphabet.RIGHT_SIBLING],
                                      self.current_state, '')
        self.pda.add_transition(self_transition)

        return super().visitStatement(ctx)

    def visitAtom_wildcard(self, ctx:Python3Parser.Atom_wildcardContext):
        return ctx.getChild(0).accept(self)

    def visitSimple_compound_wildcard(self, ctx:Python3Parser.Simple_compound_wildcardContext):
        # Go to children
        child_state = self.pda.new_state()
        child_transition = Transition(self.current_state, "", NodeTransition(''), [NavigationAlphabet.LEFT_CHILD],
                                                                             child_state, 'I')
        self.pda.add_transition(child_transition)
        self.current_state = child_state
        self.depth += 1

        # Find body node
        self_transition = Transition(child_state, "", NodeTransition(''), [NavigationAlphabet.RIGHT_SIBLING],
                                                                     child_state, '')
        self.pda.add_transition(self_transition)

        # Explore body
        return ctx.getChild(0, self.grammar.BlockContext).accept(self)

    def visitDouble_wildcard(self, ctx:Python3Parser.Double_wildcardContext):
        # Handle a case when the double wildcard is the only statement
        parent_block = self.lookbehind(ctx, self.grammar.BlockContext)
        if parent_block is None:
            logger.error("Double wildcard not in a block")
            return self.current_state

        last_child = parent_block.getChild(parent_block.getChildCount() - 1)
        maybe_this = self.lookahead(last_child, self.grammar.Double_wildcardContext)
        if  maybe_this is not None and maybe_this == ctx:
            # If the double wildcard is the last statement of the block, we need to add a transition to the end of the
            # block
            self._add_up_transition(ctx)
        return self.current_state


    def visitParameters(self, ctx:Python3Parser.ParametersContext):
        return self._handle_empty_list(ctx)

    def visitVarargslist(self, ctx:Python3Parser.VarargslistContext):
        return self._handle_empty_list(ctx)

    def visitArgument(self, ctx:Python3Parser.ArgumentContext):
        return self._handle_empty_list(ctx)

    def visitVar_wildcard(self, ctx:Python3Parser.Var_wildcardContext):
        label = ctx.NAME().getText()
        # if label not in self.__var_names:
        #     uuid_label = str(uuid.uuid4())[:8]
        #     self.__var_names[label] = f"{label}_{uuid_label}"
        # label = self.__var_names[label]
        self.pda.named_wildcards.add(label)
        self._add_up_transition(ctx, NamedTransition(f"{label}"))
        return self.current_state

    def visitMultiple_compound_wildcard(self, ctx:Python3Parser.Multiple_compound_wildcardContext):
        # Get the body of the compound wildcard, then let the superclass handle the rest
        blockChild = ctx.getChild(0, self.grammar.BlockContext)

        return super().visitGenericMultiple_compound_wildcard(ctx, blockChild)


    def visitMacro_call(self, ctx:Python3Parser.Macro_callContext):
        subpattern_name = ctx.NAME().getText()
        logger.debug(f"Calling subpattern {subpattern_name}")
        if subpattern_name not in loaded_subpatterns:
            raise ValueError(f"SubPattern {subpattern_name} is not defined. Available subpatterns: {list(loaded_subpatterns.keys())}")

        # TODO: change handling of macro args
        # Should be able to handle 3 types -> maybe check types from macro?
        # 1: simple var wildcard -> can stay the same
        # 2: expr -> change all var in expr then compile
        # 3: stmts -> change all var in stmts then compile
        args_nodes = ctx.macro_args().macro_arg()
        if args_nodes is not None:
            args_names = [arg_node.getChild(0).getText()[1:] for arg_node in args_nodes]  # Remove the leading '?'
        else:
            args_names = []

        body = ctx.block()

        subpattern = loaded_subpatterns[subpattern_name]

        # TODO: same as before, change compilation in relation to macro args 
        transformations = subpattern.compile(ctx.parentCtx, body)
        self.__dict_pda.update(transformations)
        n_args_req = sum(1 for key in subpattern.args if subpattern.args[key] is None)
        if len(args_names) < n_args_req:
            logger.error(f"Macro {subpattern_name} requires at least {n_args_req} arguments, but got {len(args_names)}")
            raise ValueError(f"Macro {subpattern_name} requires at least {n_args_req} arguments, but got {len(args_names)}")

        # TODO: Probably no change but should be checked
        if subpattern.type == "OR":
            self.__visit_or_subpattern(subpattern, args_names)
        elif subpattern.type == "AND":
            self.__visit_and_subpattern(subpattern, args_names)
        else:
            logger.error(f"Unknown subpattern type {subpattern.type} for subpattern {subpattern_name}")

        return self._add_up_transition(ctx)

    def __visit_or_subpattern(self, subpattern: SubPattern, args):
        next_state = self.pda.new_state()
        subpattern_name = subpattern.name

        for transformation in subpattern.transformations:
            logger.trace(f"Adding transformation {transformation} for OR subpattern {subpattern_name}")
            transition = Transition(self.current_state, '', CallTransition(subpattern_name, transformation, args), [],
                                    next_state, '')
            self.pda.add_transition(transition)

        self.current_state = next_state
        return self.current_state

    def __visit_and_subpattern(self, subpattern: SubPattern, args):
        transformations = list(subpattern.transformations.keys())
        n = len(transformations)
        subpattern_name = subpattern.name

        if n == 0:
            return self.current_state

        if n > 10:
            logger.warning(
                f"Macro {subpattern_name} has {n} AND-clauses, which will create a PDA with {1 << n} states."
            )

        # Create 2^n states, one for each subset of matched transformations (represented by a bitmask)
        pda_states = {mask: self.pda.new_state() for mask in range(1, 1 << n)}
        pda_states[0] = self.current_state

        for mask in range(1 << n):
            current_pda_state = pda_states[mask]

            # Add a self-loop to navigate/skip statements that do not match any required transformations.
            # This allows matching transformations in any order, interspersed with other statements.
            # The navigation path is based on the original implementation's logic.
            if mask != (1 << n) - 1:
                nav_loop_transition = Transition(
                    current_pda_state,
                    "",
                    NodeTransition(""),
                    [
                        NavigationAlphabet.PARENT,
                        NavigationAlphabet.RIGHT_SIBLING,
                        NavigationAlphabet.LEFT_CHILD,
                    ],
                    current_pda_state,
                    "",
                )
                self.pda.add_transition(nav_loop_transition)

            # For each transformation not yet matched in the current subset (mask)
            for i, trans_name in enumerate(transformations):
                if not ((mask >> i) & 1):  # Check if i-th bit is not set
                    next_mask = mask | (1 << i)
                    next_pda_state = pda_states[next_mask]

                    # Add a transition to match the transformation and move to the next state (subset)
                    match_transition = Transition(
                        current_pda_state,
                        "",
                        CallTransition(subpattern_name, trans_name, args),
                        [],
                        next_pda_state,
                        "",
                    )
                    self.pda.add_transition(match_transition)

        # The final state is the one where all transformations have been matched
        self.current_state = pda_states[(1 << n) - 1]
        return self.current_state

    def _handle_empty_list(self, ctx):
        list_wildcard = self.lookahead(ctx, self.grammar.List_wildcardContext)
        if list_wildcard is not None:
            # If the list wildcard is the only statement in the list, we need to add a transition to handle 0 elements
            logger.trace("Handling empty list")
            return self._add_up_transition(ctx)
        return self.visitChildren(ctx)
