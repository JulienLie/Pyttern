import itertools
from typing import Dict, Set, Tuple, List

from antlr4 import ParserRuleContext
from loguru import logger

from ..antlr.python import Python3Parser
from ..subpattern.SubPattern import BaseSubPattern
from .Matcher import Matcher
from .pda import PDA
from .configuration import Environment


def _is_trace_enabled() -> bool:
    return any(h._levelno <= 5 for h in logger._core.handlers.values()) if logger._core.handlers else False


def _is_debug_enabled() -> bool:
    return any(h._levelno <= 10 for h in logger._core.handlers.values()) if logger._core.handlers else False


def get_siblings(node: ParserRuleContext) -> List[ParserRuleContext]:
    """
    Retrieves the sequence of sibling nodes S(v) starting from the given AST node.
    
    Args:
        node (ParserRuleContext): The current node in the target Concrete Syntax Tree (CST).
        
    Returns:
        List[ParserRuleContext]: A list representing the sibling sequence from 'node' to the end of the block.
    """
    prec = node
    parent = prec.parentCtx
    while parent is not None and not isinstance(prec, (Python3Parser.StmtContext, Python3Parser.ExprContext, Python3Parser.TfpdefContext)):
        prec = parent
        parent = parent.parentCtx
    if parent is None:
        return [prec]
    children = parent.children if parent.children is not None else list(parent.getChildren())
    node_index = children.index(prec)
    return children[node_index:]


def search(tau: PDA, siblings: List[ParserRuleContext], m_sub: Environment) -> Set[Tuple[Environment, int]]:
    """
    Executes a structural transformation graph (TPA) over a sequence of sibling nodes.
    
    Args:
        tau (PDA): The single transformation graph (PDA rules) to execute.
        siblings (List[ParserRuleContext]): The target sequence of sibling nodes S(v).
        m_sub (Environment): The localized environment mapping containing arguments 
            bound to the subpattern's formal parameters.
            
    Returns:
        Set[Tuple[Environment, int]]: A set containing valid match outcomes. 
        Each tuple contains:
            - Environment: The resulting mapping with newly captured variables.
            - int: The exact index 'i' in the 'siblings' list where the match was accepted.
    """
    results = set()
    debug_enabled = _is_debug_enabled()
    for i, node in enumerate(siblings):
        if debug_enabled:
            logger.debug(f"Searching results in {tau} at {node} with variables: {m_sub}")
        match_set = Matcher.match(tau, node, False, m_sub)
        for match in match_set.matches:
            results.add((Environment.from_dict(match.bindings), i))
    return results

def eval_base(tau: PDA, siblings: List[ParserRuleContext], m_sub: Environment) -> Set[Tuple[Environment, int]]:
    """
    Translates a specific structural match index into a set of universally authorized block sizes.
    
    Args:
        tau (PDA): The transformation graph to execute.
        siblings (List[ParserRuleContext]): The sequence of sibling nodes S(v).
        m_sub (Environment): The local environment containing bound arguments.
        
    Returns:
        Set[Tuple[Environment, int]]: A set of valid configurations (m_out, k) where 
        'k' is any valid block size large enough to encapsulate the structural match.
    """
    results = set()
    p = len(siblings)
    
    is_stmt = len(siblings) > 0 and isinstance(siblings[0], Python3Parser.StmtContext)
    
    for m_out, i in search(tau, siblings, m_sub):
        # Authorize all block sizes k that are large enough to encapsulate match index i
        start_k = i + 1 if is_stmt else i
        end_k = p + 1 if is_stmt else p
        for k in range(start_k, end_k):
            results.add((m_out, k))
                
    return results

def NOT_operator(transformations: Dict[str, PDA], siblings: List[ParserRuleContext], m_sub: Environment, p: int) -> Set[Tuple[Environment, int]]:
    """
    Evaluates a Zero-Width Negative Lookahead (NOT), applying Scope-Bounded Negation.
    
    Args:
        transformations (Dict[str, PDA]): A list containing the single forbidden transformation graph.
        siblings (List[ParserRuleContext]): The sequence of sibling nodes S(v).
        m_sub (Environment): The local environment containing bound arguments.
        p (int): The maximum index of the sibling sequence (length - 1).
        
    Returns:
        Set[Tuple[Environment, int]]: A set of configurations (m_sub, k) authorizing 
        all block sizes 'k' strictly prior to the first occurrence of the forbidden node.
    """
    results = set()
    trans_list = list(transformations.values()) if isinstance(transformations, dict) else transformations
    all_matches = set()
    for tau in trans_list:
        all_matches.update(search(tau, siblings, m_sub))
    
    if all_matches:
        k_limit = min(i for _, i in all_matches)
    else:
        k_limit = p + 1
        
    for k in range(0, k_limit):
        results.add((m_sub, k))
        
    return results

