"""Main entry point, matcher coordinator, and CLI interface for Pyttern."""

import argparse
import glob
import os
import sys
from typing import Any

from loguru import logger

from pyttern.subpattern.subpattern_parser import parse_subpattern_from_file

from .language_processors import Languages, determine_language, get_processor
from .simulator.Matcher import Matcher


class PytternMatcher:
    """Coordinates compiling and matching patterns against source code ASTs.

    Encapsulates logic for parsing patterns, compiling them into Pushdown Automata,
    and running matches against single files, composite directories, or glob sets.

    Attributes:
        match_details: Whether to return full match metadata or simple booleans.
        stop_at_first: Whether to short-circuit matching after the first valid match.
    """

    def __init__(self, match_details: bool = False, stop_at_first: bool = False) -> None:
        """Initialize the matcher coordinator.

        Args:
            match_details: If True, returns detailed match records and bindings.
            stop_at_first: If True, stops simulation immediately on first match.
        """
        self.match_details = match_details
        self.stop_at_first = stop_at_first
        self._language_processors = {lang: get_processor(lang) for lang in Languages}
        self._extension_to_processor = {
            ext: processor
            for processor in self._language_processors.values()
            for ext in processor.get_language_extensions()
        }
        self._pattern_cache: dict[tuple[str, float], dict[str, Any]] = {}

    def _get_processor_for_file(self, file_name: str) -> Any:
        """Retrieve language processor corresponding to a file extension.

        Args:
            file_name: Name or path of the target file.

        Returns:
            BaseProcessorInterface | None: Matching language processor or None.
        """
        file_ext = os.path.splitext(file_name)[1]
        return self._extension_to_processor.get(file_ext)

    def parse_json_pattern(
        self,
        pattern_json: dict[str, Any],
        lang: str | None = None,
        _processor: Any = None,
    ) -> dict[str, Any]:
        """Parse a JSON pattern object and return compiled PDA dictionary trees.

        Args:
            pattern_json: JSON pattern hierarchy definition.
            lang: Language key string (e.g. 'python', 'java').
            _processor: Optional pre-resolved language processor.

        Returns:
            dict[str, Any]: Nested dictionary describing pattern operator and compiled PDAs.

        Raises:
            ValueError: If neither lang nor _processor is provided or language is unsupported.
        """
        processor = _processor
        if processor is None:
            if lang is None:
                raise ValueError("Either lang or _processor must be provided")
            processor = self._language_processors.get(Languages[lang.upper()])
            if not processor:
                raise ValueError(f"Unsupported language: {lang}")

        logger.debug(f"Parsing json pattern: {pattern_json}")
        if "children" in pattern_json:
            op = pattern_json["name"]
            res = [self.parse_json_pattern(child, _processor=processor) for child in pattern_json["children"]]
            return {'name': op, 'children': res}

        name = pattern_json.get('filename', 'unnamed')
        pattern_code = pattern_json["code"]
        tree = processor.generate_tree_from_code(pattern_code)
        fsm = processor.create_pda(tree)
        return {'name': name, 'result': fsm}

    def match_tree(self, pattern_tree: dict[str, Any], code_tree: Any) -> Any:
        """Match a compiled pattern tree against a target code parse tree.

        Args:
            pattern_tree: Tree structure of compiled PDAs and operators.
            code_tree: Parsed ANTLR target source code tree.

        Returns:
            Any: Boolean result or dictionary of match details based on match_details flag.
        """
        logger.debug(f"Matching pattern '{pattern_tree.get('name', 'root')}' with code tree.")
        if self.match_details:
            return self._match_pyttern_details(pattern_tree, code_tree)
        return self._match_pyttern_bool(pattern_tree, code_tree)

    def _match_pyttern_bool(self, pattern_tree: dict[str, Any], code_tree: Any) -> bool:
        """Evaluate pattern matching returning a boolean result with short-circuiting.

        Args:
            pattern_tree: Compiled pattern tree node.
            code_tree: Target code AST.

        Returns:
            bool: True if pattern matches code_tree, False otherwise.
        """
        name = pattern_tree.get('name')
        if 'children' not in pattern_tree:
            pattern_fsm = pattern_tree['result']
            res = Matcher.match(pattern_fsm, code_tree, stop_at_first=self.stop_at_first)
            match_found = res.count() > 0
            logger.debug(f"Leaf pattern '{name}' match result: {match_found}")
            return match_found

        subpatterns = pattern_tree.get('children', [])
        results_gen = (self._match_pyttern_bool(sp, code_tree) for sp in subpatterns)

        if name == 'and':
            result = all(results_gen)
            logger.debug(f"Result for 'and' operator: {result}")
            return result
        if name == 'or':
            result = any(results_gen)
            logger.debug(f"Result for 'or' operator: {result}")
            return result
        if name == 'not':
            result = not any(results_gen)
            logger.debug(f"Result for 'not' operator: {result}")
            return result
        return False

    def _match_pyttern_details(self, pattern_tree: dict[str, Any], code_tree: Any) -> dict[str, Any]:
        """Evaluate pattern matching returning full match details and traces.

        Args:
            pattern_tree: Compiled pattern tree node.
            code_tree: Target code AST.

        Returns:
            dict[str, Any]: Structured dictionary with operator results and match traces.
        """
        name = pattern_tree.get('name')
        if 'children' not in pattern_tree:
            pattern_fsm = pattern_tree['result']
            res = Matcher.match(pattern_fsm, code_tree, stop_at_first=self.stop_at_first)
            match_found = res.count() > 0
            logger.debug(f"Leaf pattern '{name}' match result: {match_found}")
            return {'name': name, 'result': match_found, 'matches': res}

        subpatterns = pattern_tree.get('children', [])
        child_results = [self._match_pyttern_details(sp, code_tree) for sp in subpatterns]
        child_bools = [cr['result'] for cr in child_results]

        result_bool = False
        if name == 'and':
            result_bool = all(child_bools)
        if name == 'or':
            result_bool = any(child_bools)
        if name == 'not':
            result_bool = not any(child_bools)

        logger.debug(f"Result for logical operator '{name}': {result_bool}")
        return {'name': name, 'result': result_bool, 'children': child_results}

    def _dir_to_pattern_tree(self, path: str, processor: Any, op: str = 'and') -> dict[str, Any]:
        """Recursively traverse a directory and convert composite pattern files into a pattern tree.

        Args:
            path: Directory path containing pattern files and operator folders.
            processor: Language processor to compile individual patterns.
            op: Default logical operator ('and', 'or', 'not').

        Returns:
            dict[str, Any]: Nested pattern dictionary tree.
        """
        logger.debug(f"Parsing directory '{path}' with operator '{op}'")
        children = []
        for item in sorted(os.listdir(path)):
            item_path = os.path.join(path, item)
            if item in ['and', 'or', 'not']:
                children.append(self._dir_to_pattern_tree(item_path, processor, op=item))
            elif os.path.isdir(item_path):
                logger.debug(f"Descending into sub-directory '{item_path}'")
                children.append(self._dir_to_pattern_tree(item_path, processor, op='and'))
            elif os.path.isfile(item_path):
                if self._get_processor_for_file(item):
                    logger.debug(f"Compiling pattern file: {item_path}")
                    tree = processor.generate_tree_from_file(item_path)
                    fsm = processor.create_pda(tree)
                    children.append({'name': item, 'result': fsm})
        return {'name': op, 'children': children}

    def match(self, pattern_path: str, code_path: str, lang: str) -> Any:
        """Compile and match a pattern against a source code file.

        Args:
            pattern_path: Path to pattern file or composite pattern directory.
            code_path: Path to target source code file.
            lang: Language string key ('python' or 'java').

        Returns:
            Any: Match result boolean or (bool, details_dict) tuple.

        Raises:
            ValueError: If the language is unsupported.
        """
        logger.info(f"Starting match for pattern '{pattern_path}' on code '{code_path}' with language '{lang}'")
        processor = self._language_processors.get(Languages[lang.upper()])
        if not processor:
            raise ValueError(f"Unsupported language: {lang}")

        # Compile pattern
        if os.path.isdir(pattern_path):
            logger.debug(f"Pattern is a directory, compiling composite pattern from '{pattern_path}'")
            pattern_tree = self._dir_to_pattern_tree(pattern_path, processor)
        else:  # single file
            try:
                mtime = os.path.getmtime(pattern_path)
            except OSError:
                mtime = 0.0
            cache_key = (os.path.abspath(pattern_path), mtime)
            if cache_key in self._pattern_cache:
                pattern_tree = self._pattern_cache[cache_key]
            else:
                logger.debug(f"Pattern is a single file, compiling from '{pattern_path}'")
                tree = processor.generate_tree_from_file(pattern_path)
                fsm = processor.create_pda(tree)
                pattern_tree = {'name': os.path.basename(pattern_path), 'result': fsm}
                self._pattern_cache[cache_key] = pattern_tree

        # Compile code
        code_tree = processor.generate_tree_from_file(code_path)

        # Match
        match_result = self.match_tree(pattern_tree, code_tree)
        if self.match_details:
            return match_result['result'], match_result
        return match_result

    def match_wildcards(self, pattern_path: str, code_path: str) -> dict[str, dict[str, Any]]:
        """Match files using glob patterns across patterns and code files.

        Args:
            pattern_path: Glob string for pattern files.
            code_path: Glob string for code files.

        Returns:
            dict[str, dict[str, Any]]: Mapping of code filepaths to pattern results.
        """
        ret = {}
        patterns_filespath = glob.glob(str(pattern_path))
        code_filespath = glob.glob(str(code_path))
        logger.info(f"Found {len(patterns_filespath)} pattern(s) and {len(code_filespath)} code file(s).")

        for code_filepath in code_filespath:
            processor = self._get_processor_for_file(code_filepath)
            if not processor:
                logger.warning(f"No processor found for code file: {code_filepath}, skipping.")
                continue

            lang = processor.language.value.lower()
            for pattern_filepath in patterns_filespath:
                result = self.match(pattern_filepath, code_filepath, lang)
                if code_filepath not in ret:
                    ret[code_filepath] = {}
                ret[code_filepath][pattern_filepath] = result
        return ret

    
