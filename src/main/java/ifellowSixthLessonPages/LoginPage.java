package ifellowSixthLessonPages;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.SetValueOptions;
import io.qameta.allure.Param;
import io.qameta.allure.Step;
import io.qameta.allure.model.Parameter;

import java.time.Duration;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class LoginPage{

    public SelenideElement login = $x("//input[@id = 'login-form-username']").as("Логин");
    public SelenideElement pass = $x("//input[@id = 'login-form-password']").as("Пароль");
    public SelenideElement button = $x("//input[@id = 'login']").as("Кнопка входа");
    public SelenideElement loggedIn = $x("//a[@id = 'header-details-user-fullname']").as("Проверка авторизации");

    @Step("Авторизация под пользователем {username}")
    public void loginIn(String username, @Param(mode = Parameter.Mode.MASKED) String password){
        login.shouldBe(enabled, visible);
        login.setValue(username);
        pass.setValue(SetValueOptions.withText(password).sensitive());
        button.click();
    }

    @Step("Проверка успешной авторизации пользователя {username}")
    public void loggedIn(String username){
        loggedIn.shouldBe(visible, Duration.ofSeconds(10));
    }
}
