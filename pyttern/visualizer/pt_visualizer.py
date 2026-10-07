import graphviz

try:
    from antlr4.tree.Tree import TerminalNode, ErrorNode
except ImportError:
    TerminalNode = ()
    ErrorNode = ()

try:
    from ..antlr.python import Python3Parser
except ImportError:
    try:
        from pyttern.antlr.python import Python3Parser
    except ImportError:
        Python3Parser = None

try:
    from ..subpattern.SubPattern import loaded_subpatterns
except ImportError:
    try:
        from pyttern.subpattern.SubPattern import loaded_subpatterns
    except ImportError:
        loaded_subpatterns = {}

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

    luminance = (0.299 * rgb[0] + 0.587 * rgb[1] + 0.114 * rgb[2])
    font_color = "white" if luminance < 140 else "black"
    fill_hex = '#%02x%02x%02x' % rgb
    
    return {'style': 'filled', 'fillcolor': fill_hex, 'fontcolor': font_color}

def flatten_tree_dict(d, current_prefix=""):
    flat = {}
    if not isinstance(d, dict):
        flat[current_prefix or "__main__"] = d
    else:
        for k, val in d.items():
            name = current_prefix if k == "__main__" else k
            if not name:
                name = "__main__"
            
            if not isinstance(val, dict):
                flat[name] = val
            else:
                flat.update(flatten_tree_dict(val, current_prefix=name))
    return flat

def find_subpattern_calls(node):
    calls = []
    if node is None:
        return calls
    
    is_sub_call = False
    rule_name = getattr(node, "rule_name", "")
    if rule_name in ("Subpattern_call", "Subpattern_callContext"):
        is_sub_call = True
    elif getattr(node, '__class__', None) and node.__class__.__name__ == 'Subpattern_callContext':
        is_sub_call = True
    elif Python3Parser is not None and isinstance(node, getattr(Python3Parser, 'Subpattern_callContext', ())):
        is_sub_call = True

    if is_sub_call:
        if hasattr(node, 'NAME') and callable(node.NAME):
            name_node = node.NAME()
            if name_node is not None:
                calls.append(name_node.getText())
        elif hasattr(node, "getChildCount") and node.getChildCount() > 0:
            first_child = node.getChild(0)
            if first_child is not None:
                calls.append(first_child.getText())
        elif hasattr(node, "children") and len(node.children) > 0:
            first_child = node.children[0]
            if first_child is not None:
                calls.append(getattr(first_child, "text", str(first_child)))

    if hasattr(node, "children") and isinstance(node.children, (list, tuple)):
        for child in node.children:
            calls.extend(find_subpattern_calls(child))
            
    return calls

def collect_subpattern_trees(root_tree, existing_keys=None):
    if existing_keys is None:
        existing_keys = set()
    
    collected = {}
    visited_subpatterns = set()
    queue = []
    
    queue.extend(find_subpattern_calls(root_tree))
    
    while queue:
        sub_name = queue.pop(0)
        if sub_name in visited_subpatterns:
            continue
        visited_subpatterns.add(sub_name)
        
        sub = loaded_subpatterns.get(sub_name)
        if sub is None or not hasattr(sub, 'transformations'):
            continue
            
        for t_name, t_tree in sub.transformations.items():
            key = f"{sub_name}::{t_name}"
            if key not in existing_keys and t_name not in existing_keys:
                collected[key] = t_tree
                existing_keys.add(key)
                
            # Check if this transformation tree calls further subpatterns
            nested_calls = find_subpattern_calls(t_tree)
            for nc in nested_calls:
                if nc not in visited_subpatterns:
                    queue.append(nc)
                    
    return collected

def visualize_parse_tree(tree, output_path: str, title: str = "Parse Tree", 
                         font_size: int = 14, node_intervals: list = None, 
                         highlights: dict = None, wrap_at: int = None):
    
    if not isinstance(tree, dict):
        trees = {"__main__": tree}
    else:
        trees = tree

    trees = flatten_tree_dict(trees)

    # Collect any called subpatterns not already in trees
    subpattern_trees = {}
    for t_name, t_node in list(trees.items()):
        discovered = collect_subpattern_trees(t_node, existing_keys=set(trees.keys()) | set(subpattern_trees.keys()))
        subpattern_trees.update(discovered)
    trees.update(subpattern_trees)

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
            color = cfg.get("color", "red") if isinstance(cfg, dict) else cfg
            parsed_highlights.append({
                "intervals": parse_intervals(range_str),
                "color": color
            })

    dot = graphviz.Digraph(comment=title)
    dot.attr(fontsize=str(font_size))
    
    # Scale spacing based on font size
    spacing = str(0.4 + (font_size / 40.0))
    dot.attr(nodesep=spacing)
    dot.attr(ranksep=spacing)
    dot.attr(pad='0.5')

    sorted_tree_names = []
    if "__main__" in trees:
        sorted_tree_names.append("__main__")
    for name in trees:
        if name != "__main__":
            sorted_tree_names.append(name)

    last_tree_root = None
    for tree_name in sorted_tree_names:
        current_tree = trees[tree_name]
        tree_name_clean = tree_name.replace(":", "_")
        
        if tree_name == "__main__":
            main_intervals = list(intervals) if intervals is not None else None
            for h in parsed_highlights:
                if h["intervals"] and main_intervals is not None:
                    main_intervals.extend(h["intervals"])
            
            root_id = _render_single_tree_to_graph(dot, current_tree, tree_name_clean, main_intervals, parsed_highlights, font_size)
        else:
            main_intervals = None
            subgraph_name = f"cluster_{tree_name_clean}"
            with dot.subgraph(name=subgraph_name) as sub:
                sub.attr(label=tree_name)
                sub.attr(color='grey')
                sub.attr(style='dashed')
                root_id = _render_single_tree_to_graph(sub, current_tree, tree_name_clean, main_intervals, parsed_highlights, font_size)

        # Connect subpatterns horizontally using invisible edges from the previous root node
        if root_id is not None:
            if last_tree_root is not None:
                dot.edge(last_tree_root, root_id, style='invis')
            last_tree_root = root_id

    dot.render(output_path, format='pdf', cleanup=True)

