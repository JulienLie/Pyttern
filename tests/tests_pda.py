import unittest

from language_processors.python_processor import PythonProcessor
from pytternfsm.python.python_to_pda import Python_to_PDA
from tests import get_test_file


class TestPDA:
    def test_pda(self):
        pattern_path = get_test_file("q1_254.py")
        tree = PythonProcessor().generate_tree_from_file(pattern_path)
        pda = Python_to_PDA().visit(tree)
        assert pda is not None


if __name__ == '__main__':
    unittest.main()
