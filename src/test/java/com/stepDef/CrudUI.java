package com.stepDef;

import com.utilities.BrowserUtils;
import com.utilities.ConfigurationReader;
import com.utilities.Driver;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class CrudUI {
    @When("The {string} button is clicked")
    public void the_button_is_clicked(String add) {
        BrowserUtils.waitFor(5);
        String addXpath = "//*[.='" + add + "']";
        WebElement addElement = Driver.getDriver().findElement(By.xpath(addXpath));
        addElement.click();
        BrowserUtils.waitFor(5);
    }
    @Then("The user is directed to the Add Post page")
    public void the_user_is_directed_to_the_add_post_page() {
        String expected = "Add Post";
        String actual = Driver.getDriver().findElement(By.xpath("//h1[contains(text(),'Add Post')]")).getText();
        Assert.assertEquals(expected, actual);
    }
    @When("The user enters a valid {string},{string} and {string} for the post")
    public void the_user_enters_a_valid_and_for_the_post(String title, String author, String content) {
        String titleXpath = "//*[@name='title']";
        String authorXpath = "//*[@name='author']";
        String contentXpath = "//*[@name='content']";
        WebElement titleElement = Driver.getDriver().findElement(By.xpath(titleXpath));
        WebElement authorElement = Driver.getDriver().findElement(By.xpath(authorXpath));
        WebElement contentElement = Driver.getDriver().findElement(By.xpath(contentXpath));
        titleElement.sendKeys(title);
        authorElement.sendKeys(author);
        contentElement.sendKeys(content);
        BrowserUtils.waitFor(5);



    }
    @Then("The post with {string} and {string} is visible on the Blog Homepage")
    public void the_post_with_and_is_visible_on_the_blog_homepage(String author, String content) {
        Driver.getDriver().navigate().refresh();
        BrowserUtils.waitFor(5);
        System.out.println("author = " + author);
        System.out.println("content = " + content);
        WebElement element = Driver.getDriver().findElement(By.xpath("(//div[@class='card-body'])[1]//p[2]"));
        String actualAuthor = element.getText();
        System.out.println(actualAuthor);
        Assert.assertTrue(actualAuthor.contains(content));


    }
    @Given("A post already exists with a known {string}")
    public void a_post_already_exists_with_a_known(String content) {
        WebElement element = Driver.getDriver().findElement(By.xpath("(//div[@class='card-body'])[1]//p[2]"));
        String actualAuthor = element.getText();
        System.out.println(actualAuthor);
        Assert.assertTrue(actualAuthor.contains(content));
    }
    @When("The {string} button corresponding to the known post is clicked")
    public void the_button_corresponding_to_the_known_post_is_clicked(String string) {

    }
    @Then("The user is directed to the Edit Post page")
    public void the_user_is_directed_to_the_edit_post_page() {
    }
    @When("The user modifies the {string} and")
    public void the_user_modifies_the_and(String content) {
        WebElement element = Driver.getDriver().findElement(By.xpath("//input[@name='author']"));
        element.clear();
        element.sendKeys(content);
        BrowserUtils.waitFor(5);
    }
    @Then("The updated {string} is visible on the Blog Homepage")
    public void the_updated_is_visible_on_the_blog_homepage(String content) {
        WebElement element = Driver.getDriver().findElement(By.xpath("(//div[@class='card-body'])[1]//p[2]"));
        String actualAuthor = element.getText();
        System.out.println(actualAuthor);
        Assert.assertTrue(actualAuthor.contains(content));
    }
    @Given("A post already exists with a known {string} and {string}")
    public void a_post_already_exists_with_a_known_and(String string, String string2) {
        
    }

    @Then("The post with {string} is not found on the Blog Homepage")
    public void the_post_with_is_not_found_on_the_blog_homepage(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

}
