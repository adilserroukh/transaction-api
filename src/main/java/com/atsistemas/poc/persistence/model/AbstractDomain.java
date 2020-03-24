package com.atsistemas.poc.persistence.model;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * Domain parent de tous les domain.
 */
@MappedSuperclass
public abstract class AbstractDomain implements Serializable {

    /**
     * Identifiant BD unique
     */
    @Id
    @GeneratedValue(
            strategy= GenerationType.AUTO,
            generator="native"
    )
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    @Getter
    @Setter
    private Long id;

}
