package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefinition extends Utils{
    RequestSpecification reqSpec;
    Response response;

    @Given("Add Place Payload with {string} {string} {string} {string} {string} {int} {double} {double}")
    public void add_place_payload_with(String name, String address, String types, String website, String language, Integer accuracy, Double latitude, Double longitude) throws FileNotFoundException, IOException {
        TestDataBuild testDataBuild = new TestDataBuild();
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
        String resp = response.asString();
        JsonPath js = new JsonPath(resp);
        assertEquals(js.get(key).toString(), value);
    }

}
