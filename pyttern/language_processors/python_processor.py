import io
from functools import lru_cache

from antlr4 import CommonTokenStream, InputStream
from loguru import logger

from .base_processor_interface import BaseProcessor
from ..Pyttern_listener import ConsolePytternListener
from ..antlr.python import Python3Parser
from ..antlr.python.Python3Lexer import Python3Lexer
from ..pyttern_error_listener import Python3ErrorListener, PytternErrorListener
from ..pytternfsm.python.python_to_pda import Python_to_PDA
from ..pytternfsm.python.tree_pruner import TreePruner


class PythonProcessor(BaseProcessor):

    @lru_cache(maxsize=128)
    def generate_tree_from_code(self, code):
        code = code.strip()
        code += "\n"
        stream = InputStream(code)
        return self.generate_tree_from_stream(stream)

    @lru_cache(maxsize=128)
    def generate_tree_from_stream(self, stream):
        logger.debug("Generating tree")
        lexer = Python3Lexer(stream)
        stream = CommonTokenStream(lexer)
        py_parser = Python3Parser(stream)

        input = io.StringIO()

        py_parser.removeErrorListeners()
        error_listener = Python3ErrorListener(input)
        py_parser.addErrorListener(error_listener)

        tree = py_parser.file_input()

        pruned_tree = TreePruner().visit(tree)

        return pruned_tree

    @lru_cache(maxsize=128)
    def generate_tree_from_file(self, file):
        with open(file, 'r', encoding="utf-8") as f:
            return self.generate_tree_from_code(f.read())

    @lru_cache(maxsize=128)
    def create_pda(self, pattern_tree):
        return Python_to_PDA().visit(pattern_tree)

    def create_listener(self):
        return ConsolePytternListener()

    def get_language_extensions(self):
        return ["py", "pyt", "pyh"]

    @staticmethod
    def parse_diagnostics(code: str) -> list[dict]:
        """
        * **Arguments:**
        - `code` (`str`): The full text content of the active document.
        * **Return Value:**
        - A list of diagnostic dictionaries, representing parsing errors, syntax warnings, or validation issues.
        - Return an empty list `[]` if no errors are found.

        #### Diagnostic Dictionary Format:
        Each dictionary in the returned list should have the following fields:

        | Field Name | Type | Description |
        | :--- | :--- | :--- |
        | `message` | `str` | The error description or description of the issue. |
        | `line` | `int` | **0-indexed** starting line number of the diagnostic. |
        | `character` | `int` | **0-indexed** starting character offset of the diagnostic on that line. |
        | `severity` | `str` | The severity of the diagnostic. Must be one of: `'error'`, `'warning'`, `'info'`, `'hint'`. |
        | `end_line` | `int` | *(Optional)* **0-indexed** ending line number. Defaults to `line` if omitted. |
        | `end_character`| `int` | *(Optional)* **0-indexed** ending character offset. Defaults to `character + 1` if omitted. |
        """
        code = code.strip() + "\n"
        stream = InputStream(code)

        logger.debug(f"Parsing {code} for debugging purpose")
        lexer = Python3Lexer(stream)
        stream = CommonTokenStream(lexer)
        py_parser = Python3Parser(stream)

        error = io.StringIO()

        py_parser.removeErrorListeners()
        error_listener = PytternErrorListener(error)
        py_parser.addErrorListener(error_listener)

        py_parser.file_input()

        logger.debug(f"Parsing done, error found: {error_listener.errors}")

        return error_listener.errors
    
    @staticmethod
    def get_completions(code: str, line: int, character: int) -> list[dict]:
        """
        * **Arguments:**
        - `code` (`str`): The full text content of the active document.
        - `line` (`int`): The **0-indexed** line number where the cursor is currently positioned.
        - `character` (`int`): The **0-indexed** character offset on that line where the cursor is positioned.
        * **Return Value:**
        - A list of completion suggestion dictionaries.
        - Return `[]` if no suggestions are available.

        #### Completion Dictionary Format:
        Each dictionary in the returned list should have the following fields:

        | Field Name | Type | Description |
        | :--- | :--- | :--- |
        | `label` | `str` | The text proposal to display in the autocomplete dropdown and insert when selected. |
        | `kind` | `str` | The type of item. Must be one of: `'keyword'`, `'variable'`, `'function'`, `'text'`, `'constant'`. |
        | `detail` | `str` | *(Optional)* A short description shown next to the label (e.g. `wildcard operator`). |
        | `documentation`| `str` | *(Optional)* Detailed documentation shown in the expandable info card (can be markdown). |
        """
        import re

        lines = code.split('\n')
        if line < 0 or line >= len(lines):
            return []

        current_line = lines[line]
        if character < 0 or character > len(current_line):
            return []

        prefix_line = current_line[:character]

        # Extract the prefix matching word characters, or wildcard starts (? or $)
        match = re.search(r'(\?[:*<{,a-zA-Z0-9$]*|\$[&|#a-zA-Z0-9]*|[a-zA-Z_]\w*)$', prefix_line)
        prefix = match.group(1) if match else ""

        # Pyttern-specific wildcard patterns
        pyttern_wildcards = [
            {
                "label": "?",
                "kind": "keyword",
                "detail": "simple wildcard",
                "documentation": "Matches exactly 1 element (variable, expression, statement, etc.)."
            },
            {
                "label": "?name",
                "kind": "keyword",
                "detail": "named wildcard",
                "documentation": "Matches 1 element and binds it to `name`."
            },
            {
                "label": "?*",
                "kind": "keyword",
                "detail": "sequence wildcard",
                "documentation": "Matches zero or more elements."
            },
            {
                "label": "?:",
                "kind": "keyword",
                "detail": "body wildcard",
                "documentation": "Matches a node that has a block/body (e.g., `if`, `for`)."
            },
            {
                "label": "?:*",
                "kind": "keyword",
                "detail": "deep body wildcard",
                "documentation": "Matches a body at any level of indentation."
            },
            {
                "label": "?<...>",
                "kind": "keyword",
                "detail": "contains wildcard",
                "documentation": "Matches if the inner pattern is found anywhere within the node."
            },
            {
                "label": "?$(...)",
                "kind": "keyword",
                "detail": "sub-pattern call",
                "documentation": "Calls a sub-pattern defined elsewhere."
            },
            {
                "label": "?{n, m}",
                "kind": "keyword",
                "detail": "range wildcard",
                "documentation": "Matches between `n` and `m` elements in a sequence."
            },
        ]

        # Pyttern sub-pattern structures
        pyttern_subpatterns = [
            {
                "label": "$&Name(args)",
                "kind": "keyword",
                "detail": "AND sub-pattern",
                "documentation": "AND operator. All internal transformations must match."
            },
            {
                "label": "$|Name(args)",
                "kind": "keyword",
                "detail": "OR sub-pattern",
                "documentation": "OR operator. At least one internal transformation must match."
            },
            {
                "label": "$#",
                "kind": "keyword",
                "detail": "transformation label",
                "documentation": "A label for a specific transformation branch/version of the pattern logic."
            },
        ]

        # Pyttern API keywords
        pyttern_api = [
            {
                "label": "match_files",
                "kind": "function",
                "detail": "pyttern.match_files",
                "documentation": "Matches patterns against files.\n\n`match_files(pattern_file, code_file, lang='python')`"
            },
            {
                "label": "PytternMatcher",
                "kind": "class",
                "detail": "pyttern.PytternMatcher",
                "documentation": "Main matcher class for matching trees.\n\n`matcher = PytternMatcher(match_details=True)`"
            },
            {
                "label": "get_processor",
                "kind": "function",
                "detail": "pyttern.language_processors.get_processor",
                "documentation": "Retrieves the processor instance for a specific language (e.g. 'python', 'java')."
            },
            {
                "label": "parse_diagnostics",
                "kind": "function",
                "detail": "PythonProcessor.parse_diagnostics",
                "documentation": "Parses code and returns a list of diagnostic dictionaries for syntax errors."
            },
            {
                "label": "generate_tree_from_code",
                "kind": "function",
                "detail": "PythonProcessor.generate_tree_from_code",
                "documentation": "Generates a pruned parse tree from Python/Pyttern code."
            }
        ]

        # Python keywords
        python_keywords_list = [
            "False", "None", "True", "and", "as", "assert", "async", "await", "break", 
            "class", "continue", "def", "del", "elif", "else", "except", "finally", 
            "for", "from", "global", "if", "import", "in", "is", "lambda", "nonlocal", 
            "not", "or", "pass", "raise", "return", "try", "while", "with", "yield"
        ]

        python_keywords = [
            {
                "label": kw,
                "kind": "keyword" if kw not in ("True", "False", "None") else "constant",
                "detail": "python keyword" if kw not in ("True", "False", "None") else "python constant",
                "documentation": f"Standard Python {'constant' if kw in ('True', 'False', 'None') else 'keyword'}."
            }
            for kw in python_keywords_list
        ]

        # Extract local definitions
        local_functions = set()
        local_variables = set()

        for line_str in lines:
            # Check function definitions
            func_match = re.search(r'\bdef\s+([a-zA-Z_]\w*)', line_str)
            if func_match:
                local_functions.add(func_match.group(1))
                continue
            
            # Check class definitions
            class_match = re.search(r'\bclass\s+([a-zA-Z_]\w*)', line_str)
            if class_match:
                local_variables.add(class_match.group(1))
                continue

            # Check variable assignments
            var_matches = re.findall(r'\b([a-zA-Z_]\w*)\s*=(?!=)', line_str)
            for var in var_matches:
                if var not in python_keywords_list:
                    local_variables.add(var)

        local_suggestions = []
        for func in sorted(local_functions):
            local_suggestions.append({
                "label": func,
                "kind": "function",
                "detail": "local function",
                "documentation": f"Function `{func}` defined in the current document."
            })
        for var in sorted(local_variables):
            local_suggestions.append({
                "label": var,
                "kind": "variable",
                "detail": "local variable/class",
                "documentation": f"Variable/class `{var}` defined in the current document."
            })

        # Combine all candidates
        all_candidates = pyttern_wildcards + pyttern_subpatterns + pyttern_api + python_keywords + local_suggestions

        # Filter candidates based on prefix
        prefix_lower = prefix.lower()
        filtered = []
        for item in all_candidates:
            label = item["label"]
            if label.startswith('?') or label.startswith('$'):
                if label.startswith(prefix):
                    filtered.append(item)
            else:
                if prefix.startswith('?') or prefix.startswith('$'):
                    continue
                if label.lower().startswith(prefix_lower):
                    filtered.append(item)

        return filtered