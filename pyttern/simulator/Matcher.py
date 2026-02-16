from antlr4.ParserRuleContext import ParserRuleContext
from antlr4.tree.Tree import TerminalNode, Tree
from loguru import logger

from .pda.PDA import PDA
from .pda.PDA_alphabets import NavigationAlphabet
from .pda.transition import NodeTransition, NamedTransition, CallTransition
from ..macro.Macro import loaded_macros
from ..pytternfsm.python.match_set import MatchSet, Match


def join_dicts(m_a: dict, m_b: dict) -> dict:
    """
    Joins two dictionaries m_a and m_b such as:
    (m_a ⊕ m_b)(t) = m_b(t) if m_b(t) is none None else m_a(t)
    :param m_a: first dictionary with "default" values
    :param m_b: second dictionary with "override" values
    :return: the joined dictionary
    """
    result = m_a.copy()
    for key, value in m_b.items():
        if value is not None:
            result[key] = value
    return result


def mapping(params: list, args: list) -> dict:
    """
    Create the mapping m_(i->j) from the macro parameters (u_1, ..., u_k) to the arguments (t_1, ..., t_k):
    m_(i->j) = {u_p -> t_p | 1 <= p <= k}
    :param params: parameters of the PDA called (P_j)
    :param args: Variables of the PDA calling (P_i)
    :return: the mapping m_(i->j)
    """

    if len(params) != len(args):
        raise ValueError("Parameters and arguments must have the same length")
    return {u: t for u, t in zip(params, args)}

def composition(mapping, bindings):
    """
    The composition of a mapping m_(i->j) with a bindings m_j is defined as:
    m_(i->j)[m_j] such as:
    m_(i->j) = {u_p -> t_p | 1 <= p <= k}
    m_j = {t_p -> v_p | 1 <= p <= k}
    m_(i->j)[m_j] = {u_p -> v_p | 1 <= p <= k}
    :param mapping: a mapping m_(i->j)
    :param bindings: The current mapping m_j
    :return: The composition m_(i->j)[m_j]
    """
    result = {}
    for u, t in mapping.items():
        if t in bindings:
            result[u] = bindings[t]
    return result

