from antlr4.tree.Tree import TerminalNode
from loguru import logger

from ...antlr.python import Python3ParserVisitor, Python3Parser
from ...simulator.pda.PDA import PDA
from ...simulator.pda.navigation_direction import NavigationDirection
from ...simulator.pda.transition import Transition


class Python_to_PDA(Python3ParserVisitor):
    def __init__(self):
        self.pda = PDA()
        self.current_state = self.pda.initial_state
        self.depth = 0

    def visit(self, tree):
        logger.debug(f"Visiting tree: {tree}")
        super().visit(tree)
        self.depth = 0
        self.pda.final_states.add(self.current_state)
        return self.pda

    def visitChildren(self, node):
        logger.debug(f"Visiting {node}")

        children = node.children
        if len(children) == 0:
            return self.visitTerminal(node)

        next_state = self.pda.new_state()
        transition = Transition(self.current_state, node.__class__.__name__, '', [NavigationDirection.LEFT_CHILD],
                                next_state, 'I')
        self.pda.add_transition(transition)
        self.current_state = next_state

        old_depth = self.depth
        self.depth = 0

        for i, child in enumerate(children):
            if i == len(children) - 1:
                self.depth = old_depth + 1
            child.accept(self)

        return next_state

    def visitStmt(self, ctx:Python3Parser.StmtContext):
        self_transition = Transition(self.current_state, "", '', [NavigationDirection.RIGHT_SIBLING], self.current_state, '')
        self.pda.add_transition(self_transition)
        return self.visitChildren(ctx)

    def visitExpr_wildcard(self, ctx:Python3Parser.Expr_wildcardContext):
        return ctx.getChild(0).accept(self)

    def visitStmt_wildcard(self, ctx:Python3Parser.Stmt_wildcardContext):
        return ctx.getChild(0).accept(self)

    def visitCompound_wildcard(self, ctx:Python3Parser.Compound_wildcardContext):
        return ctx.getChild(0).accept(self)

    def visitSimple_wildcard(self, ctx:Python3Parser.Simple_wildcardContext):
        next_state = self.pda.new_state()
        to_pop = 'I' * self.depth
        to_up = [NavigationDirection.PARENT] * self.depth
        self.depth = 0
        transition = Transition(self.current_state, "", to_pop, to_up + [NavigationDirection.RIGHT_SIBLING],
                                next_state, '')
        self.pda.add_transition(transition)
        self.current_state = next_state
        return next_state

    def visitSimple_compound_wildcard(self, ctx:Python3Parser.Simple_compound_wildcardContext):
        # Go to children
        child_state = self.pda.new_state()
        child_transition = Transition(self.current_state, "", '', [NavigationDirection.LEFT_CHILD], child_state, 'I')
        self.pda.add_transition(child_transition)
        self.current_state = child_state
        self.depth += 1

        # Find body node
        self_transition = Transition(child_state, "", '', [NavigationDirection.RIGHT_SIBLING], child_state, '')
        self.pda.add_transition(self_transition)

        # Explore body
        return ctx.getChild(0, Python3Parser.BlockContext).accept(self)

    def visitTerminal(self, node):
        logger.debug(f"Visiting terminal {node}")
        node_text = str(node).strip()

        if self._is_last_node(node):
            last_state = self.pda.new_state()
            transition = Transition(self.current_state, node_text, '', [], last_state, '')
            self.pda.add_transition(transition)
            self.current_state = last_state
            return last_state

        next_state = self.pda.new_state()
        to_pop = 'I' * self.depth
        to_up = [NavigationDirection.PARENT] * self.depth
        self.depth = 0
        transition = Transition(self.current_state, node_text, to_pop, to_up + [NavigationDirection.RIGHT_SIBLING],
                               next_state, '')
        self.pda.add_transition(transition)
        self.current_state = next_state

        return next_state

    @staticmethod
    def _is_last_node(node: TerminalNode):
        return str(node) == "<EOF>"