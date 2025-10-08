package ifellowFifthLesson.api.Task2;

import ifellowFifthLesson.Specifications;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public abstract class UserAuthBaseApi {
    public UserAuthBaseApi() {
        try {
            Properties properties = new Properties();
            try (FileInputStream input = new FileInputStream("src/main/resources/config.properties")) {
                properties.load(input);
            }
            String baseUrl = properties.getProperty("userauth.api.url");
            RestAssured.requestSpecification = Specifications.baseRequestSpec(baseUrl);
            } catch (IOException e) {
            throw new RuntimeException("ошибка при загрузке URL", e);
        }
    }
}
