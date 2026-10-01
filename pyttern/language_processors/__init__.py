from .python_processor import PythonProcessor
from .java_processor import JavaProcessor
from .languages import Languages
from .language_utils import determine_language, determine_language_from_code

def get_processor(lang):
    if lang in ('python', Languages.PYTHON):
        return PythonProcessor()
    if lang in ('java', Languages.JAVA):
        return JavaProcessor()
    raise ValueError(f"Unsupported language: {lang}")

__all__ = [
    "PythonProcessor",
    "JavaProcessor",
    "Languages",
    "get_processor",
    "determine_language",
    "determine_language_from_code",
]
