package com.atsistemas.poc.persistence.service;


import com.atsistemas.poc.persistence.model.Transaction;
import com.atsistemas.poc.persistence.repository.TransactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.stream.Stream;

@Service
@Transactional
public class TransactionService {

    private final TransactionRepository repository;

    public TransactionService(TransactionRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Optional<Transaction> findByIban(String iban) {
        return repository.findByIban(iban);
    }

    @Transactional
    public Stream<Transaction> findAll() {
        return repository.findAll();
    }

}