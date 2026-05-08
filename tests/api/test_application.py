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