def _tau_calls_not(tau) -> bool:
    """
    Checks whether a transformation PDA directly invokes a NOT subpattern.

    Args:
        tau (PDA or dict): The transformation graph to inspect.

    Returns:
        bool: True if the automaton calls a NOT subpattern, False otherwise.
    """
    if isinstance(tau, dict):
        tau = tau.get('__main__', next(iter(tau.values()))) if len(tau) > 0 else None
    if tau is None or not hasattr(tau, 'states'):
        return False
    if hasattr(tau, '_calls_not') and tau._calls_not is not None:
        return tau._calls_not
    from ..subpattern.SubPattern import loaded_subpatterns
    from .pda.transition import CallTransition
    res = False
    for s in tau.states:
        for tr in tau.get_transitions(s):
            if isinstance(tr.A, CallTransition):
                called = loaded_subpatterns.get(tr.A.subpattern_name)
                if called and called.type == 'NOT':
                    res = True
                    break
        if res:
            break
    tau._calls_not = res
    return res

def OR_operator(transformations: Dict[str, PDA], siblings: List[ParserRuleContext], m_sub: Environment, p: int) -> Set[Tuple[Environment, int]]:
    """
    Evaluates an Existential Union (OR) across multiple structural transformations.

    Args:
        transformations (Dict[str, PDA]): The transformation graphs representing the OR branches.
        siblings (List[ParserRuleContext]): The sequence of sibling nodes S(v).
        m_sub (Environment): The local environment containing bound arguments.
        p (int): The maximum index of the sibling sequence.

    Returns:
        Set[Tuple[Environment, int]]: The pure set union of all base evaluations.
    """
    results = set()
    is_stmt = len(siblings) > 0 and isinstance(siblings[0], Python3Parser.StmtContext)
    trace_enabled = _is_trace_enabled()
    for trans_name, tau in transformations.items():
        if trace_enabled:
            logger.trace(f"Computing OR for branch '{trans_name}'")
        if _tau_calls_not(tau):
            if trace_enabled:
                logger.trace(f"Branch '{trans_name}' contains NOT call; anchoring match at siblings[0]")
            if len(siblings) > 0:
                match_set = Matcher.match(tau, siblings[0], False, m_sub)
                for match in match_set.matches:
                    m_out = Environment.from_dict(match.bindings)
                    start_k = 1 if is_stmt else 0
                    end_k = p + 1 if is_stmt else p
                    for k in range(start_k, end_k):
                        results.add((m_out, k))
        else:
            if trace_enabled:
                logger.trace(f"Branch '{trans_name}' evaluating across sibling sequence")
            results.update(eval_base(tau, siblings, m_sub))

    return results

def AND_operator(transformations: Dict[str, PDA], siblings: List[ParserRuleContext], m_sub: Environment, p: int) -> Set[Tuple[Environment, int]]:
    """
    Evaluates a Universal Intersection (AND) with incremental branch pruning.

    Args:
        transformations (Dict[str, PDA]): The transformation graphs required to match concurrently.
        siblings (List[ParserRuleContext]): The sequence of sibling nodes S(v).
        m_sub (Environment): The local environment containing bound arguments.
        p (int): The maximum index of the sibling sequence.

    Returns:
        Set[Tuple[Environment, int]]: A set of valid configurations (m_merged, k) representing
        the set intersection over valid block sizes 'k' and the set union over environment mappings.
    """
    results = set()
    trans_list = list(transformations.values()) if isinstance(transformations, dict) else transformations
    all_matches = [search(tau, siblings, m_sub) for tau in trans_list]

    trace_enabled = _is_trace_enabled()
    for combo in itertools.product(*all_matches):
        envs, indices = zip(*combo)
        conflict = False
        for idx1 in range(len(indices)):
            for idx2 in range(idx1 + 1, len(indices)):
                if indices[idx1] == indices[idx2]:
                    e1, e2 = envs[idx1], envs[idx2]
                    for k1, v1 in e1.items():
                        if v1 is None:
                            continue
                        for k2, v2 in e2.items():
                            if v2 is None:
                                continue
                            if k1 != k2 and (v1 is v2 or (isinstance(v1, ParserRuleContext) and v1 == v2)):
                                conflict = True
                                if trace_enabled:
                                    logger.trace(f"AND conflict: variables '{k1}' and '{k2}' bind to identical node at sibling index {indices[idx1]}")
                                break
                        if conflict:
                            break
                if conflict:
                    break
        if conflict:
            continue
        merged_env = Environment.empty()
        valid = True
        for env in envs:
            merged_env = merged_env.merge(env)
            if merged_env is None:
                valid = False
                break
        if not valid:
            continue
        max_idx = max(indices)
        for k in range(max_idx, p + 1):
            results.add((merged_env, k))

    return results

