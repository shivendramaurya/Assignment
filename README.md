# Assignment

Description : Rest service which accepts file path and provide give the comman words present in all the files. This projcet is created from sample spring-boot project which can as standalone appication


To run the project from source code:
 Go to src/rest-service directory and run the command 'mvnw spring-boot:run'
 
To run the existing jar :
 java -jar rest-service-0.1.0.jar
 
To access the API use the following url :
http://localhost:8080/commanWords?filePath=<url_encoded_file_path>&filePath=<url_encoded_file path>&filePath=<url_encoded_file_path> ...

e.g. http://localhost:8080/commanWords?filePath=C%3A%5CTemp%5Cfile1.txt&filePath=C%3A%5CTemp%5Cfile2.txt&filePath=C%3A%5CTemp%5Cfile3.txt
 
I have tested the API with the below sameple file :
file1.txt
file2.txt
file3.txt

Further enhancements :

1. Optimise the Trie data struture so that we can reduce the space complexity.
2. Add more validation on the input file e.g. minimum 3 input files are required.
 



