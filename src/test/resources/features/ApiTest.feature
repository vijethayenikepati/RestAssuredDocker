Feature: Item Management

  Scenario: Verify an item can be created using data tables
    Given the following item details:
      | name                   | cpu_model      | price  | year | hardDiskSize|
      | Apple MacBook Pro 15   | Intel Core i8  | 1949.99| 2019 | 256GB       |
    When the request to add the item is made with 200 status code
    And the following item is created:
      | name                   | cpu_model      | price  | year | hardDiskSize|
      | Apple MacBook Pro 15   | Intel Core i8  | 1949.99| 2019 | 256GB       |

  Scenario: Verify an item can be retrieved by ID
    Given an existing item ID from creation
    When the request to retrieve the item is made with 200 status code
    And the item retrieved should have the following details:
      | name                   | cpu_model      | price  | year | hardDiskSize|
      | Apple MacBook Pro 15   | Intel Core i8  | 1949.99| 2019 | 256GB       |
    And the response should match the JSON schema

  Scenario: Verify the list of items can be retrieved and contains the created item
    When the request to retrieve the list of items is made and a 200 response code is returned
    Then the list of items should not be empty

  Scenario: Verify an item can be deleted by ID
    When the request to delete the item is made with 200 status code
    And the item should no longer be retrievable