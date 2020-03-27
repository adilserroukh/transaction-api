package com.atsistemas.poc.business.model.account;

import lombok.Getter;

@Getter
public class Account {
    private String iban;
    private Long amount;

    private Account() {
    }

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
