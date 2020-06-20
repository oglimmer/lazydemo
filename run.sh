#!/bin/bash

set -eu

curl -d '{"name":"test", "addresses": [{"city": "foo"}]}' -H 'Content-Type: application/json' http://localhost:8080/person

curl -s http://localhost:8080/person|jq

