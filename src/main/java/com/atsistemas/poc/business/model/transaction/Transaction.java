package com.atsistemas.poc.business.model.transaction;

import com.atsistemas.poc.commons.util.IDGenerator;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class Transaction {
    private String referenceNumber;
    private String iban;
    private LocalDateTime transactionDate;
    private BigDecimal amount;
    private BigDecimal fee;
    private String description;

    public enum TypeTransaction {
        CREDIT,
        DEBIT
    }
    private Transaction(){

    }

    public TypeTransaction typeTransaction() {
        return amount.compareTo(BigDecimal.ZERO) < 0 ? TypeTransaction.DEBIT : TypeTransaction.CREDIT;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private Transaction transaction = new Transaction();

        private Builder() {

        }

        public Builder referenceNumber(String referenceNumber) {
            if(!StringUtils.isBlank(referenceNumber)){
                transaction.referenceNumber = referenceNumber;
            }else{
                transaction.referenceNumber = IDGenerator.generateEntityId();
            }
            return this;
        }

        public Builder iban(String iban) {
            transaction.iban = iban;
            return this;
        }

        public Builder transactionDate(LocalDateTime transactionDate) {
            transaction.transactionDate = transactionDate;
            return this;
        }

        public Builder amount(BigDecimal amount) {
            transaction.amount = amount;
            return this;
        }

        public Builder fee(BigDecimal fee) {
            transaction.fee = fee;
            return this;
        }

        public Builder description(String description) {
            transaction.description = description;
            return this;
        }

        public Transaction create() {
            return transaction;
        }
    }
}

