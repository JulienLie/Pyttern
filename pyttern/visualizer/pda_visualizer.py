"""Graphviz-based visualizer for Pushdown Automaton (PDA) state machines."""

from typing import Any

import graphviz

from ..simulator.pda.PDA import PDA
from ..simulator.pda.PDA_alphabets import NavigationAlphabet, StackAlphabet
from ..simulator.pda.transition import CallTransition, NamedTransition, NodeTransition, Transition

COLOR_MAP = {
    "red": (255, 0, 0),
    "blue": (0, 0, 255),
    "green": (0, 128, 0),
    "yellow": (255, 255, 0),
    "orange": (255, 165, 0),
    "purple": (128, 0, 128),
    "pink": (255, 192, 203),
    "cyan": (0, 255, 255),
    "teal": (0, 128, 128),
    "magenta": (255, 0, 255),
    "grey": (128, 128, 128),
    "gray": (128, 128, 128),
    "black": (0, 0, 0),
    "white": (255, 255, 255),
    "lightblue": (173, 216, 230),
    "lightgreen": (144, 238, 144),
    "gold": (255, 215, 0),
    "darkblue": (0, 0, 139),
    "darkred": (139, 0, 0),
    "darkgreen": (0, 100, 0),
}


def get_node_style(color_name: str | None) -> dict[str, str]:
    """Calculate Graphviz fill and contrasting font color styles for a given color.

    Args:
        color_name: CSS color name or hex string (e.g. '#FF0000').

    Returns:
        dict[str, str]: Graphviz node style attribute dictionary.
    """
    if not color_name:
        return {}

    color_name = color_name.lower()
    rgb = None
    if color_name.startswith("#"):
        try:
            h = color_name.lstrip('#')
            if len(h) == 3:
                h = ''.join([c * 2 for c in h])
            rgb = tuple(int(h[i:i + 2], 16) for i in (0, 2, 4))
        except Exception:
            rgb = (200, 200, 200)
    else:
        rgb = COLOR_MAP.get(color_name, (200, 200, 200))

    # Calculate luminance to decide between black and white text
    # Standard formula: 0.299*R + 0.587*G + 0.114*B
    luminance = (0.299 * rgb[0] + 0.587 * rgb[1] + 0.114 * rgb[2])

    font_color = "white" if luminance < 140 else "black"
    fill_hex = '#%02x%02x%02x' % rgb

    return {'style': 'filled', 'fillcolor': fill_hex, 'fontcolor': font_color}


def format_stack(s: Any) -> str:
    """Format a stack symbol or sequence of symbols into readable string notation.

    Args:
        s: Stack symbol enum, list of symbols, or primitive string.

    Returns:
        str: Formatted stack string or 'ε' for empty stack.
    """
    if not s:
        return "ε"
    if isinstance(s, list):
        if not s:
            return "ε"
        return "".join([val.value if isinstance(val, StackAlphabet) else str(val) for val in s])
    if isinstance(s, StackAlphabet):
        return s.value if s.value else "ε"
    return str(s)


def format_transition_label(t: Transition) -> str:
    """Format a PDA transition into edge label text with condition and stack operations.

    Args:
        t: Transition instance.

    Returns:
        str: Multiline transition label string.
    """
    # Condition
    cond = ""
    if isinstance(t.A, NodeTransition):
        cond = t.A.name.replace("Context", "")
    elif isinstance(t.A, NamedTransition):
        cond = f"<{t.A.name}>"
    elif isinstance(t.A, CallTransition):
        cond = f"{t.A.subpattern_name}({','.join(t.A.args)})"

    # alpha -> beta
    alpha = format_stack(t.alpha)
    beta = format_stack(t.beta)

    # Navigation
    nav = ", ".join([str(n) for n in t.t])

    stack_op = f"{alpha} -> {beta}"
    label = f"{cond}, {stack_op}" if cond else stack_op
    return f"{label}\n{nav}"


