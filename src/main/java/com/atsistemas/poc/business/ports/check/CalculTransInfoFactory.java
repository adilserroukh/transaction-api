package com.atsistemas.poc.business.ports.check;


import com.atsistemas.poc.business.model.transaction.ChannelType;

public class CalculTransInfoFactory {
    private CalculTransInfoFactory() {
    }
    public static CalculTransInfo check(ChannelType channel) {

        switch (channel) {
            case INTERNAL:
                return new CalculTransInfoInternal();
            case ATM:
                return new CalculTransInfoAtm();
            case CLIENT:
                return new CalculTransInfoClient();
            default:
                return null;
        }
    }

}
