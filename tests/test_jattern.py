import os
import pytest

from pyttern import match_files

PATTERN_EXTENSION = ".jat"
PATTERN_NAME = "pattern" + PATTERN_EXTENSION

def get_test_file(path):
    return os.path.dirname(__file__) + "/tests_files_jattern/" + path

def discover_dir(directory):
    """
    pre: directory is a path to a directory in which there are test folders

         test folders should contain:
         - a pattern.jat file which will be matched against all other files directly in the folder
         - ok_[...].java files which should yield a positive result when matched against the pattern mentioned above
         - ko_[...].java files which should yield a negative result when matched against the pattern mentioned above
         If there is a pattern.jat file but the other files do not respect this naming convention, this function will throw an exception
    
    post: yields all the test cases in the following format: (pattern_filepath, code_filepath, should_they_match)
            where should_they_match is a boolean indicating whether the pattern and code files should match or not
    """
    for root, _, files in os.walk(directory):
        if not PATTERN_NAME in files:
            continue
        
        pattern_path = os.path.join(root, PATTERN_NAME)

        for file in files:
            if PATTERN_EXTENSION not in file:
                if file.startswith("ok"):
                    yield (pattern_path, os.path.join(root, file), True)
                elif file.startswith("ko"):
                    yield (pattern_path, os.path.join(root, file), False)
                else:
                    raise Exception(f"File does not respect format (should be a pattern.jat or start with ko or ok) : {file}")

class TestJattern():
    @pytest.mark.parametrize("test_data", discover_dir(get_test_file("")))
    def test_all_in_folder(self, test_data):
        # test_data is of the format (pattern_filepath, code_filepath, should_they_match), see above for more details
        pattern_path = test_data[0]
        code_path = test_data[1]
        should_they_match = test_data[2]

        res, det = match_files(pattern_path, code_path, match_details=True, lang="java")

        if should_they_match:
            assert res, det # yes, they should match
        else:
            assert not res, det # no, they should not match