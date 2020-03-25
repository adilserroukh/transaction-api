package com.atsistemas.poc.cucumber.businessRules;

import com.atsistemas.poc.cucumber.Step;
import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.destination.DataSourceDestination;
import com.ninja_squad.dbsetup.operation.Operation;
import io.cucumber.java.Before;
import io.cucumber.java8.En;
import io.restassured.specification.RequestSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import javax.sql.DataSource;

import static com.ninja_squad.dbsetup.Operations.deleteAllFrom;
import static io.restassured.RestAssured.given;

@Step
public class BusinessRulesStepTest implements En {

    private static final String APPLICATION_JSON_CONTENT_TYPE = "application/json";
    private static final int TEST_PORT = 8080;
    @LocalServerPort
    private int port;


    @Autowired
    private DataSource dataSource;

    @Autowired
    private MockMvc mvc;

    private RequestSpecification api;


    public BusinessRulesStepTest() {
        Given("A transaction that is not stored in our system", () -> {

            this.deleteAll("TRANSACTION");
            mvc.perform(get("/transaction/1")).andReturn();

           // api.get("/transaction/1")
           //         .then()
           //         .statusCode(200);
        });

        When("I check the status from any channel", () -> {
        });

        Then("The system returns the status {string}", (String string) -> {
        });
    }


    void deleteAll(final String tableName) {
        this.apply(deleteAllFrom(tableName));
    }

    void apply(final Operation operation) {
        new DbSetup(new DataSourceDestination(dataSource), operation).launch();
    }

    @Before
    public void setUp() {
        api = given().port(TEST_PORT)
                .basePath("/api");
    }
}