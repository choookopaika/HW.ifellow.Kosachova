package ifellowFourthLessonSteps;

import ifellowFourthLessonPages.NewBugPage;
import ifellowFourthLessonPages.TaskPage;
import io.cucumber.java.ru.Если;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import org.junit.jupiter.api.Assertions;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.refresh;

public class BugSteps {

    private final NewBugPage newBugPage = new NewBugPage();
    private final TaskPage taskPage = new TaskPage();
    private String lastCreatedBugName;

    @Когда("создается новый баг {string} с описанием {string}")
    public void createNewBug(String bugName, String description) {
        newBugPage.createNewBug("Ошибка", bugName, description);
        lastCreatedBugName = bugName;
    }

    @Тогда("баг должен быть создан")
    public void checkBugCreated(){
        refresh();
        taskPage.openTask(lastCreatedBugName);
        Assertions.assertEquals(lastCreatedBugName, taskPage.headerTask.getText(), "баг не создан");
    }

    @Если("найден последний созданный баг")
    public void openLastCreatedBug(){
        taskPage.openLastBug();
    }

    @Тогда("переводится баг по статусам {string}, {string}, {string}")
    public void moveBugToStatuses(String status1, String status2, String status3){
        taskPage.moveTaskToStatus(status1);
        taskPage.moveTaskToStatus(status2);
        taskPage.moveTaskToStatus(status3);
    }

    @И("статус бага должен быть {string}")
    public void checkBugStatus(String expectedStatus){
        taskPage.statusTask.shouldHave(text(expectedStatus), Duration.ofSeconds(15));
    }
}
