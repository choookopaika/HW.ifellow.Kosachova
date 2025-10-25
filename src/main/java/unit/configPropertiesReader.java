package unit;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class configPropertiesReader {

    private static final Properties properties = new Properties();

    static {
        try (InputStream input = configPropertiesReader.class.getClassLoader()
                .getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new RuntimeException("не найден config.properties в resources");
            }
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("ошибка загрузки config.properties", e);
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }

    public static RequestSpecification baseRequestSpec(String baseUrl) {
        return new RequestSpecBuilder()
                .setBaseUri(baseUrl)
                .setContentType(ContentType.JSON)
                .build();
    }
}
