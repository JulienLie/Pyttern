"""Java pattern-to-PDA compiler translating Java AST patterns into Pushdown Automata."""

import math
from typing import Any

from loguru import logger

from .tree_pruner import TreePruner
from ...antlr.java.JavaParser import JavaParser
from ...antlr.java.JavaParserVisitor import JavaParserVisitor
from ...simulator.pda.PDA_alphabets import NavigationAlphabet
from ...simulator.pda.transition import NodeTransition, Transition
from ..generic_to_pda import Generic_to_PDA


class Java_to_PDA(Generic_to_PDA, JavaParserVisitor):
    """Compiles Java AST patterns into Pushdown Automata (PDAs)."""

    def __init__(self) -> None:
        """Initialize the Java compiler with grammar rules, skippable nodes, and pruner."""
        grammar = JavaParser
        skippable_nodes = [
            "BlockStatementContext",
            "PackageDeclarationContext",
            "ImportDeclarationContext",
            "TypeDeclarationContext",
            "ClassBodyDeclarationContext",
            "InterfaceBodyDeclarationContext",
            "RecordDeclarationContext",
            "CompactConstructorDeclarationContext",
        ]
        remove_double_wildcard = [
            grammar.List_wildcardContext,
        ]
        tree_pruner = TreePruner()

        super().__init__(grammar, skippable_nodes, remove_double_wildcard, tree_pruner)

    def define_boundaries(self, ctx: Any) -> tuple[int, int | float]:
        """Compute the minimum down and maximum up traversal bounds for a Java context.

        Args:
            ctx: Java AST rule context.

        Returns:
            tuple[int, int | float]: (down_limit, up_limit) bounds.
        """
        logger.debug(f"Defining boundaries for {ctx.__class__.__name__} {hash(ctx)}: {ctx.getText()}")
        down = up = 0

        if isinstance(
            ctx,
            (
                self.grammar.CompilationUnitContext,
                self.grammar.ClassBodyContext,
                self.grammar.InterfaceBodyContext,
                self.grammar.RecordBodyContext,
                self.grammar.BlockContext,
            ),
        ):
            logger.debug(f"Context {ctx.__class__.__name__} is a file input or block, setting boundaries to 0 and inf")
            down = 0
            up = math.inf
        elif isinstance(ctx, (self.grammar.BlockStatementContext)):
            # Special case for the simple wildcard: a block statement can have either 1 child (most cases) or 2 children (local variable definition)
            logger.debug(f"Context {ctx.__class__.__name__} is a block statement, setting boundaries to 1 and 2")
            down = 1
            up = 2
        elif (
            isinstance(ctx, self.grammar.VariableDeclaratorIdContext)
            and ctx.getChildCount() == 1
            and (isinstance(ctx.getChild(0), self.grammar.Simple_wildcardContext)
                 or isinstance(ctx.getChild(0), self.grammar.Var_wildcardContext))
        ):
            # Special case for the variable declarator: it can have either 1 child (most cases) or 2n+1 children (if it is a list)
            down = 1
            up = math.inf
        else:
            for child in ctx.children:
                if self.lookahead(
                    child,
                    (
                        self.grammar.List_wildcardContext,
                        self.grammar.Simple_compound_wildcardContext,
                        self.grammar.Contains_wildcardContext,
                    ),
                ) is not None:
                    logger.debug(f"Child {child.__class__.__name__} is a list or compound wildcard, setting boundaries to 0 and inf")
                    up = math.inf
                    continue

                only_wildcard = lambda c: "wildcard" in c.__class__.__name__
                everything = lambda _: True
                predicate = everything if "list" in ctx.__class__.__name__ else only_wildcard

                simple_node = self.lookahead(child, self.grammar.Number_wildcardContext, predicate)
                if simple_node is not None:
                    numbers_node = simple_node.getChild(0, self.grammar.Wildcard_numberContext)
                    if numbers_node is not None:
                        logger.debug(f"Child {child.__class__.__name__} has wildcard numbers, visiting numbers node")
                        min_n, max_n = numbers_node.accept(self)
                        up += max_n
                        down += min_n
                        continue
                down += 1
                up += 1

        return down, up

    def visitSimple_compound_wildcard(self, ctx: Any) -> Any:
        """Visit simple compound wildcard ($*) across Java try/catch, switch/case, and blocks.

        Args:
            ctx: Simple compound wildcard context.

        Returns:
            Any: Result of visiting the block statement body.
        """
        # Transition to push B on the stack
        dummy_state = Generic_to_PDA.add_body_transition(self, False)

        # Find body node
        self_transition = Transition(dummy_state, "", NodeTransition(''), [NavigationAlphabet.RIGHT_SIBLING], dummy_state, '')
        self.pda.add_transition(self_transition)

        # Here, we need to handle different cases: try/catch, switch/case, and block vs statement
        inside_wildcard_state = self.pda.new_state()

        # TRY/CATCH
        # Add single left child transition in case it's a catchClause -> Block
        catchclause_transition = Transition(
            dummy_state, "", NodeTransition("CatchClauseContext", 0, math.inf), [NavigationAlphabet.LEFT_CHILD], dummy_state, 'I'
        )
        self.pda.add_transition(catchclause_transition)

        # Add single left child transition in case it's a finallyBlock -> Block
        finally_transition = Transition(
            dummy_state, "", NodeTransition("FinallyBlockContext", 0, math.inf), [NavigationAlphabet.LEFT_CHILD], dummy_state, 'I'
        )
        self.pda.add_transition(finally_transition)

        # SWITCH/CASE
        # Add single left child transition in case it's a switchBlockStatementGroup -> Block
        switch_state = self.pda.new_state()
        switch_transition = Transition(
            dummy_state, "", NodeTransition("SwitchBlockStatementGroupContext", 0, math.inf), [NavigationAlphabet.LEFT_CHILD], switch_state, 'I'
        )
        self.pda.add_transition(switch_transition)
        self_transition = Transition(switch_state, "", NodeTransition(''), [NavigationAlphabet.RIGHT_SIBLING], switch_state, '')
        self.pda.add_transition(self_transition)
        after_switch_transition = Transition(switch_state, "", NodeTransition(''), [], inside_wildcard_state, '')
        self.pda.add_transition(after_switch_transition)

        # BLOCK
        # Add single left child transition in case it's directly a Block node
        block_transition = Transition(
            dummy_state, "", NodeTransition("BlockContext", 0, math.inf), [NavigationAlphabet.LEFT_CHILD], inside_wildcard_state, 'I'
        )
        self.pda.add_transition(block_transition)

        # STATEMENT
        # Add double left child transition in case it's a Statement -> CompoundStatement -> Block
        after_statement_state = self.pda.new_state()
        statement_transition = Transition(
            dummy_state, "", NodeTransition("StatementContext"), [NavigationAlphabet.LEFT_CHILD], after_statement_state, 'I'
        )
        self.pda.add_transition(statement_transition)

        after_compound_state = self.pda.new_state()
        statement_transition = Transition(
            after_statement_state, "", NodeTransition("Compound_stmtContext"), [NavigationAlphabet.LEFT_CHILD], after_compound_state, 'I'
        )
        self.pda.add_transition(statement_transition)

        statement_transition = Transition(
            after_compound_state, "", NodeTransition("BlockContext", 0, math.inf), [NavigationAlphabet.LEFT_CHILD], inside_wildcard_state, 'I'
        )
        self.pda.add_transition(statement_transition)

        self.current_state = inside_wildcard_state

        # Explore body
        blockStatementChild = ctx.getChild(0, self.grammar.BlockContext).getChild(0, self.grammar.BlockStatementContext)
        if blockStatementChild is not None:
            return blockStatementChild.accept(self)

    def visitBlockStatement(self, ctx: Any) -> Any:
        """Visit block statement and route list wildcards appropriately.

        Args:
            ctx: BlockStatement context.

        Returns:
            Any: Target state or statement visit result.
        """
        lookahead_list_wildcard = self.lookahead(ctx, self.grammar.List_wildcardContext)
        if lookahead_list_wildcard:
            return self.visitList_wildcard(lookahead_list_wildcard)

        return super().visitStatement(ctx)

    def visitFormalParameters(self, ctx: Any) -> Any:
        """Visit formal parameters and handle empty list wildcard cases.

        Args:
            ctx: FormalParameters context.

        Returns:
            Any: Target state or child visit result.
        """
        return self.handle_empty_list(ctx)

    def visitExpressionList(self, ctx: Any) -> Any:
        """Visit expression list and handle empty list wildcard cases.

        Args:
            ctx: ExpressionList context.

        Returns:
            Any: Target state or child visit result.
        """
        return self.handle_empty_list(ctx)

    def visitArguments(self, ctx: Any) -> Any:
        """Visit method arguments and handle empty list wildcard cases.

        Args:
            ctx: Arguments context.

        Returns:
            Any: Target state or child visit result.
        """
        return self.handle_empty_list(ctx)

    def visitMultiple_compound_wildcard(self, ctx: Any) -> Any:
        """Visit multiple compound wildcard ($**) and delegate to generic handler.

        Args:
            ctx: Multiple compound wildcard context.

        Returns:
            Any: Target state index.
        """
        blockStatementChild = ctx.getChild(0, self.grammar.BlockContext).getChild(0, self.grammar.BlockStatementContext)

        return super().visitGenericMultiple_compound_wildcard(ctx, blockStatementChild)

