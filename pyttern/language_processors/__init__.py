from .python_processor import PythonProcessor
from .java_processor import JavaProcessor
from .languages import Languages

def get_processor(lang):
    if lang in ('python', Languages.PYTHON):
        return PythonProcessor()
    if lang in ('java', Languages.JAVA):
        return JavaProcessor()
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
