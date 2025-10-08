package ifellowFifthLesson.steps.Task2;

import ifellowFifthLesson.api.Task2.UserAuthApi;
import io.qameta.allure.Step;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

import org.apache.http.HttpStatus;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;


public class UserAuthSteps {


    private static final UserAuthApi api = new UserAuthApi() {};
    private static final ObjectMapper mapper = new ObjectMapper();
    private static final AtomicReference<String> token = new AtomicReference<>();

    @Step("Регистрация пользователя")
    public void registerUser(File userJson) {
        api.register(userJson)
                .statusCode(HttpStatus.SC_OK);
    }

    @Step("Авторизация (не найден пользователь)")
    public void loginWrongUsername(File userJson) {
        Map<String, Object> userMap = readJsonAsMap(userJson);
        userMap.put("username", "WrongUsername"); //Изменяем имя пользователя
        api.login(userMap)
                .statusCode(HttpStatus.SC_UNAUTHORIZED);
    }

    @Step("Авторизация (пароль не верный)")
    public void loginWrongPassword(File userJson) {
        Map<String, Object> userMap = readJsonAsMap(userJson);
        userMap.put("password", "WrongPassword123"); //Изменяем пароль
        api.login(userMap)
                .statusCode(HttpStatus.SC_UNAUTHORIZED);
    }

    @Step("Авторизация (успешный сценарий)")
    public void loginCorrectCredit(File userJson) {
        Map<String, Object> userMap = readJsonAsMap(userJson);
        ExtractableResponse<Response> response = api.login(userMap)
                .statusCode(HttpStatus.SC_OK)
                .extract();
        String body = response.asString(); //Извлекаем токен от ответа и сохраняем
        String token = body.replace("token :", "").trim();
        UserAuthSteps.token.set(token);
    }

    @Step("Выход из учетки (неуспешный сценарий)")
    public void logoutInvalidToken() {
        String invalidToken = UUID.randomUUID().toString(); //Генерируем случайный токен

        api.logout(invalidToken)
                .statusCode(HttpStatus.SC_UNAUTHORIZED);
    }

    @Step("Выход из учетки (верный сценарий)")
    public void logoutValidToken() {
        String token = UserAuthSteps.token.get();
        api.logout(token)
                .statusCode(HttpStatus.SC_OK);
    }

    public String getToken() {
        return token.get();
    }

    private Map<String, Object> readJsonAsMap(File file) { //читаем файл и преобразовывваем его
        try {
            String content = Files.readString(file.toPath());
            return mapper.readValue(content, HashMap.class);
        } catch (IOException e) {
            throw new RuntimeException("ошибка чтения user.json", e);
        }
    }
}
