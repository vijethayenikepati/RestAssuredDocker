package com.api.context;

import io.restassured.response.Response;
import lombok.Data;
import org.junit.After;

@Data
public class TestContext {

    private Response postItemResponse;
    private Response getItemResponse;
    private String id;

    // ThreadLocal to store the TestContext instance for each thread
    private static final ThreadLocal<TestContext> testContext = ThreadLocal.withInitial(TestContext::new);

    // Private constructor to prevent instantiation from outside
    private TestContext() {
    }

    // Method to get the TestContext instance for the current thread
    public static TestContext getInstance() {
        return testContext.get();
    }

    // Optional: Cleanup method to remove the TestContext instance after the test is done
    public static void remove() {
        testContext.remove();
    }

}