def parse_intervals(intervals_input: Any) -> list[tuple[int, int]] | None:
    """Parse comma-separated interval strings into list of (start, end) tuples.

    Args:
        intervals_input: String expression like '0-5,10,12-15' or list of intervals.

    Returns:
        list[tuple[int, int]] | None: List of integer interval tuples or None if 'all'.
    """
    if not intervals_input:
        return None

    if isinstance(intervals_input, str):
        if intervals_input.lower() == "all":
            return None
        parts = intervals_input.split(',')
    else:
        parts = intervals_input

    intervals = []
    for part in parts:
        part = str(part).strip()
        if '-' in part:
            try:
                start, end = map(int, part.split('-'))
                intervals.append((start, end))
            except ValueError:
                continue
        else:
            try:
                val = int(part)
                intervals.append((val, val))
            except ValueError:
                continue
    return intervals


def is_in_intervals(val: int, intervals: list[tuple[int, int]] | None) -> bool:
    """Check whether an integer value falls within any inclusive interval.

    Args:
        val: Integer to test.
        intervals: List of inclusive (start, end) bounds or None for all.

    Returns:
        bool: True if val is covered by the intervals.
    """
    if intervals is None:
        return True
    for start, end in intervals:
        if start <= val <= end:
            return True
    return False


def find_next_visible(pda: PDA, start_state: int, intervals: list, visited: set | None = None) -> set[int]:
    """Find visible states reachable from an omitted state through skipped transitions.

    Args:
        pda: Pushdown automaton.
        start_state: Hidden intermediate state ID.
        intervals: Allowed visible state intervals.
        visited: Visited state IDs to prevent recursion cycles.

    Returns:
        set[int]: Set of reachable visible state IDs.
    """
    if visited is None:
        visited = set()
    if start_state in visited:
        return set()
    visited.add(start_state)

    results = set()
    transitions = pda.transitions.get(start_state, [])
    for t in transitions:
        if is_in_intervals(t.q_prime, intervals):
            results.add(t.q_prime)
        else:
            results.update(find_next_visible(pda, t.q_prime, intervals, visited))
    return results


