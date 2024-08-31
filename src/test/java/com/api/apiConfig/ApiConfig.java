package com.api.apiConfig;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ApiConfig {

    private static final Logger LOGGER = LogManager.getLogger(ApiConfig.class);

    public static RequestSpecification requestSpec;
    public static ResponseSpecification responseSpec;

    static {
        RestAssured.baseURI = "https://api.restful-api.dev/objects";

        requestSpec = new RequestSpecBuilder()
                //.log(LogDetail.ALL)   // Enable while debugging
                .build();
        responseSpec = new ResponseSpecBuilder()
               // .log(LogDetail.ALL)    // Enable while debugging
                .build();

        LOGGER.info("Common API Configuration Initialized");
    }
}
