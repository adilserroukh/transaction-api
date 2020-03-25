package com.atsistemas.poc.persistence.service;


import com.atsistemas.poc.persistence.model.TransactionData;
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
    public Optional<TransactionData> findByIban(String iban) {
        return repository.findByIban(iban);
    }

    @Transactional
    public Stream<TransactionData> findAll() {

        return repository.findAll();
    }

    @Transactional
    public TransactionData create(TransactionData transaction) {
        return repository.save(transaction);
    }

}