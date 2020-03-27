package com.atsistemas.poc.cucumber.transaction;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/test/resources/features/transaction"},
        plugin = {"pretty", "html:target/cucumber/CreateTransactions"}
        , extraGlue = "com.atsistemas.poc.cucumber.shared"
)
public class CreateTransactionsRunnerTests {

}
