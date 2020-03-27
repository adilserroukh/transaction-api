package com.atsistemas.poc.controller;


import com.atsistemas.generated.api.TransactionApi;
import com.atsistemas.generated.model.StatusTransactionResponseDto;
import com.atsistemas.generated.model.TransactionDto;
import com.atsistemas.generated.model.TransationInfoRequestDto;
import com.atsistemas.poc.business.exceptions.account.AccountInsufficientBalanceException;
import com.atsistemas.poc.business.model.transaction.Transaction;
import com.atsistemas.poc.business.model.transaction.TransactionInfo;
import com.atsistemas.poc.business.model.transaction.TransactionRequest;
import com.atsistemas.poc.business.ports.CheckTransactionManager;
import com.atsistemas.poc.business.ports.CreateTransactionManager;
import com.atsistemas.poc.business.ports.SearchTransactionManager;
import com.atsistemas.poc.controller.mapper.TransactionDtoMapper;
import com.atsistemas.poc.controller.mapper.TransactionInfoDtoMapper;
import com.atsistemas.poc.controller.mapper.TransactionStatusMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class TransactionController implements TransactionApi {

    private CreateTransactionManager createTransactionManager;
    private CheckTransactionManager checkTransactionManager;
    private SearchTransactionManager searchTransactionManager;

    public TransactionController(CreateTransactionManager createTransactionManager,
                                 CheckTransactionManager checkTransactionManager,
                                 SearchTransactionManager searchTransactionManager) {
        this.createTransactionManager = createTransactionManager;
        this.checkTransactionManager = checkTransactionManager;
        this.searchTransactionManager = searchTransactionManager;
    }


    @Override
    public ResponseEntity<StatusTransactionResponseDto> createTransaction(@Valid TransactionDto transactionDto) {
        try {
            Transaction transaction = TransactionDtoMapper.fromDto(transactionDto);
            createTransactionManager.create(transaction);


            StatusTransactionResponseDto status = new StatusTransactionResponseDto();
            status.setStatus(StatusTransactionResponseDto.StatusEnum.PENDING);

            return ResponseEntity.ok(status);
        } catch (AccountInsufficientBalanceException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @Override
    public ResponseEntity<List<TransactionDto>> seachTransactions(String accountIban, @Valid String sort) {

        List<Transaction> transactions = searchTransactionManager.search(accountIban, true);

        List<TransactionDto> transactionsDto = new ArrayList<>();
        if (!transactions.isEmpty()) {
            transactionsDto = transactions.stream()
                    .map(TransactionDtoMapper::toDto)
                    .collect(Collectors.toList())
            ;
        }

        return ResponseEntity.ok(transactionsDto);
    }

    @Override
    public ResponseEntity<StatusTransactionResponseDto> statusTransation(@Valid TransationInfoRequestDto transationInfoRequestDto) {
        TransactionRequest request = TransactionInfoDtoMapper.fromRequestDto(transationInfoRequestDto);

        TransactionInfo transactionInfo = checkTransactionManager.check(request);

        StatusTransactionResponseDto tra = new StatusTransactionResponseDto();
        tra.setReference(transactionInfo.getReferenceNumber());
        tra.setStatus(TransactionStatusMapper.toDto(transactionInfo.getStatus()));
        tra.setAmount(transactionInfo.getAmount());
        tra.setFee(transactionInfo.getFee());
        return ResponseEntity.ok().body(tra);
    }


}
