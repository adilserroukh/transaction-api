package com.atsistemas.poc.business.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Account {
    private String iban;
    private Long amount;


    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private Account account = new Account();

        private Builder() {

        }

        public Account.Builder iban(String iban) {
            account.iban = iban;
            return this;
        }

        public Account.Builder amount(Long amount) {
            account.amount = amount;
            return this;
        }

        public Account create() {
            return account;
        }
    }


}
