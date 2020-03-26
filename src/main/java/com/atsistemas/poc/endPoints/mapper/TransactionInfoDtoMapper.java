package com.atsistemas.poc.endPoints.mapper;

import com.atsistemas.generated.model.StatusTransactionResponseDto;
import com.atsistemas.generated.model.TransationInfoRequestDto;
import com.atsistemas.poc.business.model.transaction.TransactionInfo;
import com.atsistemas.poc.business.model.transaction.TransactionRequest;

public class TransactionInfoDtoMapper {
    public static StatusTransactionResponseDto toStatusDto(TransactionInfo from) {

        StatusTransactionResponseDto to = new StatusTransactionResponseDto();

        to.setReference(from.getReferenceNumber());
        // to.setStatus(from.getStatus());
        to.setAmount(from.getAmount());
        to.setFee(from.getFee());
        return to;
    }


    public static TransactionRequest fromRequestDto(TransationInfoRequestDto from) {

        TransactionRequest to = TransactionRequest.builder()
                .referenceNumber(from.getReference())
                .channel(from.getChannel() != null ? TransactionRequest.TypeChannel.valueOf(from.getChannel().toString()) : null)
                .create();
        return to;
    }
}
