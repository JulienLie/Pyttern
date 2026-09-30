import pytest
from pyttern.simulator.configuration import Environment


def test_environment_empty():
    env = Environment.empty()
    assert isinstance(env, Environment)
    assert env.mapping == {}
    assert len(env) == 0


def test_environment_constructor_private():
    with pytest.raises(RuntimeError, match="constructor is private"):
        Environment({})

    with pytest.raises(RuntimeError, match="constructor is private"):
        Environment({"x": 1})


def test_environment_from_dict_and_dict_ops():
    env = Environment.from_dict({"a": 1, "b": 2})
    assert isinstance(env, Environment)
    assert env["a"] == 1
    assert env.get("b") == 2
    assert env.get("c", 3) == 3
    assert "a" in env
    assert "c" not in env
    assert len(env) == 2
    assert list(env.keys()) == ["a", "b"]
    assert list(env.values()) == [1, 2]
    assert list(env.items()) == [("a", 1), ("b", 2)]


def test_environment_bind_update_join():
    env1 = Environment.from_dict({"a": 1})
    env2 = env1.bind("b", 2)
    assert env1.mapping == {"a": 1}
    assert env2.mapping == {"a": 1, "b": 2}

    env3 = env2.update({"c": 3, "a": 10})
    assert env3.mapping == {"a": 10, "b": 2, "c": 3}

    env4 = Environment.from_dict({"a": None, "b": 20})
    joined = env4.join({"a": 100, "b": None})
    assert joined.mapping == {"a": 100, "b": 20}


def test_environment_merge():
    env1 = Environment.from_dict({"a": 1, "b": 2})
    env2 = Environment.from_dict({"b": 2, "c": 3})
    merged = env1.merge(env2)
    assert isinstance(merged, Environment)
    assert merged.mapping == {"a": 1, "b": 2, "c": 3}

    conflict_env = Environment.from_dict({"b": 99})
    assert env1.merge(conflict_env) is None


def test_environment_eq_and_hash():
    env1 = Environment.from_dict({"a": 1, "b": 2})
    env2 = Environment.from_dict({"a": 1, "b": 2})
    env3 = Environment.from_dict({"a": 1, "b": 3})

    assert env1 == env2
    assert env1 == {"a": 1, "b": 2}
    assert env1 != env3
    assert hash(env1) == hash(env2)

    s = {env1, env2, env3}
    assert len(s) == 2
