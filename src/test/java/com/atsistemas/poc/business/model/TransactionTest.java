package com.atsistemas.poc.business.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TransactionTest {

    @Test
    void testTypeTransactionCredit() {

        Transaction.Builder builder = Transaction.builder();
        Transaction tra=builder.amount(BigDecimal.valueOf(10)).create();
        assertEquals(tra.typeTransaction(), Transaction.TypeTransaction.CREDIT);

        builder.amount(BigDecimal.ZERO).create();
        assertEquals(tra.typeTransaction(), Transaction.TypeTransaction.CREDIT);

    }


    @Test
    void testTypeTransactionDebit() {

        Transaction.Builder builder = Transaction.builder();
        Transaction tra=builder.amount(BigDecimal.valueOf(-10)).create();
        assertEquals(tra.typeTransaction(), Transaction.TypeTransaction.DEBIT);


    }
}