def match_files(
    pattern_path: str,
    code_path: str,
    lang: str | None = None,
    match_details: bool = False,
    stop_at_first: bool = True,
) -> Any:
    """Convenience helper to match pattern files against code files.

    Args:
        pattern_path: Path to pattern file or directory.
        code_path: Path to target source code file.
        lang: Target programming language ('python', 'java'). If None, detected from file extensions.
        match_details: If True, returns (bool, matches_list). Otherwise returns bool.
        stop_at_first: If True, halts matching upon finding the first valid match.

    Returns:
        Any: Boolean match result, or tuple of (bool, matches) if match_details is True.

    Raises:
        ValueError: If pattern and code file languages do not match.
    """
    if lang is None:
        pattern_lang = determine_language(pattern_path)
        code_lang = determine_language(code_path)
        if code_lang != pattern_lang:
            raise ValueError(f"Pattern language ({pattern_lang}) and Code language ({code_lang}) should be the same.")
        lang = pattern_lang
    matcher = PytternMatcher(match_details, stop_at_first)
    if match_details:
        res, det = matcher.match(pattern_path, code_path, lang)
        return res, det["matches"]
    return matcher.match(pattern_path, code_path, lang)


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
    # Remove the default loguru handler
    logger.remove()

    # Map verbosity count to Loguru levels and formats
    if verbosity == 0:
        # Default mode (no -v): Only INFO and above, clean format
        log_level = "INFO"
        log_format = (
            "<green>{time:HH:mm:ss}</green> | "
            "<level>{level: <8}</level> | "
            "<level>{message}</level>"
        )
    elif verbosity == 1:
        # -v passed: DEBUG level, more context like function and line number
        log_level = "DEBUG"
        log_format = (
            "<green>{time:YYYY-MM-DD HH:mm:ss}</green> | "
            "<level>{level: <8}</level> | "
            "<cyan>{name}</cyan>:<cyan>{function}</cyan>:<cyan>{line}</cyan> - "
            "<level>{message}</level>"
        )
    else:
        # -vv or more passed: TRACE level, maximum context including milliseconds
        log_level = "TRACE"
        log_format = (
            "<green>{time:YYYY-MM-DD HH:mm:ss.SSS}</green> | "
            "<level>{level: <8}</level> | "
            "<cyan>{name}</cyan>:<cyan>{function}</cyan>:<cyan>{line}</cyan> - "
            "<level>{message}</level>"
        )

    # Add the configured handler
    logger.add(sys.stdout, format=log_format, level=log_level)


