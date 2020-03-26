package com.atsistemas.poc.endPoints.mapper;

import com.atsistemas.generated.model.TransactionDto;
import com.atsistemas.poc.business.model.transaction.Transaction;

public class TransactionDtoMapper {
    public static TransactionDto toDto(Transaction from) {

        TransactionDto to = new TransactionDto();

        to.setReference(from.getReferenceNumber());
        to.setIban(from.getIban());
        to.setDate(from.getTransactionDate());
        to.setAmount(from.getAmount());
        to.setFee(from.getFee());
        to.description(from.getDescription());

        return to;
    }


    public static Transaction fromDto(TransactionDto from) {
        Transaction to = Transaction.builder()
                .referenceNumber(from.getReference())
                .iban(from.getIban())
                .transactionDate(from.getDate())
                .amount(from.getAmount())
                .fee(from.getFee())
                .description(from.getDescription())
                .create();

        return to;
    }


}
