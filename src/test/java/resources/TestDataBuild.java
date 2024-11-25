package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class TestDataBuild {

    public AddPlace add_place_payload(String name, String address, String language, String website, double latitude ,double longitude, int accuracy, String types){
        AddPlace place = new AddPlace();
        Location location = new Location();
        location.setLat(latitude);
        location.setLng(longitude);
        place.setLocation(location);
        place.setAccuracy(accuracy);
        place.setName(name);
        place.setAddress(address);
        List<String> typesList = new ArrayList<String>();
        typesList.add(types);
        place.setTypes(typesList);
        place.setWebsite(website);
        place.setLanguage(language);
        return place;
    }
}
