import os
import shutil
import sys


def main(argv):
    fix("JavaLexer.g4")
    fix("JavaParser.g4")

def fix(file_path):
    print("Altering " + file_path)
    if not os.path.exists(file_path):
        print(f"Could not find file: {file_path}")
        sys.exit(1)

    
    # Backup the original file
    shutil.move(file_path, file_path + ".bak")

    # Open the original (backup) file for reading and the new file for writing
    with open(file_path + ".bak", 'r') as input_file, open(file_path, 'w') as output_file:
        for line in input_file:
            # Perform specific replacements or transformations here
            if '!this.' in line:
                line = line.replace('!this.', 'not self.')
            if 'this.' in line:
                line = line.replace('this.', 'self.')
            output_file.write(line)
            output_file.flush()
    
    print("Writing ...")

if __name__ == '__main__':
    main(sys.argv)
