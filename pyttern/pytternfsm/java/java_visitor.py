# Deprecated class. Use an old class and is not compatible with the new version of pyttern.
# It is kept for future works.

"""
from antlr4 import TerminalNode
from loguru import logger
from typing_extensions import deprecated

from ...antlr.java.JavaParserVisitor import JavaParserVisitor
from ...simulator.pyttern_fsm import FSM, Movement
from ...simulator.transitions import ClassTransition, StringTransition

@deprecated
class Java_Visitor(JavaParserVisitor):
    def __init__(self, strict=False):
        super().__init__()
        self.strict = strict
        self.depth = 0
        self.current_fsm_node = None
        self.up_to = []

    def text_node(self, text):
        next_node = FSM()
        transition = self.get_up_transition(next_node, StringTransition(text))
        self.current_fsm_node.add_transition(*transition)

        self.current_fsm_node = next_node
        return next_node

    def get_up_transition(self, next_node, pred=None):
        if pred is None:
            pred = ObjectTransition()

        up_to = self.up_to.pop() if len(self.up_to) > 0 else None
        if up_to is not None:
            node, depth = up_to
            null_node = FSM()
            null_transition = (null_node, pred, [])
            self.current_fsm_node.add_transition(*null_transition)
            self.current_fsm_node = null_node

            self_transition = (null_node, ExceptTransition(node), [Movement.MP])
            self.current_fsm_node.add_transition(*self_transition)

            up_node = FSM()
            transition = (next_node, VarTransition(node), [Movement.MP] * depth + [Movement.MRS])

            self.depth = 0
            return transition

        transition = (next_node, pred, [Movement.MP] * self.depth + [Movement.MRS])
        self.depth = 0
        return transition
        
    def visit(self, tree):
        FSM.default_name = 1
        self.depth = 0
        start_node = FSM("START")
        self.current_fsm_node = start_node
        super().visit(tree)

        return start_node

    def visitChildren(self, node, clazz=None): # clazz -> type of node

        if isinstance(node, TerminalNode):
            logger.debug(f"Handling {node.__class__.__name__} as Terminal")
            return self.text_node(node.getText())

        children = list(node.children) if hasattr(node, "children") else []

        if clazz is None:
            clazz = node.__class__

        start = None
        end = None
        if node.start is not None and node.stop is not None:
            start = (node.start.line - 1, node.start.column)
            end = (node.stop.line - 1, node.stop.column)

        next_node = FSM(start=start, end=end)
        transition = (next_node, ClassTransition(clazz), [Movement.MLC])
        self.current_fsm_node.add_transition(*transition)
        self.current_fsm_node = next_node

        old_depth = self.depth
        self.depth = 0
        up_to = self.up_to
        self.up_to = []

        for i, child in enumerate(children):
            if i == len(children) - 1:
                self.depth = old_depth + 1
                self.up_to = up_to + self.up_to

            if isinstance(child, TerminalNode):
                self.text_node(child.getText())
            else:
                child.accept(self)

        return next_node
"""