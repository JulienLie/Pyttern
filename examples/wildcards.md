# Pyttern Examples

This document provides detailed examples of Pyttern's syntax and features. Each section shows a **Pyttern** pattern file and the corresponding **Code** it is designed to match.

## Table of Contents
1. [Simple Wildcard (`?`)](#simple-wildcard-)
2. [Range Wildcard (`?{n, m}`)](#range-wildcard-nm)
3. [Sequence Wildcard (`?*`)](#sequence-wildcard-)
4. [Named Wildcard (`?name`)](#named-wildcard-name)
5. [Body Wildcard (`?:`)](#body-wildcard-)
6. [Deep Body Wildcard (`?:*`)](#deep-body-wildcard-)
7. [Containment Wildcard (`?<...>`)](#containment-wildcard-)
8. [Combining Wildcards](#combining-wildcards)
9. [Directory-Based Logic](#directory-based-logic)

---

### Simple Wildcard: `?`
The `?` wildcard matches exactly one element in the AST (a variable, a constant, a statement, etc.).

**Pyttern:**
```python
def ?():
    ? = 0
    return ?
```

**Matched Code:**
```python
def foo():
    x = 0
    return "bar"
```

---

### Range Wildcard: `?{n, m}`
The `?{n, m}` wildcard matches between `n` and `m` elements in a list context (like function arguments or list literals).

**Pyttern:**
```python
def foo():
    lst = [?{3, 5}]
```

**Matched Code:**
```python
def foo():
    lst = [1, 2, 3, 4] # Matches 4 elements, which is between 3 and 5
```

---

### Sequence Wildcard: `?*`
The `?*` wildcard matches zero or more elements. It is often used to skip irrelevant code or match arbitrary argument lists.

**Pyttern:**
```python
def foo(?*):
    ?*
    a = 0
    ?*
    return a
```

**Matched Code:**
```python
def foo(x, y, z):
    x = 1
    y = 2
    z = 3
    a = 0
    if a == 0:
        return a
    return a
```

---

### Named Wildcard: `?name`
The `?name` wildcard matches a single element and binds it to a name. If you use the same name elsewhere in the pattern, it must match the exact same element.

**Pyttern:**
```python
def foo(?name):
    ?name.append(0)
    return ?name
```

**Matched Code:**
```python
def foo(lst):
    lst.append(0)
    return lst
```

---

### Body Wildcard: `?:`
The `?:` wildcard matches a single statement or block that contains a body (like an `if` statement, `for` loop, or `try` block).

**Pyttern:**
```python
def foo():
    ?:
        x = 0
    return x
```

**Matched Code:**
```python
def foo():
    if True:
        x = 0
    return x
```

---

### Deep Body Wildcard: `?:*`
The `?:*` wildcard matches the body of the wildcard at any depth of indentation.

**Pyttern:**
```python
def foo():
    ?:*
        x = 0
    return x
```

**Matched Code (Deeply Nested):**
```python
def foo():
    if condition_a:
        while condition_b:
            if condition_c:
                x = 0
    return x
```

**Matched Code (Directly):**
```python
def foo():
    x = 0
    return x
```

---

### Containment Wildcard: `?<...>`
The `?<...>` wildcard matches if the pattern inside the brackets is found anywhere within the current node's subtree.

**Pyttern:**
```python
def foo(x):
    y = ?<x>
    return y
```

**Matched Code:**
```python
def foo(x):
    y = (2 * x) + (5 / z) # Matches because 'x' is contained in the expression
    return y
```

---

### Combining Wildcards
You can combine multiple wildcards to create powerful structural templates.

**Pyttern:**
```python
def ?(?*):
   ?acc = 0
   ?:*
        for ? in ?:
            ?:*
                ?acc += ?
    return ?acc
```

**Matched Code:**
```python
def sum_nested(matrix):
    total = 0
    for row in matrix:
        for val in row:
            total += val
    return total
```

---

### Directory-Based Logic
Patterns can be composed using the file system. When passing a directory to the matcher, it interprets folder names as logical operators.

**Structure:**
```text
check_logic/
└── and/
    ├── has_return.pyt
    └── or/
        ├── uses_logging.pyt
        └── uses_print.pyt
```

- **`and` folder**: All patterns inside must match.
- **`or` folder**: At least one pattern inside must match.
- **`not` folder**: No patterns inside should match.

---

## More Examples
You can find hundreds of integration and unit tests in the [tests/tests_files](../tests/tests_files) directory of this repository, covering edge cases and complex scenarios.
