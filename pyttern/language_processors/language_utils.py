def determine_language(filename):
    """
    Determines the language based on the file extension.
    Returns 'python' or 'java' atm or None for unsupported file types.
    """
    extension = filename.split('.')[-1]
    if extension in {"pyt", "py"}:
        return "python"
    if extension in {"jat", "java"}:
        return "java"
    return None
