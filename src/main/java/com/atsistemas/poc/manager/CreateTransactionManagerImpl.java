package com.atsistemas.poc.manager;

import com.atsistemas.poc.business.ports.CreateTransactionManager;
import com.atsistemas.poc.persistence.service.AccountService;
import com.atsistemas.poc.persistence.service.TransactionService;
import org.springframework.stereotype.Service;

@Service
public class CreateTransactionManagerImpl extends CreateTransactionManager {

    public CreateTransactionManagerImpl(TransactionService transactionService, AccountService accountService) {
        super(transactionService, accountService);
    }
}
