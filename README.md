# Pyttern
Pyttern is a python library to create pattern files for Python code.

## Installation
To install the pyttern library, you can use pip:

```bash
pip install git+https://github.com/JulienLie/pyttern.git
```

You can also clone the repository and install it manually:
- clone the repository
- go to the pyttern directory
- run the following command:
```bash
pip install -e .
```

## Syntax

We extended the python syntax to create pattern files. Our new syntax includes the following elements:

| Wildcard | Description                                                               |
|----------|---------------------------------------------------------------------------|
| ?        | Match 1 element                                                           |
| ?{n, m}  | Match between ``n`` and ``m`` elements                                    |
| ?*       | Match 0 or more elements                                                  |
| ?name    | Match 1 element and bind it to ``name``                                   |
| ?:       | Match 1 element with a body                                               |
| ?:*      | Match the body of the wildcard in any indentation                         |
| ?<...>   | Match if the inside of the wildcard is contained inside the matching node |



## Usage

```python
from pyttern import PytternMatcher

code = "code_file.py"
pattern = "pattern_file.py"

# Instantiate the matcher with desired options
matcher = PytternMatcher(match_details=False)

# Perform the match
match = matcher.match_files(pattern, code, lang="python")

if match:
    print("We found a match")
else:
    print("No match")
```
The `PytternMatcher` class is the main entry point for matching. The `match_files` method takes 3 arguments:
1. `pattern_file: string` The path to the file describing the pattern
2. `code_file: string` The path to the python code file
3. `lang: string` The language of the code file (e.g., "python" or "java")

The `PytternMatcher` constructor takes optional arguments:
1. `match_details: boolean` (optional) If `match_details` is set to `True`, the `match_files` method returns a tuple `(result, details)`,
where `result` is a boolean value indicating whether the code matches the pattern.
If `result` is `True`, `details` contains the match details. If `result` is `False`, `details` may contain information about the mismatch.
2. `stop_at_first: boolean` (optional) If `stop_at_first` is `True`, the matching process will stop as soon as the first match is found.

## Examples
### Wildcard: ``?``
The `?` wildcard matches any single element in the code. For example, the pattern `? = 0` will match any assignment statement where the right-hand side is 0.

#### Pyttern
```python
def ?():
    ? = 0
    return ?
```

#### Code
```python
def foo():
    x = 0
    return "bar"
```

### Wildcard: ``?{n, m}``
The `?{n, m}` option allows you to specify the number of elements to match. For example, the pattern `?{1, 2}` will match between 1 and 2 elements.

#### Pyttern
```python
def foo():
    lst = [?{3, 5}]
```

#### Code
```python
def foo():
    lst = [1, 2, 3, 4]
```

### Wildcard: ``?*``
The `?*` wildcard matches zero or more elements in the code. For example, the pattern `?*` will match any sequence of elements.

#### Pyttern
```python
def foo(?*):
    ?*
    a = 0
    ?*
    return a
```

#### Code
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

### Wildcard: ``?name``
The `?name` wildcard matches a single element in the code and binds it to the name `name`. For example, the pattern `?name = 0` will match any assignment statement where the right-hand side is 0 and bind the left-hand side to the name `name`.

#### Pyttern
```python
def foo(?name):
    ?name.append(0)
    return ?name
```

#### Code
```python
def foo(lst):
    lst.append(0)
    return lst
```

### Wildcard: ``?:``
The `?:` wildcard matches a single element in the code that has a body.

#### Pyttern
```python
def foo():
    ?:
        x = 0
    return x
```

#### Code
```python
def foo():
    if True:
        x = 0
    return x
```

### Wildcard: ``?:*``
The `?:*` wildcard matches the body of the wildcard in any indentation.

#### Pyttern
```python
def foo():
    ?:*
        x = 0
    return x
```

#### Code
```python
def foo():
    if True:
        if True:
            x = 0
    return x
```

```python
def foo():
    x = 0
    return x
```

### Wildcard: ``?<...>``
The `?<...>` wildcard matches if the inside of the wildcard is contained inside the matching node.

#### Pyttern
```python
def foo(x):
    y = ?<x>
    return y
```

#### Code
```python
def foo(x):
    y = 2*x + 1
    return y
```

### Combining Wildcards
You can combine wildcards to create more complex patterns.

TODO: Add more examples
#### Pyttern
```python
def ?(?*):
   ?acc = 0
   ?:*
        for ? in ?:
            ?:*
                ?acc += ?
    return ?acc
```

#### Code
```python
def sum(lst):
    acc = 0
    for i in lst:
        acc += i
    return acc
```

## File structure
To implement a system that allows to create logic with different patterns, we implemented a specific file structure.
The file structure is composed as follows:
```
pattern_name
└── and
    ├── pattern1.pyt
    ├── pattern2.pyt
    └── or
        ├── not
        │   └── pattern3.pyt
        └── pattern4.pyt
```
The resulting is a boolean logic tree. The `and` folder contains patterns that must all match. 
The `or` folder contains patterns that can match. 
The `not` folder contains patterns that must not match.
This allows the creation of more complex patterns with different files.


## Sub-patterns
The sub-pattern paradigm allows you to define complex patterns in a single file by breaking them down into named blocks. This is more powerful because it allows to use logic operators (AND, OR). Compared to the compound pattern paradigm, the sub-pattern paradigm allows to share variables across different blocks, which is not possible in the compound pattern paradigm.

### The *OR* operator
To define an *OR* operator in a sub-pattern, you can use the `$|` operator. This will mean that the block can be satisfied by either of the two blocks defined after the `$|` operator. 

```python
$|Incr(?x)

$# augAssign
?x += 1

$# add_v
?x = ?x + 1

$# add_x
?x = 1 + ?x
```

### The *AND* operator
To define an *AND* operator in a sub-pattern, you can use the `$&` operator. This will mean that the block must be satisfied by all of the blocks defined after the `$&` operator. 

```python
$&Assign(?var1, ?var2)

$# assign_var_1
?var1 = ?

$# assign_var_2
?var2 = ?
```

## Visualization
To visualize the matching algorithm, we implemented a web visualization tool. If you installed Pyttern, you can run it
using the following command:
```bash
pytternweb
```
You can then import your pyttern on the left part of the site and your code on the right side. You can control the
matching algorithm using the buttons on the bottom of the site.

## More examples
You can find more examples of patterns using all the features of Pyttern in the `examples` folder of the repository.