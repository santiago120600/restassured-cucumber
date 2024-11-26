package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefinition extends Utils{
    RequestSpecification reqSpec;
    Response response;
    TestDataBuild testDataBuild = new TestDataBuild();
    static String placeId;

    @Given("Add Place Payload with {string} {string} {string} {string} {string} {int} {double} {double}")
    public void add_place_payload_with(String name, String address, String types, String website, String language, Integer accuracy, Double latitude, Double longitude) throws FileNotFoundException, IOException {
        reqSpec = given()
        .filter(new AllureRestAssured())
        .spec(requestSpecification()).body(testDataBuild.add_place_payload(name, address, language, website, latitude, longitude, accuracy, types));
    }

    @When("User calls {string} with {string} http request")
    public void user_calls_with_post_http_request(String api, String httpMethod) {
        APIResources apiResource = APIResources.valueOf(api);

        if(httpMethod.equalsIgnoreCase("POST")){
            response = reqSpec.when().post(apiResource.getResource());
        }else if(httpMethod.equalsIgnoreCase("GET")){
            response = reqSpec.when().get(apiResource.getResource());
        }else if(httpMethod.equalsIgnoreCase("DELETE")){
            response = reqSpec.when().delete(apiResource.getResource());
        }
    }

    @Then("the API call got success with status code {int}")
    public void the_api_call_got_success_with_status_code(Integer statusCode) {
        assertEquals((Integer) response.getStatusCode(), statusCode);
    }

    @Then("{string} in response body is {string}")
    public void in_response_body_is(String key, String value) {
        assertEquals(getJsonPath(response, key), value);
    }

    @Then("verify place_id created maps to {string} using {string}")
    public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws IOException {
        placeId = getJsonPath(response, "place_id");
        reqSpec = given().spec(requestSpecification()).queryParam("place_id", placeId);
        user_calls_with_post_http_request(resource, "GET");
        String actualName = getJsonPath(response, "name");
        assertEquals(actualName, expectedName);
    }

    @Given("Delete Place Payload")
    public void delete_place_payload() throws IOException {
        reqSpec = given()
        .filter(new AllureRestAssured())
        .spec(requestSpecification()).body(testDataBuild.delete_place_payload(placeId));
    }

}
