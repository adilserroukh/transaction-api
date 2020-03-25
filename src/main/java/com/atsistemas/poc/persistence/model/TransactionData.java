package com.atsistemas.poc.persistence.model;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "TRANSACTION")
@Getter
@Setter
@NoArgsConstructor
public class TransactionData extends AbstractDomain {

    @Column(name = "IBAN", nullable = false)
    private String iban;
    @Column(name = "DATE_TRANS")
    private Date transactionDate;
    @Column(name = "AMOUNT", nullable = false)
    private Long amount;
    @Column(name = "FEE")
    private Integer fee;
    @Column(name = "DESCRIPTION")
    private String description;

}
