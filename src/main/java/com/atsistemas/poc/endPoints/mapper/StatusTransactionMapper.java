package com.atsistemas.poc.endPoints.mapper;

import com.atsistemas.generated.model.StatusTransactionResponseDto;
import com.atsistemas.poc.business.model.transaction.TransactionInfo;

public class StatusTransactionMapper {
    public static StatusTransactionResponseDto.StatusEnum toDto(TransactionInfo.StatusTransaction from) {

        if (from == null || from.equals("")) {
            return null;
        } else {
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
}