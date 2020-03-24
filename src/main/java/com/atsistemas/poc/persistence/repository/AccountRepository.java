
package com.atsistemas.poc.persistence.repository;

import com.atsistemas.poc.persistence.model.Account;
import com.atsistemas.poc.persistence.model.Transaction;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends CustomRepository<Account> {


}
