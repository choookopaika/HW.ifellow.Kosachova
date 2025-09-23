package ifellowThirdLessonTests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;

public class WebHooks {

    @BeforeEach
    public void InitBrowser(){
        Configuration.pageLoadStrategy = PageLoadStrategy.EAGER.toString();
        Configuration.timeout = 15000;
        Selenide.open("https://edujira.ifellow.ru");

        WebDriver driver = WebDriverRunner.getWebDriver();
        driver.manage().window().maximize();
    }

    @AfterEach
    public void AfterTest(){
        Selenide.closeWebDriver();
    }
}