def main() -> None:
    """Parse command line arguments and execute the requested pattern matching action."""
    parser = argparse.ArgumentParser(description="Pyttern: A tool for pattern matching in code.")
    parser.add_argument("--web", action="store_true", help="Launch the web application.")
    parser.add_argument("--lang", choices=['python', 'java'], help="Specify the language for single file matching.")
    parser.add_argument("--details", action="store_true", help="Return detailed match information.")
    parser.add_argument("--stop-first", action="store_true", help="Stop at the first match found.")
    parser.add_argument(
        '-s', '--sub',
        action='append',
        default=[],
        dest='sub_patterns',
        help='Sub pattern files. Use this flag multiple times for multiple sub patterns (e.g., -s file1 -s file2).'
    )
    parser.add_argument(
        '-v', '--verbose',
        action='count',
        default=0,
        help="Increase output verbosity (e.g., -v for DEBUG, -vv for TRACE)"
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

    sub_patterns = getattr(args, 'sub_patterns', None) or getattr(args, 'sub', [])
    if sub_patterns:
        sub_lang = Languages[args.lang.upper()] if args.lang else Languages.PYTHON
        for sub_pyttern in sub_patterns:
            ret = parse_subpattern_from_file(sub_pyttern, sub_lang)
            if len(ret) > 0:
                logger.info(f"Loaded {len(ret)} subpattern(s) {[pat.name for pat in ret]} from file '{sub_pyttern}'")
            else:
                logger.warning(f"No subpatterns found in '{sub_pyttern}'")

    matcher = PytternMatcher(match_details=args.details, stop_at_first=args.stop_first)

    # Use wildcards if they are likely present, otherwise treat as single files
    if '*' in args.pattern or '*' in args.code:
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

