package org.xrame;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/org.xrame",
        plugin = {"pretty", "html:target/cucumber", "json:target/cucumber-report.json"})
public class RunTest {
}
