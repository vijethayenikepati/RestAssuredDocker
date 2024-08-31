package com.api.stepdefinitions;

import com.api.apiConfig.ApiConfig;
import com.api.context.TestContext;
import com.api.pojos.common.ItemData;
import com.api.pojos.request.Item;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CreateItemSteps {

    private static final Logger LOGGER = LogManager.getLogger(CreateItemSteps.class);
    private final TestContext testContext;
    private Item item;

    public CreateItemSteps() {
        this.testContext = TestContext.getInstance(); // Use singleton instance
    }

    @Given("the following item details:")
    public void createPostApiRequestData(DataTable dataTable) {
        List<Map<String, String>> itemDetails = dataTable.asMaps(String.class, String.class);

        Map<String, String> itemDetail = itemDetails.get(0);
        item = Item.builder()
                .name(itemDetail.get("name"))
                .data(ItemData.builder()
                        .cpuModel(itemDetail.get("cpu_model"))
                        .year(Integer.parseInt(itemDetail.get("year")))
                        .hardDiskSize(itemDetail.get("hardDiskSize"))
                        .price(Double.parseDouble(itemDetail.get("price")))
                        .build())
                .build();
    }

    @When("the request to add the item is made with {int} status code")
    public void sendPostRequest(int statusCode) {

        Response response = given()
                .spec(ApiConfig.requestSpec)  // Add this while debugging
                .contentType("application/json")
                .body(item)
                .when()
                .post()
                .then()
                .spec(ApiConfig.responseSpec)
                .statusCode(200)
                .extract()
                .response();

        assertThat(response.getStatusCode()).isEqualTo(statusCode);
        assertThat(response.jsonPath().getString("name")).isNotNull();
        assertThat(response.jsonPath().getString("data['CPU model']")).isNotNull();
        assertThat(response.jsonPath().getString("data['Hard disk size']")).isNotNull();
        assertThat(response.jsonPath().getDouble("data.price")).isGreaterThan(0);

        testContext.setPostItemResponse(response);

        LOGGER.info("Response received for POST Request: {}", response.asPrettyString());
        LOGGER.info("Item id: {}", response.jsonPath().getString("id"));
    }

    @Then("the following item is created:")
    public void verifyPostApiResponse(DataTable dataTable) {
        List<Map<String, String>> expectedItemDetails = dataTable.asMaps(String.class, String.class);
        Map<String, String> expectedItem = expectedItemDetails.get(0);

        LOGGER.info("Created Item ID: {}", testContext.getPostItemResponse().jsonPath().getString("id"));

        Response response = testContext.getPostItemResponse();


        assertThat(response.jsonPath().getString("data['Hard disk size']")).isEqualTo(expectedItem.get("hardDiskSize"));
        assertThat(response.jsonPath().getDouble("data.price")).isEqualTo(Double.parseDouble(expectedItem.get("price")));

        assertThat(response.jsonPath().getDouble("data.year")).isEqualTo(Integer.parseInt(expectedItem.get("year")));
        assertThat(response.jsonPath().getString("name")).isEqualTo(expectedItem.get("name"));
        assertThat(response.jsonPath().getString("data['CPU model']")).isEqualTo(expectedItem.get("cpu_model"));

    }
}
