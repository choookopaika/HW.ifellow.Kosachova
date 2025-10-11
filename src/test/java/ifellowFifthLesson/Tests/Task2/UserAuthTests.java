package ifellowFifthLesson.Tests.Task2;

import ifellowFifthLesson.steps.Task2.UserAuthSteps;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class UserAuthTests {

    private final Logger log = LoggerFactory.getLogger(UserAuthTests.class);
    private final UserAuthSteps steps = new UserAuthSteps();
    private final File userJson = new File("src/test/resources/User.json");

    @Test
    @DisplayName("2 задание")
    void fullUserFlowTest() {
        steps.registerUser(userJson);
        steps.loginWrongUsername(userJson);
        steps.loginWrongPassword(userJson);
        steps.loginCorrectCredit(userJson);
        log.info("Токен: {}", steps.getToken());
        steps.logoutInvalidToken();
        steps.logoutValidToken();
    }
}
