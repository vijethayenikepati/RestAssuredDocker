package com.api.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"@target/failedrerun.txt"},
        glue = "com.api.stepdefinitions",
        plugin = {"pretty", "html:target/cucumber-reports.html","json:target/cucumber.json",
                "rerun:target/failedrerun.txt"},
        monochrome = true
)
public class FailedRunner {

}
