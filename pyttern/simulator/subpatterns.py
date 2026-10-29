"""Subpattern simulation operators and evaluation functions."""
from __future__ import annotations

from collections.abc import Mapping
import itertools
from typing import Any, Literal

from antlr4 import ParserRuleContext, RuleContext
from antlr4.tree.Tree import Tree
from loguru import logger

from ..antlr.python import Python3Parser
from ..subpattern.SubPattern import BaseSubPattern
from ..subpattern.catalog import SubPatternCatalog
from .Matcher import Matcher
from .configuration import Environment
from .pda.PDA import PDA


def _is_trace_enabled() -> bool:
    core = getattr(logger, "_core", None)
    return any(h._levelno <= 5 for h in core.handlers.values()) if core and hasattr(core, "handlers") else False


def _is_debug_enabled() -> bool:
    core = getattr(logger, "_core", None)
    return any(h._levelno <= 10 for h in core.handlers.values()) if core and hasattr(core, "handlers") else False


def get_siblings(node: RuleContext | Tree) -> list[Any]:
    """Retrieves the sequence of sibling nodes S(v) starting from the given AST node.

    Args:
        node (RuleContext | Tree): The current node in the target Concrete Syntax Tree (CST).

    Returns:
        list[Any]: A list representing the sibling sequence from 'node' to the end of the block.
    """
    prec: Any = node
    parent = getattr(prec, "parentCtx", None)
    while parent is not None and not isinstance(
        prec, (Python3Parser.StmtContext, Python3Parser.ExprContext, Python3Parser.TfpdefContext)
    ):
        prec = parent
        parent = getattr(parent, "parentCtx", None)
    if parent is None:
        return [prec]
    children = parent.children if getattr(parent, "children", None) is not None else list(parent.getChildren())
    node_index = children.index(prec)
    return children[node_index:]


def search(
    tau: PDA | dict[str, PDA],
    siblings: list[Any],
    m_sub: Environment,
    catalog: SubPatternCatalog | None = None,
) -> set[tuple[Environment, int]]:
    """Evaluates transformation automaton against all available starting positions in the sibling slice.

    Args:
        tau (PDA | dict[str, PDA]): The transformation Pushdown Automaton to execute.
        siblings (list[Any]): The sequence of sibling nodes S(v).
        m_sub (Environment): The local variable environment to seed the simulation with.
        catalog: Optional SubPatternCatalog for resolving nested subpattern calls.

    Returns:
        set[tuple[Environment, int]]: A set of tuples (Environment, int), where:
            - Environment: The resulting mapping with newly captured variables.
            - int: The exact index 'i' in the 'siblings' list where the match was accepted.
    """
    results: set[tuple[Environment, int]] = set()
    debug_enabled = _is_debug_enabled()
    for i, node in enumerate(siblings):
        if debug_enabled:
            logger.debug(f"Searching results in {tau} at {node} with variables: {m_sub}")
        match_set = Matcher.match(tau, node, False, m_sub, catalog=catalog)
        for match in match_set.matches:
            results.add((Environment.from_dict(match.bindings), i))
    return results


def eval_base(
    tau: PDA | dict[str, PDA],
    siblings: list[Any],
    m_sub: Environment,
    catalog: SubPatternCatalog | None = None,
) -> set[tuple[Environment, int]]:
    """Translates a specific structural match index into a set of universally authorized block sizes.

    Args:
        tau (PDA | dict[str, PDA]): The transformation graph to execute.
        siblings (list[Any]): The sequence of sibling nodes S(v).
        m_sub (Environment): The local environment containing bound arguments.
        catalog: Optional SubPatternCatalog for resolving nested subpattern calls.

    Returns:
        set[tuple[Environment, int]]: A set of valid configurations (m_out, k) where
        'k' is any valid block size large enough to encapsulate the structural match.
    """
    results: set[tuple[Environment, int]] = set()
    p = len(siblings)

    is_stmt = len(siblings) > 0 and isinstance(siblings[0], Python3Parser.StmtContext)

    for m_out, i in search(tau, siblings, m_sub, catalog=catalog):
        start_k = i + 1 if is_stmt else i
        end_k = p + 1 if is_stmt else p
        for k in range(start_k, end_k):
            results.add((m_out, k))

    return results


