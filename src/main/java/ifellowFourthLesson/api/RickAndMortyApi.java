package ifellowFourthLesson.api;

import io.restassured.response.ValidatableResponse;
import static io.restassured.RestAssured.given;

public class RickAndMortyApi extends RickAndMortyBaseApi {

    public ValidatableResponse getCharacterByName(String name) { //получить персонажа по имени
        return given()
                .queryParam("name", name)
                .get("/character/")
                .then();
    }

    public ValidatableResponse getCharacterByUrlResponse(String url) { //получить персонажа по url
        return given()
                .when()
                .get(url)
                .then();
    }

    public ValidatableResponse getEpisodeByUrlResponse(String url) { //получить эпизод по url
        return given()
                .when()
                .get(url)
                .then();
    }
}
