import math
import abc

from antlr4.tree.Tree import TerminalNode
from loguru import logger

from .node_visitor import NodeVisitor
from .._pyttern_cpp import Node
from ..simulator.pda.PDA import PDA
from ..simulator.pda.PDA_alphabets import NavigationAlphabet
from ..simulator.pda.transition import NodeTransition, TransitionCondition, NamedTransition, Transition

class Generic_to_PDA(NodeVisitor, metaclass=abc.ABCMeta):
    def __init__(self, skippable_nodes, remove_double_wildcard):
        self.pda = PDA()
        self.current_state = self.pda.initial_state
        self.depth = 0
        self.move_to_B = []
        self.dict_pda = {}
        self.skippable_nodes = skippable_nodes
        self.remove_double_wildcard = tuple(remove_double_wildcard)
        self._restrict_stmt = False
        self.__var_names = {}
        self.__is_last_branch = True

    def visit(self, tree):
        logger.debug(f"Visiting tree: {tree}")
        self.dict_pda = {}
        self.__var_names = {}
        super().visit(tree)
        self.depth = 0
        self.pda.final_states = self.current_state
        logger.trace(f"var_names: {self.__var_names}")
        self.dict_pda["__main__"] = self.pda
        return self.dict_pda
    
    @abc.abstractmethod
    def define_boundaries(self, ctx):
        pass

    def visitChildren(self, node):
        rule_name = getattr(node, "rule_name", node.__class__.__name__)
        logger.trace(f"Visiting {rule_name} {hash(node)}: {node.getText()}")

        children = node.children
        if len(children) == 0:
            return self.visitTerminal(node)

        down, up = self.define_boundaries(node)

        # Handle the double wildcard case
        while len(children) > 1 and self.lookahead(children[-1], self.remove_double_wildcard):
            children.pop()
            logger.trace("Remove double wildcard")

        # Add self-transition to be able to skip statements
        if rule_name in self.skippable_nodes or rule_name + "Context" in self.skippable_nodes or rule_name.replace("Context", "") in self.skippable_nodes:
            if not self._restrict_stmt:
                self_transition = Transition(self.current_state, "", NodeTransition(''), [NavigationAlphabet.RIGHT_SIBLING],
                                            self.current_state, '')
                self.pda.add_transition(self_transition)
            else:
                self._restrict_stmt = False

        next_state = self.pda.new_state()
        transition = Transition(self.current_state, "", NodeTransition(rule_name, down, up),
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
    
    def visitStatement(self, ctx):
        logger.trace(f"Visiting Stmt {hash(ctx)}: {ctx.getText()}")

        # Handle multiple compound wildcard
        lookahead_multiple_body = self.lookahead(ctx, "Multiple_compound_wildcard")
        if lookahead_multiple_body:
            return self.visitMultiple_compound_wildcard(lookahead_multiple_body)

        # Handle simple compound wildcard
        lookahead_simple_wildcard = self.lookahead(ctx, "Simple_wildcard")
        if lookahead_simple_wildcard:
            return self.visitSimple_wildcard(lookahead_simple_wildcard)
        
        # Handle number wildcard
        lookahead_number_wildcard = self.lookahead(ctx, "Number_wildcard")
        if lookahead_number_wildcard:
            return self.visitNumber_wildcard(lookahead_number_wildcard)

        return self.visitChildren(ctx)

    def visitExpr_wildcard(self, ctx):
        return ctx.getChild(0).accept(self)

    def visitStmt_wildcard(self, ctx):
        return ctx.getChild(0).accept(self)

    def visitCompound_wildcard(self, ctx):
        return ctx.getChild(0).accept(self)

    def visitSimple_wildcard(self, ctx):
        return self._add_up_transition(ctx)

    def visitNumber_wildcard(self, ctx):
        numbers_node = ctx.getChild(0, "Wildcard_number")
        low, high = self.visitWildcard_number(numbers_node)
        logger.trace(f"Visiting Simple_wildcard with numbers: low={low}, high={high}")

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

    def visitWildcard_number(self, ctx: Node):
        # Return the low and high limits of the wildcard
        low = int(ctx.getChild(1).getText())
        high = int(ctx.getChild(3).getText()) if ctx.getChild(3) and ctx.getChild(3).getText().isdigit() else math.inf

        if "," in [child.getText() for child in ctx.getChildren()]:
            high = low
        
        logger.trace(f"Visiting Wildcard_number: low={low}, high={high}")
        if low > high:
            logger.error(f"Invalid wildcard number: low={low} > high={high}")
            return 1, 1
        
        return low, high

    def visitList_wildcard(self, ctx):
        # Adding self-transition to search for the next element
        self_transition = Transition(self.current_state, '', NodeTransition(''), [NavigationAlphabet.RIGHT_SIBLING],
                                                               self.current_state, '')
        self.pda.add_transition(self_transition)
        return self.current_state

    def visitTerminal(self, node):
        if getattr(node, "rule_name", "") in {"TerminalNode"} or getattr(node, "is_terminal", False):
            logger.trace(f"Visiting terminal {node}")
            node_text = node.getText() if hasattr(node, "getText") else str(node).strip()
            node_transition = NodeTransition(node_text)
        else:
            rule_name = getattr(node, "rule_name", node.__class__.__name__)
            logger.trace(f"Visiting {rule_name} as terminal")
            node_text = f"{rule_name}/0,0"
            node_transition = NodeTransition(rule_name, 0, 0)

        logger.trace(f"is last branch: {self.__is_last_branch}, current node: {node}, node text: {node_text}")

        return self._add_up_transition(node, node_transition)

    def visitVar_wildcard(self, ctx):
        label = ctx.getText()
        # if label not in self.__var_names:
        #     uuid_label = str(uuid.uuid4())[:8]
        #     self.__var_names[label] = f"{label}_{uuid_label}"
        # label = self.__var_names[label]
        self.pda.named_wildcards.add(label)
        self._add_up_transition(ctx, NamedTransition(f"{label}"))
        return self.current_state

    def visitContains_wildcard(self, ctx):
        self.add_body_transition()

        logger.trace(f"Type of contains wildcard: {ctx.getChild(2).__class__.__name__}")
        #prune_tree = self.tree_pruner.visit(ctx)
        return ctx.getChild(2).accept(self)

    @abc.abstractmethod
    def visitSimple_compound_wildcard(self, ctx):
        pass

    @abc.abstractmethod
    def visitMultiple_compound_wildcard(self, ctx):
        pass

    def visitGenericMultiple_compound_wildcard(self, ctx, blockChild):
        # Transition to push B on the stack
        dummy_state = Generic_to_PDA.add_body_transition(self)

        # Explore
        if blockChild == None:
            raise Exception("Body of multiple compound wildcard cannot be empty")
        ret = blockChild.accept(self)

        skip_transition = Transition(dummy_state, "B", NodeTransition(''), [], ret, 'B')
        self.pda.add_transition(skip_transition)

        return ret

    def add_body_transition(self, allow_multiple_compound=True):
        # Push B on the stack
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

            self_transition = Transition(next_state, "", NodeTransition(''), [NavigationAlphabet.RIGHT_SIBLING],
                                                                        next_state, '')
            self.pda.add_transition(self_transition)
            back_transition = Transition(next_state, "", NodeTransition(''), [NavigationAlphabet.LEFT_CHILD],
                                                                        self.current_state, 'I')
            self.pda.add_transition(back_transition)
            self.current_state = next_state

        return dummy_state


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

        q_before_end = self.current_state

        # Commit to an intermediate state
        match_state = self.pda.new_state()
        match_transition = Transition(self.current_state, '', label, [], match_state, '')
        self.pda.add_transition(match_transition)
        self.current_state = match_state

        # Move up as many times as there I on the stack and consume them
        up_transition = Transition(self.current_state, 'I', NodeTransition(''), [NavigationAlphabet.PARENT],
                                                               self.current_state, '')
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

    def _is_last_node(self):
        return self.__is_last_branch

    def handle_empty_list(self, ctx):
        list_wildcard = self.lookahead(ctx, "List_wildcard")
        if list_wildcard is not None:
            # If the list wildcard is the only statement in the list, we need to add a transition to handle 0 elements
            logger.trace("Handling empty list")
            return self._add_up_transition(ctx)
        return self.visitChildren(ctx)

    @staticmethod
    def _get_target_names(clazz):
        if isinstance(clazz, (tuple, list, set)):
            names = set()
            for c in clazz:
                names.update(Generic_to_PDA._get_target_names(c))
            return names
        if isinstance(clazz, type):
            return {clazz.__name__, clazz.__name__.replace("Context", "")}
        s = str(clazz)
        return {s, s.replace("Context", ""), s + "Context"}

    @staticmethod
    def lookahead(ctx: Node, clazz, predicate=None) -> Node:
        """
        Check if one of the descendants of ctx is an instance of clazz. Stop if ctx has more than one child.
        """
        if ctx is None:
            return None
        rule_name = getattr(ctx, "rule_name", ctx.__class__.__name__)
        target_names = Generic_to_PDA._get_target_names(clazz)
        if rule_name in target_names or ctx.__class__.__name__ in target_names:
            return ctx

        if not hasattr(ctx, 'children'):
            return None
        if len(ctx.children) != 1:
            return None
        if predicate is not None and not predicate(ctx):
            return None
        return Generic_to_PDA.lookahead(ctx.children[0], clazz, predicate)

    @staticmethod
    def lookbehind(ctx, clazz):
        """
        Check if one of the ancestors of ctx is instance of clazz.
        """
        if ctx is None:
            return None
        rule_name = getattr(ctx, "rule_name", ctx.__class__.__name__)
        target_names = Generic_to_PDA._get_target_names(clazz)
        if rule_name in target_names or ctx.__class__.__name__ in target_names:
            return ctx
        if not hasattr(ctx, 'parentCtx') or ctx.parentCtx is None:
            return None
        return Generic_to_PDA.lookbehind(ctx.parentCtx, clazz)
