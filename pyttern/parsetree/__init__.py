"""Parse tree data structures for Pyttern."""

from typing import TYPE_CHECKING

if TYPE_CHECKING:
    from pyttern._pyttern_cpp import Node
else:
    try:
        from pyttern._pyttern_cpp import Node
    except ImportError:
        class Node:  # type: ignore[no-redef]
            """Placeholder Node when C++ extension is not yet compiled."""
            pass

__all__ = ["Node"]
