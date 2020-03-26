package com.atsistemas.poc.persistence.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "ACCOUNT")
@Getter
@Setter
@NoArgsConstructor
public class AccountData extends AbstractDomain{

    @Column(name = "IBAN_NUMER", nullable = false)
    private String iban;

    @Column(name = "AMOUNT", nullable = false)
    private BigDecimal amount;

    @OneToMany(mappedBy = "account",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<TransactionData> transactions;
}
