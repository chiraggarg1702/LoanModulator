# Loan Modulator

Project Requirements
JDK 1.8
Maven
For Unit Tests:
Junit 4
Mockito
Compiling/Building and running the unit tests
Go to the project root folder and then run: ./bin/setup.sh

Runing the project
NOTE: Before running, please make sure you do the above setup step. Otherwise it will not run. The project can be run as follows in one of the two ways:

Using file based input:: It accepts a filename as a parameter at the command prompt and read the commands from that file.
./bin/loan_modulator.sh <input_filepath>
Example: ./bin/loan_modulator.sh ./data_file.txt
Using file based input:: This will run the program in the interactive shell mode where commands can be typed in.
./bin/loan_modulator.sh
