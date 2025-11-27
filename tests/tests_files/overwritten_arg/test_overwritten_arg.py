from pathlib import Path

from pyttern import match_wildcards

BASE = Path(__file__).parent


def test_var_wildcard_in_arg():
    pattern_path = BASE / "overwritten_arg.pyt"
    code_path = BASE / "arg_*.py"

    matches = match_wildcards(pattern_path, code_path)

    for code, match in matches.items():
        for pattern, result in match.items():
            if "nok" in str(code):
                assert not result, f"{pattern} on {code} should not match"
            else:
                assert result, f"{pattern} on {code} should match"
