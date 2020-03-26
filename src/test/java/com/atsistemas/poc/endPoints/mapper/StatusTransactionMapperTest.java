package com.atsistemas.poc.endPoints.mapper;

import com.atsistemas.generated.model.StatusTransactionResponseDto;
import com.atsistemas.poc.business.model.transaction.TransactionInfo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StatusTransactionMapperTest {

    @Test
    void toDto() {


        assertNull(StatusTransactionMapper.toDto(null));

        StatusTransactionResponseDto.StatusEnum current = StatusTransactionMapper.toDto(TransactionInfo.StatusTransaction.FUTURE);
        assertNotNull(current);
        assertEquals(StatusTransactionResponseDto.StatusEnum.FUTURE, current);

        current = StatusTransactionMapper.toDto(TransactionInfo.StatusTransaction.INVALID);
        assertNotNull(current);
        assertEquals(StatusTransactionResponseDto.StatusEnum.INVALID, current);

        current = StatusTransactionMapper.toDto(TransactionInfo.StatusTransaction.SETTLED);
        assertNotNull(current);
        assertEquals(StatusTransactionResponseDto.StatusEnum.SETTLED, current);

        current = StatusTransactionMapper.toDto(TransactionInfo.StatusTransaction.PENDING);
        assertNotNull(current);
        assertEquals(StatusTransactionResponseDto.StatusEnum.PENDING, current);


    }
}