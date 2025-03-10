for d in ./*/ ; do
  cd "$d"
  python3 transformGrammar.py
  echo "Generating lexer and parser"
  java -jar ../antlr-4.13.2-complete.jar -Dlanguage=Python3 -visitor *.g4
  echo "Done!"
  cd ..
done