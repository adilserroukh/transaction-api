package com.atsistemas.poc.business.ports.check;

import com.atsistemas.poc.business.model.transaction.TransactionInfo;
import com.atsistemas.poc.persistence.model.TransactionData;

public abstract class CalculTransInfo {
    public abstract TransactionInfo check(TransactionData tranData);
}
