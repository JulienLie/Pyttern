
from pathlib import Path
from pyttern.language_processors.languages import Languages
from pyttern.subpattern.subpattern_parser import parse_subpattern_from_file

def test_subpattern_code_retrieval():
    # Use loop.myt as it has multiple transformations and a compound structure
    subpattern_file = Path("tests/tests_files/subpatterns/or/compound/loop.myt")
    ret = parse_subpattern_from_file(str(subpattern_file), Languages.PYTHON)
    
    subpattern = ret[0]
    print(f"\nSubpattern Name: {subpattern.name}")
    print(f"Subpattern Code:\n---START---\n{subpattern.code}\n---END---")
    
    # The original text should start with $|Loop or similar depending on the file content
    # Let's check the file content first to know what to expect.
    with open(subpattern_file, 'r') as f:
        original_content = f.read().strip()
    
    # Usually parse_subpattern_from_file might return multiple subpatterns if there are many in the file.
    # In loop.myt there is only one.
    
    # We expect subpattern.code to match a portion of the original file (the subpattern_stmts part)
    assert subpattern.code.strip() in original_content.strip()
    assert "$|Loop(?i, ?n)" in subpattern.code
    assert "$# for_loop" in subpattern.code
    assert "$# while_loop" in subpattern.code

if __name__ == "__main__":
    try:
        test_subpattern_code_retrieval()
        print("Verification successful!")
    except Exception as e:
        print(f"Verification failed: {e}")
        import traceback
        traceback.print_exc()
