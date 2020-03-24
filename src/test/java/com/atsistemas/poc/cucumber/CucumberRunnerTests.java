package com.atsistemas.poc.cucumber;

import cucumber.api.CucumberOptions;
import io.cucumber.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features/BusinessRules.feature", plugin = {"pretty", "html:target/cucumber"})
public class CucumberRunnerTests {

}
