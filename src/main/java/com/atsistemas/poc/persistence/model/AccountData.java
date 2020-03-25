package com.atsistemas.poc.persistence.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ACCOUNT")
@Getter
@Setter
@NoArgsConstructor
public class AccountData extends AbstractDomain{

    @Column(name = "IBAN", nullable = false)
    private String iban;
    @Column(name = "AMOUNT", nullable = false)
    private Long amount;
}
