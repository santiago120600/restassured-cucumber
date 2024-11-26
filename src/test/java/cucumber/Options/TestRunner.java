package cucumber.Options;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/java/features",
    plugin = {
        "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
        "progress",
        "summary"
    },
    glue = {"stepDefinitions"},
    tags = "@DeletePlace")
public class TestRunner {

}
