package ifellowFourthLesson.api;

import io.restassured.response.ValidatableResponse;
import java.util.Map;
import static io.restassured.RestAssured.given;

public class CreateApi extends CreateBaseApi{


    public ValidatableResponse createUser(Map<String, Object> userData) {
        return given()
                .header("x-api-key", "reqres-free-v1")
                .body(userData)
                .when()
                .post("/users")
                .then();
    }
}