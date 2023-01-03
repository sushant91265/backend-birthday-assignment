### Note: Please read the README.md for the problem statement and for the format of the input json file.

### Pre-requisites
- Java 17 / Java 8
- Junit 4
- Maven 3+
- Mac or Linux OS with Java configured


### How to run the application
- Run from 'src/main/java/com/saltpay/App.java' => `javac App.java` and then `java App <input_file_name.json>`
- OR
- Run from root directory => `mvn clean install` and then `java -jar target/optimizer-1.0.jar <input_file_name.json>`


### Assumptions
- Assuming that there will be no garbage data in the input json file.
- Assuming that the date format remains same.
- Assuming that the order of strings in the input json file remains same.


### Structure of the application
- The application is divided into 3 packages
    - `com.saltpay` - contains the main class App.java
    - `com.saltpay.model` - contains the model classes
    - `com.saltpay.service` - contains the service class
    - `com.saltpay.util` - contains the utility class
- All the classes and methods have been documented with their purpose.


### Test cases
- The test cases are present in the `src/test/java` directory.
- `BirthdayFinderTest.java` - contains the test cases for the service class
- The input test files are present in the `src/test/resources` directory, the test function names are same as input test files names.


### Improvements
- The application can be improved by adding more test cases.
- The application can be improved by adding more validations.
- We can collect the names of people whose birthday is today during the parsing phase for streaming application.