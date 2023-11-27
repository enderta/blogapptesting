Feature: API tests for Graphql Blog, User and Authentication services

  @api @create
  Scenario: Verify creation of a blog post
    Given I am an authenticated user
    When I perform a POST request to "/blogposts" with valid payload
    Then I expect the status code to be 200
    And I verify the response contains the id, title, content and author

  Scenario: Verify deletion of a blog post
    Given I am an authenticated user
    And a blog post with id "1" exists
    When I perform a DELETE request to "/blogposts/1"
    Then I expect the status code to be 204

  Scenario: Verify updating a blog post
    Given I am an authenticated user
    And a blog post with id "1" exists
    When I perform a PUT request to "/blogposts/1" with valid payload
    Then I expect the status code to be 200
    And I verify that the response reflects the changes

  Scenario: Login as an existing user
    Given I have an account with username "tester" and password "password"
    When I perform a POST request to "/login" with username "tester" and password "password"
    Then I expect the status code to be 200
    And I verify the response contains the token and user id

  Scenario: Create a comment
    Given I am an authenticated user
    And a blog post with id "1" exists
    When I perform a POST request to "/comments" with valid payload
    Then I expect the status code to be 200
    And I verify the response contains the id, post_id and content