package step_definitions;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * @project rest-assured-cucumber
 * @user veronica.aruquipa
 * @date 03-06-17 07:00 PM
 */

@RunWith(Cucumber.class)
@CucumberOptions(
        format = {"pretty","html:src/test/test-report/test-report.html"},
        // JSON file could be used in Continuous Integration System like Jenkins in order to translate their results with Jenkins plugin.
        // format = {"pretty","json:src/test/test-report/test-report.json"},
        features = "src/test/java/resources"
)
public class RunCakesTest {
}