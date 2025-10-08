package ifellowFifthLesson.api.Task2;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import java.io.File;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class UserAuthApi extends UserAuthBaseApi{

    public ValidatableResponse register(File userJson) {
        return given()
                .contentType("application/json")
                .body(userJson)
                .when()
                .post("/register")
                .then();
    }

    public ValidatableResponse login(Map<String, Object> user) {
        return given()
                .header("Content-Type", "application/json")
                .body(user)
                .post("/login")
                .then();
    }

    public ValidatableResponse logout(String token) {
        return given()
                .header("Authorization", token)
                .get("/logout")
                .then();
    }


}
