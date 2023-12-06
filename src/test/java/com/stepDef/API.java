package com.stepDef;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.utilities.BrowserUtils;
import com.utilities.ConfigurationReader;
import com.utilities.Driver;
import io.cucumber.java.en.*;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class API {
    Response response;
    Response response2;
    String updateBlogPostId;
    String author;
    String title;
    String content;
    String authorUpdate;
    static String id;
    static int numberofPosts;
    static String tkn;

    @Given("I am an authenticated user")
    public void i_am_an_authenticated_user() {
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
        API.tkn = jsonPath.getString("data.loginUser.token");
        System.out.println("tkn = " +  API.tkn);
    }
    @When("I perform a POST request to {string} with valid payload")
    public void i_perform_a_post_request_to_with_valid_payload(String string) {
        JsonObject body = new JsonObject();
        String creatingBlog="mutation Mutation($title: String!, $content: String!, $author: String!, $imageUrl: String!) {\n" +
                "  createBlogPost(title: $title, content: $content, author: $author, image_url: $imageUrl) {\n" +
                "    author\n" +
                "id\n" +
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
                .header("Authorization",  API.tkn)
                .body(body.toString())
                .when().post().prettyPeek();
        JsonPath jsonPath = response.jsonPath();
        API.id = jsonPath.getString("data.createBlogPost.id");
        System.out.println("id = " + API.id);
    }
    @Then("I expect the status code to be {int}")
    public void i_expect_the_status_code_to_be(Integer int1) {
        response.then().statusCode(int1);
    }
    @Then("I verify the response contains the id, title, content and author")
    public void i_verify_the_response_contains_the_id_title_content_and_author() {
        JsonPath jsonPath = response.jsonPath();
        API.id = jsonPath.getString("data.createBlogPost.id");
        System.out.println("id = " +API.id);
        String author = jsonPath.getString("data.createBlogPost.author");
        System.out.println("author = " + author);
        Assert.assertEquals("ender",author);
    }
    @When("I perform a POST request to {string} with valid payload for update")
    public void i_perform_a_post_request_to_with_valid_payload_for_update(String string) {

        String updateBlog = "mutation Mutation($updateBlogPostId: ID!, $title: String!, $content: String!, $author: String!) {\n" +
                "  updateBlogPost(id: $updateBlogPostId, title: $title, content: $content, author: $author) {\n" +
                "    author\n" +
                "  }\n" +
                "}";

// Create a new JsonObject for the second GraphQL mutation
        JsonObject body2 = new JsonObject();
        body2.addProperty("query", updateBlog);

        JsonObject variables2 = new JsonObject();
        variables2.addProperty("updateBlogPostId", API.id);
        variables2.addProperty("title", "API");
        variables2.addProperty("content", "API");
        variables2.addProperty("author", "ender2");

        body2.add("variables", variables2);

         response2 = given()
                .contentType(ContentType.JSON)
                .header("Authorization",  API.tkn)
                .body(body2.toString())
                .when().post().prettyPeek(); // Replace "/your_endpoint" with your actual endpoint
        System.out.println(response2.asString());
    }

    @Then("I verify the response contains the new author")
    public void i_verify_the_response_contains_the_new_author() {
        JsonObject body = new JsonObject();
        String blogPosts = "query Query {\n" +
                "  getBlogPosts {\n" +
                "    author\n" +
                "    id\n" +
                "    created_at\n" +
                "  }\n" +
                "}";

        body.addProperty("query", blogPosts);

        Response response = given()
                .contentType("application/json")
                .header("Authorization",  API.tkn)
                .body(body.toString())
                .when().post().prettyPeek(); // Replace "/your_endpoint" with your actual endpoint
        JsonPath jsonPath = response.jsonPath();

        String author = jsonPath.getString("data.getBlogPosts[-1].author");
        System.out.println("author = " + author);
        Assert.assertEquals("ender2",author);
    }
    @When("I perform a DELETE request to {string}")
    public void i_perform_a_delete_request_to(String string) {

       JsonObject bodyDelete=new JsonObject();
         String deleteBlog="mutation Mutation($deleteBlogPostId: ID!) {\n" +
                 "  deleteBlogPost(id: $deleteBlogPostId) {\n" +
                 "    author\n" +
                 "  }\n" +
                 "}";
            bodyDelete.addProperty("query",deleteBlog);
            JsonObject variablesDelete=new JsonObject();

            variablesDelete.addProperty("deleteBlogPostId",API.id);

            bodyDelete.add("variables",variablesDelete);
            response=given()
                    .contentType(ContentType.JSON)
                    .header("Authorization",API.tkn)
                    .body(bodyDelete.toString())
                    .when().post().prettyPeek();



    }
    @Then("I verify the response contains the author")
    public void i_verify_the_response_contains_author() {
        JsonPath jsonPath = response.jsonPath();
         author = jsonPath.getString("data.createBlogPost.author");
        System.out.println("author = " + author);
        Assert.assertEquals("ender",author);
    }
    @Then("The post with {string} should be visible on the Blog Homepage")
    public void the_post_with_should_be_visible_on_the_blog_homepage(String author) {
        author="ender";
        Driver.getDriver().navigate().refresh();
        BrowserUtils.waitFor(5);
        System.out.println("author = " + author);
        List<WebElement> elements = Driver.getDriver().findElements(By.xpath("//div[@class='card-body']//div[@class='card-title h5']"));
        API.numberofPosts=elements.size();
        WebElement element = Driver.getDriver().findElement(By.xpath("(//div[@class='card-body'])[1]//p[2]"));
        String actualAuthor = element.getText();
        System.out.println(actualAuthor);
      Assert.assertTrue(actualAuthor.contains(author));
    }


    @Then("The number of posts should be one less than before")
    public void the_number_of_posts_should_be_one_less_than_before() {
        Driver.getDriver().navigate().refresh();
        BrowserUtils.waitFor(5);
        List<WebElement> elements = Driver.getDriver().findElements(By.xpath("//div[@class='card-body']//div[@class='card-title h5']"));
        int numberofPosts2=elements.size();
        Assert.assertEquals(API.numberofPosts-1,numberofPosts2);
    }

}



