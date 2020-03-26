package com.atsistemas.poc.business.ports;

import com.atsistemas.poc.business.exceptions.account.AccountInsufficientBalanceException;
import com.atsistemas.poc.business.exceptions.account.AccountNotExistInDataBaseException;
import com.atsistemas.poc.business.mapper.TransactionMapper;
import com.atsistemas.poc.business.model.transaction.Transaction;
import com.atsistemas.poc.business.model.transaction.TransactionInfo;
import com.atsistemas.poc.business.model.transaction.TransactionRequest;
import com.atsistemas.poc.persistence.model.AccountData;
import com.atsistemas.poc.persistence.model.TransactionData;
import com.atsistemas.poc.persistence.service.AccountService;
import com.atsistemas.poc.persistence.service.TransactionService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.text.Bidi;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.DAYS;

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
            BigDecimal amountTrans = Optional.ofNullable(transaction.getAmount()).orElse(BigDecimal.ZERO);
            BigDecimal feeTrans = Optional.ofNullable(transaction.getFee()).orElse(BigDecimal.ZERO);

            BigDecimal totalAmount = accout.get().getAmount()
                    .subtract(amountTrans)
                    .subtract(feeTrans);

            if (totalAmount.compareTo(BigDecimal.ZERO) < 0) {
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

    public List<Transaction> findTransactionsByIban(String accountIban, boolean sortedAmoutAsc) {

        List<TransactionData> transactionsData = transactionService.findByIban(accountIban);
        List<Transaction> transactions = new ArrayList<>();

        if (!transactionsData.isEmpty()) {
            transactions = transactionsData
                    .stream()
                    .map(tra -> TransactionMapper.fromData(tra))
                    .sorted(sortedAmoutAsc ? Comparator.comparing(Transaction::getAmount) : Comparator.comparing(Transaction::getAmount)
                            .reversed()).collect(Collectors.toList());

        }
        return transactions;
    }


    public TransactionInfo statusTransaction(TransactionRequest request, Transaction transaction) {
        //Request is Null or Empty
        if (request == null || StringUtils.isEmpty(request.getReferenceNumber())) {
            return null;
        }

        TransactionInfo.Builder builder = TransactionInfo.builder();
        builder.referenceNumber(request.getReferenceNumber());

        if (transaction == null) {
            builder.status(TransactionInfo.StatusTransaction.INVALID);
        } else {

            LocalDateTime transDate = transaction.getTransactionDate().truncatedTo(DAYS);
            LocalDateTime currentDate = LocalDateTime.now().truncatedTo(DAYS);

            if (transDate.isEqual(currentDate)) {
                if (TransactionRequest.TypeChannel.INTERNAL.equals(request.getTypeChannel())) {
                    builder.fee(transaction.getFee());
                }

                builder.status(TransactionInfo.StatusTransaction.PENDING);
                builder.amount(transaction.getAmount());

            } else {
                if (TransactionRequest.TypeChannel.INTERNAL.equals(request.getTypeChannel())) {
                    builder.fee(transaction.getFee());
                }

                builder.status(TransactionInfo.StatusTransaction.SETTLED);
                builder.amount(transaction.getAmount());
            }
        }
        return builder.create();
    }
}
