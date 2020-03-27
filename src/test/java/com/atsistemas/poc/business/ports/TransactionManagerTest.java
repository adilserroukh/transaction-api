package com.atsistemas.poc.business.ports;

import com.atsistemas.poc.business.mapper.TransactionMapper;
import com.atsistemas.poc.business.model.transaction.Transaction;
import com.atsistemas.poc.business.model.transaction.TransactionInfo;
import com.atsistemas.poc.business.model.transaction.TransactionRequest;
import com.atsistemas.poc.persistence.model.TransactionData;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TransactionManagerTest {


    private TransactionData transactionDataTest = null;
    private Transaction transactionTest = null;
    private DecimalFormat formatAmoutExpected = null;


    private void initTransactionSetUp() {
        formatAmoutExpected = new DecimalFormat("#.##");

        this.transactionDataTest = new TransactionData();
        this.transactionDataTest.setReferenceNumber("12345A");
        this.transactionDataTest.setAccountIban("ES9820385778983000760236");
        this.transactionDataTest.setDateTook(LocalDateTime.now());
        this.transactionDataTest.setAmount(BigDecimal.valueOf(193.38));
        this.transactionDataTest.setFee(BigDecimal.valueOf(3.18));
        this.transactionDataTest.setDescription("Restaurant payment");

        transactionTest = TransactionMapper.fromData(transactionDataTest);

    }

    @Test
    void createTransaction() {
    }

    @Test
    void findTransactionByIban() {
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
        this.initTransactionSetUp();
        //transaction date is before today

        transactionDataTest.setDateTook(LocalDateTime.now().minusDays(32));

        TransactionManager transactionManager = new TransactionManager(null, null);


        //status from CLIENT
        TransactionRequest request = TransactionRequest
                .builder()
                .referenceNumber("12345A")
                .channel(TransactionRequest.TypeChannel.CLIENT)
                .create();

        TransactionInfo status = transactionManager.statusTransaction(request, transactionDataTest);

        assertNotNull(status);
        assertEquals("12345A", status.getReferenceNumber());
        assertEquals(TransactionInfo.StatusTransaction.SETTLED, status.getStatus());
        String amountExpected = formatAmoutExpected.format(BigDecimal.valueOf(190.20));
        String amountCurrent = formatAmoutExpected.format(status.getAmount());
        assertEquals(amountExpected, amountCurrent);
        assertNull(status.getFee());

        //status from ATM
        request = TransactionRequest
                .builder()
                .referenceNumber("12345A")
                .channel(TransactionRequest.TypeChannel.ATM)
                .create();

        status = transactionManager.statusTransaction(request, transactionDataTest);

        assertNotNull(status);
        assertEquals("12345A", status.getReferenceNumber());
        assertEquals(TransactionInfo.StatusTransaction.SETTLED, status.getStatus());
        amountExpected = formatAmoutExpected.format(BigDecimal.valueOf(190.20));
        amountCurrent = formatAmoutExpected.format(status.getAmount());
        assertEquals(amountExpected, amountCurrent);
        assertNull(status.getFee());

        //status from INTERNAL
        request = TransactionRequest
                .builder()
                .referenceNumber("12345A")
                .channel(TransactionRequest.TypeChannel.INTERNAL)
                .create();

        status = transactionManager.statusTransaction(request, transactionDataTest);

        assertNotNull(status);
        assertEquals("12345A", status.getReferenceNumber());
        assertEquals(TransactionInfo.StatusTransaction.SETTLED, status.getStatus());
        assertEquals(BigDecimal.valueOf(193.38), status.getAmount());
        assertEquals(BigDecimal.valueOf(3.18), status.getFee());

    }

    @Test
    void statusTransaction_transaction_stored_and_date_equals_today() {
        this.initTransactionSetUp();
        transactionDataTest.setDateTook(LocalDateTime.now());

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

        TransactionInfo status = transactionManager.statusTransaction(request, transactionDataTest);

        assertNotNull(status);
        assertEquals("12345A", status.getReferenceNumber());
        assertEquals(TransactionInfo.StatusTransaction.PENDING, status.getStatus());

        String amountExpected = formatAmoutExpected.format(BigDecimal.valueOf(190.20));
        String amountCurrent = formatAmoutExpected.format(status.getAmount());

        assertEquals(amountExpected, amountCurrent);
        assertNull(status.getFee());

        //status from ATM
        request = TransactionRequest
                .builder()
                .referenceNumber("12345A")
                .channel(TransactionRequest.TypeChannel.ATM)
                .create();

        status = transactionManager.statusTransaction(request, transactionDataTest);

        assertNotNull(status);
        assertEquals("12345A", status.getReferenceNumber());
        assertEquals(TransactionInfo.StatusTransaction.PENDING, status.getStatus());
        amountExpected = formatAmoutExpected.format(BigDecimal.valueOf(190.20));
        amountCurrent = formatAmoutExpected.format(status.getAmount());
        assertEquals(amountExpected, amountCurrent);
        assertNull(status.getFee());


        //status from INTERNAL
        request = TransactionRequest
                .builder()
                .referenceNumber("12345A")
                .channel(TransactionRequest.TypeChannel.INTERNAL)
                .create();

        status = transactionManager.statusTransaction(request, transactionDataTest);

        assertNotNull(status);
        assertEquals("12345A", status.getReferenceNumber());
        assertEquals(TransactionInfo.StatusTransaction.PENDING, status.getStatus());
        assertEquals(BigDecimal.valueOf(193.38), status.getAmount());
        assertEquals(BigDecimal.valueOf(3.18), status.getFee());
    }


    @Test
    void statusTransaction_transaction_stored_and_date_greater_today() {
        this.initTransactionSetUp();
        //transaction date is before today

        transactionDataTest.setDateTook(LocalDateTime.now().plusDays(32));

        TransactionManager transactionManager = new TransactionManager(null, null);


        //status from INTERNAL
        TransactionRequest request = TransactionRequest
                .builder()
                .referenceNumber("12345A")
                .channel(TransactionRequest.TypeChannel.INTERNAL)
                .create();

        TransactionInfo status = transactionManager.statusTransaction(request, transactionDataTest);

        assertNotNull(status);
        assertEquals("12345A", status.getReferenceNumber());
        assertEquals(TransactionInfo.StatusTransaction.FUTURE, status.getStatus());
        assertEquals(BigDecimal.valueOf(193.38), status.getAmount());
        assertEquals(BigDecimal.valueOf(3.18), status.getFee());

    }

}