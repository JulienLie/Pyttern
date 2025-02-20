from .python_processor import PythonProcessor
from .java_processor import JavaProcessor

def get_processor(lang):
    if lang == 'python':
        return PythonProcessor()
    elif lang == 'java':
        return JavaProcessor()
    else:
        raise ValueError(f"Unsupported language: {lang}")