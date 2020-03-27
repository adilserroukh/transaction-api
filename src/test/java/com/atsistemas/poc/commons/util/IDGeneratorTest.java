package com.atsistemas.poc.commons.util;


import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class IDGeneratorTest {

    @Test
    public void testGenerateId() {
        String identifierGenerated = IDGenerator.generateEntityId();

        assertNotNull(identifierGenerated);
        assertEquals(32, identifierGenerated.length());
    }



}