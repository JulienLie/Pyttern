import json
import pytest
from pyttern.visualizer.web.application import app

@pytest.fixture
def client():
    app.config['TESTING'] = True
    with app.test_client() as client:
        yield client

def test_batch_validate_success(client):
    data = {
        "codes": [
            {"filename": "ok.py", "code": "def foo(): pass"},
            {"filename": "error.py", "code": "def =?"}
        ],
        "lang": "python"
    }
    response = client.post('/api/batch_validate', json=data)
    assert response.status_code == 200
    result = json.loads(response.data)

    assert result["ok.py"]["status"] == "ok"
    assert result["error.py"]["status"] == "error"

def test_batch_validate_missing_lang(client):
    data = {
        "codes": [
            {"filename": "ok.py", "code": "def foo(): pass"}
        ],
        "lang": ""
    }
    response = client.post('/api/batch_validate', json=data)
    assert response.status_code == 200
    # Should fall back to try_processors and just return status ok
    result = json.loads(response.data)
    assert result["status"] == "ok"
