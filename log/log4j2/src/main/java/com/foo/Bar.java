package com.foo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Bar {

    private static final Logger LOGGER = LoggerFactory.getLogger("Bar.class");

    public boolean doIt() {
        LOGGER.error("Did it again!");
        LOGGER.info("Did it again!");
        return false;
    }
}