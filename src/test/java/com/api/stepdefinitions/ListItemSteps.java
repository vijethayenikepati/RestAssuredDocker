package com.api.stepdefinitions;

import com.api.apiConfig.ApiConfig;
import com.api.context.TestContext;
import com.api.pojos.response.PostItemResponse;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class ListItemSteps {
    private static final Logger LOGGER = LogManager.getLogger(ListItemSteps.class);
    private TestContext testContext;
    private List<PostItemResponse> itemsList;

    public ListItemSteps() {
        this.testContext = TestContext.getInstance(); // Use singleton instance
    }

    @When("the request to retrieve the list of items is made and a 200 response code is returned")
    public void sendGetRequestForItemsList() {
        Response response = given()
                .spec(ApiConfig.requestSpec)
                .get()
                .then()
                .statusCode(200)
                .spec(ApiConfig.responseSpec)
                .extract()
                .response();

        PostItemResponse[] itemsArray = response.as(PostItemResponse[].class);
        itemsList = Arrays.asList(itemsArray);

        LOGGER.info("Retrieved list of items. Total items: " + itemsList.size());
    }

    @Then("the list of items should not be empty")
    public void verifyGetListResponse() {
        assertThat(itemsList).isNotEmpty();
    }

}
