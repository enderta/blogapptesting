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
    And The "Add" button is clicked
    Then The post with "title" and "body" is visible on the Blog Homepage
  @crud @ui
  Scenario: User Edit a Post
    Given A post already exists with a known "title"
    When The "Edit" button corresponding to the known post is clicked
    Then The user is directed to the Edit Post page
    When The user modifies the "title" and
    And The "Edit" button is clicked
    Then The updated "title" is visible on the Blog Homepage
  @crud @ui
  Scenario: User Delete a Post
    Given A post already exists with a known "title" and "body"
    When The "Delete" button corresponding to the known post is clicked
    Then The post with "title" is not found on the Blog Homepage
  @crud @ui
  Scenario: User Logout
    Then I should see the Add and Logout buttons
    When I click the Logout button
    Then I should be logged out and see the Admin Login button again