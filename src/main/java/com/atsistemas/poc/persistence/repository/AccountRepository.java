
package com.atsistemas.poc.persistence.repository;

import com.atsistemas.poc.persistence.model.AccountData;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends CustomRepository<AccountData> {
    Optional<AccountData> findByIban(String iban);
}
