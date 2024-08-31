package com.api.stepdefinitions;

import com.api.context.TestContext;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class Hooks {
    @BeforeEach
    public void setUp() {
        TestContext.getInstance();
    }

    @AfterEach
    public void tearDown() {
        TestContext.getInstance().setPostItemResponse(null);
    }
}
