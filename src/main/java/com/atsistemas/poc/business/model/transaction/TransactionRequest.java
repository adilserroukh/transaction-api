package com.atsistemas.poc.business.model.transaction;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionRequest {
    private String referenceNumber;
    private TypeChannel typeChannel;

    public enum TypeChannel {
        CLIENT,
        ATM,
        INTERNAL
    }


    private TransactionRequest() {

    }

    public static TransactionRequest.Builder builder() {
        return new TransactionRequest.Builder();
    }

    public static final class Builder {
        private TransactionRequest transaction = new TransactionRequest();

        private Builder() {

        }
        public TransactionRequest.Builder referenceNumber(String referenceNumber) {
            transaction.referenceNumber = referenceNumber;
            return this;
        }

        public TransactionRequest.Builder channel(TypeChannel typeChannel) {
            transaction.typeChannel = typeChannel;
            return this;
        }

        public TransactionRequest create() {
            return transaction;
        }
    }
}
