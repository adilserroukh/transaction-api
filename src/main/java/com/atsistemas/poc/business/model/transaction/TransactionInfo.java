package com.atsistemas.poc.business.model.transaction;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class TransactionInfo {
    private String referenceNumber;
    private StatusTransaction status;
    private BigDecimal amount;
    private BigDecimal fee;

    public enum TypeStatusTransaction {
        CREDIT,
        DEBIT
    }

    public enum StatusTransaction {
        PENDING("PENDING"),
        SETTLED("SETTLED"),
        FUTURE("FUTURE"),
        INVALID("INVALID");
        private String value;

        StatusTransaction(String value) {
            this.value = value;
        }
    }

    private TransactionInfo() {

    }

    public TransactionInfo.TypeStatusTransaction typeStatusTransaction() {
        return amount.compareTo(BigDecimal.ZERO) < 0 ? TransactionInfo.TypeStatusTransaction.DEBIT : TransactionInfo.TypeStatusTransaction.CREDIT;
    }

    public static TransactionInfo.Builder builder() {
        return new TransactionInfo.Builder();
    }

    public static final class Builder {
        private TransactionInfo transaction = new TransactionInfo();

        private Builder() {

        }

        public TransactionInfo.Builder status(StatusTransaction status) {
            transaction.status = status;
            return this;
        }

        public TransactionInfo.Builder referenceNumber(String referenceNumber) {
            transaction.referenceNumber = referenceNumber;
            return this;
        }


        public TransactionInfo.Builder amount(BigDecimal amount) {
            transaction.amount = amount;
            return this;
        }

        public TransactionInfo.Builder fee(BigDecimal fee) {
            transaction.fee = fee;
            return this;
        }


        public TransactionInfo create() {
            return transaction;
        }
    }
}
