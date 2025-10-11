package ifellowFifthLesson.api;

import io.restassured.RestAssured;
import ifellowFifthLesson.Specifications;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public abstract class BaseApi {
    public BaseApi(String propertyKey) {
        try {
            Properties properties = new Properties();
            try (FileInputStream input = new FileInputStream("src/test/resources/config.properties")) {
                properties.load(input);
            }
            String baseUrl = properties.getProperty(propertyKey);
            RestAssured.requestSpecification = Specifications.baseRequestSpec(baseUrl);
        } catch (IOException e) {
            throw new RuntimeException("ошибка при загрузке URL", e);
        }
    }
}