def NOT_operator(
    transformations: dict[str, PDA] | list[PDA],
    siblings: list[Any],
    m_sub: Environment,
    p: int,
    catalog: SubPatternCatalog | None = None,
) -> set[tuple[Environment, int]]:
    """Evaluates a Zero-Width Negative Lookahead (NOT), applying Scope-Bounded Negation.

    Args:
        transformations (dict[str, PDA] | list[PDA]): The forbidden transformation graph(s).
        siblings (list[Any]): The sequence of sibling nodes S(v).
        m_sub (Environment): The local environment containing bound arguments.
        p (int): The maximum index of the sibling sequence (length - 1).
        catalog: Optional SubPatternCatalog for resolving nested subpattern calls.

    Returns:
        set[tuple[Environment, int]]: A set of configurations (m_sub, k) authorizing
        all block sizes 'k' strictly prior to the first occurrence of the forbidden node.
    """
    results: set[tuple[Environment, int]] = set()
    trans_list = list(transformations.values()) if isinstance(transformations, dict) else transformations
    all_matches: set[tuple[Environment, int]] = set()
    for tau in trans_list:
        all_matches.update(search(tau, siblings, m_sub, catalog=catalog))

    if all_matches:
        k_limit = min(i for _, i in all_matches)
    else:
        k_limit = p + 1

    for k in range(0, k_limit):
        results.add((m_sub, k))

    return results


def _tau_calls_not(tau: Any, catalog: SubPatternCatalog | None = None) -> bool:
    """Checks whether a transformation PDA directly invokes a NOT subpattern."""
    if hasattr(tau, "_calls_not") and tau._calls_not is not None:
        return tau._calls_not

    from .pda.transition import CallTransition

    if isinstance(tau, dict):
        tau = tau.get("__main__", next(iter(tau.values()))) if len(tau) > 0 else None
    if tau is None or not hasattr(tau, "states"):
        return False
    res = False
    for s in tau.states:
        for tr in tau.get_transitions(s):
            if isinstance(tr.A, CallTransition):
                called = catalog.get(tr.A.subpattern_name) if catalog is not None else None
                if called is None:
                    from ..subpattern.SubPattern import loaded_subpatterns

                    called = loaded_subpatterns.get(tr.A.subpattern_name)
                if called and getattr(called, "type", None) == "NOT":
                    res = True
                    break
        if res:
            break
    tau._calls_not = res
    return res


def OR_operator(
    transformations: dict[str, PDA],
    siblings: list[Any],
    m_sub: Environment,
    p: int,
    catalog: SubPatternCatalog | None = None,
) -> set[tuple[Environment, int]]:
    """Evaluates an Existential Union (OR) across multiple structural transformations.

    Args:
        transformations (dict[str, PDA]): The transformation graphs representing the OR branches.
        siblings (list[Any]): The sequence of sibling nodes S(v).
        m_sub (Environment): The local environment containing bound arguments.
        p (int): The maximum index of the sibling sequence.
        catalog: Optional SubPatternCatalog for resolving nested subpattern calls.

    Returns:
        set[tuple[Environment, int]]: The pure set union of all base evaluations.
    """
    results: set[tuple[Environment, int]] = set()
    is_stmt = len(siblings) > 0 and isinstance(siblings[0], Python3Parser.StmtContext)
    trace_enabled = _is_trace_enabled()
    for trans_name, tau in transformations.items():
        if trace_enabled:
            logger.trace(f"Computing OR for branch '{trans_name}'")
        if _tau_calls_not(tau, catalog=catalog):
            if trace_enabled:
                logger.trace(f"Branch '{trans_name}' contains NOT call; anchoring match at siblings[0]")
            if len(siblings) > 0:
                match_set = Matcher.match(tau, siblings[0], False, m_sub, catalog=catalog)
                for match in match_set.matches:
                    m_out = Environment.from_dict(match.bindings)
                    start_k = 1 if is_stmt else 0
                    end_k = p + 1 if is_stmt else p
                    for k in range(start_k, end_k):
                        results.add((m_out, k))
        else:
            if trace_enabled:
                logger.trace(f"Branch '{trans_name}' evaluating across sibling sequence")
            results.update(eval_base(tau, siblings, m_sub, catalog=catalog))

    return results


