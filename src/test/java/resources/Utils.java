package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class Utils {

    public RequestSpecification requestSpecification() throws FileNotFoundException, IOException{
        PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
        RequestSpecification spec = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl")).addQueryParam("key", "qaclick123")
        .addFilter(RequestLoggingFilter.logRequestTo(log))
        .addFilter(ResponseLoggingFilter.logResponseTo(log))
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
