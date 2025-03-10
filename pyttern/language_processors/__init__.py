from .python_processor import PythonProcessor
from .java_processor import JavaProcessor

def get_processor(lang):
    if lang == 'python':
        return PythonProcessor()
    elif lang == 'java':
        return JavaProcessor()
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
    return None