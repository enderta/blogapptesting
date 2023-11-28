package com.stepDef;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.utilities.ConfigurationReader;
import io.cucumber.java.en.*;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;

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
        JsonObject body = new JsonObject();
        String creatingBlog="mutation Mutation($title: String!, $content: String!, $author: String!, $imageUrl: String!) {\n" +
                "  createBlogPost(title: $title, content: $content, author: $author, image_url: $imageUrl) {\n" +
                "    author\n" +
                "  }\n" +
                "}"
                ;
        body.addProperty("query", creatingBlog);

        JsonObject variables = new JsonObject();
        variables.addProperty("title", "API");
        variables.addProperty("content", "API");
        variables.addProperty("author", "ender");
        variables.addProperty("imageUrl", "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.oreilly.com%2Flibrary%2Fview%2Frestful-java-web%2F9781789531755%2Fb0d9f0c2-3b1a-4b0e-8b0a-9b0b8b8b0b");
        body.add("variables", variables);

        response = given()
                .contentType("application/json")
                .header("Authorization", tkn)
                .body(body.toString())
                .when().post().prettyPeek();

    }
    @Then("I expect the status code to be {int}")
    public void i_expect_the_status_code_to_be(Integer int1) {
        response.then().statusCode(int1);
    }
    @Then("I verify the response contains the id, title, content and author")
    public void i_verify_the_response_contains_the_id_title_content_and_author() {
       JsonPath jsonPath = response.jsonPath();
            String author = jsonPath.getString("data.createBlogPost.author");
            System.out.println("author = " + author);
        Assert.assertEquals("ender",author);
    }
}
