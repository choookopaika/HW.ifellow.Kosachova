package ifellowFourthLessonSteps;

import ifellowFourthLessonPages.LoginPage;
import io.cucumber.java.ru.Дано;
import units.configPropertiesReader;

public class LoginSteps {

    private final LoginPage loginPage = new LoginPage();

    @Дано("авторизация в Jira")
    public void loginInJira(){
        String username = configPropertiesReader.get("jira.username");
        String password = configPropertiesReader.get("jira.password");
        loginPage.loginIn(username, password);
    }
}
