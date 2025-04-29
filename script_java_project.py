import subprocess
import sys
import os

if len(sys.argv) != 5:
    print("Usage: python run_myinfarith.py <int/float> <add/sub/mul/div> <num1> <num2>")
    sys.exit(1)


working_dir = os.path.dirname(os.path.abspath(__file__))
path = os.path.join(working_dir, "arbitraryarithmetic")


compile_cmd = [
    "javac",
    os.path.join(path, "AInteger.java"),
    os.path.join(path, "AFloat.java"),
    os.path.join(working_dir, "MyInfArith.java")
]

try:
    subprocess.run(compile_cmd, check=True)
except subprocess.CalledProcessError:
    print("Compilation failed.")
    sys.exit(1)


run_cmd = [
    "java",
    "-cp", working_dir,
    "MyInfArith",
    sys.argv[1], sys.argv[2], sys.argv[3], sys.argv[4]
]

try:
    subprocess.run(run_cmd, check=True)
except subprocess.CalledProcessError:
    print("Execution failed.")
    sys.exit(1)
