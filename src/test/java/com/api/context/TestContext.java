package com.api.context;

import io.restassured.response.Response;
import lombok.Data;

@Data
public class TestContext {
    private static TestContext instance;
    private Response postItemResponse;
    private Response getItemResponse;
    private String id;

    private TestContext() {
    }

    public static synchronized TestContext getInstance() {
        if (instance == null) {
            instance = new TestContext();
        }
        return instance;
    }
}
