package ifellowFourthLesson.api;

import ifellowFourthLesson.Specifications;
import io.restassured.RestAssured;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public abstract class RickAndMortyBaseApi {

    public RickAndMortyBaseApi() {
        try {
            Properties properties = new Properties();
            try (FileInputStream input = new FileInputStream("src/main/resources/config.properties")) {
                properties.load(input);
            }
            String baseUrl = properties.getProperty("rickandmorty.api.url");
            RestAssured.requestSpecification = Specifications.baseRequestSpec(baseUrl);

        } catch (IOException e) {
            throw new RuntimeException("ошибка при загрузке url", e);
        }
    }
}
