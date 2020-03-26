package com.atsistemas.poc.persistence.model;


import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "TRANSACTION")
@Data
public class TransactionData extends AbstractDomain {

    @Column(name = "REF_NUM")
    private String referenceNumber;

    @Column(name = "IBAN_NUMER", length = 24, nullable = false)
    private String accountIban;

    @Column(name = "DAT_TOOK")
    private LocalDateTime dateTook;

    @Column(name = "AMOUNT", nullable = false)
    private BigDecimal amount;

    @Column(name = "FEE")
    private BigDecimal fee;

    @Column(name = "DESCRIPTION")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    private AccountData account;


}
