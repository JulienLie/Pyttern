from __future__ import annotations

from .base_processor_interface import BaseProcessor, BaseProcessorInterface
from .java_processor import JavaProcessor
from .language_utils import determine_language, determine_language_from_code
from .languages import Languages
from .python_processor import PythonProcessor


def get_processor(lang: str | Languages) -> BaseProcessor:
    if lang in ("python", Languages.PYTHON):
        return PythonProcessor()
    if lang in ("java", Languages.JAVA):
        return JavaProcessor()
    raise ValueError(f"Unsupported language: {lang}")


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
