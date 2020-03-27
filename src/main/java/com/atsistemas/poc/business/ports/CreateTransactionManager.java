package com.atsistemas.poc.business.ports;

import com.atsistemas.poc.business.exceptions.account.AccountInsufficientBalanceException;
import com.atsistemas.poc.business.exceptions.account.AccountNotExistInDataBaseException;
import com.atsistemas.poc.business.model.transaction.Transaction;
import com.atsistemas.poc.persistence.model.AccountData;
import com.atsistemas.poc.persistence.model.TransactionData;
import com.atsistemas.poc.persistence.service.AccountService;
import com.atsistemas.poc.persistence.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.Optional;

public class CreateTransactionManager {

    private static final Logger LOG = LoggerFactory.getLogger(CreateTransactionManager.class);

    private TransactionService transactionService;
    private AccountService accountService;

    public CreateTransactionManager(TransactionService transactionService,
                                    AccountService accountService) {
        this.transactionService = transactionService;
        this.accountService = accountService;
    }


    /**
     * Create new Transaction and save it in database
     *
     * @param transaction
     * @return
     */
    public Transaction create(Transaction transaction) {

        LOG.info("Search for the account associated with the iban {}", transaction.getIban());

        Optional<AccountData> accout = accountService.findByIban(transaction.getIban());

        if (!accout.isPresent()) {

            LOG.warn("Associated account does not exist");
            throw new AccountNotExistInDataBaseException();

        } else {


            AccountData currentAccout = accout.get();

            //Calculate Balance in the account
            BigDecimal amountTrans = Optional.ofNullable(transaction.getAmount()).orElse(BigDecimal.ZERO);
            BigDecimal feeTrans = Optional.ofNullable(transaction.getFee()).orElse(BigDecimal.ZERO);

            BigDecimal balanceTemp = currentAccout.getAmount()
                    .add(amountTrans)
                    .subtract(feeTrans);

            if (balanceTemp.compareTo(BigDecimal.ZERO) < 0) {
                LOG.warn("Insufficient Balance in accout {} ", currentAccout.getIban());
                throw new AccountInsufficientBalanceException();
            }


            //Save transaction in database
            TransactionData transactionData = new TransactionData();
            transactionData.setReferenceNumber(transaction.getReferenceNumber());
            transactionData.setAccountIban(transaction.getIban());
            transactionData.setDateTook(transaction.getTransactionDate());
            transactionData.setAmount(transaction.getAmount());
            transactionData.setFee(transaction.getFee());
            transactionData.setDescription(transaction.getDescription());
            transactionService.create(transactionData);

            //Update account
            accout.get().setAmount(balanceTemp);
            accountService.create(accout.get());

            return transaction;
        }

    }
}
