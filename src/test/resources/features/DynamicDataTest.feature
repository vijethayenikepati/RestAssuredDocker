Feature: Verify Dynamic Data

  Scenario: Verify an item can be retrieved by ID
    When the request to retrieve is made with 200 status code
    And the response should be verified dynamically

  Scenario: Verify the list of items can be retrieved and verified
    When the request to retrieve the list of items is made with a 200 response code

  Scenario: Verify the list of items can be retrieved and verified without using POJO
    When the request to retrieve the list of items without pojo is made with a 200 response code

