package com.stepDef;

import com.utilities.BrowserUtils;
import com.utilities.ConfigurationReader;
import com.utilities.Driver;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class AdminLogin {
    @Then("I should see the Admin Login button")
    public void i_should_see_the_admin_login_button() {
        boolean displayed = Driver.getDriver().findElement(By.xpath("//button[.='Admin Login']")).isDisplayed();
        Assert.assertTrue(displayed);
    }
    @When("I click the Admin Login button")
    public void i_click_the_admin_login_button() {
        Driver.getDriver().findElement(By.xpath("//button[.='Admin Login']")).click();
    }
    @Then("I should be directed to the Admin Login page")
    public void i_should_be_directed_to_the_admin_login_page() {
        boolean login = Driver.getDriver().getCurrentUrl().contains("login");
        Assert.assertTrue(login);
    }
    @When("I enter my {string} and {string}")
    public void i_enter_my_and(String user, String pass) {
        user= ConfigurationReader.getProperty("username");
        pass=ConfigurationReader.getProperty("password");
        Driver.getDriver().findElement(By.xpath("//input[@name='username']")).
                sendKeys(user);
        Driver.getDriver().findElement(By.xpath("//input[@name='password']")).
                sendKeys(pass);

    }
    @When("I click the Login button")
    public void i_click_the_login_button() {
        BrowserUtils.waitFor(3);
        WebElement element = Driver.getDriver().findElement(By.xpath("//button[.='Login']"));
        BrowserUtils.waitFor(3);
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        js.executeScript("arguments[0].click();", element);
        BrowserUtils.waitFor(3);
        js.executeScript("arguments[0].click();", element);


    }
    @Then("I should see the Add and Logout buttons")
    public void i_should_see_the_add_and_logout_buttons() {
        BrowserUtils.waitFor(3);
        boolean add = Driver.getDriver().findElement(By.xpath("//button[.='Add']")).isDisplayed();
        boolean logout = Driver.getDriver().findElement(By.xpath("//button[.='Logout']")).isDisplayed();
        Assert.assertTrue(add);
        Assert.assertTrue(logout);
    }
    @When("I click the Logout button")
    public void i_click_the_logout_button() {
        Driver.getDriver().findElement(By.xpath("//button[.='Logout']")).click();
    }
    @Then("I should be logged out and see the Admin Login button again")
    public void i_should_be_logged_out_and_see_the_admin_login_button_again() {
        BrowserUtils.waitFor(3);
        boolean login = Driver.getDriver().findElement(By.xpath("//button[.='Admin Login']")).isDisplayed();
        Assert.assertTrue(login);
    }

}
