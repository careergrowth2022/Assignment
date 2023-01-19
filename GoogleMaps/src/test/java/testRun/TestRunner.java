package testRun;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		tags = "@f1",
		features = "src/test/resources/features",
		glue = {"stepDefinition"},
		plugin = {"pretty","html:target/report.html"})
		
public class TestRunner {

}
