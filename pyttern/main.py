import argparse
import glob
import os
import sys

from loguru import logger

from pyttern.subpattern.subpattern_parser import parse_subpattern_from_file

from .language_processors import determine_language, get_processor, Languages
from .simulator.Matcher import Matcher


class PytternMatcher:
    """
    A class to handle matching of patterns against code.
    It encapsulates the logic for parsing patterns, compiling them into Finite State Machines,
    and running the matching process against code files.
    """

    def __init__(self, match_details=False, stop_at_first=False):
        self.match_details = match_details
        self.stop_at_first = stop_at_first
        self._language_processors = {lang: get_processor(lang) for lang in Languages}
        self._extension_to_processor = {
            ext: processor
            for processor in self._language_processors.values()
            for ext in processor.get_language_extensions()
        }

    def _get_processor_for_file(self, file_name):
        """Get the language processor for a given file name based on its extension."""
        file_ext = os.path.splitext(file_name)[1]
        return self._extension_to_processor.get(file_ext)

    def parse_json_pattern(self, pattern_json, lang=None, _processor=None):
        """
        Parse a JSON pattern and return a dictionary with the patterns.
        :param pattern_json: JSON object with the patterns.
        :param lang: language of the patterns
        :param _processor: (internal) language processor to use
        :return: Dictionary with the patterns.
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

    def match_tree(self, pattern_tree, code_tree):
        """
        Match a compiled pattern tree against a compiled code tree.
        This is the dispatcher for pattern matching, calling the appropriate
        method based on `match_details`.
        """
        logger.debug(f"Matching pattern '{pattern_tree.get('name', 'root')}' with code tree.")
        if self.match_details:
            return self._match_pyttern_details(pattern_tree, code_tree)
        return self._match_pyttern_bool(pattern_tree, code_tree)

    def _match_pyttern_bool(self, pattern_tree, code_tree):
        """
        Perform pattern matching and return a boolean result. Supports short-circuiting.
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

    def _match_pyttern_details(self, pattern_tree, code_tree):
        """
        Perform pattern matching and return detailed results.
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

    def _dir_to_pattern_tree(self, path, processor, op='and'):
        """
        Recursively traverse a directory and convert its structure into a pattern_tree.
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

    def match(self, pattern_path, code_path, lang):
        """
        Main matching method. Compiles pattern and code from paths and matches them.
        The pattern can be a single file or a directory representing a composite pattern.
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
            logger.debug(f"Pattern is a single file, compiling from '{pattern_path}'")
            tree = processor.generate_tree_from_file(pattern_path)
            fsm = processor.create_pda(tree)
            pattern_tree = {'name': os.path.basename(pattern_path), 'result': fsm}

        # Compile code
        code_tree = processor.generate_tree_from_file(code_path)

        # Match
        match_result = self.match_tree(pattern_tree, code_tree)
        if self.match_details:
            return match_result['result'], match_result
        return match_result

    def match_wildcards(self, pattern_path, code_path):
        """
        Match files using glob patterns for both patterns and code files.
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
    
def match_files(pattern_path, code_path, lang=None, match_details=False, stop_at_first=True):
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


def run_application(host="0.0.0.0", port=5000):
    from .visualizer.web import application
    logger.enable("pyttern")
    application.app.run(debug=True, host=host, port=port)


def configure_logger(verbosity: int):
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



def main():
    parser = argparse.ArgumentParser(description="Pyttern: A tool for pattern matching in code.")
    parser.add_argument("--web", action="store_true", help="Launch the web application.")
    parser.add_argument("--lang", choices=['python', 'java'], help="Specify the language for single file matching.")
    parser.add_argument("--details", action="store_true", help="Return detailed match information.")
    parser.add_argument("--stop-first", action="store_true", help="Stop at the first match found.")
    parser.add_argument(
        '-s', '--sub', 
        action='append', 
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
    
    for sub_pyttern in args.sub:
        ret = parse_subpattern_from_file(sub_pyttern, Languages.PYTHON)
        if len(ret) > 0:
            logger.debug(f"Loaded sub patterns {[pat.name for pat in ret]} from file {sub_pyttern}")
        else:
            logger.warning(f"No sub pytterns found in {sub_pyttern}")

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
