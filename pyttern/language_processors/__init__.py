from .python_processor import PythonProcessor
from .java_processor import JavaProcessor
from .languages import Languages
from .ludii_processor import LudiiProcessor


def get_processor(lang):
    if lang == 'python' or lang == Languages.PYTHON:
        return PythonProcessor()
    elif lang == 'java' or lang == Languages.JAVA:
        return JavaProcessor()
    elif lang == 'ludii' or lang == Languages.LUDII:
        return LudiiProcessor()
    else:
        raise ValueError(f"Unsupported language: {lang}")

def determine_language(filename):
    """
    Determines the language based on the file extension.
    Returns 'python' or 'java' atm or None for unsupported file types.
    """
    extension = filename.split('.')[-1]

    if extension in PythonProcessor().get_language_extensions():
        return "python"
    elif extension in JavaProcessor().get_language_extensions():
        return "java"
    elif extension in LudiiProcessor().get_language_extensions():
        return "ludii"
    return None

def determine_language_from_code(code):
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