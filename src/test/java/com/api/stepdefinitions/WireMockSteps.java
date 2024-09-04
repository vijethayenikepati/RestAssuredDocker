package com.api.stepdefinitions;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class WireMockSteps {

    private Response response;

    @Before
    public void setUp() {
        WireMockServer wireMockServer = new WireMockServer(8080);
        wireMockServer.start();
        WireMock.configureFor("localhost", 8080);
    }

    @Given("the API is set up to return a 500 error on GET")
    public void setUp500ErrorOnGet() {
//        stubFor(WireMock.get(WireMock.urlPathMatching("/objects/.*"))
//                .willReturn(WireMock.aResponse()
//                        .withStatus(500)));
        stubFor(get(urlPathMatching("/objects/.*"))
                .willReturn(aResponse()
                        .withStatus(500)));

    }

    @When("I send a GET request for an object with id {string}")
    public void sendGetRequest(String id) {
        response = RestAssured.given()
                .baseUri("http://localhost:8080")
                .get("/objects/" + id);
    }

    @Then("I should receive a 500 Internal Server Error")
    public void verify500Error() {
        Assert.assertEquals(500, response.getStatusCode());
    }


}
