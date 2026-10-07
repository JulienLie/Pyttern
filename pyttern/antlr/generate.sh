#!/usr/bin/env bash
set -e

ORIGINAL_DIR="$(pwd)"
SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"

trap 'cd "$ORIGINAL_DIR"' EXIT RETURN 2>/dev/null || trap 'cd "$ORIGINAL_DIR"' EXIT

ANTLR_JAR="$SCRIPT_DIR/antlr-4.13.2-complete.jar"

cd "$SCRIPT_DIR"

echo "Running ANTLR generator from: $SCRIPT_DIR"

for d in "$SCRIPT_DIR"/*/ ; do
  [ -d "$d" ] || continue
  basename=$(basename "$d")
  if [[ $basename == _* ]]; then
    echo "Skipping directory $basename"
    continue
  fi
  echo "Processing directory: $basename"
  cd "$d"
  if [ -f "transformGrammar.py" ]; then
    python3 transformGrammar.py
  fi
  echo "Generating lexer and parser"
  java -jar "$ANTLR_JAR" -Dlanguage=Cpp -visitor *.g4
  echo "Done!"
  cd "$SCRIPT_DIR"
done

cd "$ORIGINAL_DIR"