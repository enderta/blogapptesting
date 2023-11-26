Feature: End to End tests for Blog Post creation, reading and deletion

  Scenario: Verify successful creation, retrieval and deletion of a blog post through UI and API
    Given I am an authenticated user on the homepage
    When I click on 'Create New Blog Post'
    And fill in 'Title', 'Author', 'Content' and 'Image URL' with valid data
    And click 'Submit'
    Then a success message is displayed on the UI
    And the new Blog Post is visible on the homepage

    When I make a GET request to the API endpoint '/blogposts'
    Then I verify the status code is 200
    And the response includes the blog post with the given 'Title' and 'Author'

    When I click on 'Delete' for the blog post on the UI
    Then a success message is displayed on the UI
    And the Blog Post is no longer visible on the homepage

    When I make a GET request to the API endpoint '/blogposts'
    Then I verify the status code is 200
    And the response does not include the blog post with the given 'Title' and 'Author'