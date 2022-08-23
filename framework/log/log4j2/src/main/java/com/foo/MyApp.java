package com.foo;

import java.util.concurrent.Executors;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

public class MyApp {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(MyApp.class);

    public static void main(final String... args) {
        Exception e = new Exception();
//        MDC.put("apiVersion", "${${ctx:apiVersion}}");
//        MDC.put("transaction.owner", "MDC-owner");
        LOGGER.info("Entering application.", e);

        Executors.newSingleThreadExecutor().submit(() -> {
            LOGGER.info("xxxx", e);
        });
//        Bar bar = new Bar();
//        if (!bar.doIt()) {
//            LOGGER.error("Didn't do it.");
//        }
//        LOGGER.info("Exiting application.");
    }
}