"""
Tree pruner interfaces and re-exports for language processors.
"""
from __future__ import annotations

from ..pytternfsm.generic_tree_pruner import GenericTreePruner
from ..pytternfsm.python.tree_pruner import BlockEndContext, TreePruner as PythonTreePruner
from ..pytternfsm.java.tree_pruner import TreePruner as JavaTreePruner

TreePruner = PythonTreePruner

__all__ = [
    "BlockEndContext",
    "GenericTreePruner",
    "JavaTreePruner",
    "PythonTreePruner",
    "TreePruner",
]
