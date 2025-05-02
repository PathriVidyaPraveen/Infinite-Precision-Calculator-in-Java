# CS1023-SDF-Java-Project
This is a GitHub repo of Infinite Precision Calculator in Java. The main goal of this project is to create an infinite or arbitrary precision Java calculator. It can handle both infinite length integers or floating point numbers and their corresponding arithmetic operations. Refer to project_report.pdf for more details.

**Used Tools**
1) Git Commands for commits and tags
2) Dockerfile and Docker image uploaded to Dockerhub
3) MyInfArith.java for running the package
4) .jar file created
5) Python script automation for running the project
6) Ant build system for running the project

**Git workflow**
1) git init : Initializes a new Git repository in your current folder.It creates a hidden .git folder that holds all version history.
2) git branch -M main : Renames the current branch to main (the standard default branch name).-M forces the rename even if a main branch already exists.
3) git remote add origin <link> : Connects your local Git repo to a remote repository on GitHub.origin is the nickname for the GitHub link.
4) git add . : Stages all changed files for the next commit.The . means “everything in this folder.
5) git commit -m "Message" : Creates a commit, which is a snapshot of your code.The message should explain the purpose of the changes.
6) git push origin main : Pushes your local commits to the main branch on GitHub (remote).
7) git tag -a <version_of_release> -m "Message" : Adds a tag to the current commit with a version and a message.Useful for version releases.
8) git push --tags : Pushes your tags (like version releases) to GitHub.
9) git log --oneline --graph --decorate : Shows a visual and compact log of your commit history.
10) git pull origin main --rebase : Fetches the latest changes from the remote main and rebases your local changes on top of them.It avoids messy merge commits and keeps history clean.

**JAR File**  

A JAR (Java ARchive) file is a packaged and compressed collection of .class files, metadata, and resources (like images or text files), bundled into a single file.It’s like a ZIP file for Java programs — useful for running, sharing, or deploying Java applications.  

1) javac arbitraryarithmetic/AInteger.java arbitraryarithmetic/AFloat.java
   
Compile the java files  

2) jar cf arbitraryarithmetic/aarithmetic.jar -C arbitraryarithmetic AInteger.class AFloat.class

Creates a JAR file by packaging the class files  

3) To use the JAR in another Java file (like MyInfArith.java), compile and run like this:
   javac -cp .:arbitraryarithmetic/aarithmetic.jar MyInfArith.java
   java -cp .:arbitraryarithmetic/aarithmetic.jar MyInfArith int add 123 456

**MyInfArith.java**  
1) This is a Java file for importing and running both the arbitraryarithemtic/AInteger.java and arbitraryarithmetic/AFloat.java by taking the command line arguments and then outputting the result by running in CLI.
2) Compilation : javac MyInfArith.java
3) Running the file:
   java MyInfArith <int/float> <add/sub/mul/div> operand1 operand2

**Python script automation**  
1) script_java_project.py is a python script that is used for running the AInteger or AFloat methods.
2) Usage : python3 script_java_project.py <int/float> <add/sub/mul/div> operand1 operand2









