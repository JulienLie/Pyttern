from __future__ import annotations

from pathlib import Path

from .languages import Languages
from .base_processor_interface import BaseProcessor, BaseProcessorInterface
from .java_processor import JavaProcessor
from .python_processor import PythonProcessor


def get_processor(lang: str | Languages) -> BaseProcessor:
    if lang in ("python", Languages.PYTHON):
        return PythonProcessor()
    if lang in ("java", Languages.JAVA):
        return JavaProcessor()
    raise ValueError(f"Unsupported language: {lang}")


def determine_language(filename: str | Path) -> str | None:
    """
    Determines the language based on the file extension.
    Returns 'python' or 'java' atm or None for unsupported file types.
    """
    extension = str(filename).split(".")[-1]

    if extension in PythonProcessor().get_language_extensions():
        return "python"
    elif extension in JavaProcessor().get_language_extensions():
        return "java"
    return None


def determine_language_from_code(code: str) -> Languages | None:
    """
    Determines the language based on the code content.+
    Returns 'python' or 'java' atm or None for unsupported file types.
    """
    for language in Languages:
        try:
            processor = get_processor(language)
            processor.generate_tree_from_code(code)
            return language
        except Exception:
            continue
    return None


__all__ = [
    "BaseProcessor",
    "BaseProcessorInterface",
    "JavaProcessor",
    "Languages",
    "PythonProcessor",
    "determine_language",
    "determine_language_from_code",
    "get_processor",
]
