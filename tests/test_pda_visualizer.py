import pytest
from unittest.mock import MagicMock, patch
from pyttern.visualizer.pda_visualizer import (
    get_node_style, 
    format_stack, 
    format_transition_label, 
    parse_intervals, 
    is_in_intervals,
    visualize_pda,
    add_node_to_graph
)
from pyttern.simulator.pda.PDA import PDA
from pyttern.simulator.pda.transition import Transition, NodeTransition, NamedTransition, CallTransition
from pyttern.simulator.pda.PDA_alphabets import StackAlphabet, NavigationAlphabet

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

def test_format_stack():
    assert format_stack(None) == "ε"
    assert format_stack([]) == "ε"
    
    s = StackAlphabet.BODY
    assert format_stack(s) == "B"
    
    assert format_stack([StackAlphabet.BODY, StackAlphabet.INDENT]) == "BI"
    assert format_stack(["X", "Y"]) == "XY"

def test_format_transition_label():
    nt = NodeTransition(name="TestContext")
    t = Transition(q=0, alpha="ε", A=nt, t=[NavigationAlphabet.LEFT_CHILD], q_prime=1, beta="ε")
    label = format_transition_label(t)
    assert "Test" in label
    assert "LC" in label # str(LEFT_CHILD) is "LC"
    
    named_t = NamedTransition(name="var")
    t2 = Transition(q=0, alpha="ε", A=named_t, t=[], q_prime=1, beta="ε")
    label2 = format_transition_label(t2)
    assert "<var>" in label2

    call_t = CallTransition(subpattern_name="sub", transformation_name="trans", args=[])
    t3 = Transition(q=0, alpha="ε", A=call_t, t=[], q_prime=1, beta="ε")
    label3 = format_transition_label(t3)
    assert "sub:trans" in label3

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

@patch('graphviz.Digraph')
def test_visualize_pda_font_size(mock_digraph_class):
    mock_dot = MagicMock()
    mock_digraph_class.return_value = mock_dot
    
    pda = PDA()
    # Add a transition to have something to visualize
    s1 = pda.new_state()
    pda.add_transition(Transition(q=0, alpha="ε", A=NodeTransition("A"), t=[], q_prime=s1, beta="ε"))
    pda.final_states = s1

    visualize_pda(pda, "dummy_path", font_size=20, node_intervals="all")
    
    # Check if edge_attr.update was called with fontsize=20
    mock_dot.edge_attr.update.assert_any_call(fontsize='20')
    
    # Find the node calls
    node_calls = [call for call in mock_dot.node.call_args_list]
    assert len(node_calls) >= 2
    for call in node_calls:
        args, kwargs = call
        assert kwargs['fontsize'] == '12'

def test_add_node_to_graph_fixed_font_size():
    mock_graph = MagicMock()
    pda = PDA()
    
    # Test with default node_font_size (12)
    add_node_to_graph(mock_graph, 0, pda)
    mock_graph.node.assert_called_with('0', '0', 
        shape='circle', fixedsize='true', width='0.6', fontsize='12',
        style='filled', fillcolor='lightblue', fontcolor='black'
    )

@patch('graphviz.Digraph')
def test_visualize_pda_highlights(mock_digraph_class):
    mock_dot = MagicMock()
    mock_digraph_class.return_value = mock_dot
    
    pda = PDA()
    s1 = pda.new_state()
    s2 = pda.new_state()
    pda.final_states = s1
    
    highlights = {
        "2": {"color": "green"}
    }
    
    visualize_pda(pda, "dummy_path", highlights=highlights, node_intervals="all")
    
    # Find call for node 2
    found_green = False
    for call in mock_dot.node.call_args_list:
        args, kwargs = call
        if args[0] == '2':
            if kwargs.get('fillcolor') == '#008000':
                found_green = True
    assert found_green

@patch('graphviz.Digraph')
def test_visualize_pda_wrap_at(mock_digraph_class):
    mock_dot = MagicMock()
    mock_digraph_class.return_value = mock_dot
    # Mock subgraph context manager
    mock_subgraph = MagicMock()
    mock_dot.subgraph.return_value.__enter__.return_value = mock_subgraph
    
    pda = PDA()
    for _ in range(5):
        pda.new_state()
    pda.final_states = 5
    
    # states are 0, 1, 2, 3, 4, 5
    visualize_pda(pda, "dummy_path", wrap_at=2, node_intervals="all")
    
    # With wrap_at=2, we expect 3 subgraphs (0,1), (2,3), (4,5)
    assert mock_dot.subgraph.call_count == 3
    # Check if rank='same' was set in subgraphs
    mock_subgraph.attr.assert_any_call(rank='same')
