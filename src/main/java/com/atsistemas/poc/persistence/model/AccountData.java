package com.atsistemas.poc.persistence.model;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ACCOUNT")
@Data
public class AccountData extends AbstractDomain{

    @Column(name = "IBAN", nullable = false)
    private String iban;
    @Column(name = "AMOUNT", nullable = false)
    private Long amount;
}
