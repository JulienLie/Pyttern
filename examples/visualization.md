# Visualization Guide

Pyttern provides tools to visualize the internal representation of patterns (Pushdown Automata) and the structure of source code (Parse Trees). These visualizations are essential for debugging complex patterns.

## Tools

### 1. `pytternweb`
An interactive web interface to step through the matching process. It shows the current state of the PDA and where it is in the source code.

### 2. `pytterngraph`
A CLI tool to generate PDF graphs of PDAs and Parse Trees.

```bash
pytterngraph <input_file> <output_name> [options]
```

## Graph Customization

The `pytterngraph` tool offers several options to control the appearance of the generated PDF:

### Font Size (`--font-size`)
Controls the text size in the graph. 
- In **PDA visualizations**, this parameter changes the size of the **transition labels**. The node numbers remain at a fixed, readable size to keep the graph compact.
- Default is `14`.

### Wrapping (`--wrap-at`)
For large graphs, use `--wrap-at <N>` to force the graph to wrap into multiple rows after `N` nodes. This is particularly useful for long linear code or patterns.

### Node Filtering (`--nodes`)
Focus on specific parts of the graph by providing intervals:
```bash
pytterngraph pattern.pyt output --nodes "0-5,10,15-20"
```

## Configuration Examples

You can use YAML files to save complex visualization settings. Below are default examples for both Parse Trees and PDAs.

### 1. Parse Tree (PT) Configuration
Focuses on structural highlighting of AST nodes.

**`pt_config.yaml`:**
```yaml
type: pt
lang: python
font_size: 14
wrap_at: 10
nodes: # Only show these node ID ranges
  - "1-20"
  - "50-60"
highlight:
  "3-8":
    color: "teal"
  "55":
    color: "orange"
```

### 2. PDA (TPA) Configuration
Focuses on state machine transitions and highlights specific logic paths.

**`tpa_config.yaml`:**
```yaml
type: tpa
lang: python
font_size: 18
wrap_at: 8
nodes: 
  - "0-15"
highlight:
  "5-10":
    color: "red"
    self: false # Whether to highlight self-loop transitions
  "12-14":
    color: "blue"
```

**Usage:**
```bash
pytterngraph code.py output --config pt_config.yaml
pytterngraph pattern.pyt output --config tpa_config.yaml
```

## Visual Differences

### PDA (Pushdown Automaton)
- **Nodes**: Represent states. The initial state is light blue, and final states are orange.
- **Edges**: Represent transitions. The label shows the condition (e.g., a node type or wildcard name) and the stack operations.

### PT (Parse Tree)
- **Nodes**: Represent AST nodes (Contexts).
- **Edges**: Represent the parent-child relationship in the tree.
