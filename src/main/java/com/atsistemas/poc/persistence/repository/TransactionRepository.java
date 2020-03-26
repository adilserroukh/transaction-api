
package com.atsistemas.poc.persistence.repository;

import com.atsistemas.poc.persistence.model.TransactionData;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends CustomRepository<TransactionData> {
    List<TransactionData> findAllByAccountIban(String iban);

}
