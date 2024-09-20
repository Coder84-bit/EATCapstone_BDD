package setup;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/feature",
        glue = "stepdefinitions"
)

public class TestRunner extends AbstractTestNGCucumberTests {

}