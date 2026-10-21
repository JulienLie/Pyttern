from __future__ import annotations

import json
import os
from pathlib import Path
from typing import Optional, Union

from loguru import logger

from pyttern.language_processors import Languages, get_processor
from pyttern.language_processors.base_processor_interface import BaseProcessor
from pyttern.language_processors.java_processor import JavaProcessor
from pyttern.language_processors.python_processor import PythonProcessor
from pyttern.pattern.compiled import CompiledPattern, LeafPattern
from pyttern.pattern.composite import CompositePattern, AndPattern, OrPattern, NotPattern
from pyttern.subpattern.catalog import SubPatternCatalog


class PatternCompiler:
    """
    Compiles pattern definitions from files, directories, code strings, or structured data
    into immutable CompiledPattern hierarchies using an active language and SubPatternCatalog.
    """

    def __init__(
        self,
        language: Union[Languages, str, BaseProcessor],
        catalog: Optional[SubPatternCatalog] = None,
    ) -> None:
        self.language: Optional[Languages]
        if isinstance(language, BaseProcessor):
            self.processor = language
            if isinstance(language, PythonProcessor):
                self.language = Languages.PYTHON
            elif isinstance(language, JavaProcessor):
                self.language = Languages.JAVA
            else:
                self.language = None
        elif isinstance(language, Languages):
            self.language = language
            self.processor = get_processor(language)
        elif isinstance(language, str):
            try:
                self.language = Languages(language.lower())
            except ValueError:
                try:
                    self.language = Languages[language.upper()]
                except KeyError:
                    raise ValueError(f"Unsupported language: {language}")
            self.processor = get_processor(self.language)
        else:
            raise ValueError(f"Unsupported language: {language}")

        self.catalog = catalog if catalog is not None else SubPatternCatalog()

    def compile_code(self, code: str, name: Optional[str] = None) -> LeafPattern:
        """
        Compiles a raw pattern string into an executable LeafPattern wrapping a Pushdown Automaton.
        """
        if not isinstance(code, str):
            raise TypeError(f"Pattern code must be a string, got {type(code).__name__}")

        pattern_name = name if name is not None else "unnamed"
        logger.debug(f"Compiling pattern code for '{pattern_name}'")
        tree = self.processor.generate_tree_from_code(code)
        pda = self.processor.create_pda(tree, catalog=self.catalog)
        return LeafPattern(
            name=pattern_name,
            pda=pda,
            processor=self.processor,
            catalog=self.catalog,
        )

    def compile_file(self, file_path: Union[str, Path], name: Optional[str] = None) -> LeafPattern:
        """
        Loads and compiles a pattern file into an executable LeafPattern.
        """
        path = Path(file_path)
        if not path.is_file():
            raise FileNotFoundError(f"Pattern file not found: {file_path}")

        pattern_name = name if name is not None else path.name
        logger.debug(f"Compiling pattern file '{path}' as '{pattern_name}'")
        tree = self.processor.generate_tree_from_file(str(path))
        pda = self.processor.create_pda(tree, catalog=self.catalog)
        return LeafPattern(
            name=pattern_name,
            pda=pda,
            processor=self.processor,
            catalog=self.catalog,
        )

    def compile_dir(
        self,
        path: Union[str, Path],
        op: str = "and",
        name: Optional[str] = None,
    ) -> CompositePattern:
        """
        Recursively compiles a directory tree into an immutable CompositePattern hierarchy.
        Treats subdirectories named 'and', 'or', and 'not' as logical combinators, defaulting
        other subdirectories to 'and' composition. Pattern files are filtered by processor
        extensions and compiled into LeafPattern instances.
        """
        dir_path = Path(path)
        if not dir_path.exists():
            raise FileNotFoundError(f"Pattern directory not found: {dir_path}")
        if not dir_path.is_dir():
            raise NotADirectoryError(f"Path is not a directory: {dir_path}")

        op_norm = op.lower()
        if op_norm not in ("and", "or", "not"):
            raise ValueError(f"Unsupported logical operator: {op}")

        pattern_name = name if name is not None else op_norm
        supported_exts = {e.lower().lstrip(".") for e in self.processor.get_language_extensions()}
        children: list[CompiledPattern] = []

        logger.debug(f"Compiling pattern directory '{dir_path}' with op='{op_norm}' as '{pattern_name}'")

        for item in sorted(os.listdir(dir_path)):
            if item.startswith(".") or item == "__pycache__":
                continue
            item_path = dir_path / item
            if item_path.is_dir():
                item_lower = item.lower()
                if item_lower in ("and", "or", "not"):
                    sub_op = item_lower
                else:
                    sub_op = "and"
                child_pattern = self.compile_dir(item_path, op=sub_op)
                children.append(child_pattern)
            elif item_path.is_file():
                ext = item_path.suffix.lstrip(".").lower()
                if ext in supported_exts:
                    leaf = self.compile_file(item_path)
                    children.append(leaf)

        if op_norm == "and":
            return AndPattern(children=children, name=pattern_name, processor=self.processor)
        elif op_norm == "or":
            return OrPattern(children=children, name=pattern_name, processor=self.processor)
        else:
            return NotPattern(children=children, name=pattern_name, processor=self.processor)

    def compile_json(
        self,
        pattern_json: Union[dict, str],
        lang: Optional[Union[str, Languages, BaseProcessor]] = None,
    ) -> CompiledPattern:
        """
        Recursively compiles a nested JSON pattern definition into a CompiledPattern hierarchy.
        """
        if isinstance(pattern_json, str):
            try:
                pattern_json = json.loads(pattern_json)
            except Exception as e:
                raise ValueError(f"Invalid JSON string: {e}") from e

        if not isinstance(pattern_json, dict):
            raise TypeError(
                f"Pattern JSON must be a dict or valid JSON string, got {type(pattern_json).__name__}"
            )

        if lang is not None:
            compiler = PatternCompiler(language=lang, catalog=self.catalog)
            return compiler.compile_json(pattern_json)

        # Composite node
        if "children" in pattern_json:
            raw_name = pattern_json.get("name")
            if raw_name is None:
                raw_name = "and"
            op = str(raw_name).lower()

            children = [self.compile_json(child) for child in pattern_json["children"]]

            if op == "or":
                pattern_name = pattern_json.get("name") or "or"
                return OrPattern(children=children, name=pattern_name, processor=self.processor)
            elif op == "not":
                pattern_name = pattern_json.get("name") or "not"
                return NotPattern(children=children, name=pattern_name, processor=self.processor)
            else:
                pattern_name = pattern_json.get("name") or "and"
                return AndPattern(children=children, name=pattern_name, processor=self.processor)

        # Leaf node
        name = pattern_json.get("filename") or pattern_json.get("name") or "unnamed"
        if "code" in pattern_json:
            return self.compile_code(pattern_json["code"], name=name)
        elif "file" in pattern_json:
            return self.compile_file(pattern_json["file"], name=name)
        elif "path" in pattern_json:
            return self.compile_file(pattern_json["path"], name=name)
        else:
            raise ValueError(f"Leaf pattern object must contain 'code', 'file', or 'path': {pattern_json}")

    def compile(
        self,
        source: Union[str, Path, dict],
        name: Optional[str] = None,
    ) -> CompiledPattern:
        """
        Polymorphic compilation entry point:
        dispatches between file paths, directories, raw code strings, and JSON definitions.
        """
        if isinstance(source, Path):
            if source.is_dir():
                return self.compile_dir(source, name=name)
            return self.compile_file(source, name=name)

        if isinstance(source, dict):
            return self.compile_json(source)

        if isinstance(source, str):
            trimmed = source.strip()
            if (trimmed.startswith("{") and trimmed.endswith("}")) or (
                trimmed.startswith("[") and trimmed.endswith("]")
            ):
                try:
                    parsed = json.loads(source)
                    if isinstance(parsed, dict):
                        return self.compile_json(parsed)
                except Exception:
                    pass
            if "\n" not in source and os.path.isdir(source):
                return self.compile_dir(source, name=name)
            if "\n" not in source and os.path.isfile(source):
                return self.compile_file(source, name=name)
            return self.compile_code(source, name=name)

        raise TypeError(f"Unsupported source type for compile: {type(source).__name__}")
