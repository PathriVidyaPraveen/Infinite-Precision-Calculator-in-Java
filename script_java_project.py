import subprocess # run system commands like javac and java
import sys # access command line arguments and exit the script on error
import os # useful to work with file paths in a platfoorm independent way

# Enchecks whether number of arguments is 4
if len(sys.argv) != 5:
    print("Invalid commands !!")
    sys.exit(1)

# Get the absolute path of the directory containing this script and build a path to arbitraryarithmetic directory
working_dir = os.path.dirname(os.path.abspath(__file__))
path = os.path.join(working_dir, "arbitraryarithmetic")

# Create the javac command to compile the required Java source files
compile_cmd = [
    "javac",
    os.path.join(path, "AInteger.java"),            
    os.path.join(path, "AFloat.java"),               
    os.path.join(working_dir, "MyInfArith.java")   
]

# Compiles the java files
try:
    subprocess.run(compile_cmd, check=True)           
except subprocess.CalledProcessError:
    print("Compilation failed.")                      
    sys.exit(1)                                       

# Create the java command to run the compiled program with user arguments
run_cmd = [
    "java",
    "-cp", working_dir,                              
    "MyInfArith",                                     
    sys.argv[1], sys.argv[2], sys.argv[3], sys.argv[4] 
]

# execute the java command
try:
    subprocess.run(run_cmd, check=True)               
except subprocess.CalledProcessError:
    print("Execution failed.")                        
    sys.exit(1)                                       
