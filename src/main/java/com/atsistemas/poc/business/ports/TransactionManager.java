package com.atsistemas.poc.business.ports;

import com.atsistemas.poc.business.exceptions.account.AccountInsufficientBalanceException;
import com.atsistemas.poc.business.exceptions.account.AccountNotExistInDataBaseException;
import com.atsistemas.poc.business.model.Transaction;
import com.atsistemas.poc.persistence.model.AccountData;
import com.atsistemas.poc.persistence.model.TransactionData;
import com.atsistemas.poc.persistence.service.AccountService;
import com.atsistemas.poc.persistence.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.Optional;

public class TransactionManager {

    private static final Logger LOG = LoggerFactory.getLogger(TransactionManager.class);

    private TransactionService transactionService;
    private AccountService accountService;

    public TransactionManager(TransactionService transactionService,
                              AccountService accountService) {
        this.transactionService = transactionService;
        this.accountService = accountService;
    }

    public Transaction createTransaction(Transaction transaction) {
        Optional<AccountData> accout = accountService.findByIban(transaction.getIban());

        if (accout.isPresent()) {

            BigDecimal totalAmount = accout.get().getAmount().subtract(Optional.ofNullable(transaction.getAmount()).orElse(BigDecimal.ZERO));
            if (totalAmount.compareTo(BigDecimal.ZERO) <0) {
                LOG.error("Insufficient Balance");
                throw new AccountInsufficientBalanceException();
            }


           // TransactionData transactionData = TransactionMapper.INSTANCE.fromTransaction(transaction);
            TransactionData transactionData = new TransactionData();
            transactionData.setAmount(transaction.getAmount());
            transactionData.setAccountIban(transaction.getIban());

            accout.get().setAmount(totalAmount);
            accountService.create(accout.get());

            transactionService.create(transactionData);
            return transaction;

        } else {
            LOG.error("The account does not exist");
            throw new AccountNotExistInDataBaseException();
        }

    }

    public Transaction findTransactionByIban(String accountIban) {

        Optional<TransactionData> transactionData = transactionService.findByIban(accountIban);
        if (transactionData.isPresent()) {
            Transaction.Builder builder = Transaction.builder();
            builder.iban(transactionData.get().getReferenceNumber())
                    .amount(transactionData.get().getAmount());
            return builder.create();
        }
        return Transaction.builder().create();
    }


}
