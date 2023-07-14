package org.example.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(tags = "", features = {"src/test/resources/features/*"}, glue = {"org/example/stepdefinations"}, plugin = {})
public class CucumberRunnerTests extends AbstractTestNGCucumberTests {


}
