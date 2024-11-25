package resources;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;


public class Utils {
    
    public RequestSpecification requestSpecification() throws IOException{
        RequestSpecification spec = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl")).addQueryParam("key", "qaclick123")
        .setContentType(ContentType.JSON).build();
        return spec;
    }

    private String getGlobalValue(String key) throws IOException{
        Properties properties = new Properties();
        FileInputStream file = new FileInputStream("src\\test\\java\\resources\\global.properties");
        properties.load(file);
        return properties.getProperty(key);
    }
}
