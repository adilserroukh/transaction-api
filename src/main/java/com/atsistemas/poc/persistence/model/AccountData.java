package com.atsistemas.poc.persistence.model;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "ACCOUNT")
@Data
public class AccountData extends AbstractDomain{

    @Column(name = "IBAN", nullable = false)
    private String iban;
    @Column(name = "AMOUNT", nullable = false)
    private Long amount;
}
