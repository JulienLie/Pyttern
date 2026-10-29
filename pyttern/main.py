"""Main entry point, matcher coordinator, and CLI interface for Pyttern."""
from __future__ import annotations

import argparse
import glob
import os
from pathlib import Path
import sys
from typing import Any

from loguru import logger

from pyttern.pattern import MatchResult, PatternCompiler
from pyttern.pytternfsm.python.match_set import MatchSet
from pyttern.subpattern.catalog import SubPatternCatalog

from .language_processors import BaseProcessor, Languages, determine_language, get_processor


class PytternMatcher:
    """Coordinates compiling and matching patterns against source code ASTs.

    It encapsulates the logic for orchestrating pattern matching, compiling patterns
    using PatternCompiler, and running the matching process against code files.

    Attributes:
        match_details: Whether to return full match metadata or simple booleans.
        stop_at_first: Whether to short-circuit matching after the first valid match.
    """

    def __init__(
        self,
        match_details: bool = False,
        stop_at_first: bool | None = None,
        catalog: SubPatternCatalog | None = None,
    ) -> None:
        """Initialize the matcher coordinator.

        Args:
            match_details: If True, returns detailed match records and bindings.
            stop_at_first: If True, stops simulation immediately on first match.
            catalog: Optional subpattern catalog to use.
        """
        self.match_details = match_details
        if not match_details:
            self.stop_at_first = True
        else:
            self.stop_at_first = bool(stop_at_first) if stop_at_first is not None else False

        self.catalog = catalog if catalog is not None else SubPatternCatalog()
        self._language_processors: dict[Languages, BaseProcessor] = {lang: get_processor(lang) for lang in Languages}
        self._extension_to_processor: dict[str, BaseProcessor] = {}
        for processor in self._language_processors.values():
            for ext in processor.get_language_extensions():
                clean_ext = ext.lstrip(".")
                self._extension_to_processor[clean_ext] = processor
                self._extension_to_processor[f".{clean_ext}"] = processor

    def _get_processor_for_file(self, file_name: str | Path | os.PathLike[str]) -> BaseProcessor | None:
        """Retrieve language processor corresponding to a file extension.

        Args:
            file_name: Name or path of the target file.

        Returns:
            BaseProcessor | None: Matching language processor or None.
        """
        file_ext = os.path.splitext(str(file_name))[1].lstrip(".")
        return self._extension_to_processor.get(file_ext)

    def match(
        self,
        pattern_path: str | Path,
        code_path: str | Path,
        lang: str | Languages | BaseProcessor,
    ) -> bool | tuple[bool, MatchResult]:
        """Main matching method. Compiles pattern and code from paths and matches them.

        The pattern can be a single file, a raw code string, or a directory representing a composite pattern.

        Args:
            pattern_path: Path to the pattern file or directory.
            code_path: Path to the target code file.
            lang: Programming language string, enum, or processor instance.

        Returns:
            bool | tuple[bool, MatchResult]: Match result or tuple of (matched, MatchResult).
        """
        logger.info(f"Starting match for pattern '{pattern_path}' on code '{code_path}' with language '{lang}'")
        compiler = PatternCompiler(language=lang, catalog=self.catalog)
        compiled_pattern = compiler.compile(pattern_path)

        match_result = compiled_pattern.matches(code_path, stop_at_first=self.stop_at_first)
        if self.match_details:
            return match_result.matched, match_result
        return match_result.matched

    def match_wildcards(
        self,
        pattern_path: str | Path,
        code_path: str | Path,
    ) -> dict[str, dict[str, bool | tuple[bool, MatchResult]]]:
        """Match files using glob patterns for both patterns and code files.

        Pre-compiles discovered patterns once before iterating across target code files.
        Parses each target code file once per code file, and matches against pre-compiled patterns.

        Args:
            pattern_path: Glob string matching pattern files.
            code_path: Glob string matching target code files.

        Returns:
            dict: Nested dictionary mapping code paths to pattern match results.
        """
        ret: dict[str, dict[str, bool | tuple[bool, MatchResult]]] = {}
        patterns_filespath = sorted(glob.glob(str(pattern_path)))
        code_filespath = sorted(glob.glob(str(code_path)))
        logger.info(f"Found {len(patterns_filespath)} pattern(s) and {len(code_filespath)} code file(s).")

        # Pre-compile discovered pattern files once (O(M))
        compiled_patterns = {}
        for p_path in patterns_filespath:
            processor = self._get_processor_for_file(p_path)
            if not processor:
                for c_path in code_filespath:
                    processor = self._get_processor_for_file(c_path)
                    if processor:
                        break
            if not processor:
                processor = self._language_processors[Languages.PYTHON]

            compiler = PatternCompiler(language=processor, catalog=self.catalog)
            compiled_patterns[p_path] = compiler.compile(p_path)

        # Parse each target code file once per code file, and match against pre-compiled patterns (O(N))
        for code_filepath in code_filespath:
            processor = self._get_processor_for_file(code_filepath)
            if not processor:
                logger.warning(f"No processor found for code file: {code_filepath}, skipping.")
                continue

            code_tree = processor.generate_tree_from_file(code_filepath)
            for pattern_filepath, compiled_pattern in compiled_patterns.items():
                match_result = compiled_pattern.matches(code_tree, stop_at_first=self.stop_at_first)

                if code_filepath not in ret:
                    ret[code_filepath] = {}

                if self.match_details:
                    ret[code_filepath][pattern_filepath] = (match_result.matched, match_result)
                else:
                    ret[code_filepath][pattern_filepath] = match_result.matched

        return ret



