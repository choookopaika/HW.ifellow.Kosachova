package ifellowFinalTaskAPI.api.RickAndMorty;

import ifellowFinalTaskAPI.api.BaseApi;
import io.restassured.response.ValidatableResponse;

import java.util.Map;

import static ifellowFinalTaskAPI.api.requests.GetRequests.getDefault;
import static ifellowFinalTaskAPI.api.requests.GetRequests.getWithParams;

public class RickAndMortyApi extends BaseApi {

    public RickAndMortyApi() {
        super("rickandmorty.api.url"); // ключ из config.properties
    }
    public ValidatableResponse getCharacterByName(String name) {
        return getWithParams("/character/", Map.of("name", name));
    }

    public ValidatableResponse getCharacterByUrlResponse(String url) {
        return getDefault(url);
    }

    public ValidatableResponse getEpisodeByUrlResponse(String url) {
        return getDefault(url);
    }
}
