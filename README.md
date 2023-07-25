# scrabblePointsService
1. Instructions to run in local instance
  > Create DB in MySQL: "create database scrabblePointsService;"
  > update userName and password of DB in application.properties which is in project resource classpath
  > To Run From IDE: Go to com.example.demo.scrabblePointsService.class and run as Java Application
  > To start application from Maven: 1. After cloning the repo, go to the project folder 2. run "mvn install" to build the application 3. once build is success, go to the target folder of the project and run the command "java -jar scrabblePointsService-0.0.1-SNAPSHOT.jar"
2. Used MySQL as the database
3. Added logs for each trations using Logger
4. Used Exception Handling for Errors
5. Covered Junit for Controller, Service and Repository classes
6. Attached postman collections for the API's in E-Mail
