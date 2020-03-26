package com.atsistemas.poc.cucumber.shared;

import com.atsistemas.poc.business.model.Account;
import com.atsistemas.poc.business.model.Transaction;
import com.atsistemas.poc.persistence.model.TransactionData;
import io.cucumber.core.api.TypeRegistry;
import io.cucumber.core.api.TypeRegistryConfigurer;
import io.cucumber.datatable.DataTableType;
import io.cucumber.datatable.TableEntryTransformer;

import java.math.BigDecimal;
import java.util.Locale;

public class TypeConfigurer implements TypeRegistryConfigurer {

    @Override
    public Locale locale() {
        return Locale.FRENCH;
    }

    @Override
    public void configureTypeRegistry(TypeRegistry registry) {

        registry.defineDataTableType(new DataTableType(Transaction.class, (TableEntryTransformer<Transaction>) entry -> {
            Transaction.Builder builder = Transaction.builder();
            builder.iban(entry.get("iban"))
                    .amount(new BigDecimal(entry.get("amount")));
            ;
            return builder.create();
        }));


        registry.defineDataTableType(new DataTableType(TransactionData.class, (TableEntryTransformer<TransactionData>) entry -> {
            TransactionData transactionData = new TransactionData();
            transactionData.setAccountIban(entry.get("iban"));
            transactionData.setAmount(new BigDecimal(entry.get("amount")));
            return transactionData;
        }));

        registry.defineDataTableType(new DataTableType(Account.class, (TableEntryTransformer<Account>) entry -> {
            Account.Builder builder = Account.builder();
            builder.iban(entry.get("iban"))
                    .amount(Long.parseLong(entry.get("amount")))
            ;
            return builder.create();
        }));


    }
}