import json
import pytest
from pyttern.visualizer.web.application import app


@pytest.fixture
def client():
    app.config['TESTING'] = True
    with app.test_client() as client:
        yield client

@pytest.fixture
def data():
    with open("tests/api/data.json", 'r') as file:
        yield json.load(file)


def test_batch_match_success(client, data):
    """Test batch_match endpoint with a successful match."""
    response = client.post('/api/batch_match', json=data)
    assert response.status_code == 200
    result = json.loads(response.data)
    assert len(result) == 2

    for r in result:
        assert len(r['patternsMatchResults']) == 2
        print(r)
        result_okreturn = r['patternsMatchResults']["okreturn.pyt"]
        result_early_return = r['patternsMatchResults']['earlyreturn.pyt']
        print(f"{r['match']} == (not {result_okreturn}) and {result_early_return}")
        assert r['match'] == (not result_okreturn) and result_early_return


def test_validate_python_success(client):
    """Test validate endpoint with valid Python code."""
    response = client.post('/api/validate', json={"code": "def foo(): pass", "lang": "python"})
    assert response.status_code == 200
    result = json.loads(response.data)
    assert result["status"] == "ok"


def test_validate_python_syntax_error(client):
    """Test validate endpoint with syntactically invalid Python code."""
    response = client.post('/api/validate', json={"code": "def foo(", "lang": "python"})
    assert response.status_code == 200
    result = json.loads(response.data)
    assert result["status"] == "error"
    assert "message" in result
    assert result["message"]["line"] == 1
    assert result["message"]["column"] == 0
    assert result["message"]["symbol"] == "<EOF>"
    assert result["message"]["msg"] == "expected 'match', '_', name/identifier, '*', ')', '**', or '?' but found end of file"


def test_pattern_compilation_success(client):
    """Test pattern endpoint with a simple wildcard pattern."""
    response = client.post('/api/pattern', json={"code": "a = ?", "lang": "python"})
    assert response.status_code == 200
    result = json.loads(response.data)
    assert result["status"] == "ok"
    assert "graph" in result
    assert "GRAPH" in result["graph"]
    assert "TREE" in result["graph"]


def test_code_parsing_success(client):
    """Test code endpoint parsing standard Python code."""
    response = client.post('/api/code', json={"code": "x = 42", "lang": "python"})
    assert response.status_code == 200
    result = json.loads(response.data)
    assert result["status"] == "ok"
    assert "graph" in result
    assert "TREE" in result["graph"]


def test_match_execution_success(client):
    """Test match endpoint matching a pattern against code."""
    response = client.post('/api/match', json={"code": "x = 42", "pattern": "? = ?", "lang": "python"})
    assert response.status_code == 200
    result = json.loads(response.data)
    assert result["status"] == "ok"
    assert "n_steps" in result
    assert result["n_steps"] > 0
    assert "match_states" in result


def test_subpattern_lifecycle(client):
    """Test the subpattern API endpoint lifecycle (GET, POST, DELETE)."""
    # 1. Verify GET lists subpatterns
    response = client.get('/api/subpattern')
    assert response.status_code == 200
    result = json.loads(response.data)
    assert result["status"] == "ok"
    assert "subpatterns" in result

    # 2. Add a new subpattern via POST
    subpattern_code = "$|Incr(?x)\n$# aug\n?x += 1\n"
    response = client.post('/api/subpattern', json={"code": subpattern_code})
    assert response.status_code == 200
    result = json.loads(response.data)
    assert result["status"] == "ok"
    assert "Incr" in result["names"]

    # 3. Verify GET shows the added subpattern
    response = client.get('/api/subpattern')
    result = json.loads(response.data)
    subpattern_names = [sp["name"] for sp in result["subpatterns"]]
    assert "Incr" in subpattern_names

    # 4. Delete the subpattern
    response = client.delete('/api/subpattern', json={"name": "Incr"})
    assert response.status_code == 200
    result = json.loads(response.data)
    assert result["status"] == "ok"

    # 5. Verify GET shows it is removed
    response = client.get('/api/subpattern')
    result = json.loads(response.data)
    subpattern_names = [sp["name"] for sp in result["subpatterns"]]
    assert "Incr" not in subpattern_names