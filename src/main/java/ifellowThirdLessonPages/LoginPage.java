package ifellowThirdLessonPages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class LoginPage{

    public SelenideElement login = $x("//input[@id = 'login-form-username']");
    public SelenideElement pass = $x("//input[@id = 'login-form-password']");
    public SelenideElement button = $x("//input[@id = 'login']");

    public void loginIn(String username, String password){
        login.setValue(username);
        pass.setValue(password);
        button.click();;
    }
}
