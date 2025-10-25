package ifellowFinalTaskAPI.Tests.Hooks;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.PageLoadStrategy;

public class Hooks {

    @Before("@homework")
    public void beforeHomework(Scenario scenario) {
        Configuration.pageLoadStrategy = PageLoadStrategy.EAGER.toString();
        Configuration.timeout = 15000;
    }

    @After("@homework")
    public void afterHomework(){
        Selenide
    }
}
