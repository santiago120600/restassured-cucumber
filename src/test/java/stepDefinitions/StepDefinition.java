package stepDefinitions;

import java.util.ArrayList;
import java.util.List;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import pojo.AddPlace;
import pojo.Location;

public class StepDefinition {
    RequestSpecification req;
    ResponseSpecification resspec;
    RequestSpecification res;
    Response response;

    @Given("Add Place Payload")
    public void add_place_payload() {
        AddPlace place = new AddPlace();
        Location location = new Location();
        location.setLat(-38.383494);
        location.setLng(33.427362);
        place.setLocation(location);
        place.setAccuracy(50);
        place.setName("Canton de santi");
        place.setAddress("Melchor ocampo");
        List<String> types = new ArrayList<String>();
        types.add("shop");
        place.setTypes(types);
        place.setWebsite("http://google.com");
        place.setLanguage("Spanish");
        req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123")
        .setContentType(ContentType.JSON).build();

        resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
        res = given().spec(req).body(place);
    }

    @When("User calls {string} with Post http request")
    public void user_calls_with_post_http_request(String string) {
        response = res.when().post("/maps/api/place/add/json").then().spec(resspec).extract().response();
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
