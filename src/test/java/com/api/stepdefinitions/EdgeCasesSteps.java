package com.api.stepdefinitions;

import com.api.apiConfig.ApiConfig;
import com.api.utils.DataGeneratorUtils;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class EdgeCasesSteps {

    private static final Logger LOGGER = LogManager.getLogger(EdgeCasesSteps.class);
    private Response response;

    @When("the request to get an object with a non-existent ID is made")
    public void sendGetItemRequestWithInvalidId() {
        String randomId = DataGeneratorUtils.generateRandomId();
        LOGGER.info("Generated random ID: {}", randomId);

        response = given()
                .spec(ApiConfig.requestSpec)
                .when()
                .get("/{id}", randomId)
                .then()
                .spec(ApiConfig.responseSpec)
                .extract()
                .response();

        LOGGER.info("Response for get object with non-existent ID: {}", response.asString());
    }

    @Then("a 404 Not Found response is returned")
    public void verifyNotFoundResponse() {
        assertThat(response.getStatusCode()).isEqualTo(404);
    }

    @When("the request to delete an object with a non-existent ID is made")
    public void sendDeleteItemRequestWithInvalidId() {
        String randId = DataGeneratorUtils.generateRandomId();
        LOGGER.info("Generated random ID : {}", randId);

        response = given()
                .spec(ApiConfig.requestSpec)
                .when()
                .delete("/{id}", randId)
                .then()
                .spec(ApiConfig.responseSpec)
                .extract()
                .response();

        LOGGER.info("Response for delete object with non-existent ID: " + response.asString());
    }


}


