Feature: Blogging System Administration

  Background:
    Given I am on the main page I see the "Welcome to my Blog" text
    And I click the "Home" link
    Then I should see the Admin Login button
    When I click the Admin Login button
    Then I should be directed to the Admin Login page
    When I enter my "username" and "password"
    And I click the Login button
    Then I should see the Add and Logout buttons

  @crud @ui
  Scenario: User Login and Create Post
    When The "Add" button is clicked
    Then The user is directed to the Add Post page
    When The user enters a valid "title","content" and "author" for the post
    And The "Submit" button is clicked
    Then The post with "author" and "content" is visible on the Blog Homepage


  Scenario: User Edit a Post
    Given A post already exists with a known "content"
    When The "Edit" button is clicked
    When The user modifies the "content2" and
    And The "Submit" button is clicked
    Then The updated "content2" is visible on the Blog Homepage

  @crud @ui
  Scenario: User Delete a Post
    Given A post already exists with a known "content"
    When The "Delete" button is clicked
    Then The post with "content" is not found on the Blog Homepage

