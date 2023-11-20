package com.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"html:target/cucumber-report.html",
                "json:target/cucumber.json",
                "rerun:target/rerun.txt",
                "pretty"
        },
        glue = {"com.stepDef"},
        features = {"src/test/java/resources/features"},
        dryRun = true,
        tags = "@ui and @admin"
)
public class CukesRunner {
}