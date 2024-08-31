package com.api.stepdefinitions;

import com.api.apiConfig.ApiConfig;
import com.api.context.TestContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.Assertions;

import static io.restassured.RestAssured.given;

public class DeleteItemSteps {

    private static final Logger LOGGER = LogManager.getLogger(DeleteItemSteps.class);
    private final TestContext testContext;

    public DeleteItemSteps() {
        this.testContext = TestContext.getInstance();
    }

    @When("the request to delete the item is made with {int} status code")
    public void sendDeleteRequest(int statusCode) {
        String id = testContext.getPostItemResponse().jsonPath().getString("id");

        Response deleteResponse = given()
                .spec(ApiConfig.requestSpec)
                .when()
                .delete("/{id}", id)
                .then()
                .spec(ApiConfig.responseSpec)
                .statusCode(200)
                .extract()
                .response();

        Assertions.assertThat(deleteResponse.getStatusCode()).isEqualTo(statusCode);
        LOGGER.info("DELETE Response received: {}", deleteResponse.asPrettyString());
    }

    @Then("the item should no longer be retrievable")
    public void verifyItemNotPresentAfterDelete() {
        String id = testContext.getPostItemResponse().jsonPath().getString("id");

        Response response = given()
                .when()
                .get("/{id}", id)
                .then()
                .extract()
                .response();

        LOGGER.info("Verify DELETE After ID is deleted: {}", response.asPrettyString());

        Assertions.assertThat(response.getStatusCode()).isEqualTo(404);
    }

}
