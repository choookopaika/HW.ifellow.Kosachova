package ifellowFinalTaskUITests;
import ifellowFinalTaskUIPages.*;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import units.configPropertiesReader;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

@DisplayName("Jira тест")
public class JiraHwTest extends WebHooks{

    private final Logger log = LoggerFactory.getLogger(JiraHwTest.class);
    private final LoginPage loginPage = new LoginPage();
    private final ProjectPage projectPage = new ProjectPage();
    private final NewTaskPage newTaskPage = new NewTaskPage();
    private final TaskPage taskPage = new TaskPage();
    private final NewBugPage newBugPage = new NewBugPage();

    @Test
    @Story("Авторизация")
    @DisplayName("Тест 1.")
    @Tag("Test_1")
    public void loginTest() {
        String username = configPropertiesReader.get("jira.username");
        String password = configPropertiesReader.get("jira.password");
        loginPage.loginIn(username, password);
        loginPage.loggedIn(username);
    }

    @Test
    @Story("Открытие проекта")
    @DisplayName("Тест 2.")
    @Tag("Test_2")
    public void openProjectTest() {
        loginTest();

        projectPage.openProject();
        Assertions.assertTrue(projectPage.projectHeader.is(visible), "проект не найден.");
    }

    @Test
    @Story("Создание задачи")
    @DisplayName("Тест 3.")
    @Tag("Test_3")
    public void createNewTaskTest(){
        openProjectTest();

        int before = projectPage.getCountOfProject();
        log.info("задач до создания: " + before);
        newTaskPage.createNewTask("Задача", "NewTask_9");
        sleep(2000);
        refresh();
        projectPage.projectHeader.shouldBe(visible);
        int after = projectPage.getCountOfProject();
        log.info("задач после создания: " + after);
        Assertions.assertEquals(before+1, after,"счетчик не увеличился.");
    }

    @Test
    @Story("Проверка TestSeleniumATHomework")
    @DisplayName("Тест 4.")
    @Tag("Test_4")
    public void checkTaskDetailsTest(){
        createNewTaskTest();

        taskPage.openTask("TestSeleniumATHomework");
        taskPage.headerTask.shouldHave(text("TestSeleniumATHomework"));
        Assertions.assertEquals("СДЕЛАТЬ", taskPage.statusTask.getText(), "статус задачи неверный");
        Assertions.assertEquals("Version 2.0", taskPage.versionTask.getText(), "версия задачи неверная");

    }

    @Test
    @Story("Создание ошибки и проведение по статусам")
    @DisplayName("Тест 5.")
    @Tag("Test_5")
    public void createNewBugTest(){
        checkTaskDetailsTest();

       newBugPage.createNewBug("Ошибка","NewBug_9","описание бага через автотест");
        sleep(2000);
        refresh();
        taskPage.openTask("NewBug_9");
        Assertions.assertEquals("NewBug_9", taskPage.headerTask.getText(), "баг не создан");
        taskPage.openLastBug();


        taskPage.moveTaskToStatus("Нужно сделать");
        taskPage.moveTaskToStatus("В работе");
        taskPage.moveTaskToStatus("Выполнено");
        refresh();
        taskPage.statusTask.shouldHave(text("ГОТОВО"), Duration.ofSeconds(15));
        log.info("Статус: " + taskPage.statusTask.getText());
        Assertions.assertEquals("ГОТОВО", taskPage.statusTask.getText(), "задача не выполнена");
    }
}
