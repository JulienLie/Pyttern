import argparse
import glob
import os
import time

from loguru import logger
from tqdm import tqdm

from .language_processors import get_processor, Languages
from .simulator.Matcher import Matcher


def parse_subfolders(folder_path, op="and"):
    """
    Parse all subfolders in the given folder path and return a dictionary with the patterns.
    :param folder_path: Path to the folder containing the patterns.
    :param op:
    :return: Dictionary with the patterns.
    """
    res = []
    for subfolder in os.listdir(folder_path):
        subfolder_path = os.path.join(folder_path, subfolder)
        if os.path.isdir(subfolder_path) and subfolder in ["and", "or", "not"]:
            res.append(parse_subfolders(subfolder_path, subfolder))

        for language in Languages:
            processor = get_processor(language)
            for file_extension in processor.get_language_extensions():
                if subfolder.endswith(file_extension):
                    logger.info(f"Processing pattern file: {subfolder} with language: {language}")
                    pattern_file_path = os.path.join(folder_path, subfolder)
                    tree = processor.generate_tree_from_file(pattern_file_path)
                    fsm = processor.create_pda(tree)
                    res.append(fsm)
                    break
    return {op: res}


def match_pyttern(pattern_tree, code_tree, match_details=False, stop_at_first=False):
    """
    Match a pattern tree with a code tree.
    :param pattern_tree: The pattern tree to match.
    :param code_tree: The code tree to match against.
    :param match_details: If True, the function will return the match details.
    :param stop_at_first: If True, stop at the first match found.
    :return: A boolean indicating if the match was successful or a list of matches if match_details is True.
    """
    if isinstance(pattern_tree, dict):
        # Handle logical operations like 'and', 'or', 'not'
        op = list(pattern_tree.keys())[0]
        subpatterns = pattern_tree[op]
        logger.debug(f"Processing logical operation: {op} with {len(subpatterns)} subpatterns")
        if op == "and":
            all_matches = []
            for subpattern in subpatterns:
                result = match_pyttern(subpattern, code_tree, match_details, stop_at_first)
                all_matches.append(result)
                if not result and not match_details:
                    return False
            if match_details:
                return {op: all_matches, 'res': all([len(m) > 0 for m in all_matches])}
            return True
        elif op == "or":
            all_matches = []
            for subpattern in subpatterns:
                result = match_pyttern(subpattern, code_tree, match_details, stop_at_first)
                all_matches.append(result)
                if result and not match_details:
                    return True
            if match_details:
                return {op: all_matches, 'res': any([len(m) > 0 for m in all_matches])}
            return False
        elif op == "not":
            all_matches = []
            for subpattern in subpatterns:
                result = match_pyttern(subpattern, code_tree, match_details, stop_at_first)
                all_matches.append(result)
                if result and not match_details:
                    return False
            if match_details:
                return {op: all_matches, 'res': not any([len(m) > 0 for m in all_matches])}
            return True
        else:
            if match_details:
                return {op: [], 'res': False}
            return False  # Unknown operation
    else:
        logger.debug("Matching pattern tree with code tree")
        res = Matcher.match(pattern_tree, code_tree, stop_at_first=stop_at_first)
        if res.count() > 0:
            logger.debug(f"Match found with {res.count()} matches")
        else:
            logger.debug("No match found")
        if match_details:
            return {'res': res.count() > 0, 'matches': res}
        return True if res.count() > 0 else False

