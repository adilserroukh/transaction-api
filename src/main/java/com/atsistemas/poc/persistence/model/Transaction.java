package com.atsistemas.poc.persistence.model;


import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "TRANSATION")
@Data
public class Transaction extends AbstractDomain {

    @Column(name = "ACCOUNT_IBAN", nullable = false)
    private String iban;
    @Column(name = "DATE_TRANS")
    private LocalDateTime transactionDate;
    @Column(name = "AMOUNT", nullable = false)
    private Long amout;
    @Column(name = "FEE")
    private Integer fee;
    @Column(name = "DESCRIPTION")
    private String description;

}