def match_files(
    pattern_path: str | Path,
    code_path: str | Path,
    lang: str | Languages | None = None,
    match_details: bool = False,
    stop_at_first: bool = True,
    catalog: SubPatternCatalog | None = None,
) -> bool | tuple[bool, MatchSet]:
    """Helper function to execute pattern matching against files on disk.

    Args:
        pattern_path: Path to pattern file or directory.
        code_path: Path to target source code file.
        lang: Optional language override.
        match_details: If True, returns match details.
        stop_at_first: If True, stops on first match.
        catalog: Optional subpattern catalog.

    Returns:
        bool | tuple[bool, MatchSet]: Match boolean result, or tuple of (bool, matches) if match_details is True.

    Raises:
        ValueError: If pattern and code file languages do not match.
    """
    if lang is None:
        code_lang = determine_language(code_path)
        if os.path.isdir(str(pattern_path)):
            pattern_lang = code_lang
        else:
            pattern_lang = determine_language(pattern_path)
        if code_lang != pattern_lang:
            raise ValueError(f"Pattern language ({pattern_lang}) and Code language ({code_lang}) should be the same.")
        if pattern_lang is None:
            raise ValueError(f"Could not determine language for pattern '{pattern_path}' or code '{code_path}'.")
        lang = pattern_lang
    matcher = PytternMatcher(match_details, stop_at_first, catalog=catalog)
    if match_details:
        match_out = matcher.match(pattern_path, code_path, lang)
        if isinstance(match_out, tuple):
            res, det = match_out
            return res, det.matches if det.matches is not None else MatchSet()
        return match_out, MatchSet()
    res_bool = matcher.match(pattern_path, code_path, lang)
    assert isinstance(res_bool, bool)
    return res_bool


def run_application(host: str = "0.0.0.0", port: int = 5000) -> None:
    """Launch the Pyttern interactive Flask web visualizer.

    Args:
        host: Network interface address to bind server to.
        port: Port number for the HTTP server.
    """
    from .visualizer.web import application

    logger.enable("pyttern")
    application.app.run(debug=True, host=host, port=port)


