package ifellowThirdLessonPages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class LoginPage{

    public SelenideElement login = $x("//input[@id = 'login-form-username']").as("Логин");
    public SelenideElement pass = $x("//input[@id = 'login-form-password']").as("Пароль");
    public SelenideElement button = $x("//input[@id = 'login']").as("Кнопка входа");
    public SelenideElement loggedIn = $x("//a[@id = 'header-details-user-fullname']").as("Проверка авторизации");

    @Step("Авторизация под пользователем {username}")
    public void loginIn(String username, String password){
        login.setValue(username);
        pass.setValue(password);
        button.click();
    }

    @Step("Проверка успешной авторизации пользователя {username}")
    public void loggedIn(String username){
        loggedIn.shouldBe(visible, Duration.ofSeconds(10));
    }
}
