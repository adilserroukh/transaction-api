package com.atsistemas.poc.persistence.service;

import com.atsistemas.poc.persistence.model.AccountData;
import com.atsistemas.poc.persistence.repository.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class AccountService {
    private final AccountRepository repository;

    public AccountService(AccountRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public AccountData create(AccountData accountData) {
        return repository.save(accountData);
    }

    @Transactional
    public void delete(AccountData accountData) {
        repository.delete(accountData);
    }

    @Transactional
    public Optional<AccountData> findByIban(String iban) {
        return repository.findByIban(iban);
    }
}
