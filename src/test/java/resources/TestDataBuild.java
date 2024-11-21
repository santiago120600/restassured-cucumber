package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class TestDataBuild {

    public AddPlace add_place_payload(){
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
        return place;
    }
}
