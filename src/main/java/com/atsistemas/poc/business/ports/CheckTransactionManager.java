package com.atsistemas.poc.business.ports;

import com.atsistemas.poc.business.model.transaction.TransactionInfo;
import com.atsistemas.poc.business.model.transaction.TransactionRequest;
import com.atsistemas.poc.business.ports.check.CalculTransInfoFactory;
import com.atsistemas.poc.persistence.model.TransactionData;
import com.atsistemas.poc.persistence.service.TransactionService;
import org.apache.commons.lang3.StringUtils;

import java.util.Optional;

public class CheckTransactionManager {


    private TransactionService transactionService;

    public CheckTransactionManager(TransactionService transactionService) {
        this.transactionService = transactionService;
    }


    public TransactionInfo check(TransactionRequest request) {
        Optional<TransactionData> transactionData = transactionService.findByReference(request.getReferenceNumber());
        return this.check(request, transactionData.isPresent() ? transactionData.get() : null);
    }


    /**
     * Calcal status and additional information for a specific transaction
     *
     * @param request
     * @param transaction
     * @return
     */
    public TransactionInfo check(TransactionRequest request,
                                 TransactionData transaction) {
        TransactionInfo.Builder builder = TransactionInfo.builder()
                .status(TransactionInfo.StatusTransaction.INVALID)
                .referenceNumber(request.getReferenceNumber());

        if (!StringUtils.isEmpty(request.getReferenceNumber()) && transaction != null) {
            return CalculTransInfoFactory.check(request.getChannelType()).check(transaction);
        }
        return builder.create();
    }
}
