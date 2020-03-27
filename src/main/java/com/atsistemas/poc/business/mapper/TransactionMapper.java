package com.atsistemas.poc.business.mapper;

import com.atsistemas.poc.business.model.transaction.Transaction;
import com.atsistemas.poc.persistence.model.TransactionData;

/**
 * Class to map objects Transaction
 */
public class TransactionMapper {

    private TransactionMapper() {

    }

    /**
     * Mapper Bean DataBase to Bean Business
     *
     * @param from Bean Database
     * @return
     */
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
