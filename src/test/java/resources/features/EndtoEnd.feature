Feature: End-to-End tests for Blog Post creation, reading, and deletion

  Scenario: Verify successful creation of a blog post through API
    Given I am an authenticated user
    When I perform a POST request to "/blogposts" with valid payload
    Then I expect the status code to be 200
    And I verify the response contains the id, title, content, and author

  Scenario: Verify the newly created blog post is visible on the UI
    Given I am on the main page
    When I see the "Welcome to my Blog" text
    And I click the "Home" link
    Then The post with "content" should be visible on the Blog Homepage

  Scenario: Delete the post through the API
    Given I am an authenticated user
    When I perform a DELETE request to "/blogposts/{id}" with valid post id
    Then I expect the status code to be 200

  Scenario: Verify the deleted blog post is not visible on the UI
    Given I am on the main page
    When I see the "Welcome to my Blog" text
    And I click the "Home" link
    Then The post with a specified id should not be found on the Blog Homepage