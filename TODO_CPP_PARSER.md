# Architecture Roadmap: Total Removal of Python ANTLR in Pyttern

This roadmap defines the plan to completely eliminate the `antlr4-python3-runtime` library from Pyttern. All lexing, parsing, error listening, and tree pruning will run entirely in C++ (`_pyttern_cpp`), with Python operating strictly on a lightweight native C++ `Node` tree.

---

## Architecture Overview

```
                      ┌───────────────────────────────────────┐
                      │             Python Code               │
                      └──────────────────┬────────────────────┘
                                         │
                                         ▼
                      ┌───────────────────────────────────────┐
                      │           C++ Core Engine             │
                      │            (_pyttern_cpp)             │
                      ├───────────────────────────────────────┤
                      │ 1. Python3Lexer (C++)                 │
                      │ 2. Python3Parser (C++)                │
                      │ 3. TreeBuilder (prunes & simplifies)  │
                      └──────────────────┬────────────────────┘
                                         │
                                         ▼
                      ┌───────────────────────────────────────┐
                      │    Lightweight Node Tree (Node.h)     │
                      │    (Exposed to Python via pybind11)   │
                      └──────────────────┬────────────────────┘
                                         │
           ┌─────────────────────────────┼─────────────────────────────┐
           ▼                             ▼                             ▼
┌─────────────────────┐       ┌─────────────────────┐       ┌─────────────────────┐
│     Matcher.py      │       │   Python_to_PDA     │       │ SubPattern_Visitor  │
│  (FSM execution on  │       │ (PDA compilation of │       │ (Visitor over C++   │
│   code Node tree)   │       │   pattern tree)     │       │     Node tree)      │
└─────────────────────┘       └─────────────────────┘       └─────────────────────┘
```

---

## Phase 1: Package Dependencies & Grammar Scripts

- [x] **Remove `antlr4-python3-runtime` from `pyproject.toml`**
  - Remove `antlr4-python3-runtime = "4.13.2"` from `[tool.poetry.dependencies]`.
  - Keep `pybind11` in `[tool.poetry.group.dev.dependencies]`.
  - Run `poetry lock --no-update` to clean the lockfile.

- [x] **Update `pyttern/antlr/generate.sh`**
  - Update `generate.sh` to generate **only** the C++ parser:
    ```bash
    java -jar "$ANTLR_JAR" -Dlanguage=Cpp -visitor *.g4
    ```
  - Remove all leftover `.py` files in `pyttern/antlr/python/` (`Python3Parser.py`, `Python3Lexer.py`, etc.).

---

## Phase 2: C++ Core Engine & Entry Points (`_pyttern_cpp`)

- [ ] **Fix `Node` Class Binding in `pyttern/parsetree/bindings.cpp`**
  - Remove `.def_property_readonly("__class__", ...)` (CPython forbids properties assigned to `__class__`).
  - Expose rule type proxies or dynamic Python types so `node.__class__.__name__` or `isinstance(node, ...)` matches rule names:
    ```cpp
    static std::unordered_map<std::string, py::object> type_cache;

    static py::object get_or_create_node_type(py::module_ &m, const std::string &rule_name) {
        auto it = type_cache.find(rule_name);
        if (it != type_cache.end()) return it->second;

        py::object builtins = py::module_::import("builtins");
        py::object base = m.attr("Node");
        py::object new_type = builtins.attr("type")(rule_name, py::make_tuple(base), py::dict());
        type_cache[rule_name] = new_type;
        return new_type;
    }

    static void fix_node_types(py::module_ &m, const std::shared_ptr<Node> &node) {
        if (!node) return;
        py::object py_node = py::cast(node);
        if (!node->rule_name.empty()) {
            py::object dyn_type = get_or_create_node_type(m, node->rule_name);
            Py_SET_TYPE(py_node.ptr(), reinterpret_cast<PyTypeObject*>(dyn_type.ptr()));
        }
        for (const auto &child : node->children) {
            fix_node_types(m, child);
        }
    }
    ```

- [ ] **Implement `TreeBuilder.cpp` & `TreeBuilder.h`**
  - **Rule mapping:** Map `ctx->getRuleIndex()` to PascalCase context names (`"file_input"` $\to$ `"File_inputContext"`, `"stmt"` $\to$ `"StmtContext"`).
  - **Punctuation filtering:** Discard `(`, `)`, `:`, `,`, `.` and indentation tokens (`NEWLINE`, `INDENT`, `DEDENT`).
  - **EOF handling:** Convert `EOF` to a terminal `Node` with text `"<EOF>"`.
  - **Single-child pruning:** Collapse intermediate rules for `atom_expr`, `expr_stmt`, and `tfpdef` until reaching a terminal or `NameContext`/`ExprContext`/`Expr_wildcardContext`.
  - **Block handling:** In `visitBlock`, append a synthetic `BlockEndContext` node (unless the block contains only `Double_wildcardContext`).

- [ ] **Expose All Required Parsing Entry Points in `bindings.cpp`**
  - `parse_code(code: str) -> Node`: Parses general Python code (`file_input` rule) and returns the pruned root `Node`.
  - `parse_subpattern(code: str) -> Node`: Parses subpattern definitions (`subpattern_input` rule).
  - `parse_diagnostics(code: str) -> list[dict]`: Runs lexer + parser with a C++ error listener and returns syntax errors directly as `[{"message": ..., "line": ..., "character": ...}]`.

---

