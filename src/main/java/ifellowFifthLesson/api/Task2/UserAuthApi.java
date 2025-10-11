package ifellowFifthLesson.api.Task2;

import ifellowFifthLesson.api.BaseApi;
import io.restassured.response.ValidatableResponse;

import java.io.File;
import java.util.Map;

import static ifellowFifthLesson.api.requests.GetRequests.getWithAuth;;
import static io.restassured.RestAssured.given;


public class UserAuthApi extends BaseApi {

    public UserAuthApi() {
        super("userauth.api.url");
    }

    public ValidatableResponse register(File userJson) { //отправляется файл напрямую
        return given()
                .contentType("application/json")
                .body(userJson)
                .post("/register")
                .then();
    }

    public ValidatableResponse login(Map<String, Object> user) { //маппим json
        return given()
                .contentType("application/json")
                .body(user)
                .post("/login")
                .then();
    }

    public ValidatableResponse logout(String token) {
        return getWithAuth("/logout", token);
    }
}
