# Rest Assured API Test with Cucumber BDD Framework

# Requirements
- Java 8+
- Maven

# Project Setup
1. Clone the repository.
2. Run `mvn clean install` to install dependencies.
3. Run tests using `mvn test`.

# Scenario
- Verify that all users from the city `FanCode` have completed more than 50% of their todos.
- FanCode city is defined by latitude between `-40` and `5`, and longitude between `5` and `100`.

# Output
The test will output users who meet the criteria, and the results will be available in `target/cucumber-reports`.

# Structure
- **Feature Files**: Define the behavior using Gherkin syntax.
- **Step Definitions**: Map the feature steps to Java code.
- **Utilities**: Reusable methods to handle API requests and responses.

