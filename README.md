# RestAssured Cucumber API Testing

## Overview
This project is a REST API testing framework built using RestAssured and Cucumber. It allows for easy testing of RESTful services with a focus on behavior-driven development (BDD).

## Features
- BDD-style testing with Cucumber
- API testing using RestAssured


## Install dependencies:
   ```bash
   mvn install
   ```

## Running Tests
To run the tests, you can use the following command:
```bash
mvn test
 ```

### Generate `allure-report` folder
`allure generate ./allure-results --clean`

### Show report
`allure serve allure-results` or `allure open`