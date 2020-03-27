package com.atsistemas.poc.cucumber.businessRules;

import cucumber.api.CucumberOptions;
import io.cucumber.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features/businessRules"
        , plugin = {"pretty", "html:target/cucumber"}
        , extraGlue = "com.atsistemas.poc.cucumber.shared"
)
public class BusinessRulesRunnerTests {

}
