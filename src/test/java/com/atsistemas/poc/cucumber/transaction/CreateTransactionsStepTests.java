package com.atsistemas.poc.cucumber.transaction;


import com.atsistemas.generated.model.TransactionDto;
import com.atsistemas.poc.business.model.account.Account;
import com.atsistemas.poc.cucumber.Step;
import com.atsistemas.poc.cucumber.util.LocalDateTimeDeserializer;
import com.atsistemas.poc.persistence.model.AccountData;
import com.atsistemas.poc.persistence.model.TransactionData;
import com.atsistemas.poc.persistence.service.AccountService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.destination.DataSourceDestination;
import com.ninja_squad.dbsetup.operation.Insert;
import com.ninja_squad.dbsetup.operation.Operation;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java8.En;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.LocalServerPort;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.ninja_squad.dbsetup.Operations.deleteAllFrom;
import static com.ninja_squad.dbsetup.Operations.sequenceOf;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@Step
public class CreateTransactionsStepTests implements En {

    @Autowired
    private DataSource dataSource;


    @Autowired
    private AccountService accountService;

    @LocalServerPort
    private int port;

    private RequestSpecification api;
    private ValidatableResponse response;


    private static final String APPLICATION_JSON_CONTENT_TYPE = "application/json";
    private static final String APPLICATIONS_WS_ROOT = "/transaction/receive";


    public CreateTransactionsStepTests() {
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

        When("receive the transaction", (DataTable dataTable) -> {
            List<TransactionDto> transactions = dataTable.asList(TransactionDto.class);

            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.serializeNulls();
            Gson gson = gsonBuilder.create();

            transactions.stream()
                    .forEach(tra -> {
                        response = api
                                .contentType(APPLICATION_JSON_CONTENT_TYPE)
                                .body("{\"date\":\"2019-07-16T16:55:42.000\",\"fee\":" + tra.getFee() + ",\"iban\":\"ES9820385778983000760236\",\"reference\":\"\",\"amount\":" + tra.getAmount() + ",\"description\":\"\"}")
                                .when()
                                .post(APPLICATIONS_WS_ROOT)
                                .then();
                    });
        });

        Then("The system returns the status {string}", (String string) -> {

        });

        Then("account user", (DataTable dataTable) -> {
            List<String> expecteds = new ArrayList<>();
            final ArrayList<String> expectedResultats = new ArrayList<>();
            List<String> columns = dataTable.asLists().get(0);
            for (List<String> row : dataTable.asLists().subList(1, columns.size())) {
                expecteds.add(StringUtils.join(row, "|"));
            }

            List<String> currents = selectAll("account", dataTable);


            assertThat(expecteds).containsExactlyElementsOf(currents);
        });

        Then("the JSON response should have:", (String docString) -> {

            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.serializeNulls();
            gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer());
            Gson gson = gsonBuilder.create();

            TransactionData response = gson.fromJson(docString, TransactionData.class);


        });

    }

    void deleteAll(final String tableName) {
        this.apply(deleteAllFrom(tableName));
    }

    void apply(final Operation operation) {
        new DbSetup(new DataSourceDestination(dataSource), operation).launch();
    }

    List<String> selectAll(final String tableName,
                           final DataTable data) throws Exception {

        final List<String> resultats = new ArrayList<>();
        final List<String> columns = data.asLists().get(0);
        final StringBuilder queryBuilder = new StringBuilder()
                .append(String.format("SELECT %s FROM %s ", StringUtils.join(columns, ","), tableName));
        try (Connection conn = dataSource.getConnection()) {

            PreparedStatement stmt = conn.prepareStatement(queryBuilder.toString());
            final ResultSet rs = stmt.executeQuery();

            int j = 0;
            List<String> resul;

            while (rs.next()) {
                resul = new ArrayList<>();
                for (int i = 0; i < columns.size(); i++) {
                    resul.add(i, rs.getString(i + 1));
                }
                resultats.add(StringUtils.join(resul, "|"));
                j++;
            }

        } catch (SQLException exp) {
            throw new Exception(exp.getMessage());
        }
        return resultats;
    }

    void insert(final String tableName, final DataTable data) {

        final List<String> rows = data.asList();
        final String columns = rows.get(0);

        final List<Operation> operations = new ArrayList<>();

        int i = 1;
        for (String row : rows.subList(1, rows.size())) {
            final Insert.Builder builder = Insert.into(tableName);
            builder.columns(columns);
            builder.values(rows.get(i));
            operations.add(builder.build());
            i++;
        }

        this.apply(sequenceOf(operations));
    }


    @Before
    public void setUp() {
        api = given().port(port)
                .basePath("/api");
    }

}
