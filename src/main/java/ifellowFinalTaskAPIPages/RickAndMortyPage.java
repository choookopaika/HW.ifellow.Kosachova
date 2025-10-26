package ifellowFinalTaskAPIPages;

import ifellowFinalTaskAPI.api.RickAndMorty.RickAndMortyApi;
import ifellowFinalTaskAPI.dto.Character;
import ifellowFinalTaskAPI.dto.Episode;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;

import java.util.List;

public class RickAndMortyPage {

    private static final RickAndMortyApi api = new RickAndMortyApi();

    public Character getCharacterByName(String name) {
        ExtractableResponse<Response> response = api.getCharacterByName(name)
                .statusCode(200)
                .extract();

        List<Character> characters = response.jsonPath().getList("results", ifellowFinalTaskAPI.dto.Character.class);

        if (characters.isEmpty()) {
            throw new RuntimeException(name + " не найден!");
        }
        return characters.get(0);
    }

    public String getMortyLastEpisode() {
        ExtractableResponse<Response> response = api.getCharacterByName("Morty Smith")
                .statusCode(HttpStatus.SC_OK)
                .extract();

        List<ifellowFinalTaskAPI.dto.Character> morties = response.jsonPath().getList("results", ifellowFinalTaskAPI.dto.Character.class);

        if (morties.isEmpty()) {
            throw new RuntimeException("Морти Смит не найден:(");
        }

        ifellowFinalTaskAPI.dto.Character morty = morties.get(0);
        String[] episodes = morty.getEpisode();
        String lastEpisode = episodes[episodes.length - 1];
        return lastEpisode;
    }

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

    public Character getCharacterByUrl(String characterUrl) {
        ExtractableResponse<Response> response = api.getCharacterByUrlResponse(characterUrl)
                .statusCode(HttpStatus.SC_OK)
                .extract();

        Character character = response.as(Character.class);
        return character;
    }
}