def configure_logger(verbosity: int) -> None:
    """Configure Loguru logging levels and formatting based on verbosity count.

    Args:
        verbosity: Verbosity integer level (0 for INFO, 1 for DEBUG, 2+ for TRACE).
    """
    logger.remove()

    if verbosity == 0:
        log_level = "INFO"
        log_format = (
            "<green>{time:HH:mm:ss}</green> | "
            "<level>{level: <8}</level> | "
            "<level>{message}</level>"
        )
    elif verbosity == 1:
        log_level = "DEBUG"
        log_format = (
            "<green>{time:YYYY-MM-DD HH:mm:ss}</green> | "
            "<level>{level: <8}</level> | "
            "<cyan>{name}</cyan>:<cyan>{function}</cyan>:<cyan>{line}</cyan> - "
            "<level>{message}</level>"
        )
    else:
        log_level = "TRACE"
        log_format = (
            "<green>{time:YYYY-MM-DD HH:mm:ss.SSS}</green> | "
            "<level>{level: <8}</level> | "
            "<cyan>{name}</cyan>:<cyan>{function}</cyan>:<cyan>{line}</cyan> - "
            "<level>{message}</level>"
        )

    logger.add(sys.stdout, format=log_format, level=log_level)


def main() -> None:
    """Parse command line arguments and execute the requested pattern matching action."""
    parser = argparse.ArgumentParser(description="Pyttern: A tool for pattern matching in code.")
    parser.add_argument("--web", action="store_true", help="Launch the web application.")
    parser.add_argument("--lang", choices=["python", "java"], help="Specify the language for single file matching.")
    parser.add_argument("--details", action="store_true", help="Return detailed match information.")
    parser.add_argument("--stop-first", action="store_true", help="Stop at the first match found.")
    parser.add_argument(
        "-s",
        "--sub",
        action="append",
        default=[],
        dest="sub",
        help="Sub pattern files. Use this flag multiple times for multiple sub patterns (e.g., -s file1 -s file2).",
    )
    parser.add_argument(
        "-v",
        "--verbose",
        action="count",
        default=0,
        help="Increase output verbosity (e.g., -v for DEBUG, -vv for TRACE)",
    )

    parser.add_argument("pattern", nargs="?", help="Pattern file path or glob pattern.")
    parser.add_argument("code", nargs="?", help="Code file path or glob pattern.")

    args = parser.parse_args()

    configure_logger(args.verbose)

    if args.web:
        run_application()
        return

    if not args.pattern or not args.code:
        parser.error("You must specify a pattern and a code file/path when not running the web application.")
        return

    catalog = SubPatternCatalog()
    sub_patterns = getattr(args, "sub_patterns", None) or getattr(args, "sub", [])
    if sub_patterns:
        sub_lang = Languages[args.lang.upper()] if args.lang else Languages.PYTHON
        for sub_pyttern in sub_patterns:
            ret = catalog.load_file(sub_pyttern, language=sub_lang)
            if len(ret) > 0:
                logger.info(f"Loaded {len(ret)} subpattern(s) {[pat.name for pat in ret]} from file '{sub_pyttern}'")
            else:
                logger.warning(f"No subpatterns found in '{sub_pyttern}'")

    matcher = PytternMatcher(match_details=args.details, stop_at_first=args.stop_first, catalog=catalog)

    if "*" in args.pattern or "*" in args.code:
        results = matcher.match_wildcards(args.pattern, args.code)
        logger.info(results)
    else:
        if not args.lang:
            parser.error("--lang is required for single file matching.")
            return

        if not os.path.exists(args.pattern):
            logger.error(f"Pattern file not found: {args.pattern}")
            return
        if not os.path.exists(args.code):
            logger.error(f"Code file not found: {args.code}")
            return

        result = matcher.match(args.pattern, args.code, args.lang)
        if args.details:
            assert isinstance(result, tuple)
            res, det = result
            if res:
                logger.success("Match found!")
                logger.info(det)
            else:
                logger.warning("No match found.")
        else:
            if result:
                logger.success("Match found!")
            else:
                logger.warning("No match found.")


if __name__ == "__main__":
    logger.enable("pyttern")
    main()
