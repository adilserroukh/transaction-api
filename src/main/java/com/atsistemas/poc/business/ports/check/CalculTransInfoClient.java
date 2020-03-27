package com.atsistemas.poc.business.ports.check;

import com.atsistemas.poc.business.model.transaction.TransactionInfo;
import com.atsistemas.poc.persistence.model.TransactionData;

import java.time.LocalDateTime;

import static java.time.temporal.ChronoUnit.DAYS;

public class CalculTransInfoClient extends CalculTransInfo {
    @Override
    public TransactionInfo check(TransactionData tranData) {
        TransactionInfo.Builder builder = TransactionInfo.builder();
        builder.referenceNumber(tranData.getReferenceNumber());

        LocalDateTime transDate = tranData.getDateTook().truncatedTo(DAYS);
        LocalDateTime currentDate = LocalDateTime.now().truncatedTo(DAYS);

        if (transDate.isEqual(currentDate)) {
            builder.status(TransactionInfo.StatusTransaction.PENDING);
            builder.amount(tranData.getAmount().subtract(tranData.getFee()));

        } else {
            if (transDate.isBefore(currentDate)) {

                builder.amount(tranData.getAmount().subtract(tranData.getFee()));
                builder.status(TransactionInfo.StatusTransaction.SETTLED);


            }
        }
        return builder.create();
    }
}