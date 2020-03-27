package com.atsistemas.poc.persistence.service;


import com.atsistemas.poc.persistence.model.TransactionData;
import com.atsistemas.poc.persistence.repository.TransactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TransactionService {

    private final TransactionRepository repository;

    public TransactionService(TransactionRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public List<TransactionData> findByIban(String iban) {
        return repository.findAllByAccountIban(iban);
    }

    @Transactional
    public Optional<TransactionData> findByReference(String reference) {
        return repository.findByReferenceNumber(reference);
    }

    @Transactional
    public TransactionData create(TransactionData transaction) {
        return repository.save(transaction);
    }

}