def AND_operator(
    transformations: dict[str, PDA] | list[PDA],
    siblings: list[Any],
    m_sub: Environment,
    p: int,
    catalog: SubPatternCatalog | None = None,
) -> set[tuple[Environment, int]]:
    """Evaluates a Universal Intersection (AND) with incremental branch pruning.

    Args:
        transformations (dict[str, PDA] | list[PDA]): The transformation graphs required to match concurrently.
        siblings (list[Any]): The sequence of sibling nodes S(v).
        m_sub (Environment): The local environment containing bound arguments.
        p (int): The maximum index of the sibling sequence.
        catalog: Optional SubPatternCatalog for resolving nested subpattern calls.

    Returns:
        set[tuple[Environment, int]]: A set of valid configurations (m_merged, k) representing
        the set intersection over valid block sizes 'k' and the set union over environment mappings.
    """
    results: set[tuple[Environment, int]] = set()
    trans_list = list(transformations.values()) if isinstance(transformations, dict) else transformations
    all_matches = [search(tau, siblings, m_sub, catalog=catalog) for tau in trans_list]

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
                                break
                        if conflict:
                            break
                if conflict:
                    break
        if conflict:
            continue
        merged_env: Environment | None = Environment.empty()
        for env in envs:
            if merged_env is None:
                break
            merged_env = merged_env.merge(env)
        if merged_env is None:
            continue
        max_idx = max(indices)
        for k in range(max_idx, p + 1):
            results.add((merged_env, k))
    return results


def eval_operator(
    op: Literal["AND", "OR", "NOT"] | str,
    transformations: dict[str, PDA],
    node: Any,
    m_sub: Environment,
    catalog: SubPatternCatalog | None = None,
) -> set[tuple[Environment, int]]:
    """Evaluates a logical subpattern operator over the sibling context of an AST node.

    Args:
        op (Literal["AND", "OR", "NOT"] | str): The subpattern logical composition operator.
        transformations (dict[str, PDA]): Named Pushdown Automata corresponding to the transformation branches.
        node: The active root node in the target Concrete Syntax Tree (CST).
        m_sub (Environment): The current local variable environment.
        catalog: Optional SubPatternCatalog for resolving nested subpattern calls.

    Returns:
        set[tuple[Environment, int]]: A set of resulting configurations (m_out, k).
    """
    siblings = get_siblings(node)
    p = len(siblings) - 1

    if op == "OR":
        return OR_operator(transformations, siblings, m_sub, p, catalog=catalog)
    elif op == "AND":
        return AND_operator(transformations, siblings, m_sub, p, catalog=catalog)
    elif op == "NOT":
        return NOT_operator(transformations, siblings, m_sub, p, catalog=catalog)
    else:
        raise ValueError(f"Unknown subpattern operator: {op}")


def mapping(params: list[str], args: list[str]) -> dict[str, str]:
    """Create the mapping m_(i->j) from parameters to arguments."""
    if len(params) != len(args):
        raise ValueError("Parameters and arguments must have the same length")
    return dict(zip(params, args))


def composition(mapping: Mapping[str, str], bindings: Mapping[str, Any] | Environment) -> dict[str, Any]:
    """The composition of a mapping m_(i->j) with bindings m_j."""
    result: dict[str, Any] = {}
    for u, t in mapping.items():
        if t in bindings:
            result[u] = bindings[t]
    return result


def join_dicts(m_a: Mapping[str, Any] | Environment, m_b: Mapping[str, Any] | Environment) -> dict[str, Any]:
    """Performs a non-conflicting overriding join between two dictionaries."""
    result: dict[str, Any] = dict(m_a)
    for key, value in m_b.items():
        if value is not None:
            result[key] = value
    return result


