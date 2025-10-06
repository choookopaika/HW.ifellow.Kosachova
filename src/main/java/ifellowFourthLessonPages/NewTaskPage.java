package ifellowFourthLessonPages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$x;

public class NewTaskPage {

    public SelenideElement createButton = $x("//a[contains(@title,'Создать новую задачу')]").as("Кнопка создать");;
    public SelenideElement typeOfTask = $x("//input[@id='issuetype-field']").as("Выбор типа: задача");;
    public SelenideElement nameTask = $x("//input[@class='text long-field']").as("Название задачи");;
    public SelenideElement nextButton = $x("//input[@value='Создать']").as("Создание задачи");;

    public void createNewTask(String type, String name){
        createButton.click();
        typeOfTask.click();
        typeOfTask.clear();
        typeOfTask.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        typeOfTask.sendKeys(Keys.BACK_SPACE);
        typeOfTask.setValue(type);
        $x("//div[@id='issuetype-suggestions']//a[normalize-space(.)='" + type + "']")
                .as("Выбор типа задачи")
                .click();
        nameTask.setValue(name);
        nextButton.click();
    }
}
