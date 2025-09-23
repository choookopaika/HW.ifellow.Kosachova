package ifellowThirdLessonTests;
import ifellowThirdLessonPages.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class JiraHwTest extends WebHooks{

    private final LoginPage loginPage = new LoginPage();
    private final ProjectPage projectPage = new ProjectPage();
    private final NewTaskPage newTaskPage = new NewTaskPage();
    private final TaskPage taskPage = new TaskPage();
    private final NewBugPage newBugPage = new NewBugPage();

    @Test
    @DisplayName("Пункт 1.")
    public void loginTest() {
        loginPage.loginIn("AT9", "Qwerty123");//1
        boolean loggedIn = $x("//a[@id = 'header-details-user-fullname']").shouldBe(visible, Duration.ofSeconds(10)).exists();
        Assertions.assertTrue(loggedIn,"не удалось войти в систему.");
    }

    @Test
    @DisplayName("Пункт 2.")
    public void openProjectTest() {
        loginPage.loginIn("AT9", "Qwerty123");//1
        boolean loggedIn = $x("//a[@id = 'header-details-user-fullname']").shouldBe(visible, Duration.ofSeconds(10)).exists();
        Assertions.assertTrue(loggedIn,"не удалось войти в систему.");

        projectPage.openProject();//2
        Assertions.assertTrue(projectPage.projectHeader.is(visible), "проект не найден.");
    }

    @Test
    @DisplayName("Пункт 3.")
    public void createNewTask(){
        loginPage.loginIn("AT9","Qwerty123");//1
        boolean loggedIn = $x("//a[@id = 'header-details-user-fullname']").shouldBe(visible, Duration.ofSeconds(10)).exists();
        Assertions.assertTrue(loggedIn,"не удалось войти в систему.");

        projectPage.openProject();//2
        Assertions.assertTrue(projectPage.projectHeader.is(visible), "проект не найден.");

        int before = projectPage.getCountOfProject();//3
        System.out.println("задач до создания: " + before);
        newTaskPage.createNewTask("Задача", "NewTask");
        sleep(2000);
        refresh();
        projectPage.projectHeader.shouldBe(visible);
        int after = projectPage.getCountOfProject();
        System.out.println("задач после создания: " + after);
        Assertions.assertEquals(before+1, after,"счетчик не увеличился.");
    }

    @Test
    @DisplayName("Пункт 4.")
    public void checkTaskDetails(){
        loginPage.loginIn("AT9","Qwerty123");//1
        boolean loggedIn = $x("//a[@id = 'header-details-user-fullname']").shouldBe(visible, Duration.ofSeconds(10)).exists();
        Assertions.assertTrue(loggedIn,"не удалось войти в систему.");

        projectPage.openProject();//2
        Assertions.assertTrue(projectPage.projectHeader.is(visible), "проект не найден.");

        int before = projectPage.getCountOfProject();//3
        System.out.println("задач до создания: " + before);
        newTaskPage.createNewTask("Задача", "NewTask");
        sleep(2000);
        refresh();
        projectPage.projectHeader.shouldBe(visible);
        int after = projectPage.getCountOfProject();
        System.out.println("задач после создания: " + after);
        Assertions.assertEquals(before+1, after,"счетчик не увеличился.");

        taskPage.openTask("TestSeleniumATHomework");//4
        taskPage.headerTask.shouldHave(text("TestSeleniumATHomework"));
        Assertions.assertEquals("СДЕЛАТЬ", taskPage.statusTask.getText(), "статус задачи неверный");
        Assertions.assertEquals("Version 2.0", taskPage.versionTask.getText(), "версия задачи неверная");

    }

    @Test
    @DisplayName("Пункт 5.")
    public void createNewBug(){
        loginPage.loginIn("AT9","Qwerty123");//1
        boolean loggedIn = $x("//a[@id = 'header-details-user-fullname']").shouldBe(visible, Duration.ofSeconds(10)).exists();
        Assertions.assertTrue(loggedIn,"не удалось войти в систему.");

        projectPage.openProject();//2
        Assertions.assertTrue(projectPage.projectHeader.is(visible), "проект не найден.");

        int before = projectPage.getCountOfProject();//3
        System.out.println("задач до создания: " + before);
        newTaskPage.createNewTask("Задача", "NewTask");
        sleep(2000);
        refresh();
        projectPage.projectHeader.shouldBe(visible);
        int after = projectPage.getCountOfProject();
        System.out.println("задач после создания: " + after);
        Assertions.assertEquals(before+1, after,"счетчик не увеличился.");

        taskPage.openTask("TestSeleniumATHomework");//4
        taskPage.headerTask.shouldHave(text("TestSeleniumATHomework"));
        Assertions.assertEquals("СДЕЛАТЬ", taskPage.statusTask.getText(), "статус задачи неверный");
        Assertions.assertEquals("Version 2.0", taskPage.versionTask.getText(), "версия задачи неверная");

        newBugPage.createNewBug("Ошибка","NewBug","описание бага через автотест.");//5
        sleep(2000);
        refresh();
        taskPage.openTask("NewBug");
        Assertions.assertEquals("NewBug", taskPage.headerTask.getText(), "баг не создан");
        taskPage.moveTaskToStatus("Нужно сделать");
        System.out.println("статус: Нужно сделать.");
        taskPage.moveTaskToStatus("В работе");
        System.out.println("статус: В работе.");
        taskPage.moveTaskToStatus("Выполнено");
        System.out.println("статус: Выполнено.");
    }
}
