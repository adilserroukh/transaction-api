package com.atsistemas.poc.endPoints;


import com.atsistemas.generated.api.TransactionApi;
import com.atsistemas.generated.model.StatusTransactionResponseDto;
import com.atsistemas.generated.model.TransactionDto;
import com.atsistemas.generated.model.TransationInfoRequestDto;
import com.atsistemas.poc.business.exceptions.account.AccountInsufficientBalanceException;
import com.atsistemas.poc.business.model.transaction.Transaction;
import com.atsistemas.poc.business.model.transaction.TransactionInfo;
import com.atsistemas.poc.business.model.transaction.TransactionRequest;
import com.atsistemas.poc.business.ports.TransactionManager;
import com.atsistemas.poc.endPoints.mapper.StatusTransactionMapper;
import com.atsistemas.poc.endPoints.mapper.TransactionDtoMapper;
import com.atsistemas.poc.endPoints.mapper.TransactionInfoDtoMapper;
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

    private TransactionManager transactionManager;

    public TransactionController(TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }


    @Override
    public ResponseEntity<StatusTransactionResponseDto> createTransaction(@Valid TransactionDto transactionDto) {
        try {
            Transaction transaction = TransactionDtoMapper.fromDto(transactionDto);
            transactionManager.createTransaction(transaction);


            StatusTransactionResponseDto status = new StatusTransactionResponseDto();
            status.setStatus(StatusTransactionResponseDto.StatusEnum.PENDING);

            return ResponseEntity.ok(status);
        } catch (AccountInsufficientBalanceException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @Override
    public ResponseEntity<List<TransactionDto>> seachTransactions(String accountIban, @Valid String sort) {

        List<Transaction> transactions = transactionManager.findTransactionsByIban(accountIban, true);

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

        TransactionInfo transactionInfo = transactionManager.statusTransaction(request);

        StatusTransactionResponseDto tra = new StatusTransactionResponseDto();
        tra.setReference(transactionInfo.getReferenceNumber());
        tra.setStatus(StatusTransactionMapper.toDto(transactionInfo.getStatus()));
        tra.setAmount(transactionInfo.getAmount());
        tra.setFee(transactionInfo.getFee());
        return ResponseEntity.ok().body(tra);
    }


}
