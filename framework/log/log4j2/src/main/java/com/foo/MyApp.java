package com.foo;

import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

public class MyApp {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(MyApp.class);
    private static final org.slf4j.Logger LOGGERV2 = LoggerFactory.getILoggerFactory().getLogger(MyApp.class.getName());
    private static final org.slf4j.Logger LOGGERV3 = LoggerFactory.getLogger(MyApp.class);
    private static final org.slf4j.Logger LOGGERV4 = LoggerFactory.getILoggerFactory().getLogger(MyApp.class.getName());

    public static void main(final String... args) {

        MDC.put("transaction.owner", "MDC-owner");
        LOGGER.info("Entering application.");
        LOGGERV2.info("Entering application.");
        LOGGERV3.info("Entering application.");
        LOGGERV4.info("Entering application.");
        Bar bar = new Bar();
        if (!bar.doIt()) {
            LOGGER.error("Didn't do it.");
            LOGGERV2.error("Didn't do it.");
            LOGGERV3.error("Didn't do it.");
            LOGGERV4.error("Didn't do it.");
        }
        LOGGER.info("Exiting application.");
        LOGGERV2.info("Exiting application.");
        LOGGERV3.info("Exiting application.");
        LOGGERV4.info("Exiting application.");
    }
}