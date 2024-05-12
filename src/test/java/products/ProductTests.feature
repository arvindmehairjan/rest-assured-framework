Feature: Product Tests

  Scenario: Create a Product
    When I make a POST request to create the product
    Then the response status code should be 200
    And the product title should be "BMW"

  Scenario: Get Product Details
    When I make a GET request to retrieve the product details
    Then the response status code should be 200
    And the product title should be "iPhone 9"
    And the product price should be 549
    And the product discount percentage should be 12.96
