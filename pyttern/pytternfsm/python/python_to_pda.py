import math

from loguru import logger

from pyttern._pyttern_cpp import Node

from ...subpattern.SubPattern import loaded_subpatterns, SubPatternCallContext
from ...simulator.pda.PDA_alphabets import NavigationAlphabet
from ...simulator.pda.transition import NodeTransition, NamedTransition, Transition
from ..generic_to_pda import Generic_to_PDA

class Python_to_PDA(Generic_to_PDA):
    def __init__(self):
        skippable_nodes = [
            "Stmt",
            "StmtContext"
        ]
        remove_double_wildcard = [
            "List_wildcard",
            "Double_wildcard"
        ]
        
        super().__init__(skippable_nodes, remove_double_wildcard)

    def define_boundaries(self, ctx: Node):
        """
        Define the boundaries for the current context.
        :param ctx: The context to define boundaries for.
        :return: A tuple of (down, up) boundaries.
        """
        rule_name = getattr(ctx, "rule_name", ctx.__class__.__name__)
        logger.trace(f"Defining boundaries for {rule_name} {hash(ctx)}: {ctx.getText()}")
        down = up = 0
        if rule_name in ("File_input", "Block"):
            logger.trace(f"Context {rule_name} is a file input or block, setting boundaries to 1 and inf")
            down = 1
            up = math.inf
        elif rule_name in ("If_stmt"):
            logger.trace(f"Context {rule_name} is an if statement, setting boundaries to 1 and inf")
            down = 1
            up = math.inf
        elif rule_name in ("Expr"): # TODO: generalize this probably
            logger.trace(f"Context {rule_name} is an Expression context, setting boundaries to 1 and inf")
            down = 1
            up = math.inf
        else:
            for child in ctx.children:
                if self.lookahead(child, ("Double_wildcard", "List_wildcard", "Subpattern_call")) is not None:
                    child_rule = getattr(child, "rule_name", child.__class__.__name__)
                    logger.trace(f"Child {child_rule} is a double wildcard or subpattern call, setting boundaries to 0 and inf")
                    up = math.inf
                    continue

                only_wildcard = lambda c: "wildcard" in getattr(c, "rule_name", c.__class__.__name__).lower()
                everything = lambda _: True
                predicate = everything if "list" in rule_name.lower() else only_wildcard

                simple_node = self.lookahead(child, "Number_wildcard", predicate)
                if simple_node is not None:
                    numbers_node = simple_node.getChild(0, "Wildcard_number")
                    if numbers_node is not None:
                        logger.trace(f"Child {getattr(child, 'rule_name', child.__class__.__name__)} has wildcard numbers, visiting numbers node")
                        min_n, max_n = numbers_node.accept(self)
                        up += max_n
                        down += min_n
                        continue
                down += 1
                up += 1

        return down, up
    
    def _find_direct_subpattern_calls(self, node):
        if node.rule_name in ("Subpattern_call"):
            return [node]
        if node.rule_name in ("Block"):
            return []
        if not hasattr(node, 'children') or node.children is None:
            return []
        calls = []
        for child in node.children:
            calls.extend(self._find_direct_subpattern_calls(child))
        return calls

    def visitBlockEnd(self, ctx: Node):
        logger.debug("Visiting BlockEnd")
        node = self.current_state
        self_transition = Transition(node, "", NodeTransition(""), [NavigationAlphabet.RIGHT_SIBLING], node, "")
        self.pda.add_transition(self_transition)
        return self.visitChildren(ctx)

    def visitFile_input(self, ctx):
        subpattern_call = self.lookahead(ctx, "Subpattern_call")
        logger.trace(f"Checking for subpatterns in {ctx.getText()} -> {subpattern_call}")

        if subpattern_call:
            name = subpattern_call.NAME().getText()
            subpattern = loaded_subpatterns.get(name)
            logger.debug(f"Handling {subpattern} at block level")

            context = SubPatternCallContext(ctx, None)
            transformations = subpattern.compile(context)
            self.dict_pda.update(transformations)

            args_nodes = subpattern_call.subpattern_args().subpattern_arg() if subpattern_call.subpattern_args() is not None else None
            if args_nodes is not None:
                args_names = [arg_node.getChild(0).getText()[1:] for arg_node in args_nodes]  # Remove the leading '?'
            else:
                args_names = []

            new_state = subpattern.generate_pda(self.pda, args_names, self.current_state)
            self.current_state = new_state
            return self._add_up_transition(NodeTransition(getattr(ctx, "rule_name", ctx.__class__.__name__)))

        return self.visitChildren(ctx)

    def visitStmt(self, ctx:Node):
        # Handle double wildcard as Stmt
        logger.trace(f"Visiting Stmt {hash(ctx)}: {ctx.getText()}")

        lookahead_double_wildcard = self.lookahead(ctx, "Double_wildcard")
        if lookahead_double_wildcard:
            return self.visitDouble_wildcard(lookahead_double_wildcard)

        lookahead_call_transition = self.lookahead(ctx, "Subpattern_call")
        if lookahead_call_transition:
            name = lookahead_call_transition.NAME().getText()
            subpattern = loaded_subpatterns.get(name)
            if subpattern is None:
                logger.error(f"Calling {name} subpattern, but was not loaded. Loaded subpattern: {list(loaded_subpatterns.keys())}")
                raise ValueError(f"Calling {name} subpattern, but was not loaded. Loaded subpattern: {list(loaded_subpatterns.keys())}")

            context = SubPatternCallContext(ctx, None)
            transformations = subpattern.compile(context)
            self.dict_pda.update(transformations)

            args_nodes = lookahead_call_transition.subpattern_args().subpattern_arg() if lookahead_call_transition.subpattern_args() is not None else None
            if args_nodes is not None:
                args_names = [arg_node.getChild(0).getText()[1:] for arg_node in args_nodes]  # Remove the leading '?'
            else:
                args_names = []

            new_state = subpattern.generate_pda(self.pda, args_names, self.current_state)
            self.current_state = new_state

            self._restrict_stmt = True
            logger.trace("Restricting self transition on next stmt")
            return self.current_state

        return super().visitStatement(ctx)

    def visitAtom_wildcard(self, ctx: Node):
        return ctx.getChild(0).accept(self)

    def visitExpr(self, ctx: Node):
        wildcard = self.lookahead(ctx, "Number_wildcard")
        if wildcard is not None:
            return wildcard.accept(self)

        subpattern_call = self.lookahead(ctx, "Subpattern_call")
        if subpattern_call is not None:
            name = subpattern_call.NAME().getText()
            subpattern = loaded_subpatterns.get(name)
            if subpattern is not None:
                context = SubPatternCallContext(ctx, None)
                transformations = subpattern.compile(context)
                self.dict_pda.update(transformations)

                args_nodes = subpattern_call.subpattern_args().subpattern_arg() if subpattern_call.subpattern_args() is not None else None
                args_names = [arg_node.getChild(0).getText()[1:] for arg_node in args_nodes] if args_nodes else []

                new_state = subpattern.generate_pda(self.pda, args_names, self.current_state)
                self.current_state = new_state
                return self._add_up_transition(ctx)

        return self.visitChildren(ctx)



    def visitSimple_compound_wildcard(self, ctx:Node):
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
        return ctx.getChild(0, "Block").accept(self)

    def visitDouble_wildcard(self, ctx:Node):
        # Handle a case when the double wildcard is the only statement
        parent_block = self.lookbehind(ctx, "Block")
        if parent_block is None:
            logger.error("Double wildcard not in a block")
            return self.current_state

        last_child = parent_block.getChild(parent_block.getChildCount() - 1)
        maybe_this = self.lookahead(last_child, "Double_wildcard")
        if  maybe_this is not None and maybe_this == ctx:
            # If the double wildcard is the last statement of the block, we need to add a transition to the end of the
            # block
            self._add_up_transition(ctx)
        return self.current_state


    def visitParameters(self, ctx:Node):
        return self.handle_empty_list(ctx)

    def visitVarargslist(self, ctx:Node):
        return self.handle_empty_list(ctx)

    def visitArgument(self, ctx:Node):
        return self.handle_empty_list(ctx)

    def visitVar_wildcard(self, ctx:Node):
        label = ctx.getChild(1).getText()
        # if label not in self.__var_names:
        #     uuid_label = str(uuid.uuid4())[:8]
        #     self.__var_names[label] = f"{label}_{uuid_label}"
        # label = self.__var_names[label]
        self.pda.named_wildcards.add(label)
        self._add_up_transition(ctx, NamedTransition(f"{label}"))
        return self.current_state

    def visitMultiple_compound_wildcard(self, ctx:Node):
        # Get the body of the compound wildcard, then let the superclass handle the rest
        blockChild = ctx.getChild(0, "Block")

        return super().visitGenericMultiple_compound_wildcard(ctx, blockChild)

    def visitSubpattern_call(self, ctx:Node):
        subpattern_name = ctx.NAME().getText()
        logger.debug(f"Calling subpattern {subpattern_name}")
        if subpattern_name not in loaded_subpatterns:
            raise ValueError(f"SubPattern {subpattern_name} is not defined. Available subpatterns: {list(loaded_subpatterns.keys())}")

        args_nodes = ctx.subpattern_args().subpattern_arg() if ctx.subpattern_args() is not None else None
        if args_nodes is not None:
            args_names = [arg_node.getChild(0).getText()[1:] for arg_node in args_nodes]  # Remove the leading '?'
        else:
            args_names = []

        body = ctx.block()

        subpattern = loaded_subpatterns[subpattern_name]

        ast_ctx = ctx.parentCtx
        while ast_ctx is not None and "wildcard" in getattr(ast_ctx, "rule_name", ast_ctx.__class__.__name__).lower():
            ast_ctx = ast_ctx.parentCtx
        context = SubPatternCallContext(ast_ctx, body=body) # first parent in also subpattern

        transformations = subpattern.compile(context)
        self.dict_pda.update(transformations)
        n_args_req = sum(1 for key in subpattern.args if subpattern.args[key] is None)
        if len(args_names) < n_args_req:
            logger.error(f"Subpattern {subpattern_name} requires at least {n_args_req} arguments, but got {len(args_names)}")
            raise ValueError(f"Subpattern {subpattern_name} requires at least {n_args_req} arguments, but got {len(args_names)}")

        # self._restrict_stmt = True
        # logger.trace("Restricting self transition on next stmt")


        self.current_state = subpattern.generate_pda(self.pda, args_names, self.current_state)
        return self._add_up_transition(ctx)
