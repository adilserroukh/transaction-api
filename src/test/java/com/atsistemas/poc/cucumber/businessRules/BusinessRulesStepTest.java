package com.atsistemas.poc.cucumber.businessRules;

import com.atsistemas.generated.model.StatusTransactionResponseDto;
import com.atsistemas.poc.business.model.account.Account;
import com.atsistemas.poc.cucumber.Step;
import com.atsistemas.poc.cucumber.util.LocalDateTimeDeserializer;
import com.atsistemas.poc.persistence.model.AccountData;
import com.atsistemas.poc.persistence.service.AccountService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.destination.DataSourceDestination;
import com.ninja_squad.dbsetup.operation.Operation;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java8.En;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.LocalServerPort;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.List;

import static com.ninja_squad.dbsetup.Operations.deleteAllFrom;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.equalToObject;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Step
public class BusinessRulesStepTest implements En {

    private static final String APPLICATION_JSON_CONTENT_TYPE = "application/json";
    private static final String APPLICATIONS_WS_ROOT_STATUS = "/transaction/status";
    private static final String APPLICATIONS_WS_ROOT_CREATE = "/transaction/receive";
    private static final int TEST_PORT = 8080;
    @LocalServerPort
    private int port;

    @Autowired
    private AccountService accountService;

    @Autowired
    private DataSource dataSource;


    private RequestSpecification api;
    private ValidatableResponse response;

    private DecimalFormat formatAmoutExpected = new DecimalFormat("#.##");


    public BusinessRulesStepTest() {
        Given("delete all Accounts", () -> {
            deleteAll("ACCOUNT");
        });

        Given("delete all Transactions", () -> {
            deleteAll("TRANSACTION");
        });

        Given("create account user", (DataTable dataTable) -> {

            List<Account> accounts = dataTable.asList(Account.class);
            accounts.stream()
                    //.map(account -> AccountMapper.INSTANCE.fromAccount(account))
                    .forEach(account -> {
                        AccountData accountData = new AccountData();
                        accountData.setIban(account.getIban());
                        accountData.setAmount(BigDecimal.valueOf(account.getAmount()));
                        accountService.create(accountData);
                    });
        });

        Given("A transaction that is not stored in our system", () -> {
            this.deleteAll("TRANSACTION");
        });

        Given("A transaction that is stored in our system:", (String transaction) -> {

            api
                    .contentType(APPLICATION_JSON_CONTENT_TYPE)
                    .body(transaction)
                    .when()
                    .post(APPLICATIONS_WS_ROOT_CREATE)
                    .then();

        });

        When("I check the status from any channel:", (String request) ->

        {
            response = api
                    .contentType(APPLICATION_JSON_CONTENT_TYPE)
                    .body(request)
                    .when()
                    .get(APPLICATIONS_WS_ROOT_STATUS)
                    .then();
        });

        Then("The system returns the status:", (String responseExpectedJson) ->

        {


            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.serializeNulls();
            gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer());
            Gson gson = gsonBuilder.create();

            StatusTransactionResponseDto responseExpected = gson.fromJson(responseExpectedJson, StatusTransactionResponseDto.class);

            response.statusCode(200);

            //gson.fromJson(response.extract().body().jsonPath().toString(), StatusTransactionResponseDto.class);
            JsonPath jsonCurrent = response.extract().body().jsonPath();
            assertEquals(responseExpected.getReference(), jsonCurrent.get("reference"));
            assertEquals(responseExpected.getStatus().toString(), jsonCurrent.get("status").toString());
            assertEquals(responseExpected.getAmount() != null ? responseExpected.getAmount().toString() : null, jsonCurrent.get("amount") != null ? jsonCurrent.get("amount").toString() : null);
            assertEquals(responseExpected.getFee() != null ? responseExpected.getFee().toString() : null, jsonCurrent.get("fee") != null ? jsonCurrent.get("fee").toString() : null);


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
        api = given().port(port)
                .basePath("/api");
    }


}