package com.atsistemas.poc.business.model.transaction;

import lombok.Getter;

@Getter
public class TransactionRequest {
    private String referenceNumber;
    private ChannelType channelType;




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

        public TransactionRequest.Builder channel(ChannelType channelType) {
            transaction.channelType = channelType;
            return this;
        }

        public TransactionRequest create() {
            return transaction;
        }
    }
}
