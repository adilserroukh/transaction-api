package com.atsistemas.poc.business.mapper;

import com.atsistemas.poc.business.model.transaction.Transaction;
import com.atsistemas.poc.persistence.model.TransactionData;

public class TransactionMapper {
    public static Transaction fromData(TransactionData from) {

        return Transaction.builder()
                .referenceNumber(from.getReferenceNumber())
                .iban(from.getReferenceNumber())
                .transactionDate(from.getDateTook())
                .amount(from.getAmount())
                .fee(from.getFee())
                .description(from.getDescription())
                .create();
    }
}
