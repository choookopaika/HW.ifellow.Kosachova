package ifellowFinalTaskUITests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import units.configPropertiesReader;

public class WebHooks {

    @BeforeAll
    public static void setUpAllure(){
        String allure_properties = configPropertiesReader.get("allure.properties");
        boolean allure_properties_value = Boolean.parseBoolean(allure_properties);
        SelenideLogger.addListener("AllureSelenide",
                new AllureSelenide().
                        screenshots(allure_properties_value).
                        savePageSource(allure_properties_value)
        );
    }

    @BeforeEach
    public void InitBrowser(){
        Configuration.pageLoadStrategy = PageLoadStrategy.EAGER.toString();
        Configuration.timeout = 15000;
        String url = configPropertiesReader.get("jira.url");
        Selenide.open(url);

        WebDriver driver = WebDriverRunner.getWebDriver();
        driver.manage().window().maximize();
    }


    @AfterEach
    public void AfterTest(){
        Selenide.closeWebDriver();
    }
}