def match_folders(pattern_path, code_path, match_details=False, stop_at_first=False):
    """
    Match all files in the pattern folder with all files in the code folder.
    The pattern_path and code_path can contain wildcards.
    The function returns a dictionary with the results of the matches.
    :param pattern_path: Path to the pattern files with wildcards.
    :param code_path: Path to the python files with wildcards.
    :param match_details: If True, the function will return the match details.
    :param lang: Language of the code (default is "python").
    :param stop_at_first: If True, stop at the first match found.
    :return: Dictionary with the results of the matches.
    """

    # Compile all patterns to speed up matching
    logger.info("Compiling patterns...")
    start_pattern = time.time()
    patterns = {}
    for pyttern_folder in os.listdir(pattern_path):
        pyttern_folder_path = os.path.join(pattern_path, pyttern_folder)
        if os.path.isdir(pyttern_folder_path):
            patterns[pyttern_folder] = parse_subfolders(pyttern_folder_path)
    end_pattern = time.time()
    logger.info(f"Patterns compiled in {end_pattern - start_pattern} s")

    # Compile all code files to speed up matching
    logger.info("Compiling code files...")
    start_code = time.time()
    code_trees = []
    for root, dirs, files in os.walk(code_path):
        for file in files:
            if file.endswith('.py'):
                processor = get_processor("python")
            elif file.endswith('.java'):
                processor = get_processor("java")
            else:
                logger.warning(f"Unknown file type in {root}: {file}")
                continue
            code_file_path = os.path.join(root, file)
            try:
                code_tree = processor.generate_tree_from_file(code_file_path)
                code_trees.append((code_file_path, code_tree))
            except Exception as e:
                logger.error(f"Error processing file {code_file_path}: {e}")
                continue

    end_code = time.time()
    logger.info(f"Code files compiled in {end_code - start_code} s")

    logger.info("Starting matching...")
    start_match = time.time()
    ret = {}
    for code_file_path, code_tree in code_trees:
        for pyttern in patterns:
            pattern_tree = patterns[pyttern]
            ret[code_file_path] = match_pyttern(pattern_tree, code_tree, match_details=match_details, stop_at_first=stop_at_first)
    stop_match = time.time()
    logger.info(f"Matching completed in {stop_match - start_match} s.")


    end_match = time.time()
    logger.info(f"Matching completed in {end_match - start_match} s.")

    return ret

def match_files(pattern_path, code_path, match_details=False, lang="python", stop_at_first=False):
    language_processor = get_processor(lang)
    pattern = language_processor.generate_tree_from_file(pattern_path)
    code = language_processor.generate_tree_from_file(code_path)

    fsm = language_processor.create_pda(pattern)
    matches = Matcher.match(fsm, code, stop_at_first=stop_at_first)
    if match_details:
        return matches.count() > 0, matches
    return matches.count() > 0


def match_wildcards(pattern_path, code_path, match_details=False):
    """
    Match all python files with all pattern files.
    The path_pattern_with_wildcards and path_python_with_wildcard
    can contain wildcards. The function returns a dictionary with the results of the matches.
    :param pattern_path: Path to the pattern files with wildcards.
    :param code_path: Path to the python files with wildcards.
    :param match_details: If True, the function will return the match details.
    :return: Dictionary with the results of the matches.
    """
    ret = {}
    patterns_filespath = glob.glob(str(pattern_path))
    pythons_filespath = glob.glob(str(code_path))

    try:
        pythons_filespath = tqdm(pythons_filespath)
    except NameError:
        pass

    for python_filepath in pythons_filespath:
        for pattern_filepath in patterns_filespath:
            result = match_files(pattern_filepath, python_filepath, match_details)
            if python_filepath not in ret:
                ret[python_filepath] = {}
            ret[python_filepath][pattern_filepath] = result
    return ret


def run_application():
    from .visualizer.web import application
    logger.enable("pyttern")
    application.app.run(debug=True)


def main():
    parser = argparse.ArgumentParser()

    parser.add_argument("--web", action="store_true", help="Launch the web application")
    parser.add_argument("--lang", choices=['python', 'java'], help="Specify the language (python/java)")

    parser.add_argument("pattern", nargs="?", help="Pattern file path")
    parser.add_argument("code", nargs="?", help="Code file path")

    args = parser.parse_args()

    if args and args.web:
        run_application()
        return
    
    if not args.lang or not args.pattern or not args.code:
        print("You must specify --lang, pattern file, and code file when not running the web application.")
        return

    processor = get_processor(args.lang) # Get processor behaviour adapted to language
    pattern = args.pattern
    code = args.code

    try:
        pattern_tree = processor.generate_tree_from_file(pattern)
        code_tree = processor.generate_tree_from_file(code)
    except Exception as e:
        print(e)
        return

    fsm = processor.create_pda(pattern_tree)

    simu = Matcher(fsm, code_tree)

    listener = processor.create_listener()

    simu.add_listener(listener)
    simu.start()

    while len(simu.configurations) > 0:
        simu.step()
    print(simu.match_set.matches)

logger.disable("pyttern")
if __name__ == "__main__":
    logger.enable("pyttern")
    main()
