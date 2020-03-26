package com.atsistemas.poc.business.model;

import com.atsistemas.poc.business.model.transaction.Transaction;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class TransactionTest {

    @Test
    void typeTransactionCreditTest() {

        Transaction.Builder builder = Transaction.builder();
        Transaction tra=builder.amount(BigDecimal.valueOf(10)).create();
        assertEquals(tra.typeTransaction(), Transaction.TypeTransaction.CREDIT);

        builder.amount(BigDecimal.ZERO).create();
        assertEquals(tra.typeTransaction(), Transaction.TypeTransaction.CREDIT);

    }


    @Test
    void typeTransactionDebitTest() {

        Transaction.Builder builder = Transaction.builder();
        Transaction tra=builder.amount(BigDecimal.valueOf(-10)).create();
        assertEquals(tra.typeTransaction(), Transaction.TypeTransaction.DEBIT);


    }

    @Test
    void shouldGenerateReferenceNumberTest() {

        Transaction.Builder builder = Transaction.builder();
        Transaction tra=builder.referenceNumber(null).create();
        assertNotNull(tra.getReferenceNumber());
        assertEquals(32, tra.getReferenceNumber().length());

        builder.referenceNumber("12345A").create();
        assertEquals("12345A", tra.getReferenceNumber());

    }
}