package com.atsistemas.poc.business.exceptions.account;

public class AccountNotExistInDataBaseException extends RuntimeException {
    private static final String ACCOUNT_NOT_EXIST = "The account does not exist";

    public AccountNotExistInDataBaseException() {
        super(ACCOUNT_NOT_EXIST);
    }
}