def visualize_pda(
    pda: PDA | dict[str, PDA],
    output_path: str,
    title: str = "PDA",
    wrap_at: int | None = None,
    node_intervals: list | str | None = None,
    highlights: dict | None = None,
    font_size: int = 14,
) -> None:
    """Render a PDA or dictionary of PDAs to a PDF file using Graphviz.

    Args:
        pda: Pushdown automaton or dictionary of named PDAs.
        output_path: Target path for the output PDF (without extension).
        title: Title of the generated diagram.
        wrap_at: Maximum number of states per row before wrapping.
        node_intervals: Filter intervals for visible states (e.g. '0-10,20-30' or 'all').
        highlights: Dictionary mapping intervals to highlight color configurations.
        font_size: Font size for state and transition labels.
    """
    if isinstance(pda, PDA):
        pdas = {"__main__": pda}
    else:
        pdas = pda

    def flatten_pda_dict(d: Any, current_prefix: str = "") -> dict[str, PDA]:
        flat = {}
        if isinstance(d, PDA):
            flat[current_prefix or "__main__"] = d
        elif isinstance(d, dict):
            for k, val in d.items():
                name = current_prefix if k == "__main__" else k
                if not name:
                    name = "__main__"

                if isinstance(val, PDA):
                    flat[name] = val
                elif isinstance(val, dict):
                    flat.update(flatten_pda_dict(val, current_prefix=name))
        return flat

    pdas = flatten_pda_dict(pdas)

    intervals = parse_intervals(node_intervals)
    if node_intervals == "all":
        intervals = None
    elif intervals is None and node_intervals is not None:
        intervals = []
    elif intervals is None and node_intervals is None:
        intervals = None  # Show all states by default

    parsed_highlights = []
    if highlights:
        for range_str, cfg in highlights.items():
            parsed_highlights.append({
                "intervals": parse_intervals(range_str),
                "color": cfg.get("color", "red"),
                "highlight_self": cfg.get("self", False)
            })

    dot = graphviz.Digraph(comment=title)
    dot.attr(pad='0.5')
    dot.edge_attr.update(fontsize=str(font_size))

    # Scale spacing based on font size
    spacing = str(0.4 + (font_size / 40.0))
    dot.attr(nodesep=spacing)
    dot.attr(ranksep=spacing)

    if wrap_at:
        dot.attr(rankdir='TB')
    else:
        dot.attr(rankdir='LR')

    dot.attr(overlap='false')
    dot.attr(splines='true')

    # Render each PDA
    last_pda_initial = None
    for pda_name, current_pda in pdas.items():
        if pda_name == "__main__":
            target_graph = dot
            main_intervals = list(intervals) if intervals is not None else None
            for h in parsed_highlights:
                if h["intervals"] and main_intervals is not None:
                    main_intervals.extend(h["intervals"])

            if main_intervals is not None:
                first = current_pda.initial_state
                last = current_pda.final_states
                if not is_in_intervals(first, main_intervals):
                    main_intervals.append((first, first))
                if not is_in_intervals(last, main_intervals):
                    main_intervals.append((last, last))

            visible_states = [s for s in sorted(list(current_pda.states)) if is_in_intervals(s, main_intervals)]
        else:
            main_intervals = None
            visible_states = sorted(list(current_pda.states))

        pda_name_clean = pda_name.replace(":", "_")
        if pda_name == "__main__":
            _render_single_pda_to_graph(dot, current_pda, pda_name_clean, visible_states, main_intervals, wrap_at, parsed_highlights)
        else:
            subgraph_name = f"cluster_{pda_name_clean}"
            with dot.subgraph(name=subgraph_name) as sub:
                sub.attr(label=pda_name)
                sub.attr(color='grey')
                sub.attr(style='dashed')
                _render_single_pda_to_graph(sub, current_pda, pda_name_clean, visible_states, main_intervals, None, parsed_highlights)

        # Connect subpatterns horizontally using invisible edges from the previous initial state
        if current_pda.states:
            current_initial = "0" if pda_name_clean == "__main__" else f"{pda_name_clean}_0"
            if last_pda_initial is not None:
                dot.edge(last_pda_initial, current_initial, style='invis')
            last_pda_initial = current_initial

    dot.render(output_path, format='pdf', cleanup=True)


