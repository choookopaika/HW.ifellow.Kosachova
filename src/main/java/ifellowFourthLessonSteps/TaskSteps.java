package ifellowFourthLessonSteps;

import ifellowFourthLessonPages.TaskPage;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import org.junit.jupiter.api.Assertions;

public class TaskSteps {

    private final TaskPage taskPage = new TaskPage();

    @Когда("открывается задача {string}")
    public void openTask(String taskName) {
        taskPage.openTask(taskName);
    }

    @Тогда("статус задачи должен быть {string}")
    public void checkTaskStatus(String expectedStatus){
        Assertions.assertEquals(expectedStatus, taskPage.statusTask.getText(), "неверный статус задачи");
    }

    @И("версия задачи должна быть {string}")
    public void checkTaskVersion(String expectedVersion) {
        Assertions.assertEquals(expectedVersion, taskPage.versionTask.getText(), "неверная версия задачи");
    }
}
