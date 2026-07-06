import graphviz
from ..simulator.pda.PDA import PDA
from ..simulator.pda.transition import Transition, NodeTransition, NamedTransition, CallTransition
from ..simulator.pda.PDA_alphabets import StackAlphabet, NavigationAlphabet

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

def get_node_style(color_name):
    if not color_name:
        return {}
    
    color_name = color_name.lower()
    rgb = None
    if color_name.startswith("#"):
        try:
            h = color_name.lstrip('#')
            if len(h) == 3:
                h = ''.join([c*2 for c in h])
            rgb = tuple(int(h[i:i+2], 16) for i in (0, 2, 4))
        except:
            rgb = (200, 200, 200)
    else:
        rgb = COLOR_MAP.get(color_name, (200, 200, 200))

    # Calculate luminance to decide between black and white text
    # Standard formula: 0.299*R + 0.587*G + 0.114*B
    luminance = (0.299 * rgb[0] + 0.587 * rgb[1] + 0.114 * rgb[2])
    
    font_color = "white" if luminance < 140 else "black"
    fill_hex = '#%02x%02x%02x' % rgb
    
    return {'style': 'filled', 'fillcolor': fill_hex, 'fontcolor': font_color}

def format_stack(s):
    if not s:
        return "ε"
    if isinstance(s, list):
        if not s:
            return "ε"
        return "".join([val.value if isinstance(val, StackAlphabet) else str(val) for val in s])
    if isinstance(s, StackAlphabet):
        return s.value if s.value else "ε"
    return str(s)

def format_transition_label(t: Transition):
    # Condition
    cond = ""
    if isinstance(t.A, NodeTransition):
        cond = t.A.name.replace("Context", "")
    elif isinstance(t.A, NamedTransition):
        cond = f"<{t.A.name}>"
    elif isinstance(t.A, CallTransition):
        cond = f"{t.A.subpattern_name}:{t.A.transformation_name}"
    
    # alpha -> beta
    alpha = format_stack(t.alpha)
    beta = format_stack(t.beta)
    
    # Navigation
    nav = ", ".join([str(n) for n in t.t])
    
    return f"{cond}, {alpha} -> {beta}\n{nav}"

def parse_intervals(intervals_input):
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

def is_in_intervals(val, intervals):
    if intervals is None:
        return True
    for start, end in intervals:
        if start <= val <= end:
            return True
    return False

def find_next_visible(pda: PDA, start_state: int, intervals: list, visited=None):
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

def visualize_pda(pda: PDA, output_path: str, title: str = "PDA", 
                  wrap_at: int = None, 
                  node_intervals: list = None,
                  highlights: dict = None,
                  font_size: int = 14):
    
    intervals = parse_intervals(node_intervals)
    if intervals is None and node_intervals is not None and node_intervals != "all":
        intervals = []
    elif intervals is None and node_intervals is None:
        intervals = []

    parsed_highlights = []
    if highlights:
        for range_str, cfg in highlights.items():
            parsed_highlights.append({
                "intervals": parse_intervals(range_str),
                "color": cfg.get("color", "red"),
                "highlight_self": cfg.get("self", False)
            })

    # Add highlighted nodes to intervals
    for h in parsed_highlights:
        if h["intervals"]:
            if intervals is not None:
                intervals.extend(h["intervals"])
    
    if intervals is not None:
        first = pda.initial_state
        last = pda.final_states
        if not is_in_intervals(first, intervals):
            intervals.append((first, first))
        if not is_in_intervals(last, intervals):
            intervals.append((last, last))
    
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

    visible_states = [s for s in sorted(list(pda.states)) if is_in_intervals(s, intervals)]
    
    # Add Nodes
    node_to_row = {}
    if wrap_at:
        first_nodes_of_rows = []
        for row_idx, i in enumerate(range(0, len(visible_states), wrap_at)):
            with dot.subgraph() as s:
                s.attr(rank='same')
                chunk = visible_states[i:i + wrap_at]
                if chunk:
                    first_nodes_of_rows.append(chunk[0])
                for state in chunk:
                    node_to_row[state] = row_idx
                    add_node_to_graph(s, state, pda, parsed_highlights)
        
        for j in range(len(first_nodes_of_rows) - 1):
            dot.edge(str(first_nodes_of_rows[j]), str(first_nodes_of_rows[j+1]), style='invis')
    else:
        for state in visible_states:
            add_node_to_graph(dot, state, pda, parsed_highlights)

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
                
                dot.edge(str(t.q), str(t.q_prime), label=label, **attr)
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
                        
                        dot.edge(str(state), str(v), **attr)
                        processed_skips.add((state, v))

    dot.render(output_path, format='pdf', cleanup=True)

def add_node_to_graph(graph, state, pda, highlights_config=None, node_font_size=12):
    # Scale node width based on font size (e.g., 14pt -> ~0.65in)
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
        
    graph.node(str(state), str(state), **attr)
