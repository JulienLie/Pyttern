# Sub-patterns

Sub-patterns allow you to define reusable, modular, and logically complex pattern blocks. They are particularly useful for defining alternative ways to express the same logic or for sharing variables across different parts of a pattern.

## Syntax

Sub-patterns are defined using the `$` prefix and include logical operators:

- `$&Name(args)`: **AND** operator. All internal transformations must match.
- `$|Name(args)`: **OR** operator. At least one internal transformation must match.

## Anatomy of a Sub-pattern File

A sub-pattern file consists of two main parts: the **Header Declaration** and one or more **Transformation Blocks**.

### 1. The Header Declaration
This defines the sub-pattern's entry point, its logic type, and its parameters.

```python
$|SubPatternName(?arg1, ?arg2)
```
- **`$`**: Indicates the start of a sub-pattern definition.
- **`|` / `&`**: The logical operator (OR or AND).
- **`SubPatternName`**: The name you will use to call this sub-pattern.
- **`(?arg1, ...)`**: A comma-separated list of named wildcards. These act as placeholders that are replaced by the values passed during the call.

### 2. Transformation Blocks
Each transformation represents one "version" or "branch" of the pattern logic.

```python
$# TransformationName
code_pattern_here
```
- **`$#`**: Indicates the start of a transformation.
- **`TransformationName`**: A label for this specific version. This name appears in the **Web Visualizer** and debug logs, making it easier to see which specific branch of a sub-pattern matched.
- **`code_pattern_here`**: A standard Pyttern pattern. It can use the parameters defined in the header (e.g., `?arg1`) as well as any other Pyttern wildcards.

## Defining a Sub-pattern

Sub-patterns are typically defined in `.myt` files. A definition consists of a header and one or more transformations marked with `$#`. One file can contain multiple sub-pattern definitions.

### Example: Increment Logic
We want to match any way a variable `?x` can be incremented by 1.

**Definition (`incr.myt`):**
```python
$|Incr(?x)  # This is an OR sub-pattern named 'Incr' taking one argument '?x'

$# augAssign
?x += 1

$# add_v
?x = ?x + 1

$# add_x
?x = 1 + ?x
```

## Using a Sub-pattern

To use a sub-pattern in another pattern file, use the `?$Name(args)` syntax.

**Pattern (`usage.pyt`):**
```python
def foo(?i):
    # Match any increment of ?i defined in the Incr sub-pattern
    ?$Incr(?i)
    return ?i
```

## Advanced Logic: The AND Operator

The AND operator (`$&`) is useful when you want to ensure multiple conditions are met, potentially in any order or interspersed with other code.

**Definition (`checks.myt`):**
```python
$&ValidateList(?lst, ?val)

$# contains_append
?lst.append(?val)

$# check_not_empty
?<len(?lst) > 0>
```

**Usage:**
```python
def process(?my_list):
    ?v = 10
    # Matches if both .append(?v) and len(?my_list) > 0 occur within the function
    ?$ValidateList(?my_list, ?v)
```

## Variable Sharing

One of the most powerful features of sub-patterns is the ability to share variables between the calling context and the sub-pattern, and across transformations.

```python
$|FindSafe(?obj)

$# check_none
if ?obj is not None:
    ?:*
```

When you call `?$FindSafe(my_var)`, Pyttern binds `?obj` to `my_var` and ensures the structural constraints are met.

## Implementation Details

Sub-patterns are compiled into the same Pushdown Automaton (PDA) as the main pattern.
- **OR sub-patterns** create branching paths in the PDA.
- **AND sub-patterns** create a bitmask-based state machine that allows transitions to be satisfied in any order.
