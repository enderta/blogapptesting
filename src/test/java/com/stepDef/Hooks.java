package com.stepDef;


import com.utilities.BrowserUtils;
import com.utilities.ConfigurationReader;
import com.utilities.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.restassured.RestAssured;
import org.openqa.selenium.*;


public class Hooks {

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
        //wait for response

    }

    @After("@ui")
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            final byte[] screenShot = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenShot, "image/png", "screenshot");
        }

        Driver.closeDriver();


    }
}