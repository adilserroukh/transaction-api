package com.atsistemas.poc.controllers;


import com.atsistemas.generated.api.TransactionApi;
import com.atsistemas.generated.model.StatusDto;
import com.atsistemas.generated.model.TransactionDto;
import com.atsistemas.poc.business.exceptions.account.AccountInsufficientBalanceException;
import com.atsistemas.poc.business.model.Transaction;
import com.atsistemas.poc.business.ports.TransactionManager;
import com.atsistemas.poc.persistence.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;

@RestController
public class TransactionController implements TransactionApi {

    private static final Logger LOG = LoggerFactory.getLogger(TransactionController.class);

    private TransactionService transactionService;
    private TransactionManager transactionManager;

    public TransactionController(TransactionService transactionService,
                                 TransactionManager transactionManager) {
        this.transactionService = transactionService;
        this.transactionManager = transactionManager;
    }


    @Override
    public ResponseEntity<StatusDto> createTransaction(@Valid TransactionDto transactionDto) {
        try {
            Transaction.Builder builderTra = Transaction.builder();
            builderTra.iban(transactionDto.getIban())
                    .amount(transactionDto.getAmount().longValue());

            transactionManager.createTransaction(builderTra.create());
            StatusDto status = new StatusDto();
            status.setStatus(StatusDto.StatusEnum.PENDING);

            return ResponseEntity.ok(status);
        } catch (AccountInsufficientBalanceException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @Override
    public ResponseEntity<TransactionDto> findTransactionByIban(String accountIban) {
        transactionManager.findTransactionByIban(accountIban);
        TransactionDto tra = new TransactionDto();
        tra.setAmount(BigDecimal.ONE);
        transactionService.findByIban("1");
        return ResponseEntity.ok(tra);
    }
}
