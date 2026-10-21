from __future__ import annotations

from pathlib import Path


def determine_language(filename: str | Path) -> str | None:
    """
    Determines the language based on the file extension.
    Returns 'python' or 'java' atm or None for unsupported file types.
    """
    extension = str(filename).split(".")[-1]
    if extension in {"pyt", "py"}:
        return "python"
    if extension in {"jat", "java"}:
        return "java"
    return None


__all__ = ["determine_language"]
