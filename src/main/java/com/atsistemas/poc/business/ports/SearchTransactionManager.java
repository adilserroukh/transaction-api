package com.atsistemas.poc.business.ports;

import com.atsistemas.poc.business.mapper.TransactionMapper;
import com.atsistemas.poc.business.model.transaction.Transaction;
import com.atsistemas.poc.persistence.model.TransactionData;
import com.atsistemas.poc.persistence.service.TransactionService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SearchTransactionManager {

    private TransactionService transactionService;

    public SearchTransactionManager(TransactionService transactionService) {
        this.transactionService = transactionService;
    }


    /**
     * Fin the transactions in the database
     *
     * @param iban
     * @param sortedAmoutAsc
     * @return
     */
    public List<Transaction> search(String iban,
                                   boolean sortedAmoutAsc) {

        //Search transactions in data base
        List<TransactionData> transactionsData = transactionService.findByIban(iban);


        // Sort transactions
        List<Transaction> transactions = new ArrayList<>();
        if (!transactionsData.isEmpty()) {
            transactions = transactionsData
                    .stream()
                    .map(TransactionMapper::fromData)
                    .sorted(sortedAmoutAsc ?
                            Comparator.comparing(Transaction::getAmount) : Comparator.comparing(Transaction::getAmount).reversed())
                    .collect(Collectors.toList());
        }
        return transactions;
    }

}
