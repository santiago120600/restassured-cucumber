package resources;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class Utils {
    private static final String BASE_URL = "https://rahulshettyacademy.com";

    public RequestSpecification requestSpecification() throws FileNotFoundException{
        PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
        RequestSpecification spec = new RequestSpecBuilder().setBaseUri(BASE_URL).addQueryParam("key", "qaclick123")
        .addFilter(RequestLoggingFilter.logRequestTo(log))
        .addFilter(ResponseLoggingFilter.logResponseTo(log))
        .setContentType(ContentType.JSON).build();
        return spec;
    }

}
