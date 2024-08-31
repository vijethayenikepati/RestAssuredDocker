package com.api.stepdefinitions;

import com.api.apiConfig.ApiConfig;
import com.api.context.TestContext;
import com.api.utils.FileUtils;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.Assertions;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class GetItemSteps {

    private static final Logger LOGGER = LogManager.getLogger(GetItemSteps.class);
    private final TestContext testContext;

    public GetItemSteps() {
        this.testContext = TestContext.getInstance();
    }

    @Given("an existing item ID from creation")
    public void verifyItemIdFromPostRequest() {
        Assertions.assertThat(testContext.getPostItemResponse().jsonPath().getString("id")).isNotEmpty();

        LOGGER.info("Using Item ID: {}", testContext.getPostItemResponse().jsonPath().getString("id"));
    }

    @When("the request to retrieve the item is made with {int} status code")
    public void sendGetRequestWithId(int statusCode) {
        String id = testContext.getPostItemResponse().jsonPath().getString("id");

        Response response = given()
                .spec(ApiConfig.requestSpec)
                .when()
                .get("/{id}", id)
                .then()
                .spec(ApiConfig.responseSpec)
                .statusCode(200)
                .extract()
                .response();

        Assertions.assertThat(response.getStatusCode()).isEqualTo(statusCode);
        Assertions.assertThat(response).isNotNull();

        LOGGER.info("Response received for GET Request: {}", response.asPrettyString());

        testContext.setGetItemResponse(response);

    }

    @Then("the item retrieved should have the following details:")
    public void verifyGetRequestItemResponse(DataTable dataTable) {
        List<Map<String, String>> expectedItemDetails = dataTable.asMaps(String.class, String.class);
        Map<String, String> expectedItem = expectedItemDetails.get(0);

        Response getItemResponse = testContext.getGetItemResponse();

        assertThat(getItemResponse.jsonPath().getString("data['Hard disk size']")).isEqualTo(expectedItem.get("hardDiskSize"));
        assertThat(getItemResponse.jsonPath().getDouble("data.price")).isEqualTo(Double.parseDouble(expectedItem.get("price")));

        assertThat(getItemResponse.jsonPath().getDouble("data.year")).isEqualTo(Integer.parseInt(expectedItem.get("year")));
        assertThat(getItemResponse.jsonPath().getString("name")).isEqualTo(expectedItem.get("name"));
        assertThat(getItemResponse.jsonPath().getString("data['CPU model']")).isEqualTo(expectedItem.get("cpu_model"));

    }

    @And("the response should match the JSON schema")
    public void theResponseShouldMatchTheJSONSchema() {
        String schema = FileUtils.readFileAsString("src/test/resources/schemas/GetResponseSchema.json");
        Response response = testContext.getGetItemResponse();
        LOGGER.info("Validating Response with JSON schema");

        response.then().assertThat().body(matchesJsonSchema(schema));
        LOGGER.info("Schema Validation Assertion passed");
    }
}