def eval_operator(
    op: str, 
    transformations: Dict[str, PDA], 
    current_node: ParserRuleContext, 
    m_sub: Environment
) -> Set[Tuple[Environment, int]]:
    """
    Dynamically routes a logical operator evaluation to its corresponding method.
    
    Args:
        op (str): The logical operator string (e.g., 'AND', 'OR', 'NOT').
        transformations (Dict[str, PDA]): The parsed subpattern transformation graphs.
        current_node (ParserRuleContext): The active node pointer in the target syntax tree.
        m_sub (Environment): The initialized sub-environment resolving argument bindings.
        
    Returns:
        Set[Tuple[Environment, int]]: A mathematical set representing all valid next-step 
        configurations for the TPA. Each tuple contains a unified environment and an 
        authorized structural block size.
        
    Raises:
        ValueError: If the provided operator string does not match an implemented method.
    """
    siblings = get_siblings(current_node)
    p = len(siblings) - 1
    
    method_name = f"{op}_operator"
    operator_func = globals().get(method_name)
    
    if not operator_func:
        raise ValueError(f"Unknown or unsupported operator: {op}")
        
    return operator_func(transformations, siblings, m_sub, p)


def join_dicts(m_a: dict, m_b: dict) -> dict:
    """
    Joins two dictionaries with priority given to non-None values in m_b.

    (m_a ⊕ m_b)(t) = m_b(t) if m_b(t) is not None else m_a(t)

    Args:
        m_a (dict): Base dictionary with fallback default values.
        m_b (dict): Overriding dictionary with specialized values.

    Returns:
        dict: The unified dictionary.
    """
    result = m_a.copy()
    for key, value in m_b.items():
        if value is not None:
            result[key] = value
    return result


def mapping(params: list, args: list) -> dict:
    """
    Creates parameter-to-argument mapping m_(i->j) from formal parameters to caller arguments.

    m_(i->j) = {u_p -> t_p | 1 <= p <= k}

    Args:
        params (list): Formal parameter names of the called subpattern (P_j).
        args (list): Argument variable names passed by caller pattern (P_i).

    Returns:
        dict: The parameter to argument mapping.

    Raises:
        ValueError: If params and args lengths do not match.
    """
    if len(params) != len(args):
        raise ValueError("Parameters and arguments must have the same length")
    return dict(zip(params, args))


def composition(mapping: dict, bindings: dict) -> dict:
    """
    Composes a formal-to-caller mapping with an active variable environment.

    Args:
        mapping (dict): Parameter mapping m_(i->j).
        bindings (dict or Environment): Current variable assignments.

    Returns:
        dict: Composed bindings mapping formal variables directly to values.
    """
    result = {}
    for u, t in mapping.items():
        if t in bindings:
            result[u] = bindings[t]
    return result


def call_subpattern(
    subpattern: BaseSubPattern,
    current_node: ParserRuleContext,
    caller_env: Environment,
    args: list[str]
) -> list[tuple[Environment, int]]:
    """
    Executes a subpattern call against the given AST node and caller environment.

    Args:
        subpattern (BaseSubPattern): The subpattern instance to invoke.
        current_node (ParserRuleContext): Active node in the target parse tree.
        caller_env (Environment): Active variable environment in the caller.
        args (list[str]): Argument variable names passed in the call transition.

    Returns:
        list[tuple[Environment, int]]: List of valid (merged_environment, sibling_offset) configurations.
    """
    caller_env = Environment.from_dict(caller_env)

    subpattern_name = subpattern.name
    op = subpattern.type
    transformations = subpattern.get_compiled_transf()

    m_j_to_i = mapping(subpattern.args_order, args)
    comp = composition(m_j_to_i, caller_env)
    m_j_epsilon = {u: None for u, _ in subpattern.args.items()}
    m_sub = Environment.from_dict(join_dicts(m_j_epsilon, comp))
    if _is_trace_enabled():
        logger.trace(f"Calling subpattern {subpattern_name} on node {current_node} with bindings {m_sub}")

    results = eval_operator(op, transformations, current_node, m_sub)

    if len(results) == 0:
        if _is_trace_enabled():
            logger.trace(f"subpattern {subpattern_name} did not match")
        return []

    new_envs = []
    debug_enabled = _is_debug_enabled()
    for new_env, k in results:
        if debug_enabled:
            pretty_bindings = {
                var: (f"{val.__class__.__name__}: {val.getText() if hasattr(val, 'getText') else str(val)}" if val is not None else "None")
                for var, val in new_env.items()
            }
            logger.debug(f"subpattern {subpattern_name} matched with bindings {pretty_bindings} at S({k})")

        m_i_to_j = mapping(args, subpattern.args_order)
        comp = composition(m_i_to_j, new_env)
        new_binding = caller_env.merge(comp)
        if new_binding is not None:
            new_envs.append((new_binding, k))

    return new_envs