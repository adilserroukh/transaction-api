package com.atsistemas.poc.endPoints;


import com.atsistemas.generated.api.TransactionApi;
import com.atsistemas.generated.model.StatusTransactionResponseDto;
import com.atsistemas.generated.model.TransactionDto;
import com.atsistemas.generated.model.TransationInfoRequestDto;
import com.atsistemas.poc.business.exceptions.account.AccountInsufficientBalanceException;
import com.atsistemas.poc.business.model.Transaction;
import com.atsistemas.poc.business.ports.TransactionManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.math.BigDecimal;

@RestController
public class TransactionController implements TransactionApi {

    private TransactionManager transactionManager;

    public TransactionController(TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }


    @Override
    public ResponseEntity<StatusTransactionResponseDto> createTransaction(@Valid TransactionDto transactionDto) {
        try {
            Transaction.Builder builderTra = Transaction.builder();
            builderTra.iban(transactionDto.getIban())
                    .amount(transactionDto.getAmount().longValue());

            transactionManager.createTransaction(builderTra.create());
            StatusTransactionResponseDto status = new StatusTransactionResponseDto();
            status.setStatus(StatusTransactionResponseDto.StatusEnum.PENDING);

            return ResponseEntity.ok(status);
        } catch (AccountInsufficientBalanceException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @Override
    public ResponseEntity<TransactionDto> seachTransactions(String accountIban, @Valid String sort) {
        transactionManager.findTransactionByIban(accountIban);
        TransactionDto tra = new TransactionDto();
        tra.setAmount(BigDecimal.ONE);
        transactionManager.findTransactionByIban("1");
        return ResponseEntity.ok(tra);
    }

    @Override
    public ResponseEntity<StatusTransactionResponseDto> statusTransation(@Valid TransationInfoRequestDto transationInfoRequestDto) {
        StatusTransactionResponseDto tra= new StatusTransactionResponseDto();
        tra.setStatus(StatusTransactionResponseDto.StatusEnum.INVALID);
        return ResponseEntity.ok(tra);
    }


}