def _render_single_pda_to_graph(
    graph: Any,
    pda: PDA,
    pda_name: str,
    visible_states: list[int],
    intervals: list[tuple[int, int]] | None,
    wrap_at: int | None,
    parsed_highlights: list[dict[str, Any]],
) -> None:
    """Render states and transitions of a single PDA onto a Graphviz graph or cluster.

    Args:
        graph: Target Graphviz Digraph or Subgraph.
        pda: Pushdown automaton to render.
        pda_name: Prefix name for namespacing node IDs.
        visible_states: Filtered list of visible state IDs.
        intervals: Allowed interval list.
        wrap_at: Number of states before row break.
        parsed_highlights: Color highlight specifications.
    """
    node_to_row = {}
    if wrap_at:
        first_nodes_of_rows = []
        for row_idx, i in enumerate(range(0, len(visible_states), wrap_at)):
            with graph.subgraph() as s:
                s.attr(rank='same')
                chunk = visible_states[i:i + wrap_at]
                if chunk:
                    first_nodes_of_rows.append(chunk[0])
                for state in chunk:
                    node_to_row[state] = row_idx
                    add_node_to_graph(s, state, pda, parsed_highlights, pda_name=pda_name)

        for j in range(len(first_nodes_of_rows) - 1):
            n1 = str(first_nodes_of_rows[j]) if pda_name == "__main__" else f"{pda_name}_{first_nodes_of_rows[j]}"
            n2 = str(first_nodes_of_rows[j + 1]) if pda_name == "__main__" else f"{pda_name}_{first_nodes_of_rows[j + 1]}"
            graph.edge(n1, n2, style='invis')
    else:
        for state in visible_states:
            add_node_to_graph(graph, state, pda, parsed_highlights, pda_name=pda_name)

    processed_skips = set()

    for state in visible_states:
        for t in pda.transitions.get(state, []):
            if is_in_intervals(t.q_prime, intervals):
                label = format_transition_label(t)
                attr = {}

                if wrap_at:
                    row_q = node_to_row.get(t.q)
                    row_q_prime = node_to_row.get(t.q_prime)
                    if row_q is not None and row_q_prime is not None and row_q != row_q_prime:
                        attr['constraint'] = 'false'

                h_color = None
                is_self = (t.q == t.q_prime)

                for h in parsed_highlights:
                    if is_in_intervals(t.q, h["intervals"]) and is_in_intervals(t.q_prime, h["intervals"]):
                        if not is_self or h["highlight_self"]:
                            h_color = h["color"]
                            break

                if h_color:
                    attr['color'] = h_color
                    attr['penwidth'] = '2.0'
                    attr['fontcolor'] = h_color

                node_q = str(t.q) if pda_name == "__main__" else f"{pda_name}_{t.q}"
                node_qp = str(t.q_prime) if pda_name == "__main__" else f"{pda_name}_{t.q_prime}"
                graph.edge(node_q, node_qp, label=label, **attr)
            else:
                next_visible = find_next_visible(pda, t.q_prime, intervals)
                for v in next_visible:
                    if (state, v) not in processed_skips:
                        attr = {"style": "dashed", "color": "grey", "label": "..."}
                        if wrap_at:
                            row_q = node_to_row.get(state)
                            row_v = node_to_row.get(v)
                            if row_q is not None and row_v is not None and row_q != row_v:
                                attr['constraint'] = 'false'

                        node_state = str(state) if pda_name == "__main__" else f"{pda_name}_{state}"
                        node_v = str(v) if pda_name == "__main__" else f"{pda_name}_{v}"
                        graph.edge(node_state, node_v, **attr)
                        processed_skips.add((state, v))


def add_node_to_graph(
    graph: Any,
    state: int,
    pda: PDA,
    highlights_config: list[dict[str, Any]] | None = None,
    node_font_size: int = 12,
    pda_name: str = "__main__",
) -> None:
    """Format and add a single PDA state node to the Graphviz graph.

    Args:
        graph: Target Graphviz graph or cluster.
        state: Integer state identifier.
        pda: Parent PDA instance.
        highlights_config: Optional highlight settings.
        node_font_size: Font size for state text.
        pda_name: Graphviz node prefix.
    """
    width = str(0.3 + (node_font_size / 40.0) * 1.0)
    attr = {'shape': 'circle', 'fixedsize': 'true', 'width': width, 'fontsize': str(node_font_size)}

    highlight_color = None
    if highlights_config:
        for h in highlights_config:
            if is_in_intervals(state, h["intervals"]):
                highlight_color = h["color"]
                break

    if state == pda.initial_state:
        attr.update({'style': 'filled', 'fillcolor': 'lightblue', 'fontcolor': 'black'})
    elif state == pda.final_states:
        attr.update({'shape': 'doublecircle', 'style': 'filled', 'fillcolor': 'orange', 'fontcolor': 'black'})
    elif highlight_color:
        attr.update(get_node_style(highlight_color))
    else:
        attr.update({'style': 'filled', 'fillcolor': 'lightblue', 'fontcolor': 'black'})

    node_id = str(state) if pda_name == "__main__" else f"{pda_name}_{state}"
    graph.node(node_id, str(state), **attr)

