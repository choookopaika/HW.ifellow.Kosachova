package ifellowFourthLessonTests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import units.configPropertiesReader;

public class Hooks {

    @Before("@homework")
    public void beforeTest(Scenario scenario){
        Configuration.pageLoadStrategy = PageLoadStrategy.EAGER.toString();
        Configuration.timeout = 15000;
        String url = configPropertiesReader.get("jira.url");
        Selenide.open(url);

        WebDriver driver = WebDriverRunner.getWebDriver();
        driver.manage().window().maximize();
    }

    @After
    public void afterTest(){
        Selenide.closeWebDriver();
    }
}
