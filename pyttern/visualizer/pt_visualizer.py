import graphviz
from antlr4.tree.Tree import TerminalNode, ErrorNode

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

def visualize_parse_tree(tree, output_path: str, title: str = "Parse Tree", 
                         font_size: int = 14, node_intervals: list = None, 
                         highlights: dict = None):
    
    dot = graphviz.Digraph(comment=title)
    dot.attr(fontsize=str(font_size))
    
    # Scale spacing based on font size
    spacing = str(0.4 + (font_size / 40.0))
    dot.attr(nodesep=spacing)
    dot.attr(ranksep=spacing)
    dot.attr(pad='0.5')
    
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
                "color": cfg.get("color", "red")
            })
            if intervals is not None:
                intervals.extend(parse_intervals(range_str))

    # First Pass: Assign DFS IDs and identify nodes
    node_info = {}
    dfs_order = []
    
    def first_pass(node):
        dfs_id = len(dfs_order)
        dfs_order.append(node)
        node_info[node] = {"dfs_id": dfs_id}
        if hasattr(node, "children") and node.children:
            for child in node.children:
                first_pass(child)
    
    first_pass(tree)
    
    # Root (0) and last node (len-1) are always visible in filtered mode
    if intervals is not None:
        if not is_in_intervals(0, intervals):
            intervals.append((0, 0))
        last_id = len(dfs_order) - 1
        if not is_in_intervals(last_id, intervals):
            intervals.append((last_id, last_id))

    def get_visible_descendants(node):
        results = []
        if not hasattr(node, "children") or not node.children:
            return results
        
        for child in node.children:
            child_id = node_info[child]["dfs_id"]
            if is_in_intervals(child_id, intervals):
                results.append(child)
            else:
                results.extend(get_visible_descendants(child))
        return results

    # Second Pass: Add nodes and edges
    def second_pass(node):
        node_id = node_info[node]["dfs_id"]
        if not is_in_intervals(node_id, intervals):
            return None

        unique_id = str(hash(node))
        
        # Determine Color
        h_color = None
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
        if isinstance(node, TerminalNode):
            content = node.symbol.text
            attr['shape'] = 'ellipse'
        elif isinstance(node, ErrorNode):
            content = f"Error: {node.getText()}"
            attr['shape'] = 'ellipse'
            attr['fillcolor'] = 'red'
        else:
            name = node.__class__.__name__.replace("Context", "")
            content = f"<{name}>"
        
        if h_color:
            attr.update(get_node_style(h_color))
            
        label = f"{node_id}\n{content}"
        dot.node(unique_id, label, **attr)
        
        # Add edges to visible descendants
        children = getattr(node, "children", [])
        skipped_any = False
        
        for child in children:
            cid = node_info[child]["dfs_id"]
            if is_in_intervals(cid, intervals):
                child_unique_id = second_pass(child)
                dot.edge(unique_id, child_unique_id)
            else:
                # Find visible descendants below this skipped child
                visible_below = get_visible_descendants(child)
                if not visible_below:
                    skipped_any = True
                else:
                    for vd in visible_below:
                        vd_unique_id = second_pass(vd)
                        dot.edge(unique_id, vd_unique_id, style="dashed", color="grey")
        
        if skipped_any:
            # Add an empty-ish node to show that children were skipped
            skip_id = f"skip_{unique_id}"
            dot.node(skip_id, "...", shape="plain", fontsize=str(font_size))
            dot.edge(unique_id, skip_id, style="dotted", arrowhead="none")
        
        return unique_id

    second_pass(tree)
    dot.render(output_path, format='pdf', cleanup=True)
