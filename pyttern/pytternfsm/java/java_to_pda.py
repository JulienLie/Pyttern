import math

from antlr4.tree.Tree import TerminalNode
from loguru import logger

from ...antlr.java import JavaParserVisitor, JavaParser
from ...simulator.pda.PDA import PDA
from ...simulator.pda.PDA_alphabets import NavigationAlphabet
from ...simulator.pda.transition import NodeTransition, CallTransition, TransitionCondition, NamedTransition, Transition


def rightmost_terminal(root):
    node = root
    # Drill down the last child at each level until you hit a terminal
    while not isinstance(node, TerminalNode) or "wildcard" in node.__class__.__name__:
        # If you only have getChildren(), materialize once per level
        children = list(node.getChildren())
        if not children:
            return None  # malformed/empty subtree
        node = children[-1]
    return node

class Java_to_PDA(JavaParserVisitor.JavaParserVisitor):
    def __init__(self):
        self.pda = PDA()
        self.current_state = self.pda.initial_state
        self.depth = 0
        self.move_to_B = []
        self.__var_names = {}
        self.__last_node = None
        self.__is_last_branch = True

    def visit(self, tree):
        logger.debug(f"Visiting tree: {tree}")
        self.__var_names = {}
        self.__last_node = rightmost_terminal(tree)
        super().visit(tree)
        self.depth = 0
        self.pda.final_states = self.current_state
        logger.debug(f"var_names: {self.__var_names}")
        return self.pda

    def visitChildren(self, node):
        logger.debug(f"Visiting {node}")
        logger.debug(f"Class name: {node.__class__.__name__} {hash(node)}: {node.getText()}")

        children = node.children
        if len(children) == 0:
            return self.visitTerminal(node)

        down, up = self._define_boundaries(node)

        while len(children) > 1 and (
                # self.lookahead(children[-1], JavaParser.JavaParser.Double_wildcardContext) or
                self.lookahead(children[-1], JavaParser.JavaParser.List_wildcardContext)
        ):
            children.pop()
            logger.debug("Remove double wildcard")

        if node.__class__.__name__ in [
                "BlockStatementContext",
                "PackageDeclarationContext",
                "ImportDeclarationContext",
                "TypeDeclarationContext",
                "ClassBodyDeclarationContext",
                "MemberDeclarationContext",
                "InterfaceBodyDeclarationContext",
                "RecordDeclarationContext",
                "CompactConstructorDeclarationContext"
            ]:
            self_transition = Transition(self.current_state, "", NodeTransition(''), [NavigationAlphabet.RIGHT_SIBLING],
                                        self.current_state, '')
            self.pda.add_transition(self_transition)

        next_state = self.pda.new_state()
        transition = Transition(self.current_state, "", NodeTransition(node.__class__.__name__, down, up),
                                [NavigationAlphabet.LEFT_CHILD], next_state, 'I')
        self.pda.add_transition(transition)
        self.current_state = next_state

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

    def _define_boundaries(self, ctx):
        """
        Define the boundaries for the current context.
        :param ctx: The context to define boundaries for.
        :return: A tuple of (down, up) boundaries.
        """
        logger.debug(f"Defining boundaries for {ctx.__class__.__name__} {hash(ctx)}: {ctx.getText()}")
        down = up = 0

        if isinstance(ctx, (JavaParser.JavaParser.CompilationUnitContext, JavaParser.JavaParser.ClassBodyContext, JavaParser.JavaParser.BlockContext)):
            logger.debug(f"Context {ctx.__class__.__name__} is a file input or block, setting boundaries to 0 and inf")
            down = 0
            up = math.inf
        elif isinstance(ctx, (JavaParser.JavaParser.BlockStatementContext)):
            # Special case for the simple wildcard: a block statement can have either 1 child (most cases) or 2 children (local variable definition)
            logger.debug(f"Context {ctx.__class__.__name__} is a block statement, setting boundaries to 1 and 2")
            down = 1
            up = 2
        else:
            for child in ctx.children:
                if self.lookahead(child, (JavaParser.JavaParser.List_wildcardContext,
                                          JavaParser.JavaParser.Simple_compound_wildcardContext,
                                          JavaParser.JavaParser.Contains_wildcardContext)) is not None:
                    logger.debug(f"Child {child.__class__.__name__} is a list or compound wildcard, setting boundaries to 0 and inf")
                    up = math.inf
                    continue

                only_wildcard = lambda c: "wildcard" in c.__class__.__name__
                everything = lambda _: True
                predicate = everything if "list" in ctx.__class__.__name__ else only_wildcard

                simple_node = self.lookahead(child, JavaParser.JavaParser.Number_wildcardContext, predicate)
                if simple_node is not None:
                    numbers_node = simple_node.getChild(0, JavaParser.JavaParser.Wildcard_numberContext)
                    if numbers_node is not None:
                        logger.debug(f"Child {child.__class__.__name__} has wildcard numbers, visiting numbers node")
                        min_n, max_n = numbers_node.accept(self)
                        up += max_n
                        down += min_n
                        continue
                down += 1
                up += 1

        return down, up

    def visitStatement(self, ctx):
        # Handle double wildcard as Stmt
        logger.debug(f"Visiting Stmt {hash(ctx)}: {ctx.getText()}")

        """
        lookahead_double_wildcard = self.lookahead(ctx, JavaParser.Double_wildcardContext)
        if lookahead_double_wildcard:
            return self.visitDouble_wildcard(lookahead_double_wildcard)
        """

        lookahead_multiple_body = self.lookahead(ctx, JavaParser.JavaParser.Multiple_compound_wildcardContext)
        if lookahead_multiple_body:
            return self.visitMultiple_compound_wildcard(lookahead_multiple_body)

        lookahead_simple_wildcard = self.lookahead(ctx, JavaParser.JavaParser.Simple_wildcardContext)
        if lookahead_simple_wildcard:
            return self.visitSimple_wildcard(lookahead_simple_wildcard)
        
        lookahead_number_wildcard = self.lookahead(ctx, JavaParser.JavaParser.Number_wildcardContext)
        if lookahead_number_wildcard:
            return self.visitNumber_wildcard(lookahead_number_wildcard)

        return self.visitChildren(ctx)

    def visitExpr_wildcard(self, ctx):
        return ctx.getChild(0).accept(self)

    def visitStmt_wildcard(self, ctx):
        return ctx.getChild(0).accept(self)

    def visitCompound_wildcard(self, ctx):
        return ctx.getChild(0).accept(self)

    def visitAtom_wildcard(self, ctx):
        return ctx.getChild(0).accept(self)

    def visitSimple_wildcard(self, ctx):
        return self._add_up_transition(ctx)

    def visitNumber_wildcard(self, ctx):
        numbers_node = ctx.getChild(0, JavaParser.JavaParser.Wildcard_numberContext)
        low, high = numbers_node.accept(self)
        logger.debug(f"Visiting Simple_wildcard with numbers: low={low}, high={high}")

        if low > high:
            logger.error(f"Invalid simple wildcard: low={low} > high={high}")
            raise ValueError(f"Invalid simple wildcard: low={low} > high={high}")

        for _ in range(1, low):
            # Add transitions for low - 1
            next_state = self.pda.new_state()
            transition = Transition(self.current_state, '', NodeTransition(''), [NavigationAlphabet.RIGHT_SIBLING],
                                                                           next_state, '')
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
            transition = Transition(self.current_state, '', NodeTransition(''), [NavigationAlphabet.RIGHT_SIBLING],
                                                                           next_state, '')
            self.pda.add_transition(transition)

            # No more siblings
            up_transition = Transition(next_state, '', NodeTransition(''), [], dummy_state, '')
            self.pda.add_transition(up_transition)
            self.current_state = next_state

        self.current_state = dummy_state
        return self._add_up_transition(ctx)

    def visitWildcard_number(self, ctx):
        low = int(ctx.DECIMAL_LITERAL(0).getText())
        high = int(ctx.DECIMAL_LITERAL(1).getText()) if ctx.DECIMAL_LITERAL(1) else math.inf
        if ctx.COMMA() is None:
            high = low
        logger.debug(f"Visiting Wildcard_number: low={low}, high={high}")
        if low > high:
            logger.error(f"Invalid wildcard number: low={low} > high={high}")
            return 1, 1
        return low, high

    def visitSimple_compound_wildcard(self, ctx):
        # Find body node
        self_transition = Transition(self.current_state, "", NodeTransition(''), [NavigationAlphabet.RIGHT_SIBLING],
                                                                     self.current_state, '')
        self.pda.add_transition(self_transition)

        # Here, we need to handle different cases: try/catch, switch/case, and block vs statement
        inside_wildcard_state = self.pda.new_state()

        # TRY/CATCH
        # Add single left child transition in case it's a catchClause -> Block
        catchclause_transition = Transition(self.current_state, "", NodeTransition("CatchClauseContext", 0, math.inf), 
                                     [NavigationAlphabet.LEFT_CHILD], self.current_state, 'I')
        self.pda.add_transition(catchclause_transition)

        # Add single left child transition in case it's a finallyBlock -> Block
        finally_transition = Transition(self.current_state, "", NodeTransition("FinallyBlockContext", 0, math.inf), 
                                     [NavigationAlphabet.LEFT_CHILD], self.current_state, 'I')
        self.pda.add_transition(finally_transition)

        # SWITCH/CASE
        # Add single left child transition in case it's a switchBlockStatementGroup -> Block
        switch_state = self.pda.new_state()
        switch_transition = Transition(self.current_state, "", NodeTransition("SwitchBlockStatementGroupContext", 0, math.inf), 
                                     [NavigationAlphabet.LEFT_CHILD], switch_state, 'I')
        self.pda.add_transition(switch_transition)
        self_transition = Transition(switch_state, "", NodeTransition(''), [NavigationAlphabet.RIGHT_SIBLING], switch_state, '')
        self.pda.add_transition(self_transition)
        after_switch_transition = Transition(switch_state, "", NodeTransition(''), [], inside_wildcard_state, '')
        self.pda.add_transition(after_switch_transition)

        # BLOCK
        # Add single left child transition in case it's directly a Block node
        block_transition = Transition(self.current_state, "", NodeTransition("BlockContext", 0, math.inf), [NavigationAlphabet.LEFT_CHILD], inside_wildcard_state, 'I')
        self.pda.add_transition(block_transition)

        # STATEMENT
        # Add double left child transition in case it's a Statement -> CompoundStatement -> Block
        after_statement_state = self.pda.new_state()
        statement_transition = Transition(self.current_state, "", NodeTransition("StatementContext"), [NavigationAlphabet.LEFT_CHILD], after_statement_state, 'I')
        self.pda.add_transition(statement_transition)

        after_compound_state = self.pda.new_state()
        statement_transition = Transition(after_statement_state, "", NodeTransition("Compound_stmtContext"), [NavigationAlphabet.LEFT_CHILD], after_compound_state, 'I')
        self.pda.add_transition(statement_transition)

        statement_transition = Transition(after_compound_state, "", NodeTransition("BlockContext", 0, math.inf), [NavigationAlphabet.LEFT_CHILD], inside_wildcard_state, 'I')
        self.pda.add_transition(statement_transition)

        self.current_state = inside_wildcard_state

        # Explore body
        blockStatementChild = ctx.getChild(0, JavaParser.JavaParser.BlockContext).getChild(0, JavaParser.JavaParser.BlockStatementContext)
        if blockStatementChild != None:
            return blockStatementChild.accept(self)

    def visitList_wildcard(self, ctx):
        # Adding self-transition to search for the next element
        self_transition = Transition(self.current_state, '', NodeTransition(''), [NavigationAlphabet.RIGHT_SIBLING],
                                                               self.current_state, '')
        self.pda.add_transition(self_transition)
        return self.current_state

    def visitTerminal(self, node):
        if isinstance(node, TerminalNode):
            logger.debug(f"Visiting terminal {node}")
            node_text = str(node).strip()
            node_transition = NodeTransition(node_text)
        else:
            logger.debug(f"Visiting {node.__class__.__name__} as terminal")
            node_text = f"{node.__class__.__name__}/0,0"
            node_transition = NodeTransition(node.__class__.__name__, 0, 0)

        logger.debug(f"last node: {self.__last_node}, current node: {node}, node text: {node_text}")

        return self._add_up_transition(node, node_transition)

    def visitFormalParameters(self, ctx):
        return self._handle_empty_list(ctx)

    def visitExpressionList(self, ctx):
        return self._handle_empty_list(ctx)

    def visitArguments(self, ctx):
        return self._handle_empty_list(ctx)

    def visitVar_wildcard(self, ctx):
        label = ctx.getText()
        # if label not in self.__var_names:
        #     uuid_label = str(uuid.uuid4())[:8]
        #     self.__var_names[label] = f"{label}_{uuid_label}"
        # label = self.__var_names[label]
        self.pda.named_wildcards.add(label)
        self._add_up_transition(ctx, NamedTransition(f"{label}"))
        return self.current_state

    def visitMultiple_compound_wildcard(self, ctx):
        # Transition to push B on the stack
        dummy_state = self.__add_body_transition()

        # Explore
        blockStatementChild = ctx.getChild(0, JavaParser.JavaParser.BlockContext).getChild(0, JavaParser.JavaParser.BlockStatementContext)
        if blockStatementChild == None:
            raise Exception("Body of multiple compound wildcard cannot be empty")
        ret = blockStatementChild.accept(self)

        skip_transition = Transition(dummy_state, "", NodeTransition(''), [], ret, '')
        self.pda.add_transition(skip_transition)

        return ret

    def visitContains_wildcard(self, ctx):
        self.__add_body_transition()

        logger.debug(f"Type of contains wildcard: {ctx.getChild(2).__class__.__name__}")
        # prune_tree = TreePruner().visit(ctx) # TODO: prune tree
        prune_tree = ctx
        return prune_tree.getChild(2).accept(self)

    def __add_body_transition(self):
        dummy_state = self.pda.new_state()
        dummy_transition = Transition(self.current_state, "", NodeTransition(''), [], dummy_state, 'B')
        self.pda.add_transition(dummy_transition)
        self.current_state = dummy_state

        self.move_to_B.append(self.depth)

        next_state = self.pda.new_state()
        child_transition = Transition(self.current_state, "", NodeTransition(''), [], next_state, '')
        self.pda.add_transition(child_transition)

        self_transition = Transition(next_state, "", NodeTransition(''), [NavigationAlphabet.RIGHT_SIBLING],
                                                                    next_state, '')
        self.pda.add_transition(self_transition)
        back_transition = Transition(next_state, "", NodeTransition(''), [NavigationAlphabet.LEFT_CHILD],
                                                                    self.current_state, 'I')
        self.pda.add_transition(back_transition)
        self.current_state = next_state

        return dummy_state


    def _handle_empty_list(self, ctx):
        list_wildcard = self.lookahead(ctx, JavaParser.JavaParser.List_wildcardContext)
        if list_wildcard is not None:
            # If the list wildcard is the only statement in the list, we need to add a transition to handle 0 elements
            logger.debug("Handling empty list")
            return self._add_up_transition(ctx)
        return self.visitChildren(ctx)


    def _add_up_transition(self, node, label:TransitionCondition=None):
        if label is None:
            label = NodeTransition('')

        if self._is_last_node():
            logger.debug(f"Node {node} is the last node in the tree, adding transition to the end")
            self_transition = Transition(self.current_state, '', NodeTransition(''), [NavigationAlphabet.RIGHT_SIBLING],
                                         self.current_state, '')
            self.pda.add_transition(self_transition)

            last_state = self.pda.new_state()
            transition = Transition(self.current_state, '', label, [], last_state, '')
            self.pda.add_transition(transition)
            self.current_state = last_state
            return last_state


        if len(self.move_to_B) > 0:
            return self._add_up_to_B_transition(label)

        return self._add_up_default_transition(label)

    def _add_up_default_transition(self, label:TransitionCondition):
        next_state = self.pda.new_state()
        to_pop = 'I' * self.depth
        to_up = [NavigationAlphabet.PARENT] * self.depth
        self.depth = 0
        transition = Transition(self.current_state, to_pop, label, to_up +
                                [NavigationAlphabet.RIGHT_SIBLING], next_state, '')
        self.pda.add_transition(transition)
        self.current_state = next_state
        return next_state

    def _add_up_to_B_transition(self, label:TransitionCondition):
        depth = self.move_to_B.pop()
        self.depth = depth

        match_state = self.pda.new_state()
        match_transition = Transition(self.current_state, '', label, [], match_state, '')
        self.pda.add_transition(match_transition)
        self.current_state = match_state

        up_transition = Transition(self.current_state, 'I', NodeTransition(''), [NavigationAlphabet.PARENT],
                                                              self.current_state, '')
        self.pda.add_transition(up_transition)

        next_state = self.pda.new_state()
        next_transition = Transition(self.current_state, 'B', NodeTransition(''), [], next_state, '')
        self.pda.add_transition(next_transition)
        self.current_state = next_state

        self._add_up_transition(None)

        return match_state

    def _is_last_node(self):
        return self.__is_last_branch

    @staticmethod
    def lookahead(ctx, clazz, predicate=None):
        """
        Check if one of the descendants of ctx is an instance of clazz. Stop if ctx has more than one child.
        :param ctx:
        :param clazz:
        :param predicate:
        :return: The first instance of clazz found in the descendant of ctx or None if not found.
        """
        if isinstance(ctx, clazz):
            return ctx
        if not hasattr(ctx, 'children'):
            return None
        if len(ctx.children) != 1:
            return None
        if predicate is not None and not predicate(ctx):
            return None
        return Java_to_PDA.lookahead(ctx.children[0], clazz)

    @staticmethod
    def lookbehind(ctx, clazz):
        """
        Check if one of the ancestors of ctx is instance of clazz.
        :param ctx:
        :param clazz:
        :return: The first instance of clazz found in the ancestors of ctx or None if not found.
        """
        if isinstance(ctx, clazz):
            return ctx
        if not hasattr(ctx, 'parentCtx'):
            return None
        return Java_to_PDA.lookbehind(ctx.parentCtx, clazz)
