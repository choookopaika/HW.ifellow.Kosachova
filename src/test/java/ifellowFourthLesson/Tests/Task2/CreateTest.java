package ifellowFourthLesson.Tests.Task2;

import ifellowFourthLesson.steps.Task2.CreateSteps;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateTest {

    private final CreateSteps steps = new CreateSteps();

    @Test
    @DisplayName("2 задание")
    void testCreateUserFromFile() {
        File jsonFile = new File("src/test/resources/File.json");

        ExtractableResponse<Response> response = steps.createUserFromFile(jsonFile); //Создаем пользователя
        assertEquals(201, response.statusCode(), "Статус код не 201");
        System.out.println(response.asString());
        assertEquals("Tomato", response.jsonPath().getString("name"));
        assertEquals("Eat maket", response.jsonPath().getString("job"));
    }
}
