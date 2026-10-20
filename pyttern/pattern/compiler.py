from __future__ import annotations

import os
from pathlib import Path
from typing import Optional, Union

from loguru import logger

from pyttern.language_processors import Languages, get_processor
from pyttern.language_processors.base_processor_interface import BaseProcessor
from pyttern.language_processors.java_processor import JavaProcessor
from pyttern.language_processors.python_processor import PythonProcessor
from pyttern.pattern.compiled import CompiledPattern, LeafPattern
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

        pattern_name = name if name is not None else "raw_pattern"
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
            if hasattr(self, "compile_dir") and source.is_dir():
                return getattr(self, "compile_dir")(source)
            return self.compile_file(source, name=name)

        if isinstance(source, str):
            if hasattr(self, "compile_dir") and "\n" not in source and os.path.isdir(source):
                return getattr(self, "compile_dir")(source)
            if "\n" not in source and os.path.isfile(source):
                return self.compile_file(source, name=name)
            return self.compile_code(source, name=name)

        if isinstance(source, dict) and hasattr(self, "compile_json"):
            return getattr(self, "compile_json")(source)

        raise TypeError(f"Unsupported source type for compile: {type(source).__name__}")
