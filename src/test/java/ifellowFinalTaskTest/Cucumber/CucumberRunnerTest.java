package ifellowFinalTaskTest.Cucumber;

import org.junit.platform.suite.api.*;

import static io.cucumber.junit.platform.engine.Constants.*;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("ifellowFinalTaskAPI/features")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "ifellowFinalTaskAPISteps,ifellowFinalTaskTest.Hooks")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty, summary, io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm")
public class CucumberRunnerTest {

}
