package com.stepDef;

import com.utilities.BrowserUtils;
import com.utilities.ConfigurationReader;
import com.utilities.Driver;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class CrudUI {
    @When("The {string} button is clicked")
    public void the_button_is_clicked(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("The user is directed to the Add Post page")
    public void the_user_is_directed_to_the_add_post_page() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @When("The user enters a valid {string},{string} and {string} for the post")
    public void the_user_enters_a_valid_and_for_the_post(String string, String string2, String string3) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("The post with {string} and {string} is visible on the Blog Homepage")
    public void the_post_with_and_is_visible_on_the_blog_homepage(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Given("A post already exists with a known {string}")
    public void a_post_already_exists_with_a_known(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @When("The {string} button corresponding to the known post is clicked")
    public void the_button_corresponding_to_the_known_post_is_clicked(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("The user is directed to the Edit Post page")
    public void the_user_is_directed_to_the_edit_post_page() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @When("The user modifies the {string} and")
    public void the_user_modifies_the_and(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("The updated {string} is visible on the Blog Homepage")
    public void the_updated_is_visible_on_the_blog_homepage(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Given("A post already exists with a known {string} and {string}")
    public void a_post_already_exists_with_a_known_and(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("The post with {string} is not found on the Blog Homepage")
    public void the_post_with_is_not_found_on_the_blog_homepage(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

}
