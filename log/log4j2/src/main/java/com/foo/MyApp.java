package com.foo;

import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

public class MyApp {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(StackLocatorTest.class);

    public static void main(final String... args) {
        MDC.put("transaction.owner", "MDC-owner");
        LOGGER.info("Entering application.");
        Bar bar = new Bar();
        if (!bar.doIt()) {
            LOGGER.error("Didn't do it.");
        }
        LOGGER.info("Exiting application.");
    }
}