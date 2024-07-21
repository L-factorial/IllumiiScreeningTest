Building the project: 
In the terminal cd to project main directory i.e. IllumioScreeningTest
run the command :  mvn clean package  

After you run the above command, you should see the target directory which will have a jar file IllumioScreeningTest-1.0-SNAPSHOT.jar
cd inside the target directory
Run the command : java -jar IllumioScreeningTest-1.0-SNAPSHOT.jar <EXACT_FILE_PATH_OF_INPUT_FILE> <EXACT_FILE_PATH_OF_PRE_DEFINED_DICTIONARY_WORDS>

Following is the sample command I ran locally (I have input.txt and dictionary.txt in the project but still provided the exact file path for both) 
java -jar IllumioScreeningTest-1.0-SNAPSHOT.jar /Users/prajwalrupakheti/projects/javaProjects/IllumioScreeningTest/input.txt /Users/prajwalrupakheti/projects/javaProjects/IllumioScreeningTest/dictionary.txt


The output looks like below : 

name                          : 2

ai                            : 1


