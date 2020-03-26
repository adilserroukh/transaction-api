package com.atsistemas.poc.commons.util;

import java.util.UUID;

public class IDGenerator {
    public static String generateEntityId(){
        return UUID.randomUUID().toString().toUpperCase().replace("-","");
    }
}
