package ifellowFinalTaskAPI.api.requests;

import io.restassured.response.ValidatableResponse;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class GetRequests {

    public static ValidatableResponse getWithParams(String endpoint, Map<String, ?> queryParams) {
        return given()
                .queryParams(queryParams)
                .when()
                .get(endpoint)
                .then();
    }

    public static ValidatableResponse getDefault(String endpoint) {
        return given()
                .when()
                .get(endpoint)
                .then();
    }

    public static ValidatableResponse getWithAuth(String endpoint, String token) {
        return given()
                .header("Authorization", token)
                .when()
                .get(endpoint)
                .then();
    }
}
