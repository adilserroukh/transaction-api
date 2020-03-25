package com.atsistemas.poc.business.mapper;

import com.atsistemas.poc.business.model.Transaction;
import com.atsistemas.poc.persistence.model.TransactionData;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface  TransactionMapper {

    TransactionMapper INSTANCE = Mappers.getMapper(TransactionMapper.class);

    TransactionData fromTransaction(Transaction from);


}
