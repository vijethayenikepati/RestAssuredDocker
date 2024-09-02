package com.api.stepdefinitions;

import com.api.context.TestContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;

public class Hooks {

    private static final Logger LOGGER = LogManager.getLogger(Hooks.class);

    @Before
    public void setUp() {
        LOGGER.info("Starting scenario...");
    }


    @After
    public void tearDown() {
        TestContext.remove();
        LOGGER.info("TestContext has been removed after scenario.");
    }
}
