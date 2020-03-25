package com.atsistemas.poc.business.exceptions.account;

public class AccountInsufficientBalanceException extends RuntimeException {
    private static final String ACCOUNT_INSUFFICIENT_BALANCE = "Insufficient account balance";

    public AccountInsufficientBalanceException() {
        super(ACCOUNT_INSUFFICIENT_BALANCE);
    }
}
