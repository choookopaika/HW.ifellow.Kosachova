package ifellowFinalTaskAPISteps;

import ifellowFinalTaskAPIPages.UserAuthPage;
import io.cucumber.java.ru.*;
import io.qameta.allure.Step;

import java.io.File;

public class UserAuthSteps {

    private final UserAuthPage userAuthSteps = new UserAuthPage();
    private final File userJson = new File("src/test/resources/User.json");

    @Дано("регистрация пользователя")
    @Step("Регистрация пользователя")
    public void registerUser() {
        userAuthSteps.registerUser(userJson);
    }

    @Допустим("авторизация с неверным логином")
    @Step("Авторизация (не найден пользователь)")
    public void loginWrongUsername() {
        userAuthSteps.loginWrongUsername(userJson);
    }

    @Допустим("авторизация с неверным паролем")
    @Step("Авторизация (пароль не верный)")
    public void loginWrongPassword() {
        userAuthSteps.loginWrongPassword(userJson);
    }

    @Затем("авторизация с правильными кредами")
    @Step("Авторизация (успешный сценарий)")
    public void loginCorrectCredit() {
        userAuthSteps.loginCorrect(userJson);
    }

    @Тогда("токен сохраняется")
    @Step("Токен сохраняется")
    public void checkToken() {
        String token = userAuthSteps.getToken();
        if (token == null || token.isEmpty()) {
            throw new AssertionError("Токен не был сохранен!");
        }
    }

    @Допустим("выход из учетки с рандомным значением")
    @Step("Выход из учетки (неуспешный сценарий)")
    public void logoutInvalidToken() {
        userAuthSteps.logoutInvalidToken();
    }

    @Затем("выход из учетки с токеном")
    @Step("Выход из учетки (верный сценарий)")
    public void logoutValidToken() {
        userAuthSteps.logoutValidToken();
    }

}
