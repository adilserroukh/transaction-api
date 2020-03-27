package com.atsistemas.poc.controller.mapper;

import com.atsistemas.generated.model.StatusTransactionResponseDto;
import com.atsistemas.poc.business.model.transaction.TransactionInfo;

public class TransactionStatusMapper {

    private TransactionStatusMapper() {
    }

    public static StatusTransactionResponseDto.StatusEnum toDto(TransactionInfo.StatusTransaction from) {
        if (from == null) {
            return null;
        }
        
        switch (from) {
            case FUTURE:
                return StatusTransactionResponseDto.StatusEnum.FUTURE;
            case INVALID:
                return StatusTransactionResponseDto.StatusEnum.INVALID;
            case PENDING:
                return StatusTransactionResponseDto.StatusEnum.PENDING;
            case SETTLED:
                return StatusTransactionResponseDto.StatusEnum.SETTLED;
            default:
                return null;
        }
    }
}