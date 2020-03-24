
package com.atsistemas.poc.persistence.repository;

import com.atsistemas.poc.persistence.model.Transaction;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.stream.Stream;

@Repository
public interface TransactionRepository extends CustomRepository<Transaction> {

    Optional<Transaction> findByIban(String iban);
    
}
