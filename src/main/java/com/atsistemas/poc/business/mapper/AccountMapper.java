package com.atsistemas.poc.business.mapper;

import com.atsistemas.poc.business.model.Account;
import com.atsistemas.poc.persistence.model.AccountData;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AccountMapper {

    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    AccountData fromAccount(Account from);

}
