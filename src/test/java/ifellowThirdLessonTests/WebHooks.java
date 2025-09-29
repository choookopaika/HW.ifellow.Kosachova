package ifellowThirdLessonTests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import units.configPropertiesReader;

public class WebHooks {

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
