"""Utility functions for language identification and resolution."""
from __future__ import annotations

from pathlib import Path
from typing import Optional

from .languages import Languages


def determine_language(filename: str | Path) -> Optional[str]:
    """
    Determines the language ('python' or 'java') based on the file extension.
    Returns None for unsupported file types.
    """
    from .java_processor import JavaProcessor
    from .python_processor import PythonProcessor

    extension = str(filename).split(".")[-1].lower()

    if extension in PythonProcessor().get_language_extensions():
        return "python"
    elif extension in JavaProcessor().get_language_extensions():
        return "java"
    return None


def determine_language_from_code(code: str) -> Optional[Languages]:
    """
    Determines the language based on code parsing validity.
    Returns the Languages enum value or None if unrecognized.
    """
    from . import get_processor

    for language in Languages:
        try:
            processor = get_processor(language)
            processor.generate_tree_from_code(code)
            return language
        except Exception:
            continue
    return None


__all__ = ["determine_language", "determine_language_from_code"]
