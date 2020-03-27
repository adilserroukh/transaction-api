package com.atsistemas.poc.cucumber.shared;

import com.atsistemas.generated.model.TransactionDto;
import com.atsistemas.poc.business.model.account.Account;
import com.atsistemas.poc.business.model.transaction.Transaction;
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
                    .amount(new BigDecimal(entry.get("amount")))
                    .fee(new BigDecimal(entry.get("fee")))
                    .referenceNumber(entry.get("reference"));
            return builder.create();
        }));

        registry.defineDataTableType(new DataTableType(TransactionDto.class, (TableEntryTransformer<TransactionDto>) entry -> {
            TransactionDto dto = new TransactionDto();
            dto.iban(entry.get("iban"));
            dto.amount(new BigDecimal(entry.get("amount")));
            dto.fee(new BigDecimal(entry.get("fee")));
            dto.reference(entry.get("reference"));
           return dto;
        }));


        registry.defineDataTableType(new DataTableType(TransactionData.class, (TableEntryTransformer<TransactionData>) entry -> {
            TransactionData transactionData = new TransactionData();
            transactionData.setAccountIban(entry.get("iban"));
            transactionData.setAmount(new BigDecimal(entry.get("amount")));
            transactionData.setFee(new BigDecimal(entry.get("fee")));
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