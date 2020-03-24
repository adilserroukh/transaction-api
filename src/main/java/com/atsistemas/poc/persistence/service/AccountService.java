package com.atsistemas.poc.persistence.service;

import com.atsistemas.poc.persistence.model.Account;
import com.atsistemas.poc.persistence.repository.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AccountService {
    private final AccountRepository repository;

    public AccountService(AccountRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Account create(Account account) {
        return repository.save(account);
    }

    @Transactional
    public void delete(Account account) {
        repository.delete(account);
    }
}
