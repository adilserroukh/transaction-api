package com.atsistemas.poc.controllers;


import com.atsistemas.generated.api.TransactionApi;
import com.atsistemas.generated.model.TransactionDto;
import com.atsistemas.poc.persistence.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TransactionController implements TransactionApi {

    private TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @Override
    public ResponseEntity<TransactionDto> getTransactionsByIban(String accountIban) {
        TransactionDto tra= new TransactionDto();
        tra.setAmount("1");
        transactionService.findByIban("1");
        return ResponseEntity.ok(tra);
    }
}
