Feature: Get an object and handle 500 error

  Scenario: Getting an object returns a 500 Internal Server Error
    Given the API is set up to return a 500 error on GET
    When I send a GET request for an object with id "123"
    Then I should receive a 500 Internal Server Error

