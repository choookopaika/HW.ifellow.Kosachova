package ifellowFourthLesson.steps.Task2;

import com.fasterxml.jackson.databind.ObjectMapper;
import ifellowFourthLesson.api.Task2.CreateApi;
import io.qameta.allure.Step;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class CreateSteps {
    private final CreateApi api = new CreateApi();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Step("Создать пользователя")
    public ExtractableResponse<Response> createUserFromFile(File jsonFile) {
        try {
            Map<String, Object> userData = objectMapper.readValue(jsonFile, Map.class); //Читаем из файла

            userData.put("name", "Tomato"); //Меняем и добавлем поле
            userData.put("job", "Eat maket");

            return api.createUser(userData)
                    .statusCode(HttpStatus.SC_CREATED)
                    .extract();

        } catch (IOException e) {
            throw new RuntimeException("Ошибка при чтении JSON файла", e);
        }
    }
}