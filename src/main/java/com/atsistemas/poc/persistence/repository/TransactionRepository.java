
package com.atsistemas.poc.persistence.repository;

import com.atsistemas.poc.persistence.model.TransactionData;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends CustomRepository<TransactionData> {

    List<TransactionData> findAllByAccountIban(String iban);

    Optional<TransactionData> findByReferenceNumber(String reference);
    
}
