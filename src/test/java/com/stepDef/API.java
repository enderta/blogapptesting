package com.stepDef;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.utilities.ConfigurationReader;
import io.cucumber.java.en.*;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class API {
    String tkn;
    Response response;
    @Given("I am an authenticated user")
    public void i_am_an_authenticated_user() {
           // Input body setup
        JsonObject body = new JsonObject();

        // The mutation provided
        String graphQlMutation = "mutation LoginUser($username: String!, $password: String!) {" +
                "loginUser(username: $username, password: $password) { token } }";

        body.addProperty("query", graphQlMutation);

        // Add variables to the request
        JsonObject variables = new JsonObject();
        variables.addProperty("username", ConfigurationReader.getProperty("username"));
        variables.addProperty("password", ConfigurationReader.getProperty("password"));
        body.add("variables", variables);

        // Execute Post Request
         response = given()
                .contentType("application/json")
                .body(body.toString())
                .when().post().prettyPeek();

        JsonPath jsonPath = response.jsonPath();
        tkn = jsonPath.getString("data.loginUser.token");
        System.out.println("tkn = " + tkn);
    }
    @When("I perform a POST request to {string} with valid payload")
    public void i_perform_a_post_request_to_with_valid_payload(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("I expect the status code to be {int}")
    public void i_expect_the_status_code_to_be(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("I verify the response contains the id, title, content and author")
    public void i_verify_the_response_contains_the_id_title_content_and_author() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
}
