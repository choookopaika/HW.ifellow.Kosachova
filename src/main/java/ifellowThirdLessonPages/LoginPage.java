package ifellowThirdLessonPages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$x;

public class LoginPage{

    public SelenideElement login = $x("//input[@id = 'login-form-username']").as("Логин");
    public SelenideElement pass = $x("//input[@id = 'login-form-password']").as("Пароль");
    public SelenideElement button = $x("//input[@id = 'login']").as("Кнопка входа");

    public void loginIn(String username, String password){
        login.setValue(username);
        pass.setValue(password);
        button.click();;
    }
}
