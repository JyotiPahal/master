package com.example.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
    features = "src/test/resources/features", // Path of feature files
    glue = "com.example.stepdefinitions",       // Package of step definitions 
    plugin = {
        "pretty",                                // Prints Gherkin steps in a nice format
        "html:target/cucumber-reports/cucumber-pretty.html", // Generates HTML report
        "json:target/cucumber-reports/CucumberTestReport.json", // Generates JSON report
        "rerun:target/rerun.txt" // Creates a file with failed scenarios for re-run
    },
    monochrome = true, // Readable console output
    tags = "" // Optional: To run specific scenarios/features (e.g., "@Smoke" or "not @Ignore")
    // dryRun = true // Optional: Checks if all Gherkin steps have corresponding step definitions without actually running the tests
)
public class TestRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = true) // Runs scenarios in parallel
    public Object[][] scenarios() {
        return super.scenarios();
    }
} 