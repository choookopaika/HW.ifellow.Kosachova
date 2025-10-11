package ifellowFourthLessonSteps;

import ifellowFourthLessonPages.NewTaskPage;
import ifellowFourthLessonPages.ProjectPage;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import org.junit.jupiter.api.Assertions;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.refresh;
import static com.codeborne.selenide.Selenide.sleep;

public class ProjectSteps {

    private final ProjectPage projectPage = new ProjectPage();
    private final NewTaskPage newTaskPage = new NewTaskPage();
    private int beforeCount;
    private int afterCount;

    @Когда("открывается проект {string}")
    public void openProject(String projectName){
        projectPage.openProject();
        projectPage.projectHeader.shouldBe(visible);
    }

    @Когда("создается новая задача {string}")
    public void createNewTask(String taskName){
        beforeCount = projectPage.getCountOfProject();
        newTaskPage.createNewTask("Задача", taskName);
    }

    @Тогда("количество задач увеличивается на 1")
    public void taskCountIncreased(){
        sleep(2000);
        refresh();
        afterCount = projectPage.getCountOfProject();
        Assertions.assertEquals(beforeCount + 1, afterCount, "счётчик не увеличился");
    }

    @Тогда("проект должен быть открыт")
    public void projectOpened() {
        Assertions.assertTrue(projectPage.projectHeader.is(visible), "проект не открыт");
    }
}
