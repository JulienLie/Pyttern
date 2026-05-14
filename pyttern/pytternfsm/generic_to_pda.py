import math

from antlr4.tree.Tree import TerminalNode
from loguru import logger

from ..simulator.pda.PDA import PDA
from ..simulator.pda.PDA_alphabets import NavigationAlphabet
from ..simulator.pda.transition import NodeTransition, TransitionCondition, NamedTransition, Transition

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

class Generic_to_PDA():
    def __init__(self, grammar, skippable_nodes, remove_double_wildcard):
        self.pda = PDA()
        self.current_state = self.pda.initial_state
        self.depth = 0
        self.move_to_B = []
        self.__var_names = {}
        self.__last_node = None
        self.__is_last_branch = True
        self.dict_pda = {}
        self.grammar = grammar
        self.skippable_nodes = skippable_nodes
        self.remove_double_wildcard = tuple(remove_double_wildcard)

    def visit(self, tree):
        logger.debug(f"Visiting tree: {tree}")
        self.dict_pda = {}
        self.__var_names = {}
        self.__last_node = rightmost_terminal(tree)
        super().visit(tree)
        self.depth = 0
        self.pda.final_states = self.current_state
        logger.debug(f"var_names: {self.__var_names}")
        self.dict_pda["__main__"] = self.pda
        return self.dict_pda
    

    def visitChildren(self, node):
        logger.debug(f"Visiting {node}")
        logger.debug(f"Class name: {node.__class__.__name__} {hash(node)}: {node.getText()}")

        children = node.children
        if len(children) == 0:
            return self.visitTerminal(node)

        down, up = self.define_boundaries(node)

        # Handle the double wildcard case
        while len(children) > 1 and self.lookahead(children[-1], self.remove_double_wildcard):
            children.pop()
            logger.debug("Remove double wildcard")

        # Add self-transition to be able to skip statements
        if node.__class__.__name__ in self.skippable_nodes:
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
    
    def visitStatement(self, ctx):
        logger.debug(f"Visiting Stmt {hash(ctx)}: {ctx.getText()}")

        # Handle multiple compound wildcard
        lookahead_multiple_body = self.lookahead(ctx, self.grammar.Multiple_compound_wildcardContext)
        if lookahead_multiple_body:
            return self.visitMultiple_compound_wildcard(lookahead_multiple_body)

        # Handle simple compound wildcard
        lookahead_simple_wildcard = self.lookahead(ctx, self.grammar.Simple_wildcardContext)
        if lookahead_simple_wildcard:
            return self.visitSimple_wildcard(lookahead_simple_wildcard)
        
        # Handle number wildcard
        lookahead_number_wildcard = self.lookahead(ctx, self.grammar.Number_wildcardContext)
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
        numbers_node = ctx.getChild(0, self.grammar.Wildcard_numberContext)
        low, high = self.visitWildcard_number(numbers_node)
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
        # Return the low and high limits of the wildcard
        low = int(ctx.getChild(1).getText())
        high = int(ctx.getChild(3).getText()) if ctx.getChild(3) and ctx.getChild(3).getText().isdigit() else math.inf

        if ctx.COMMA() is None:
            high = low
        
        logger.debug(f"Visiting Wildcard_number: low={low}, high={high}")
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
        prune_tree = ctx # TreePruner().visit(ctx) # TODO
        return prune_tree.getChild(2).accept(self)

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

        return match_state

    def _is_last_node(self):
        return self.__is_last_branch

    def handle_empty_list(self, ctx):
        list_wildcard = self.lookahead(ctx, self.grammar.List_wildcardContext)
        if list_wildcard is not None:
            # If the list wildcard is the only statement in the list, we need to add a transition to handle 0 elements
            logger.debug("Handling empty list")
            return self._add_up_transition(ctx)
        return self.visitChildren(ctx)

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
        return Generic_to_PDA.lookahead(ctx.children[0], clazz)

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
        return Generic_to_PDA.lookbehind(ctx.parentCtx, clazz)
