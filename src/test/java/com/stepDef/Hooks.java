package com.stepDef;

import com.google.gson.JsonObject;
import com.utilities.BrowserUtils;
import com.utilities.ConfigurationReader;
import com.utilities.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.openqa.selenium.*;

import java.util.List;

import static io.restassured.RestAssured.given;

public class Hooks {
    protected static String tkn;
    protected static String id;
    protected static int numberofPosts;
    protected static Response response;

    @Before("@db")
    public void dbHook() {
        System.out.println("creating database connection");
        //BrowserUtils.createConnection();
    }

    @After("@db")
    public void afterDbHook() {
        System.out.println("closing database connection");
        //BrowserUtils.destroyConnection();

    }

    @Before("@ui")
    public void setUp() {
        // we put a logic that should apply to every scenario
        BrowserUtils.waitFor(5);

        // for example: setting up driver, maximizing browser, setting up implicit wait
        Driver.getDriver().get(ConfigurationReader.getProperty("url"));

        Driver.getDriver().navigate().refresh();


    }

    @Before("@api")
    public void setUpApi() throws InterruptedException {
        //restasured base uri
        RestAssured.baseURI = ConfigurationReader.getProperty("base_url");
    }

    @After("@ui")
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            final byte[] screenShot = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenShot, "image/png", "screenshot");
        }
        BrowserUtils.waitFor(3);
        // Driver.getDriver().findElement(By.xpath("//button[.='Logout']")).click();
        System.out.println("logouted");
        Driver.closeDriver();

    }

    @After("@api and @ui")
    public void tearDownAfterScenario() {
        // Reset the tkn, id and numberofPosts variables after each scenario
        tkn = null;
        id = null;
        numberofPosts = 0;
    }

    // Rest of your hooks implementation...
}
