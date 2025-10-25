package ifellowFinalTaskAPIPages;

import com.fasterxml.jackson.databind.ObjectMapper;
import ifellowFinalTaskAPI.api.UserAuth.UserAuthApi;
import io.qameta.allure.Allure;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

public class UserAuthPage {

    private final Logger log = LoggerFactory.getLogger(UserAuthPage.class);
    private static final UserAuthApi api = new UserAuthApi() {};
    private static final ObjectMapper mapper = new ObjectMapper();
    private static final AtomicReference<String> token = new AtomicReference<>();

    public void registerUser(File userJson) {
        api.register(userJson).statusCode(HttpStatus.SC_OK);
    }

    public void loginWrongUsername(File userJson) {
        Map<String, Object> userMap = readJsonAsMap(userJson);
        userMap.put("username", "WrongUsername");
        api.login(userMap).statusCode(HttpStatus.SC_UNAUTHORIZED);
    }

    public void loginWrongPassword(File userJson) {
        Map<String, Object> userMap = readJsonAsMap(userJson);
        userMap.put("password", "WrongPassword123");
        api.login(userMap).statusCode(HttpStatus.SC_UNAUTHORIZED);
    }

    public void loginCorrect(File userJson) {
        Map<String, Object> userMap = readJsonAsMap(userJson);
        ExtractableResponse<Response> response = api.login(userMap)
                .statusCode(HttpStatus.SC_OK)
                .extract();

        String body = response.asString();

        String tokenValue = body.replace("token", "")
                .replace(":", "")
                .trim();
        token.set(tokenValue);

        log.info("токен: " + tokenValue);
        Allure.addAttachment("Auth Token", tokenValue);
    }

    public void logoutInvalidToken() {
        String invalidToken = UUID.randomUUID().toString();
        api.logout(invalidToken).statusCode(HttpStatus.SC_UNAUTHORIZED);
    }

    public void logoutValidToken() {
        String validToken = token.get();
        api.logout(validToken).statusCode(HttpStatus.SC_OK);
    }

    public String getToken() {
        return token.get();
    }

    private Map<String, Object> readJsonAsMap(File file) {
        try {
            String content = Files.readString(file.toPath());
            return mapper.readValue(content, HashMap.class);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка чтения User.json", e);
        }
    }
}
