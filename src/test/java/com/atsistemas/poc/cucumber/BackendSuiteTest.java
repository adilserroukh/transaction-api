package com.atsistemas.poc.cucumber;

import com.atsistemas.poc.cucumber.businessRules.BusinessRulesRunnerTests;
import com.atsistemas.poc.cucumber.transaction.CreateTransactionsRunnerTests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        CreateTransactionsRunnerTests.class,
        BusinessRulesRunnerTests.class
})
public class BackendSuiteTest {
}
