package com.atsistemas.poc.manager;

import com.atsistemas.poc.business.ports.CheckTransactionManager;
import com.atsistemas.poc.persistence.service.TransactionService;
import org.springframework.stereotype.Service;

@Service
public class CheckTransactionManagerImpl extends CheckTransactionManager {

    public CheckTransactionManagerImpl(TransactionService transactionService) {
        super(transactionService);
    }
}