def call_subpattern(
    subpattern: BaseSubPattern,
    current_node: Any,
    caller_env: Environment | Mapping[str, Any],
    args: list[str],
    catalog: SubPatternCatalog | None = None,
    context_type: Any = None,
    call_id: int | None = None,
) -> list[tuple[Environment, int]]:
    """Invokes and evaluates a subpattern at the current AST node context during simulation.

    Args:
        subpattern (BaseSubPattern): The subpattern instance to invoke.
        current_node: Active node in the target parse tree.
        caller_env: Active variable environment in the caller.
        args (list[str]): Argument variable names passed in the call transition.
        catalog: Optional SubPatternCatalog for resolving nested subpattern calls.
        context_type: Target AST context type.
        call_id: Unique invocation identifier.

    Returns:
        list[tuple[Environment, int]]: List of valid (merged_environment, sibling_offset) configurations.
    """
    env = Environment.from_dict(caller_env)

    subpattern_name = subpattern.name
    op = subpattern.type
    transformations = None
    if catalog is not None and call_id is not None:
        transformations = catalog.get_compiled(subpattern_name, current_node, call_id=call_id)

    if transformations is None:
        lookup_ctx = context_type if context_type is not None else current_node

        if catalog is not None:
            from ..language_processors.python_processor import PythonProcessor

            try:
                transformations = catalog.get_or_compile(subpattern_name, lookup_ctx, PythonProcessor())
            except (ValueError, TypeError, Exception) as e:
                if lookup_ctx is not current_node:
                    try:
                        transformations = catalog.get_or_compile(subpattern_name, current_node, PythonProcessor())
                    except (ValueError, TypeError, Exception):
                        pass
                if transformations is None:
                    for (c_name, _), trans in getattr(catalog, "_compiled_cache", {}).items():
                        if c_name == subpattern_name:
                            transformations = trans
                            break
                if transformations is None:
                    logger.trace(f"Subpattern {subpattern_name} cannot be compiled: {e}")
                    return []
        else:
            from ..language_processors.python_processor import PythonProcessor

            try:
                transformations = PythonProcessor().compile_subpattern(subpattern, lookup_ctx)
            except (ValueError, TypeError, Exception) as e:
                if lookup_ctx is not current_node:
                    try:
                        transformations = PythonProcessor().compile_subpattern(subpattern, current_node)
                    except (ValueError, TypeError, Exception):
                        pass
                if transformations is None:
                    logger.trace(f"Subpattern {subpattern_name} cannot be compiled: {e}")
                    return []

    if subpattern.body_var is not None:
        sub_dict = dict(env)
        for arg in args:
            if arg not in sub_dict:
                sub_dict[arg] = None
        m_sub = Environment.from_dict(sub_dict)
    else:
        m_j_to_i = mapping(subpattern.args_order, args)
        comp = composition(m_j_to_i, env)
        m_j_epsilon = {u: None for u, _ in subpattern.args.items()}
        m_sub = Environment.from_dict(join_dicts(m_j_epsilon, comp))
    if _is_trace_enabled():
        logger.trace(f"Calling subpattern {subpattern_name} on node {current_node} with bindings {m_sub}")

    results = eval_operator(op, transformations, current_node, m_sub, catalog=catalog)

    if len(results) == 0:
        if _is_trace_enabled():
            logger.trace(f"subpattern {subpattern_name} did not match")
        return []

    new_envs: list[tuple[Environment, int]] = []
    debug_enabled = _is_debug_enabled()
    for new_env, k in results:
        if debug_enabled:
            pretty_bindings = {
                var: (
                    f"{val.__class__.__name__}: {val.getText() if hasattr(val, 'getText') else str(val)}"
                    if val is not None
                    else "None"
                )
                for var, val in new_env.items()
            }
            logger.debug(f"subpattern {subpattern_name} matched with bindings {pretty_bindings} at S({k})")

        new_binding: Environment | None
        if op == "NOT":
            new_binding = env
        elif subpattern.body_var is not None:
            filtered_env = {k: v for k, v in new_env.items() if not k.startswith("__")}
            new_binding = env.merge(filtered_env)
        else:
            m_i_to_j = mapping(args, subpattern.args_order)
            comp = composition(m_i_to_j, new_env)
            for k_var, v_val in new_env.items():
                if k_var not in subpattern.args:
                    comp[k_var] = v_val
            new_binding = env.merge(comp)
        if new_binding is not None:
            new_envs.append((new_binding, k))

    return new_envs


__all__ = [
    "get_siblings",
    "search",
    "eval_base",
    "NOT_operator",
    "OR_operator",
    "AND_operator",
    "eval_operator",
    "mapping",
    "composition",
    "join_dicts",
    "call_subpattern",
    "_tau_calls_not",
]
