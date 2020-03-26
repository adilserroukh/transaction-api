package com.atsistemas.poc.endPoints.mapper;

import com.atsistemas.generated.model.TransactionDto;
import com.atsistemas.poc.business.model.transaction.Transaction;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TransactionDtoMapperTest {

    @Test
    void testMapperTransaction_toDto_Iban() {

        Transaction.Builder builder = Transaction.builder();

        Transaction from = builder.iban("ES9820385778983000760236").create();

        TransactionDto to = TransactionDtoMapper.toDto(from);
        assertNull(to.getReference());
        assertNotNull(to.getIban());
        assertEquals("ES9820385778983000760236", to.getIban());
        assertNull(to.getDate());
        assertNull(to.getAmount());
        assertNull(to.getFee());
        assertNull(to.getDescription());

    }

    @Test
    void testMapperTransaction_toDto_Reference() {

        Transaction.Builder builder = Transaction.builder();

        Transaction from = builder.referenceNumber("12345A").create();

        TransactionDto to = TransactionDtoMapper.toDto(from);
        assertEquals("12345A", to.getReference());
        assertNull(to.getIban());
        assertNull(to.getDate());
        assertNull(to.getAmount());
        assertNull(to.getFee());
        assertNull(to.getDescription());

    }

    @Test
    void testMapperTransaction_toDto_Date() {
        Transaction.Builder builder = Transaction.builder();

        Transaction from = builder.transactionDate(LocalDateTime.now()).create();

        TransactionDto to = TransactionDtoMapper.toDto(from);
        assertNull(to.getReference());
        assertNull(to.getIban());
        assertNotNull(to.getDate());
        assertNull(to.getAmount());
        assertNull(to.getFee());
        assertNull(to.getDescription());
    }

    @Test
    void testMapperTransaction_toDto_Amount() {
        Transaction.Builder builder = Transaction.builder();

        Transaction from = builder.amount(BigDecimal.ONE).create();

        TransactionDto to = TransactionDtoMapper.toDto(from);
        assertNull(to.getReference());
        assertNull(to.getIban());
        assertNull(to.getDate());
        assertNotNull(to.getAmount());
        assertEquals(BigDecimal.ONE, to.getAmount());
        assertNull(to.getFee());
        assertNull(to.getDescription());
    }

    @Test
    void testMapperTransaction_toDto_Fee() {
        Transaction.Builder builder = Transaction.builder();

        Transaction from = builder.fee(BigDecimal.ONE).create();

        TransactionDto to = TransactionDtoMapper.toDto(from);
        assertNull(to.getReference());
        assertNull(to.getIban());
        assertNull(to.getDate());
        assertNull(to.getAmount());
        assertEquals(BigDecimal.ONE, to.getFee());
        assertNull(to.getDescription());
    }

    @Test
    void testMapperTransaction_toDto_Description() {
        Transaction.Builder builder = Transaction.builder();

        Transaction from = builder.description("Restaurant paymen").create();

        TransactionDto to = TransactionDtoMapper.toDto(from);
        assertNull(to.getReference());
        assertNull(to.getIban());
        assertNull(to.getDate());
        assertNull(to.getAmount());
        assertNull(to.getFee());
        assertEquals("Restaurant paymen", to.getDescription());
    }


    @Test
    void testMapperTransaction_fromDto_Iban() {


        TransactionDto from = new TransactionDto();
        from.iban("ES9820385778983000760236");


        Transaction to = TransactionDtoMapper.fromDto(from);
        assertNotNull(to.getReferenceNumber());
        assertNotNull(to.getIban());
        assertEquals("ES9820385778983000760236", to.getIban());
        assertNull(to.getTransactionDate());
        assertNull(to.getAmount());
        assertNull(to.getFee());
        assertNull(to.getDescription());

    }

    @Test
    void testMapperTransaction_fromDto_Reference() {


        TransactionDto from = new TransactionDto();
        from.reference("12345A");

        Transaction to = TransactionDtoMapper.fromDto(from);
        assertEquals("12345A", to.getReferenceNumber());
        assertNull(to.getIban());
        assertNull(to.getTransactionDate());
        assertNull(to.getAmount());
        assertNull(to.getFee());
        assertNull(to.getDescription());

    }

    @Test
    void testMapperTransaction_fromDto_Date() {

        TransactionDto from = new TransactionDto();
        from.date(LocalDateTime.now());
        Transaction to = TransactionDtoMapper.fromDto(from);

        assertNotNull(to.getReferenceNumber());
        assertNull(to.getIban());
        assertNotNull(to.getTransactionDate());
        assertNull(to.getAmount());
        assertNull(to.getFee());
        assertNull(to.getDescription());
    }

    @Test
    void testMapperTransaction_fromDto_Amount() {


        TransactionDto from = new TransactionDto();
        from.amount(BigDecimal.ONE);
        Transaction to = TransactionDtoMapper.fromDto(from);

        assertNotNull(to.getReferenceNumber());
        assertNull(to.getIban());
        assertNull(to.getTransactionDate());
        assertNotNull(to.getAmount());
        assertEquals(BigDecimal.ONE, to.getAmount());
        assertNull(to.getFee());
        assertNull(to.getDescription());
    }

    @Test
    void testMapperTransaction_fromDto_Fee() {

        TransactionDto from = new TransactionDto();
        from.fee(BigDecimal.ONE);
        Transaction to = TransactionDtoMapper.fromDto(from);

        assertNotNull(to.getReferenceNumber());
        assertNull(to.getIban());
        assertNull(to.getTransactionDate());
        assertNull(to.getAmount());
        assertEquals(BigDecimal.ONE, to.getFee());
        assertNull(to.getDescription());
    }

    @Test
    void testMapperTransaction_fromDto_Description() {

        TransactionDto from = new TransactionDto();
        from.description("Restaurant paymen");
        Transaction to = TransactionDtoMapper.fromDto(from);

        assertNotNull(to.getReferenceNumber());
        assertNull(to.getIban());
        assertNull(to.getTransactionDate());
        assertNull(to.getAmount());
        assertNull(to.getFee());
        assertEquals("Restaurant paymen", to.getDescription());
    }

}