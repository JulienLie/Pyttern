import math

from antlr4.tree.Tree import TerminalNode
from loguru import logger

from .tree_pruner import TreePruner
from ...antlr.python import Python3ParserVisitor, Python3Parser
from ...subpattern.SubPattern import loaded_subpatterns, SubPatternCallContext
from ...simulator.pda.PDA_alphabets import NavigationAlphabet
from ...simulator.pda.transition import NodeTransition, NamedTransition, Transition
from ..generic_to_pda import Generic_to_PDA

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
        tree_pruner = TreePruner()
        
        super().__init__(grammar, skippable_nodes, remove_double_wildcard, tree_pruner)

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
    
    def _find_direct_subpattern_calls(self, node):
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

    def visitBlock(self, ctx:Python3Parser.BlockContext):
        subpattern_call = self.lookahead(ctx, Python3Parser.Subpattern_callContext)
        logger.trace(f"Checking for subpattern in {ctx.getText()} -> {subpattern_call}")

        if subpattern_call:
            name = subpattern_call.NAME().getText()
            subpattern = loaded_subpatterns.get(name)
            logger.debug(f"Handling {subpattern} at block level")

            context = SubPatternCallContext(ctx, None, True)
            transformations = subpattern.compile(context)
            self.dict_pda.update(transformations)

            args_nodes = subpattern_call.subpattern_args().subpattern_arg() if subpattern_call.subpattern_args() is not None else None
            if args_nodes is not None:
                args_names = [arg_node.getChild(0).getText()[1:] for arg_node in args_nodes]  # Remove the leading '?'
            else:
                args_names = []

            new_state = subpattern.generate_pda(self.pda, args_names, self.current_state, context)
            self.current_state = new_state
            return self._add_up_transition(NodeTransition(ctx.__class__.__name__))

        return self.visitChildren(ctx)

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
        return self.handle_empty_list(ctx)

    def visitVarargslist(self, ctx:Python3Parser.VarargslistContext):
        return self.handle_empty_list(ctx)

    def visitArgument(self, ctx:Python3Parser.ArgumentContext):
        return self.handle_empty_list(ctx)

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


    def visitSubpattern_call(self, ctx:Python3Parser.Subpattern_callContext):
        subpattern_name = ctx.NAME().getText()
        logger.debug(f"Calling subpattern {subpattern_name}")
        if subpattern_name not in loaded_subpatterns:
            raise ValueError(f"SubPattern {subpattern_name} is not defined. Available subpatterns: {list(loaded_subpatterns.keys())}")

        # TODO: change handling of subpattern args
        # Should be able to handle 3 types -> maybe check types from subpattern?
        # 1: simple var wildcard -> can stay the same
        # 2: expr -> change all var in expr then compile
        # 3: stmts -> change all var in stmts then compile
        args_nodes = ctx.subpattern_args().subpattern_arg() if ctx.subpattern_args() is not None else None
        if args_nodes is not None:
            args_names = [arg_node.getChild(0).getText()[1:] for arg_node in args_nodes]  # Remove the leading '?'
        else:
            args_names = []

        body = ctx.block()

        subpattern = loaded_subpatterns[subpattern_name]

        context = SubPatternCallContext(ast_ctx=ctx.parentCtx, body=body, alone=False) # Alone is always false here as it should be match at body lvl

        # TODO: same as before, change compilation in relation to subpattern args 
        transformations = subpattern.compile(context)
        self.dict_pda.update(transformations)
        n_args_req = sum(1 for key in subpattern.args if subpattern.args[key] is None)
        if len(args_names) < n_args_req:
            logger.error(f"Subpattern {subpattern_name} requires at least {n_args_req} arguments, but got {len(args_names)}")
            raise ValueError(f"Subpattern {subpattern_name} requires at least {n_args_req} arguments, but got {len(args_names)}")


        self.current_state = subpattern.generate_pda(self.pda, args_names, self.current_state, context)
        return self._add_up_transition(ctx)