class Matcher:
    def __init__(self, pdas: dict[str, PDA], parse_tree: Tree):
        self.pda = pdas["__main__"]
        self.callable = pdas
        self.parse_tree = parse_tree
        self.match_set = MatchSet()
        self.configurations = []
        self.n_step = 0
        self._listeners = []

    def add_listener(self, listener):
        self._listeners.append(listener)

    def remove_listener(self, listener):
        self._listeners.remove(listener)

    def remove_all_listeners(self):
        self._listeners.clear()

    @staticmethod
    def match(pda: dict[str, PDA], parse_tree: ParserRuleContext, stop_at_first=False, bindings=None) -> MatchSet:
        """
        Matches a given parse tree against a Pushdown Automaton (PDA) and returns the resulting matches.

        :param pda: The Pushdown Automaton (PDA) to use for matching.
        :param parse_tree: The parse tree to match against the PDA.
        :param stop_at_first: A boolean indicating whether to stop after the first match is found. Defaults to False.
        :param bindings: An optional dictionary of initial variable bindings. Defaults to None.
        :return: A MatchSet object containing the results of the matching process.
        """

        matcher = Matcher(pda, parse_tree)
        logger.debug("Starting match")
        matcher.start(bindings)
        while len(matcher.configurations) > 0:
            matcher.step()
            if stop_at_first and matcher.match_set.count() > 0:
                break
        logger.debug(f"Match finished with {matcher.match_set.count()} matches")
        return matcher.match_set

    def start(self, initial_bindings=None):
        bindings = {t: None for t in self.pda.named_wildcards}
        if initial_bindings is not None:
            bindings.update(initial_bindings)
        first_config = (self.pda.initial_state, self.parse_tree, "", bindings, [])
        self.configurations.append(first_config)
        for listener in self._listeners:
            listener.on_start(self, self.pda.initial_state, self.parse_tree)
        return self

    def step(self):
        logger.trace(f"Step {self.n_step}")
        if len(self.configurations) == 0:
            raise Warning("No more configurations to process")
        current_config = self.configurations.pop()
        logger.trace(f"Checking config: {current_config}")
        current_state, current_node, stack, var, matches = current_config
        for listener in self._listeners:
            listener.step(self, current_state, current_node, stack, var, matches)

        if current_state == self.pda.final_states:
            logger.info("Match found")
            match = Match(self.n_step, var.copy(), matches)
            self.match_set.record(match)
            for listener in self._listeners:
                listener.on_match(self, match)
            return self


        for transition in self.pda.get_transitions(current_state):
            # Transition components
            alpha = transition.alpha
            A = transition.A
            t = transition.t
            q_prime = transition.q_prime
            beta = transition.beta

            if not stack.endswith(alpha):
                logger.trace(f"Wrong stack elements: expecting {alpha} but was {stack[-len(alpha):]}")
                continue
            new_stack = stack.removesuffix(alpha)

            class_name = current_node.__class__.__name__

            new_var = var.copy()
            new_vars = []

            # Default terminal node
            if isinstance(A, NodeTransition):
                if not self._match_node(current_node, A):
                    if isinstance(current_node, TerminalNode):
                        logger.trace(f"Wrong input: expecting {A.name} but was {str(current_node)}")
                    else:
                        logger.trace(f"Wrong input: expecting {A.name} but was {class_name}")
                    continue
                new_vars.append(new_var)

            # Handle Variables
            elif isinstance(A, NamedTransition):
                name = A.name
                if new_var[name] is None:
                    logger.trace(f"New variable: {name}")
                    new_var[name] = current_node
                elif not self._match_tree(new_var[name], current_node):
                    logger.trace(f"Wrong variable: {name} expecting {new_var[name]} but was {current_node}")
                    continue
                new_vars.append(new_var)

            # Handle Macros
            elif isinstance(A, CallTransition):
                macro_name = A.macro_name
                trnsf_name = A.transformation_name
                args = A.args

                logger.trace(f"Handling macro: {macro_name} with transformation {trnsf_name} and args {args}")

                possible_bindings = self.call_macro(macro_name, trnsf_name, args, current_node, new_var)
                if len(possible_bindings) < 1:
                    continue
                new_vars += possible_bindings

            else:
                logger.error(f"Unknown transition type: {A}")
                raise ValueError(f"Unknown transition type: {A} ({type(A)})")


            next_node = self._get_next_node(current_node, t)
            if next_node is None:
                logger.trace(f"Wrong direction: cannot get next node at {t}")
                continue

            logger.trace(f"Taking {transition}")

            new_stack += beta
            new_matches = matches + [(transition, current_node)]

            for variables in new_vars:
                new_config = (q_prime, next_node, new_stack, variables.copy(), new_matches)
                self.configurations.append(new_config)

        self.n_step += 1
        return self

    def call_macro(self, macro_name, trnsf_name, args, current_node, bindings):
        """
        Calls a macro with the given name and transformation name, matching it against the current node.

        :param args:
        :param macro_name: The name of the macro to call.
        :param trnsf_name: The name of the transformation within the macro.
        :param current_node: The current node in the parse tree.
        :param bindings: The current variable bindings.
        :return: A MatchSet containing the results of the macro call.
        """
        macro = loaded_macros[macro_name]
        to_call = f"{macro_name}::{trnsf_name}"
        if to_call not in self.callable or not macro:
            logger.warning(f"Macro or transformation not found: {macro_name}:{trnsf_name}")
            return []

        macro_pdas = self.callable[to_call]
        macro_pda = macro_pdas["__main__"]

        m_j_to_i = mapping(macro.args_order, args)
        comp = composition(m_j_to_i, bindings)
        m_j_epsilon = {u: None for u in macro_pda.named_wildcards}
        macro_params = join_dicts(m_j_epsilon, comp)

        logger.trace(f"Calling macro {macro_name}:{trnsf_name} on node {current_node} with bindings {macro_params}")

        match_set = Matcher.match(macro_pdas, current_node, stop_at_first=False, bindings=macro_params)
        if match_set.count() == 0:
            logger.trace(f"Macro {macro_name}:{trnsf_name} did not match")
            return []

        new_bindings = []
        for match in match_set.matches:
            pretty_bindings = {k: (f"{v.__class__.__name__}: {v.getText()}" if v is not None else "None") for k,
            v in match.bindings.items()}
            print(f"Macro {macro_name}:{trnsf_name} matched with bindings {pretty_bindings}")
            sub_bindings = match.bindings
            m_i_to_j = mapping(args, macro.args_order)
            comp = composition(m_i_to_j, sub_bindings)
            new_binding = bindings.copy()
            new_binding.update(comp)
            new_bindings.append(new_binding)

        return new_bindings


    def _get_next_node(self, node, directions):
        current_node = node
        for direction in directions:
            match direction:
                case NavigationAlphabet.RIGHT_SIBLING:
                    if current_node == self.parse_tree:
                        return None
                    try:
                        parent = current_node.parentCtx
                        if parent is None:
                            return None
                        siblings = list(parent.getChildren())
                        index = siblings.index(current_node)
                        current_node = siblings[index + 1]
                    except IndexError:
                        return None
                case NavigationAlphabet.LEFT_CHILD:
                    try:
                        children = list(current_node.getChildren())
                        current_node = children[0]
                    except AttributeError:
                        return None
                    except IndexError:
                        return None
                case NavigationAlphabet.PARENT:
                    if current_node == self.parse_tree:
                        return None
                    current_node = current_node.parentCtx
        return current_node

    @staticmethod
    def _match_node(input, A: NodeTransition):
        name = A.name
        down, up = A.down, A.up
        if name == "":
            return True
        if isinstance(input, TerminalNode):
            return str(input) == name

        return input.__class__.__name__ == name and down <= input.getChildCount() <= up

    @staticmethod
    def _match_tree(tree1, tree2):
        logger.trace(f'Matching {tree1} and {tree2}')
        if tree1 is None or tree2 is None:
            return False
        if isinstance(tree1, TerminalNode) and isinstance(tree2, TerminalNode):
            return str(tree1) == str(tree2)
        if tree1.__class__.__name__ != tree2.__class__.__name__:
            return False
        if len(tree1.children) != len(tree2.children):
            return False
        for child1, child2 in zip(tree1.children, tree2.children):
            if not Matcher._match_tree(child1, child2):
                return False
        return True