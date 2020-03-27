package com.atsistemas.poc.util;

import java.util.UUID;

/**
 * Class to generate unique identifier
 */
public class IDGenerator {

    public static String generateEntityId() {
        return UUID.randomUUID().toString().toUpperCase().replace("-", "");
    }
}
