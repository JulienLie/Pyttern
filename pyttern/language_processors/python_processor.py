"""Python language processor for Pyttern."""
from __future__ import annotations

import io
from pathlib import Path
from typing import TYPE_CHECKING, Any

from antlr4 import CommonTokenStream, InputStream, ParserRuleContext
from antlr4.tree.Tree import Tree
from loguru import logger

from .base_processor_interface import BaseProcessor
from ..antlr.python import Python3Parser
from ..antlr.python.Python3Lexer import Python3Lexer
from ..pyttern_error_listener import Python3ErrorListener
from ..pytternfsm.PDA import PDA
from ..pytternfsm.python.python_to_pda import Python_to_PDA
from ..pytternfsm.python.tree_pruner import TreePruner

if TYPE_CHECKING:
    from ..Pyttern_listener import ConsolePytternListener
    from ..subpattern.SubPattern import BaseSubPattern, SubPatternCallContext
    from ..subpattern.catalog import SubPatternCatalog


class PythonProcessor(BaseProcessor):
    """
    Language processor implementation for Python source and Pyttern pattern files.
    """

    def generate_tree_from_code(self, code: str) -> ParserRuleContext | Tree:
        """
        Parses Python code or pattern string into a pruned parse tree.

        Args:
            code (str): Source code or pattern content.

        Returns:
            ParserRuleContext | Tree: The pruned ANTLR file input parse tree.
        """
        code = code.strip() + "\n"
        stream = InputStream(code)
        return self.generate_tree_from_stream(stream)

    def generate_tree_from_stream(self, stream: InputStream) -> ParserRuleContext | Tree:
        """
        Parses an ANTLR stream of Python code and returns a pruned parse tree.

        Args:
            stream (InputStream): The ANTLR input stream.

        Returns:
            ParserRuleContext | Tree: The pruned ANTLR parse tree.
        """
        logger.debug("Generating Python parse tree")
        lexer = Python3Lexer(stream)
        token_stream = CommonTokenStream(lexer)
        py_parser = Python3Parser(token_stream)

        input_err = io.StringIO()
        py_parser.removeErrorListeners()
        error_listener = Python3ErrorListener(input_err)
        py_parser.addErrorListener(error_listener)

        tree = py_parser.file_input()
        pruned_tree: ParserRuleContext | Tree = TreePruner().visit(tree)
        return pruned_tree

    def generate_tree_from_file(self, file: str | Path) -> ParserRuleContext | Tree:
        """
        Reads a Python or Pyttern file and returns its pruned parse tree.

        Args:
            file (str | Path): Path to the source file.

        Returns:
            ParserRuleContext | Tree: The pruned parse tree.
        """
        with open(file, "r", encoding="utf-8") as f:
            return self.generate_tree_from_code(f.read())

    def create_pda(
        self,
        pattern_tree: ParserRuleContext | Tree,
        catalog: SubPatternCatalog | None = None,
    ) -> dict[str, PDA]:
        """
        Compiles a Python pattern parse tree into a Pushdown Automaton.

        Args:
            pattern_tree (ParserRuleContext | Tree): The pattern parse tree.
            catalog (SubPatternCatalog | None): Optional subpattern catalog.

        Returns:
            dict[str, PDA]: A dictionary containing compiled PDA instances.
        """
        return Python_to_PDA(catalog=catalog).visit(pattern_tree)

    @staticmethod
    def _adapt_stmt_to_tfpdef(tree: ParserRuleContext | Tree) -> Python3Parser.TfpdefContext | None:
        desc: Any = tree
        while desc and not isinstance(desc, Python3Parser.Expr_stmtContext):
            children = list(desc.getChildren())
            if len(children) != 1:
                break
            desc = children[0]

        if desc and isinstance(desc, Python3Parser.Expr_stmtContext):
            target_name = desc.getChild(0)
            while target_name.getChildCount() == 1:
                target_name = target_name.getChild(0)

            if desc.getChildCount() > 1:
                annassign = desc.getChild(1)
                type_expr = annassign.getChild(0)
                tfpdef = Python3Parser.TfpdefContext(None, parent=None, invokingState=-1)
                tfpdef.children = [target_name, type_expr]
                target_name.parentCtx = tfpdef
                type_expr.parentCtx = tfpdef
                return tfpdef
            else:
                tfpdef = Python3Parser.TfpdefContext(None, parent=None, invokingState=-1)
                tfpdef.children = [target_name]
                target_name.parentCtx = tfpdef
                return tfpdef
        return None

    def prune_subpattern_tree(
        self,
        tree: ParserRuleContext | Tree,
        ctx: type | ParserRuleContext | Tree | None,
    ) -> ParserRuleContext | Tree | None:
        """
        Prunes a subpattern transformation parse tree down to the target context type.
        """
        if ctx is not None and isinstance(ctx, (Python3Parser.ParametersContext, Python3Parser.TfpdefContext)):
            adapted = self._adapt_stmt_to_tfpdef(tree)
            if adapted is not None:
                return adapted

        if ctx is not None and isinstance(
            ctx,
            (
                Python3Parser.StmtContext,
                Python3Parser.Compound_stmtContext,
                Python3Parser.Compound_subpattern_callContext,
            ),
        ):
            desc_stmt: Any = tree
            while desc_stmt and not isinstance(desc_stmt, Python3Parser.StmtContext):
                children = list(desc_stmt.getChildren())
                if len(children) != 1:
                    break
                desc_stmt = children[0]
            if desc_stmt and isinstance(desc_stmt, Python3Parser.StmtContext):
                return desc_stmt
            return tree

        target_cls: type | None = ctx if isinstance(ctx, type) else (ctx.__class__ if ctx is not None else None)
        if target_cls is None:
            return None
        desc: Any = tree
        while desc and not isinstance(desc, target_cls):
            children = list(desc.getChildren())
            if len(children) != 1:
                return None
            desc = children[0]
        return desc

    def compile_subpattern(
        self,
        subpattern: BaseSubPattern,
        context: SubPatternCallContext | ParserRuleContext | Tree | None,
        catalog: SubPatternCatalog | None = None,
    ) -> dict[str, PDA]:
        """
        Compiles a subpattern definition into a Pushdown Automaton given a call context.

        Args:
            subpattern: The subpattern definition to compile.
            context: The call context (SubPatternCallContext or AST node).
            catalog: Optional subpattern catalog to resolve nested subpatterns.

        Returns:
            dict[str, PDA]: A dictionary mapping transformation names to compiled PDA instances.
        """
        from ..subpattern.SubPattern import (
            SubPatternCallContext,
            clone_tree,
            extract_body_stmts,
            is_body_var_stmt,
            isolate_internal_wildcards,
            rename_vars_in_tree,
            substitute_body,
        )

        body = getattr(context, "body", None) if isinstance(context, SubPatternCallContext) else None
        args = getattr(context, "args", None) if isinstance(context, SubPatternCallContext) else None
        call_id = getattr(context, "call_id", None) if isinstance(context, SubPatternCallContext) else None
        ast_ctx = context.ast_ctx if isinstance(context, SubPatternCallContext) else context

        if subpattern.body_var is not None and body is None:
            raise ValueError(
                f"Subpattern '{subpattern.name}' requires a body wildcard '(?{subpattern.body_var})', but no body was provided at call site."
            )
        if subpattern.body_var is None and body is not None:
            raise ValueError(f"Subpattern '{subpattern.name}' does not accept a body, but a body was provided.")

        body_stmts: list[Any] = []
        param_to_arg_map: dict[str, str] = {}
        if subpattern.body_var is not None and body is not None:
            body_stmts = extract_body_stmts(body)
            if args is not None:
                for caller_arg, param in zip(args, subpattern.args_order):
                    if caller_arg != param:
                        param_to_arg_map[param] = caller_arg

        compiled: dict[str, PDA] = {}
        for name, trans in subpattern.transformations.items():
            cloned_trans = clone_tree(trans)
            if subpattern.body_var is not None and body_stmts:
                isolate_internal_wildcards(
                    cloned_trans, set(subpattern.args_order), subpattern.body_var, call_id if call_id is not None else 0
                )
                if param_to_arg_map:
                    rename_vars_in_tree(cloned_trans, param_to_arg_map)
                if is_body_var_stmt(cloned_trans, subpattern.body_var) and len(body_stmts) == 1:
                    cloned_trans = clone_tree(body_stmts[0])
                else:
                    substitute_body(cloned_trans, body_stmts, subpattern.body_var)

            pruned_trans = self.prune_subpattern_tree(cloned_trans, ast_ctx)
            if pruned_trans is None:
                ctx_name = ast_ctx.__class__.__name__ if ast_ctx is not None else "None"
                raise ValueError(f"Cannot prune tree {cloned_trans.getText()} to {ctx_name}")
            pda_result = Python_to_PDA(catalog=catalog).visit(pruned_trans)
            if isinstance(pda_result, dict):
                pda = pda_result.get("__main__", next(iter(pda_result.values())))
            else:
                pda = pda_result
            compiled[name] = pda

        return compiled

    def create_listener(self) -> ConsolePytternListener:
        """
        Creates a console listener for Python pattern matching.

        Returns:
            ConsolePytternListener: The listener instance.
        """
        from ..Pyttern_listener import ConsolePytternListener

        return ConsolePytternListener()

    def get_language_extensions(self) -> list[str]:
        """
        Returns supported file extensions for Python and Pyttern files.

        Returns:
            list[str]: Supported extensions ['py', 'pyt', 'pyh'].
        """
        return ["py", "pyt", "pyh"]

    @staticmethod
    def parse_diagnostics(code: str) -> list[dict[str, Any]]:
        """
        Parses code and returns a list of diagnostic dictionaries for syntax errors.

        Args:
            code (str): Source code or pattern text to inspect.

        Returns:
            list[dict]: List of diagnostic error objects with line, column, and message.
        """
        from ..editor_support import run_diagnostics

        return run_diagnostics(code, entry_rule="file_input")

    @staticmethod
    def get_completions(code: str, line: int, character: int) -> list[dict[str, Any]]:
        """
        Returns auto-completion suggestion dictionaries at the given document position.

        Args:
            code (str): The document text.
            line (int): 0-indexed line number.
            character (int): 0-indexed character offset on that line.

        Returns:
            list[dict]: Completion items matching the prefix.
        """
        from ..editor_support import get_editor_completions

        return get_editor_completions(code, line, character, mode="pattern")


__all__ = ["PythonProcessor"]