## Phase 3: Pure-Python AST Visitor (`NodeVisitor`)

Because `Python3ParserVisitor` from ANTLR Python is removed, introduce a clean Python visitor base class that visits `Node` trees:

- [ ] **Create `pyttern/parsetree/node_visitor.py`**
  ```python
  class NodeVisitor:
      """Lightweight visitor for C++ Node parse trees (replaces ANTLR Python visitors)."""
      
      def visit(self, node):
          if node is None:
              return None
          rule_name = getattr(node, "rule_name", "")
          # Strip 'Context' suffix: 'StmtContext' -> 'Stmt'
          method_name = f"visit{rule_name.removesuffix('Context')}"
          visitor = getattr(self, method_name, self.visitChildren)
          return visitor(node)

      def visitChildren(self, node):
          results = []
          for child in node.children:
              res = self.visit(child)
              if res is not None:
                  results.append(res)
          return results
  ```

- [ ] **Add `accept()` Support to `Node`**
  In [`bindings.cpp`](file:///home/julien/Documents/phd/Pyttern/pyttern/parsetree/bindings.cpp):
  ```cpp
  .def("accept", [](const std::shared_ptr<Node> &self, py::object visitor) {
      return visitor.attr("visit")(self);
  })
  ```

---

## Phase 4: Adapt Language Processors

- [ ] **Refactor `pyttern/language_processors/python_processor.py`**
  - Remove all imports from `antlr4` (`CommonTokenStream`, `InputStream`, `Python3Lexer`, `Python3Parser`, `TreePruner`).
  - Update methods:
    ```python
    from pyttern import _pyttern_cpp

    class PythonProcessor(BaseProcessor):
        def generate_tree_from_code(self, code: str):
            return _pyttern_cpp.parse_code(code)

        def generate_tree_from_stream(self, stream):
            return self.generate_tree_from_code(stream.strdata if hasattr(stream, 'strdata') else str(stream))

        def generate_tree_from_file(self, file_path: str):
            with open(file_path, 'r', encoding='utf-8') as f:
                return _pyttern_cpp.parse_code(f.read())

        @staticmethod
        def parse_diagnostics(code: str) -> list[dict]:
            return _pyttern_cpp.parse_diagnostics(code)
    ```

- [ ] **Update `pyttern/language_processors/base_processor_interface.py`**
  - Remove `from antlr4 import FileStream, InputStream`.

---

## Phase 5: Refactor Pattern & Subpattern Compilation

- [ ] **Refactor `Python_to_PDA` in `pyttern/pytternfsm/python/python_to_pda.py`**
  - Replace `from ...antlr.python import Python3ParserVisitor, Python3Parser` with `NodeVisitor`.
  - Update `lookahead` / `lookbehind` checks: replace `isinstance(node, Python3Parser.XContext)` with `node.rule_name == "XContext"`.
  - Remove dependencies on ANTLR's Python `RuleContext` and `TerminalNode`.

- [ ] **Refactor `pyttern/subpattern/subpattern_parser.py`**
  - Remove `antlr4` imports.
  - Parse subpatterns using `_pyttern_cpp.parse_subpattern(code)`.

- [ ] **Refactor `pyttern/subpattern/subpattern_visitor.py`**
  - Replace `Python3ParserVisitor` base class with `NodeVisitor`.
  - Discard `from antlr4.tree.Tree import TerminalNodeImpl`.

- [ ] **Refactor `pyttern/subpattern/SubPattern.py`**
  - Remove `from antlr4 import RuleContext` and `from antlr4.tree.Tree import Tree, ParseTree`.

---

## Phase 6: Refactor Matcher and Visualizers

- [ ] **Refactor `pyttern/simulator/Matcher.py`**
  - Remove `from antlr4.ParserRuleContext import ParserRuleContext` and `from antlr4.tree.Tree import TerminalNode, Tree`.
  - Update `_match_node`:
    ```python
    @staticmethod
    def _match_node(input_node, A: NodeTransition):
        name = A.name
        down, up = A.down, A.up
        if name == "":
            return True
        if input_node.is_terminal:
            return input_node.text == name
        return input_node.rule_name == name and down <= input_node.getChildCount() <= up
    ```
  - Update `_unwrap` and `_match_tree` to use `node.rule_name`, `node.is_terminal`, `node.text`, and `node.children`.

- [ ] **Refactor `pyttern/simulator/subpatterns.py` and `configuration.py`**
  - Replace `isinstance(val, Python3Parser.NameContext)` with `getattr(val, "rule_name", None) == "NameContext"`.
  - Remove `from antlr4 import ParserRuleContext`.

- [ ] **Refactor `pyttern/visualizer/pt_visualizer.py`**
  - Remove `from antlr4.tree.Tree import TerminalNode, ErrorNode`.
  - Use `node.is_terminal` and `node.rule_name`.

---

## Phase 7: Verification and Benchmarks

- [ ] **Compile C++ Extension**
  ```bash
  poetry run python build.py
  ```

- [ ] **Verify Zero ANTLR Python Runtime Dependencies**
  Run an import audit to ensure `antlr4` is never loaded:
  ```bash
  poetry run python -c "import pyttern, sys; assert 'antlr4' not in sys.modules, 'antlr4 is still being imported!'"
  ```

- [ ] **Run Full Test Suite**
  ```bash
  poetry run pytest
  ```
  Target execution time: **< 5 seconds** (down from 66 seconds).
