# JUnit 5 - allure

Not so traditional framework, which uses Allure Test as BDD and JUnit 5 for parallel running and assertions.

1. Run the server/server.js
2. Run the feature by pressing the run button (cli run later on)

## Commands

To run the mocked server:

```
$ node server/server.js
```

To run the tests:

```
$ mvn test
```

Select the proper test runner or the feature file and run by clicking on the run the button

To run the report:

```
$ allure serve
```

## Technologies:

Java 17/Maven
Spring Test - Api request handling
Selenium - later on
JUnit5 - (parallel) test running
Allure - BDD/report generator
AssertJ - for assertion

## License

MIT