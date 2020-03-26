
package com.atsistemas.poc.persistence.repository;

import com.atsistemas.poc.persistence.model.TransactionData;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransactionRepository extends CustomRepository<TransactionData> {

    Optional<TransactionData> findByAccountIban(String iban);

}
