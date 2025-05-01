# Import the subprocess module to run system commands like javac and java
import subprocess
# Import sys to access command-line arguments and exit the script on errors
import sys
# Import os to work with file paths in a platform-independent way
import os

# Ensure the script receives exactly 4 arguments (excluding the script name)
if len(sys.argv) != 5:
    # Print usage instructions if the number of arguments is incorrect and exit the script
    print("Usage: python run_myinfarith.py <int/float> <add/sub/mul/div> <num1> <num2>")
    sys.exit(1)

# Get the absolute path of the directory containing this script
working_dir = os.path.dirname(os.path.abspath(__file__))
# Build the path to the "arbitraryarithmetic" directory containing AInteger and AFloat
path = os.path.join(working_dir, "arbitraryarithmetic")

# Create the javac command to compile the required Java source files
compile_cmd = [
    "javac",
    os.path.join(path, "AInteger.java"),              # Path to AInteger.java
    os.path.join(path, "AFloat.java"),                # Path to AFloat.java
    os.path.join(working_dir, "MyInfArith.java")      # Path to the main class MyInfArith.java
]

# Try compiling the Java files
try:
    subprocess.run(compile_cmd, check=True)           # Run the compile command and raise an error if it fails
except subprocess.CalledProcessError:
    print("Compilation failed.")                      # Print error message on compilation failure
    sys.exit(1)                                       # Exit with error

# Create the java command to run the compiled program with user arguments
run_cmd = [
    "java",
    "-cp", working_dir,                               # Set classpath to the working directory
    "MyInfArith",                                     # The main class to run
    sys.argv[1], sys.argv[2], sys.argv[3], sys.argv[4] # Pass the 4 command-line arguments to the Java program
]

# Try executing the Java program
try:
    subprocess.run(run_cmd, check=True)               # Run the Java program and raise an error if it fails
except subprocess.CalledProcessError:
    print("Execution failed.")                        # Print error message on runtime failure
    sys.exit(1)                                       # Exit with error
