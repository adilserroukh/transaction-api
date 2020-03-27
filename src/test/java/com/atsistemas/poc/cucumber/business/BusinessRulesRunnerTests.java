package com.atsistemas.poc.cucumber.businessRules;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features/business"
        , plugin = {"pretty", "html:target/cucumber/BusinessRules"}
        , extraGlue = "com.atsistemas.poc.cucumber.shared"
)
public class BusinessRulesRunnerTests {

}
