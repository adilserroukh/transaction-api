package com.atsistemas.poc.controller.mapper;

import com.atsistemas.generated.model.TransationInfoRequestDto;
import com.atsistemas.poc.business.model.transaction.ChannelType;
import com.atsistemas.poc.business.model.transaction.TransactionRequest;

public class TransactionInfoDtoMapper {
    private TransactionInfoDtoMapper() {

    }

    public static TransactionRequest fromRequestDto(TransationInfoRequestDto from) {

        return TransactionRequest.builder()
                .referenceNumber(from.getReference())
                .channel(from.getChannel() != null ? ChannelType.valueOf(from.getChannel().toString()) : null)
                .create();
    }
}
