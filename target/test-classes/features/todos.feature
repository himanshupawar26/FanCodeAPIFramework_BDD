Feature: Validate todos completion for FanCode city users

  Scenario: Verify users from FanCode city have more than 50% todos completed
    Given the API is available
    When I fetch the user data from "/users"
    And I fetch the todos data from "/todos"
    Then all users from FanCode city should have more than 50% of their todos completed

