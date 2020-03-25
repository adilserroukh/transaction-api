package com.atsistemas.poc.manager;

import com.atsistemas.poc.business.ports.TransactionManager;
import com.atsistemas.poc.persistence.service.AccountService;
import com.atsistemas.poc.persistence.service.TransactionService;
import org.springframework.stereotype.Service;

@Service
public class TransactionManagerImpl extends TransactionManager {
    public TransactionManagerImpl(TransactionService transactionService, AccountService accountService) {
        super(transactionService, accountService);
    }
}
