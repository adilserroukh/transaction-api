package com.atsistemas.poc.persistence.repository;


import com.atsistemas.poc.persistence.model.AbstractDomain;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.Repository;

import java.util.stream.Stream;

/**
 * Interface mère des repository.
 */
public interface CustomRepository<S extends AbstractDomain> extends Repository<S, Long>, QuerydslPredicateExecutor<S> {

    Stream<S> findAll();

    void delete(S entity);

    void deleteAllInBatch();

    S save(S entity);
}