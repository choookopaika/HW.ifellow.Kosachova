package ifellowThirdLessonPages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$x;

public class NewTaskPage {

    public SelenideElement createButton = $x("//a[contains(@title,'Создать новую задачу')]");
    public SelenideElement typeOfTask = $x("//input[@id='issuetype-field']");
    public SelenideElement nameTask = $x("//input[@class='text long-field']");
    public SelenideElement nextButton = $x("//input[@value='Создать']");

    public void createNewTask(String type, String name){
        createButton.click();
        typeOfTask.click();
        typeOfTask.clear();
        typeOfTask.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        typeOfTask.sendKeys(Keys.BACK_SPACE);
        typeOfTask.setValue(type);
        $x("//div[@id='issuetype-suggestions']//a[normalize-space(.)='" + type + "']").click();
        nameTask.setValue(name);
        nextButton.click();
    }
}
