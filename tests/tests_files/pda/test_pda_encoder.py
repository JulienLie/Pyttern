import pytest

from pyttern.simulator.pda.PDA import PDAEncoder, PDA
from pyttern.simulator.pda.transition import Transition, NavigationAlphabet, NodeTransition


class TestPDAEncoder:
    @pytest.fixture(autouse=True)
    def setUp(self):
        self.transition = Transition(0, "a", NodeTransition(""), [NavigationAlphabet.LEFT_CHILD], 1, "A")
        self.pda = PDA({0, 1}, {"a", "b"}, {0: [self.transition]}, 0, 1)
        self.navigation_direction = NavigationAlphabet.LEFT_CHILD

    def test_encode_pda(self):
        encoder = PDAEncoder()
        result = encoder.default(self.pda)
        assert isinstance(result, dict)
        assert "states" in result
        assert isinstance(result["states"], list)

    def test_encode_transition(self):
        encoder = PDAEncoder()
        result = encoder.default(self.transition)
        assert isinstance(result, dict)
        assert "q" in result

    def test_encode_navigation_direction(self):
        encoder = PDAEncoder()
        result = encoder.default(self.navigation_direction)
        assert isinstance(result, str)
        assert result == "LEFT_CHILD"

    def test_encode_unknown_type(self):
        encoder = PDAEncoder()
        with pytest.raises(TypeError):
            encoder.default(object())
