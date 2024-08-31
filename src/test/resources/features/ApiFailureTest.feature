Feature: Negative Test Cases for REST API

  Scenario: Attempt to get a non-existent object
    When the request to get an object with a non-existent ID is made
    Then a 404 Not Found response is returned

  Scenario: Attempt to delete a non-existent object
    When the request to delete an object with a non-existent ID is made
    Then a 404 Not Found response is returned