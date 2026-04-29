from pathlib import Path

from pyttern import match_files

BASE = Path(__file__).parent


def test_simple_number(subtests):
    for place in ["arg", "list", "stmt"]:
        for folder in ["low", "between", "strict"]:
            with subtests.test(msg=f"Testing simple number wildcard in {place} -> {folder}", place=place, folder=folder):
                pattern_path = BASE / place / folder / f"{folder}.pyt"
                code_path = BASE / place / folder / f"{folder}_ok.py"

                res, det = match_files(pattern_path, code_path, match_details=True)
                assert res, f"Was {res} for {place}/{folder} but should be True"

                code_path = BASE / place / folder / f"{folder}_ko.py"
                res, det = match_files(pattern_path, code_path, match_details=True)
                assert not res, f"Was {res} for {place}/{folder} but should be False"
