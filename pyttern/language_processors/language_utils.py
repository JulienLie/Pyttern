"""
Utility functions for language identification and resolution.
"""

from typing import Optional
from .languages import Languages


def determine_language(filename: str) -> Optional[str]:
    """
    Determines the language ('python' or 'java') based on the file extension.
    Returns None for unsupported file types.
    """
    from .python_processor import PythonProcessor
    from .java_processor import JavaProcessor

    extension = str(filename).split('.')[-1].lower()

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
