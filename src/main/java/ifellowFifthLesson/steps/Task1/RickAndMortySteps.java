package ifellowFifthLesson.steps.Task1;

import io.qameta.allure.Step;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import ifellowFifthLesson.api.Task1.RickAndMortyApi;
import ifellowFifthLesson.dto.Character;
import ifellowFifthLesson.dto.Episode;

import java.util.List;

public class RickAndMortySteps {

    private static final RickAndMortyApi api = new RickAndMortyApi();

    @Step("Получить персонажа по имени")
    public Character getCharacterByName(String name) {
        ExtractableResponse<Response> response = api.getCharacterByName(name)
                .statusCode(200)
                .extract();

        List<Character> characters = response.jsonPath().getList("results", Character.class);
                
    if (characters.isEmpty()) {
            throw new RuntimeException(name + " не найден!");
        }
        return characters.get(0);
    }

    @Step("Получить персонажа Морти и получить последний эпизод с ним")
    public String getMortyLastEpisode() {
        ExtractableResponse<Response> response = api.getCharacterByName("Morty Smith")
                .statusCode(HttpStatus.SC_OK)
                .extract();

        List<Character> morties = response.jsonPath().getList("results", Character.class);

        if (morties.isEmpty()) {
            throw new RuntimeException("Морти Смит не найден:(");
        }

        Character morty = morties.get(0);
        String[] episodes = morty.getEpisode();
        String lastEpisode = episodes[episodes.length - 1];
        return lastEpisode;
    }

    @Step("Получить последнего персонажа из последнего эпизода")
    public String getLastCharacterFromEpisode(String episodeUrl) {
        ExtractableResponse<Response> response = api.getEpisodeByUrlResponse(episodeUrl)
                .statusCode(HttpStatus.SC_OK)
                .extract();

        Episode episode = response.as(Episode.class);
        String[] characters = episode.getCharacters();

        if (characters.length == 0) {
            throw new RuntimeException("В данном эпизоде нет персонажей");
        }

        String lastCharacterUrl = characters[characters.length - 1];
        return lastCharacterUrl;
    }

    @Step("Получить данные персонажа по URL")
    public Character getCharacterByUrl(String characterUrl) {
        ExtractableResponse<Response> response = api.getCharacterByUrlResponse(characterUrl)
                .statusCode(HttpStatus.SC_OK)
                .extract();

        Character character = response.as(Character.class);
        return character;
    }
}
