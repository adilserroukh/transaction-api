package com.atsistemas.poc.business.ports;

import com.atsistemas.poc.business.model.transaction.Transaction;
import com.atsistemas.poc.business.model.transaction.TransactionInfo;
import com.atsistemas.poc.business.model.transaction.TransactionRequest;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TransactionManagerTest {

    @Test
    void createTransaction() {
    }

    @Test
    void findTransactionByIban() {
    }

    @Test
    void statusTransaction_with_request_null() {
        TransactionManager transactionManager = new TransactionManager(null, null);
        TransactionInfo status = transactionManager.statusTransaction(null, null);

        assertNull(status);
    }

    @Test
    void statusTransaction_with_request_empty() {
        TransactionManager transactionManager = new TransactionManager(null, null);
        TransactionRequest request = TransactionRequest.builder().create();
        TransactionInfo status = transactionManager.statusTransaction(request, null);

        assertNull(status);
    }

    @Test
    void statusTransaction_transaction_not_stored() {
        TransactionManager transactionManager = new TransactionManager(null, null);

        TransactionRequest request = TransactionRequest
                .builder()
                .referenceNumber("XXXXXX")
                .create();

        TransactionInfo status = transactionManager.statusTransaction(request, null);

        assertNotNull(status);
        assertEquals("XXXXXX", status.getReferenceNumber());
        assertNotNull(status.getStatus());
        assertEquals(TransactionInfo.StatusTransaction.INVALID, status.getStatus());

    }

    @Test
    void statusTransaction_transaction_stored_and_date_before_today() {
        TransactionManager transactionManager = new TransactionManager(null, null);

        //transaction date is before today
        Transaction transaction = Transaction
                .builder()
                .referenceNumber("12345A")
                .referenceNumber("ES9820385778983000760236")
                .transactionDate(LocalDateTime.now().minusDays(2))
                .amount(BigDecimal.valueOf(193.38))
                .fee(BigDecimal.valueOf(3.18))
                .description("Restaurant payment")
                .create();


        //status from CLIENT
        TransactionRequest request = TransactionRequest
                .builder()
                .referenceNumber("12345A")
                .channel(TransactionRequest.TypeChannel.CLIENT)
                .create();

        TransactionInfo status = transactionManager.statusTransaction(request, transaction);

        assertNotNull(status);
        assertEquals("12345A", status.getReferenceNumber());
        assertEquals(TransactionInfo.StatusTransaction.SETTLED, status.getStatus());
        assertEquals(BigDecimal.valueOf(193.38), status.getAmount());
        assertNull(status.getFee());

        //status from ATM
        request = TransactionRequest
                .builder()
                .referenceNumber("12345A")
                .channel(TransactionRequest.TypeChannel.ATM)
                .create();

        status = transactionManager.statusTransaction(request, transaction);

        assertNotNull(status);
        assertEquals("12345A", status.getReferenceNumber());
        assertEquals(TransactionInfo.StatusTransaction.SETTLED, status.getStatus());
        assertEquals(BigDecimal.valueOf(193.38), status.getAmount());
        assertNull(status.getFee());

        //status from INTERNAL
        request = TransactionRequest
                .builder()
                .referenceNumber("12345A")
                .channel(TransactionRequest.TypeChannel.INTERNAL)
                .create();

        status = transactionManager.statusTransaction(request, transaction);

        assertNotNull(status);
        assertEquals("12345A", status.getReferenceNumber());
        assertEquals(TransactionInfo.StatusTransaction.SETTLED, status.getStatus());
        assertEquals(BigDecimal.valueOf(193.38), status.getAmount());
        assertEquals(BigDecimal.valueOf(3.18), status.getFee());

    }

    @Test
    void statusTransaction_transaction_stored_and_date_equals_today() {
        TransactionManager transactionManager = new TransactionManager(null, null);

        //transaction date is equals today
        Transaction transaction = Transaction
                .builder()
                .referenceNumber("12345A")
                .referenceNumber("ES9820385778983000760236")
                .transactionDate(LocalDateTime.now())
                .amount(BigDecimal.valueOf(193.38))
                .fee(BigDecimal.valueOf(3.18))
                .description("Restaurant payment")
                .create();
        //status from CLIENT
        TransactionRequest request = TransactionRequest
                .builder()
                .referenceNumber("12345A")
                .channel(TransactionRequest.TypeChannel.CLIENT)
                .create();

        TransactionInfo status = transactionManager.statusTransaction(request, transaction);

        assertNotNull(status);
        assertEquals("12345A", status.getReferenceNumber());
        assertEquals(TransactionInfo.StatusTransaction.PENDING, status.getStatus());
        assertEquals(BigDecimal.valueOf(193.38), status.getAmount());
        assertNull(status.getFee());

        //status from ATM
        request = TransactionRequest
                .builder()
                .referenceNumber("12345A")
                .channel(TransactionRequest.TypeChannel.ATM)
                .create();

        status = transactionManager.statusTransaction(request, transaction);

        assertNotNull(status);
        assertEquals("12345A", status.getReferenceNumber());
        assertEquals(TransactionInfo.StatusTransaction.PENDING, status.getStatus());
        assertEquals(BigDecimal.valueOf(193.38), status.getAmount());
        assertNull(status.getFee());


        //status from INTERNAL
        request = TransactionRequest
                .builder()
                .referenceNumber("12345A")
                .channel(TransactionRequest.TypeChannel.INTERNAL)
                .create();

        status = transactionManager.statusTransaction(request, transaction);

        assertNotNull(status);
        assertEquals("12345A", status.getReferenceNumber());
        assertEquals(TransactionInfo.StatusTransaction.PENDING, status.getStatus());
        assertEquals(BigDecimal.valueOf(193.38), status.getAmount());
        assertEquals(BigDecimal.valueOf(3.18), status.getFee());
    }

}