package org.prog.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Assertions;


public class RestSteps {

    private String URL;
    @Given("I set {string} for request")
    public String setUrlForRequest(String url) {
        this.URL = url;
        return URL;
    }
    @When("I send GET http request to URL")
    public int sendRequest() {
        return RestAssured.get(setUrlForRequest(URL)).getStatusCode();
    }
    @Then("I receive valid http response code {int}")
    public void receiveResponse(int code) {
        Assertions.assertEquals(code, sendRequest(), "Response is not ok");
    }
}
