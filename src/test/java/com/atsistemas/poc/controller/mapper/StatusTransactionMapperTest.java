package com.atsistemas.poc.controller.mapper;

import com.atsistemas.generated.model.StatusTransactionResponseDto;
import com.atsistemas.poc.business.model.transaction.TransactionInfo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StatusTransactionMapperTest {

    @Test
    void toDto() {


        assertNull(TransactionStatusMapper.toDto(null));

        StatusTransactionResponseDto.StatusEnum current = TransactionStatusMapper.toDto(TransactionInfo.StatusTransaction.FUTURE);
        assertNotNull(current);
        assertEquals(StatusTransactionResponseDto.StatusEnum.FUTURE, current);

        current = TransactionStatusMapper.toDto(TransactionInfo.StatusTransaction.INVALID);
        assertNotNull(current);
        assertEquals(StatusTransactionResponseDto.StatusEnum.INVALID, current);

        current = TransactionStatusMapper.toDto(TransactionInfo.StatusTransaction.SETTLED);
        assertNotNull(current);
        assertEquals(StatusTransactionResponseDto.StatusEnum.SETTLED, current);

        current = TransactionStatusMapper.toDto(TransactionInfo.StatusTransaction.PENDING);
        assertNotNull(current);
        assertEquals(StatusTransactionResponseDto.StatusEnum.PENDING, current);


    }
}