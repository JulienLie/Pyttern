import argparse
import glob
from tqdm import tqdm

from .language_processors import get_processor


def match_files(pattern_path, code_path, strict_match=False, match_details=False):
    try:
        pattern = generate_tree_from_file(pattern_path)
        code = generate_tree_from_file(code_path)
    except Exception as e:
        assert False, e

    fsm = Python_Visitor(strict_match).visit(pattern)

    simu = Simulator(fsm, code)
    simu.start()
    while len(simu.states) > 0:
        simu.step()
    if match_details:
        return len(simu.match_set.matches) > 0, simu.match_set.matches
    return len(simu.match_set.matches) > 0


def match_wildcards(pattern_path, code_path, strict_match=False, match_details=False):
    """
    Match all python files with all pattern files.
    The path_pattern_with_wildcards and path_python_with_wildcard
    can contain wildcards. The function returns a dictionary with the results of the matches.
    :param pattern_path: Path to the pattern files with wildcards.
    :param code_path: Path to the python files with wildcards.
    :param strict_match: If True, the match will be strict, otherwise it will be soft.
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
            result = match_files(pattern_filepath, python_filepath, strict_match, match_details)
            if python_filepath not in ret:
                ret[python_filepath] = {}
            ret[python_filepath][pattern_filepath] = result
    return ret


def run_application():
    from .visualizer.web import application
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

    fsm = processor.create_fsm(pattern_tree)

    simu = processor.create_simulator(fsm, code_tree)

    listener = processor.create_listener()

    simu.add_listener(listener)
    simu.start()

    while len(simu.states) > 0:
        simu.step()
    print(simu.match_set.matches)

if __name__ == "__main__":
    main()
