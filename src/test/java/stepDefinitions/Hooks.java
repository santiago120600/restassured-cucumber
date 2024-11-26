package stepDefinitions;

import java.io.FileNotFoundException;
import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
    @Before("@DeletePlace")
    public void beforeDelete() throws FileNotFoundException, IOException{
        StepDefinition sd = new StepDefinition();
        String placeName = "Dummy Place";
        String address = "123 Dummy St";
        String type = "dummy";
        String website = "http://dummy.com";
        String language = "en";
        int accuracy = 50;
        double latitude = -37.8136;
        double longitude = 144.9631;

        if(StepDefinition.placeId == null){
            sd.add_place_payload_with(placeName, address, type, website, language, accuracy, latitude, longitude);
            sd.user_calls_with_post_http_request("addPlaceAPI", "Post");
            sd.verify_place_id_created_maps_to_using(placeName, "getPlaceAPI");
        }
    }

}
