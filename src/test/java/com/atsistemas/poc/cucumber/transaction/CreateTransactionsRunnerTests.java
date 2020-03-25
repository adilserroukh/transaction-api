package com.atsistemas.poc.cucumber.transaction;

import cucumber.api.CucumberOptions;
import io.cucumber.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features/CreateTransaction.feature",
        plugin = {"pretty", "html:target/cucumber"}
        , extraGlue = "com.atsistemas.poc.cucumber.shared"
)
public class CreateTransactionsRunnerTests {

}
