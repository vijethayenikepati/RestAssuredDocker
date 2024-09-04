package com.api.stepdefinitions;

import com.api.apiConfig.ApiConfig;
import com.api.context.TestContext;
import com.api.pojos.common.ItemData;
import com.api.pojos.response.GetItemResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class DynamicDataSteps {

    private static final Logger LOGGER = LogManager.getLogger(DynamicDataSteps.class);
    private final TestContext testContext;

    public DynamicDataSteps(){
        this.testContext= TestContext.getInstance();
    }

    @When("the request to retrieve is made with {int} status code")
    public void the_request_to_retrieve_is_made_with_status_code(Integer int1) throws JsonProcessingException {
        GetItemResponse itemResponse= RestAssured.given(ApiConfig.requestSpec)
                .when()
                .get("/{id}", 7)
                .then()
                .spec(ApiConfig.responseSpec)
                .extract()
                .as(GetItemResponse.class);

        ItemData itemData = RestAssured.given(ApiConfig.requestSpec)
                .when()
                .get("/{id}", 1)
                .then()
                .spec(ApiConfig.responseSpec)
                .extract()
                        .jsonPath()
                .getObject("data",ItemData.class);

        System.out.println(itemResponse);
        System.out.println(itemData);

        testContext.setGetModelItemResponse(itemResponse);


    }
    @When("the response should be verified dynamically")
    public void the_response_should_be_verified_dynamically() {
        GetItemResponse itemResponse = testContext.getGetModelItemResponse();
        //Add assertions
    }

    @When("the request to retrieve the list of items is made with a {int} response code")
    public void the_request_to_retrieve_the_list_of_items_is_made_with_a_response_code(Integer status) {
        Response response = given()
                .spec(ApiConfig.requestSpec)
                .get()
                .then()
                .statusCode(status)
                .spec(ApiConfig.responseSpec)
                .extract()
                .response();

        List<GetItemResponse> getItemResponses = response.as(new TypeRef<List<GetItemResponse>>() {});
        System.out.println(getItemResponses.size());


    }

    @When("the request to retrieve the list of items without pojo is made with a {int} response code")
    public void the_request_to_retrieve_the_list_of_items_without_pojo_is_made_with_a_response_code(Integer int1) {
            Response response = given()
                .spec(ApiConfig.requestSpec)
                .get()
                .then()
                .statusCode(200)
                .spec(ApiConfig.responseSpec)
                .extract()
                .response();

        List<Map<String, Object>> maps = response.as(new TypeRef<List<Map<String, Object>>>() {
        });
        System.out.println(maps);
    }



}
