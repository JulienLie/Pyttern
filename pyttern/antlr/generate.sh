for d in ./*/ ; do
  basename=$(basename "$d")
  if [[ $basename == _* ]]; then
    echo "Skipping directory $basename"
    continue
  fi
  echo "Processing directory: $basename"
  cd "$d"
  python3 transformGrammar.py
  echo "Generating lexer and parser"
  java -jar ../antlr-4.13.2-complete.jar -Dlanguage=Python3 -visitor *.g4
  echo "Done!"
  cd ..
done