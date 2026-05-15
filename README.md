# Pyttern

Pyttern is a powerful Python library and toolset designed for advanced pattern matching in source code. It extends Python's syntax to allow for expressive, structural queries that can match complex code patterns across multiple files and languages (Python and Java).

## Key Features

- **Structural Matching**: Match code based on its Abstract Syntax Tree (AST), not just text.
- **Rich Wildcards**: Expressive wildcards for matching single elements, sequences, bodies, and containment.
- **Logical Composition**: Combine patterns using AND, OR, and NOT operators via directory structures, or AND and OR via sub-patterns.
- **Variable Binding**: Bind matched code elements to names for reuse within the same pattern.
- **Interactive Debugging**: Web-based visualization to step through the matching process.
- **Multi-language Support**: Supports both Python and Java.

## Installation

Install Pyttern directly from GitHub:

```bash
pip install git+https://github.com/JulienLie/pyttern.git
```

Or for development:

```bash
git clone https://github.com/JulienLie/pyttern.git
cd pyttern
pip install -e .
```

## Syntax Reference

Pyttern extends standard Python syntax with special wildcard characters (`?`) and sub-pattern operators (`$`).

| Wildcard | Name | Description |
|----------|------|-------------|
| `?` | Simple | Matches exactly 1 element (variable, expression, statement, etc.). |
| `?name` | Named | Matches 1 element and binds it to `name`. |
| `?{n, m}` | Range | Matches between `n` and `m` elements in a sequence. |
| `?*` | Sequence | Matches zero or more elements. |
| `?:` | Body | Matches a node that has a block/body (e.g., `if`, `for`). |
| `?:*` | Deep Body | Matches a body at any level of indentation. |
| `?<...>` | Contains | Matches if the inner pattern is found anywhere within the node. |

> For detailed usage and snippets, see the [Wildcard Examples Guide](examples/wildcards.md).

## Usage

### Python API

```python
from pyttern import match_files, PytternMatcher

# Simple boolean match
is_match = match_files("pattern.pyt", "code.py", lang="python")

# Advanced usage with match details
matcher = PytternMatcher(match_details=True)
res, details = matcher.match("pattern.pyt", "code.py", lang="python")
```

### Command Line Interface

```bash
# Basic match
pyttern pattern.pyt code.py --lang python

# Match with detailed output and verbosity
pyttern pattern.pyt code.py --lang python --details -v

# Search using glob patterns
pyttern "patterns/*.pyt" "src/**/*.py"
```

### Web Visualization & Debugging

Pyttern includes a web-based visualization tool to help you understand and debug how your patterns match against code. It visualizes the underlying Pushdown Automaton (PDA) and its transitions.

```bash
pytternweb
```
*Accessible at `http://localhost:5000`.*

---

## Advanced Pattern Logic

### 1. Directory-based Logic
Organize your pattern files into specifically named directories to create complex boolean logic:
- **`and/`**: All patterns in this folder must match.
- **`or/`**: At least one pattern in this folder must match.
- **`not/`**: No patterns in this folder should match.

```text
my_pattern/
├── and/
│   ├── check_assign.pyt
│   └── or/
│       ├── match_if.pyt
│       └── match_while.pyt
```

### 2. Sub-patterns
Sub-patterns allow you to define reusable blocks of logic with arguments. They support powerful logical operators:
- `$&`: **AND** (all transformations must match).
- `$|`: **OR** (one transformation must match).

**Example Definition:**
```python
$|Incr(?x)
$# aug
?x += 1
$# add
?x = ?x + 1
```

> Learn more about modular patterns in the [Sub-patterns Guide](examples/subpatterns.md).

---

## Examples

### Matching assignments in a loop
Matches a variable being assigned and then having `.append()` called on it.

**Pattern:**
```python
for ? in ?:
    ?name = ?
    ?name.append(?)
```

### Deep body matching
Matches any function that contains a `return` statement at any level of nesting.

**Pattern:**
```python
def ?():
    ?:*
        return ?
```

> **View more:** Check out the [examples/](examples/) directory for comprehensive guides.

## Contributing
Detailed tests and edge cases are located in the `tests/` folder. Contributions and feedback are welcome!
