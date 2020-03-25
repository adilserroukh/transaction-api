package com.atsistemas.poc.business.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class Transaction {

    private String iban;
    private Date transactionDate;
    private Long amount;
    private Integer fee;
    private String description;


    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private Transaction transaction = new Transaction();

        private Builder() {

        }

        public Builder iban(String iban) {
            transaction.iban = iban;
            return this;
        }

        public Builder transactionDate(Date transactionDate) {
            transaction.transactionDate = transactionDate;
            return this;
        }

        public Builder amount(Long amount) {
            transaction.amount = amount;
            return this;
        }

        public Builder fee(Integer fee) {
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

