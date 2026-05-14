import math

from antlr4.tree.Tree import TerminalNode
from loguru import logger

from ...antlr.java.JavaParserVisitor import JavaParserVisitor
from ...antlr.java.JavaParser import JavaParser
from ...simulator.pda.PDA import PDA
from ...simulator.pda.PDA_alphabets import NavigationAlphabet
from ...simulator.pda.transition import NodeTransition, TransitionCondition, NamedTransition, Transition
from ..generic_to_pda import Generic_to_PDA, rightmost_terminal

class Java_to_PDA(Generic_to_PDA, JavaParserVisitor):
    def __init__(self):
        self.pda = PDA()
        self.current_state = self.pda.initial_state
        self.depth = 0
        self.move_to_B = []
        self.__var_names = {}
        self.__last_node = None
        self.__is_last_branch = True
        self.__dict_pda = {}

    def visit(self, tree):
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

    def visitChildren(self, node):
        logger.debug(f"Visiting {node}")
        logger.debug(f"Class name: {node.__class__.__name__} {hash(node)}: {node.getText()}")

        children = node.children
        if len(children) == 0:
            return self.visitTerminal(node)

        down, up = self._define_boundaries(node)

        # Handle the double wildcard case
        while len(children) > 1 and (
                # self.lookahead(children[-1], JavaParser.Double_wildcardContext) or
                self.lookahead(children[-1], JavaParser.List_wildcardContext)
        ):
            children.pop()
            logger.debug("Remove double wildcard")

        # Add self-transition to be able to skip statements
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

    def _define_boundaries(self, ctx):
        """
        Define the boundaries for the current context.
        :param ctx: The context to define boundaries for.
        :return: A tuple of (down, up) boundaries.
        """
        logger.debug(f"Defining boundaries for {ctx.__class__.__name__} {hash(ctx)}: {ctx.getText()}")
        down = up = 0

        if isinstance(ctx, (JavaParser.CompilationUnitContext, JavaParser.ClassBodyContext, JavaParser.BlockContext)):
            logger.debug(f"Context {ctx.__class__.__name__} is a file input or block, setting boundaries to 0 and inf")
            down = 0
            up = math.inf
        elif isinstance(ctx, (JavaParser.BlockStatementContext)):
            # Special case for the simple wildcard: a block statement can have either 1 child (most cases) or 2 children (local variable definition)
            logger.debug(f"Context {ctx.__class__.__name__} is a block statement, setting boundaries to 1 and 2")
            down = 1
            up = 2
        elif isinstance(ctx, JavaParser.VariableDeclaratorIdContext) and ctx.getChildCount() == 1 \
            and (isinstance(ctx.getChild(0), JavaParser.Simple_wildcardContext) or isinstance(ctx.getChild(0), JavaParser.Var_wildcardContext)):
            # Special case for the variable declarator: it can have either 1 child (most cases) or 2n+1 children (if it is a list)
            down = 1
            up = math.inf
        else:
            for child in ctx.children:
                if self.lookahead(child, (JavaParser.List_wildcardContext,
                                          JavaParser.Simple_compound_wildcardContext,
                                          JavaParser.Contains_wildcardContext)) is not None:
                    logger.debug(f"Child {child.__class__.__name__} is a list or compound wildcard, setting boundaries to 0 and inf")
                    up = math.inf
                    continue

                only_wildcard = lambda c: "wildcard" in c.__class__.__name__
                everything = lambda _: True
                predicate = everything if "list" in ctx.__class__.__name__ else only_wildcard

                simple_node = self.lookahead(child, JavaParser.Number_wildcardContext, predicate)
                if simple_node is not None:
                    numbers_node = simple_node.getChild(0, JavaParser.Wildcard_numberContext)
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
        logger.debug(f"Visiting Stmt {hash(ctx)}: {ctx.getText()}")

        # Handle multiple compound wildcard
        lookahead_multiple_body = self.lookahead(ctx, JavaParser.Multiple_compound_wildcardContext)
        if lookahead_multiple_body:
            return self.visitMultiple_compound_wildcard(lookahead_multiple_body)

        # Handle simple compound wildcard
        lookahead_simple_wildcard = self.lookahead(ctx, JavaParser.Simple_wildcardContext)
        if lookahead_simple_wildcard:
            return self.visitSimple_wildcard(lookahead_simple_wildcard)
        
        # Handle number wildcard
        lookahead_number_wildcard = self.lookahead(ctx, JavaParser.Number_wildcardContext)
        if lookahead_number_wildcard:
            return self.visitNumber_wildcard(lookahead_number_wildcard)

        return self.visitChildren(ctx)

    def visitNumber_wildcard(self, ctx):
        numbers_node = ctx.getChild(0, JavaParser.Wildcard_numberContext)
        return Generic_to_PDA.visitGenericNumber_wildcard(self, ctx, numbers_node)

    def visitWildcard_number(self, ctx):
        # Return the low and high limits of the wildcard
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
        # Transition to push B on the stack
        dummy_state = Generic_to_PDA.add_body_transition(self, False)

        # Find body node
        self_transition = Transition(dummy_state, "", NodeTransition(''), [NavigationAlphabet.RIGHT_SIBLING], dummy_state, '')
        self.pda.add_transition(self_transition)

        # Here, we need to handle different cases: try/catch, switch/case, and block vs statement
        inside_wildcard_state = self.pda.new_state()

        # TRY/CATCH
        # Add single left child transition in case it's a catchClause -> Block
        catchclause_transition = Transition(dummy_state, "", NodeTransition("CatchClauseContext", 0, math.inf), 
                                     [NavigationAlphabet.LEFT_CHILD], dummy_state, 'I')
        self.pda.add_transition(catchclause_transition)

        # Add single left child transition in case it's a finallyBlock -> Block
        finally_transition = Transition(dummy_state, "", NodeTransition("FinallyBlockContext", 0, math.inf), 
                                     [NavigationAlphabet.LEFT_CHILD], dummy_state, 'I')
        self.pda.add_transition(finally_transition)

        # SWITCH/CASE
        # Add single left child transition in case it's a switchBlockStatementGroup -> Block
        switch_state = self.pda.new_state()
        switch_transition = Transition(dummy_state, "", NodeTransition("SwitchBlockStatementGroupContext", 0, math.inf), 
                                     [NavigationAlphabet.LEFT_CHILD], switch_state, 'I')
        self.pda.add_transition(switch_transition)
        self_transition = Transition(switch_state, "", NodeTransition(''), [NavigationAlphabet.RIGHT_SIBLING], switch_state, '')
        self.pda.add_transition(self_transition)
        after_switch_transition = Transition(switch_state, "", NodeTransition(''), [], inside_wildcard_state, '')
        self.pda.add_transition(after_switch_transition)

        # BLOCK
        # Add single left child transition in case it's directly a Block node
        block_transition = Transition(dummy_state, "", NodeTransition("BlockContext", 0, math.inf), [NavigationAlphabet.LEFT_CHILD], inside_wildcard_state, 'I')
        self.pda.add_transition(block_transition)

        # STATEMENT
        # Add double left child transition in case it's a Statement -> CompoundStatement -> Block
        after_statement_state = self.pda.new_state()
        statement_transition = Transition(dummy_state, "", NodeTransition("StatementContext"), [NavigationAlphabet.LEFT_CHILD], after_statement_state, 'I')
        self.pda.add_transition(statement_transition)

        after_compound_state = self.pda.new_state()
        statement_transition = Transition(after_statement_state, "", NodeTransition("Compound_stmtContext"), [NavigationAlphabet.LEFT_CHILD], after_compound_state, 'I')
        self.pda.add_transition(statement_transition)

        statement_transition = Transition(after_compound_state, "", NodeTransition("BlockContext", 0, math.inf), [NavigationAlphabet.LEFT_CHILD], inside_wildcard_state, 'I')
        self.pda.add_transition(statement_transition)

        self.current_state = inside_wildcard_state

        # Explore body
        blockStatementChild = ctx.getChild(0, JavaParser.BlockContext).getChild(0, JavaParser.BlockStatementContext)
        if blockStatementChild != None:
            return blockStatementChild.accept(self)


    def visitFormalParameters(self, ctx):
        return self._handle_empty_list(ctx)

    def visitExpressionList(self, ctx):
        return self._handle_empty_list(ctx)

    def visitArguments(self, ctx):
        return self._handle_empty_list(ctx)

    def visitMultiple_compound_wildcard(self, ctx):
        # Transition to push B on the stack
        dummy_state = Generic_to_PDA.add_body_transition(self)

        # Explore
        blockStatementChild = ctx.getChild(0, JavaParser.BlockContext).getChild(0, JavaParser.BlockStatementContext)
        if blockStatementChild == None:
            raise Exception("Body of multiple compound wildcard cannot be empty")
        ret = blockStatementChild.accept(self)

        skip_transition = Transition(dummy_state, "", NodeTransition(''), [], ret, '')
        self.pda.add_transition(skip_transition)

        return ret


    def _handle_empty_list(self, ctx):
        list_wildcard = self.lookahead(ctx, JavaParser.List_wildcardContext)
        if list_wildcard is not None:
            # If the list wildcard is the only statement in the list, we need to add a transition to handle 0 elements
            logger.debug("Handling empty list")
            return self._add_up_transition(ctx)
        return self.visitChildren(ctx)


    def _is_last_node(self):
        return self.__is_last_branch