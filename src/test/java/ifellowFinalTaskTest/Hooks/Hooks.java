package ifellowFinalTaskTest.Hooks;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.selenide.AllureSelenide;
import org.openqa.selenium.PageLoadStrategy;
import unit.configPropertiesReader;

public class Hooks {

    @Before
    public void beforeTest(Scenario scenario) {
        Configuration.pageLoadStrategy = PageLoadStrategy.EAGER.toString();
        Configuration.timeout = 15000;

        String allure_properties = configPropertiesReader.get("allure.properties");
        boolean allure_properties_value = Boolean.parseBoolean(allure_properties);

        SelenideLogger.addListener("AllureSelenide",
                new AllureSelenide()
                        .screenshots(allure_properties_value)
                        .savePageSource(allure_properties_value)
        );
    }

    @After
    public void afterTest() {
        Selenide.closeWebDriver();
    }
}
