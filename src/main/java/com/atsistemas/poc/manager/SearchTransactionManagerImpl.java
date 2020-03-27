package com.atsistemas.poc.manager;

import com.atsistemas.poc.business.ports.SearchTransactionManager;
import com.atsistemas.poc.persistence.service.TransactionService;
import org.springframework.stereotype.Service;

@Service
public class SearchTransactionManagerImpl extends SearchTransactionManager {

    public SearchTransactionManagerImpl(TransactionService transactionService) {
        super(transactionService);
    }
}