def _render_single_tree_to_graph(graph, tree, tree_name, intervals, parsed_highlights, font_size):
    # First Pass: Assign DFS IDs and identify nodes
    node_info = {}
    dfs_order = []
    
    def first_pass(node):
        dfs_id = len(dfs_order)
        dfs_order.append(node)
        node_info[node] = {"dfs_id": dfs_id}
        if hasattr(node, "children") and isinstance(node.children, (list, tuple)):
            for child in node.children:
                first_pass(child)
    
    first_pass(tree)
    if not dfs_order:
        return None
    
    # Root (0) and last node (len-1) are always visible in filtered mode
    current_intervals = list(intervals) if intervals is not None else None
    if current_intervals is not None:
        if not is_in_intervals(0, current_intervals):
            current_intervals.append((0, 0))
        last_id = len(dfs_order) - 1
        if not is_in_intervals(last_id, current_intervals):
            current_intervals.append((last_id, last_id))

    def get_visible_descendants(node):
        results = []
        if not hasattr(node, "children") or not isinstance(node.children, (list, tuple)):
            return results
        
        for child in node.children:
            child_id = node_info[child]["dfs_id"]
            if is_in_intervals(child_id, current_intervals):
                results.append(child)
            else:
                results.extend(get_visible_descendants(child))
        return results

    # Second Pass: Add nodes and edges
    def second_pass(node):
        node_id = node_info[node]["dfs_id"]
        if not is_in_intervals(node_id, current_intervals):
            return None

        unique_id = str(hash(node)) if tree_name == "__main__" else f"{tree_name}_{hash(node)}"
        
        # Determine Color
        h_color = None
        if parsed_highlights:
            for h in parsed_highlights:
                if is_in_intervals(node_id, h["intervals"]):
                    h_color = h["color"]
                    break
        
        attr = {
            'fontsize': str(font_size), 
            'style': 'filled', 
            'fillcolor': 'lightblue',
            'fontcolor': 'black',
            'penwidth': '0',
            'shape': 'box'
        }
        
        content = ""
        is_term = getattr(node, "is_terminal", False) or (isinstance(TerminalNode, type) and isinstance(node, TerminalNode))
        is_err = (getattr(node, "rule_name", "") == "ErrorNode") or (isinstance(ErrorNode, type) and isinstance(node, ErrorNode))

        if is_err:
            text = node.getText() if hasattr(node, "getText") else getattr(node, "text", str(node))
            content = f"Error: {text}"
            attr['shape'] = 'ellipse'
            attr['fillcolor'] = 'red'
        elif is_term:
            if hasattr(node, 'symbol'):
                content = getattr(node.symbol, 'text', str(node))
            elif hasattr(node, 'getText'):
                content = node.getText()
            else:
                content = getattr(node, 'text', str(node))
            attr['shape'] = 'ellipse'
        else:
            if hasattr(node, "rule_name"):
                name = node.rule_name.replace("Context", "")
            else:
                name = node.__class__.__name__.replace("Context", "")
            content = f"<{name}>"
        
        if h_color:
            attr.update(get_node_style(h_color))
            
        label = f"{node_id}\n{content}"
        graph.node(unique_id, label, **attr)
        
        # Add edges to visible descendants
        children = getattr(node, "children", [])
        if not isinstance(children, (list, tuple)):
            children = []
        skipped_any = False
        
        for child in children:
            cid = node_info[child]["dfs_id"]
            if is_in_intervals(cid, current_intervals):
                child_unique_id = second_pass(child)
                if child_unique_id:
                    graph.edge(unique_id, child_unique_id)
            else:
                # Find visible descendants below this skipped child
                visible_below = get_visible_descendants(child)
                if not visible_below:
                    skipped_any = True
                else:
                    for vd in visible_below:
                        vd_unique_id = second_pass(vd)
                        if vd_unique_id:
                            graph.edge(unique_id, vd_unique_id, style="dashed", color="grey")
        
        if skipped_any:
            # Add an empty-ish node to show that children were skipped
            skip_id = f"skip_{unique_id}"
            graph.node(skip_id, "...", shape="plain", fontsize=str(font_size))
            graph.edge(unique_id, skip_id, style="dotted", arrowhead="none")
        
        return unique_id

    return second_pass(tree)
