import pytest
from unittest.mock import MagicMock, patch
from antlr4.tree.Tree import TerminalNode, ErrorNode
from pyttern.visualizer.pt_visualizer import (
    get_node_style, 
    parse_intervals, 
    is_in_intervals,
    flatten_tree_dict,
    find_subpattern_calls,
    collect_subpattern_trees,
    visualize_parse_tree
)
from pyttern.language_processors.python_processor import PythonProcessor
from pyttern.language_processors.languages import Languages
from pyttern.subpattern.subpattern_parser import parse_subpattern_from_string
from pyttern.subpattern.SubPattern import loaded_subpatterns

def test_get_node_style():
    # Test named color
    style = get_node_style("red")
    assert style['fillcolor'] == '#ff0000'
    assert style['fontcolor'] == 'white'
    
    # Test hex color
    style = get_node_style("#FFFFFF")
    assert style['fillcolor'] == '#ffffff'
    assert style['fontcolor'] == 'black'
    
    # Test invalid color
    style = get_node_style("invalid")
    assert style['fillcolor'] == '#c8c8c8' # default grey

def test_parse_intervals():
    assert parse_intervals(None) is None
    assert parse_intervals("all") is None
    assert parse_intervals("1,2-5, 10") == [(1, 1), (2, 5), (10, 10)]
    assert parse_intervals([1, "2-5"]) == [(1, 1), (2, 5)]

def test_is_in_intervals():
    intervals = [(1, 1), (5, 10)]
    assert is_in_intervals(1, intervals) is True
    assert is_in_intervals(2, intervals) is False
    assert is_in_intervals(5, intervals) is True
    assert is_in_intervals(7, intervals) is True
    assert is_in_intervals(10, intervals) is True
    assert is_in_intervals(11, intervals) is False
    assert is_in_intervals(5, None) is True

def test_flatten_tree_dict():
    tree1 = MagicMock()
    tree2 = MagicMock()
    tree3 = MagicMock()

    flat = flatten_tree_dict({"__main__": tree1, "sub": {"__main__": tree2, "nested": tree3}})
    assert flat["__main__"] == tree1
    assert flat["sub"] == tree2
    assert flat["nested"] == tree3

@patch('graphviz.Digraph')
def test_visualize_parse_tree_font_size(mock_digraph_class):
    mock_dot = MagicMock()
    mock_digraph_class.return_value = mock_dot
    
    processor = PythonProcessor()
    tree = processor.generate_tree_from_code("x = 1")

    visualize_parse_tree(tree, "dummy_path", font_size=20, node_intervals="all")
    
    # Check if dot.attr was called with fontsize='20'
    mock_dot.attr.assert_any_call(fontsize='20')

@patch('graphviz.Digraph')
def test_visualize_parse_tree_highlights(mock_digraph_class):
    mock_dot = MagicMock()
    mock_digraph_class.return_value = mock_dot
    
    processor = PythonProcessor()
    tree = processor.generate_tree_from_code("x = 1")
    
    highlights = {
        "0": {"color": "green"}
    }
    
    visualize_parse_tree(tree, "dummy_path", highlights=highlights, node_intervals="all")
    
    # Find call for node 0
    found_green = False
    for call in mock_dot.node.call_args_list:
        args, kwargs = call
        if "\n<File_input>" in args[1] and args[1].startswith("0\n"):
            if kwargs.get('fillcolor') == '#008000':
                found_green = True
    assert found_green

@patch('graphviz.Digraph')
def test_visualize_parse_tree_node_intervals(mock_digraph_class):
    mock_dot = MagicMock()
    mock_digraph_class.return_value = mock_dot
    
    processor = PythonProcessor()
    tree = processor.generate_tree_from_code("x = 1\ny = 2\nz = 3")
    
    visualize_parse_tree(tree, "dummy_path", node_intervals="0-2")
    
    # Should render filtered nodes and a skip node
    skip_found = False
    for call in mock_dot.node.call_args_list:
        args, kwargs = call
        if len(args) > 1 and args[1] == "...":
            skip_found = True
    assert skip_found

@patch('graphviz.Digraph')
def test_visualize_parse_tree_with_subpatterns_dict(mock_digraph_class):
    mock_dot = MagicMock()
    mock_digraph_class.return_value = mock_dot
    
    # Mock subgraph context manager
    mock_subgraph = MagicMock()
    mock_dot.subgraph.return_value.__enter__.return_value = mock_subgraph
    
    processor = PythonProcessor()
    tree1 = processor.generate_tree_from_code("x = 1")
    tree2 = processor.generate_tree_from_code("y = 2")
    
    tree_dict = {
        "__main__": tree1,
        "SubPattern::trans": tree2
    }
    
    visualize_parse_tree(tree_dict, "dummy_path", node_intervals="all")
    
    # Check that a subgraph was created for SubPattern::trans
    mock_dot.subgraph.assert_any_call(name="cluster_SubPattern__trans")
    mock_subgraph.attr.assert_any_call(label="SubPattern::trans")
    mock_subgraph.attr.assert_any_call(color="grey")
    mock_subgraph.attr.assert_any_call(style="dashed")
    
    # Check invisible edge connecting __main__ and SubPattern::trans
    mock_dot.edge.assert_any_call(str(hash(tree1)), f"SubPattern__trans_{hash(tree2)}", style="invis")

@patch('graphviz.Digraph')
def test_visualize_parse_tree_with_called_subpatterns_auto(mock_digraph_class):
    mock_dot = MagicMock()
    mock_digraph_class.return_value = mock_dot
    
    mock_subgraph = MagicMock()
    mock_dot.subgraph.return_value.__enter__.return_value = mock_subgraph
    
    subpattern_code = """
$|Incr(?x)

$# augAssign
?x += 1

$# add_v
?x = ?x + 1
"""
    parse_subpattern_from_string(subpattern_code, Languages.PYTHON)
    
    pattern_code = """
def foo(?x):
    ?$Incr(?x)
    return ?x
"""
    processor = PythonProcessor()
    tree = processor.generate_tree_from_code(pattern_code)
    
    visualize_parse_tree(tree, "dummy_path")
    
    # Subgraphs for Incr::augAssign and Incr::add_v should be created
    mock_dot.subgraph.assert_any_call(name="cluster_Incr__augAssign")
    mock_dot.subgraph.assert_any_call(name="cluster_Incr__add_v")

@patch('graphviz.Digraph')
def test_visualize_parse_tree_nested_subpatterns_auto(mock_digraph_class):
    mock_dot = MagicMock()
    mock_digraph_class.return_value = mock_dot
    
    mock_subgraph = MagicMock()
    mock_dot.subgraph.return_value.__enter__.return_value = mock_subgraph
    
    subpattern_code = """
$|Outer(?x)

$# CallInner
?$Inner(?x)

$|Inner(?y)

$# DoWork
?y = ?y + 1
"""
    parse_subpattern_from_string(subpattern_code, Languages.PYTHON)
    
    pattern_code = """
def test(?val):
    ?$Outer(?val)
"""
    processor = PythonProcessor()
    tree = processor.generate_tree_from_code(pattern_code)
    
    visualize_parse_tree(tree, "dummy_path")
    
    # Outer::CallInner and Inner::DoWork should both be found and visualized
    mock_dot.subgraph.assert_any_call(name="cluster_Outer__CallInner")
    mock_dot.subgraph.assert_any_call(name="cluster_Inner__DoWork")
