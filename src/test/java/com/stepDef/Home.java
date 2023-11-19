package com.stepDef;

import com.utilities.BrowserUtils;
import com.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Home {
    @Given("I am on the main page I see the {string} text")
    public void i_am_on_the_main_page_i_see_the_text(String welcome){
        String text = Driver.getDriver().findElement(By.xpath("//h1[contains(text(),'Welcome')]")).getText();
        Assert.assertEquals(welcome, text);

    }
        @When("I see the {string} link")
    public void i_see_the_link(String link) {
            String text = Driver.getDriver().findElement(By.xpath("//a[.='Home']//h1")).getText();
            Assert.assertEquals(link, text);
    }
    @Then("I click the {string} link")
    public void i_click_the_link(String string) {
        Driver.getDriver().findElement(By.xpath("//a[.='Home']")).click();
        BrowserUtils.waitFor(5);
    }
    @Then("I should see the posts in")
    public void i_should_see_the_posts_in() {
        WebElement element = Driver.getDriver().findElement(By.xpath("//a[.='Admin Login']"));
        Assert.assertTrue(element.isDisplayed());

    }


}
