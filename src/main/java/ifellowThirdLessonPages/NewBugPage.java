package ifellowThirdLessonPages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.*;

public class NewBugPage {

    public SelenideElement createButtonBug = $x("//a[contains(@title,'Создать новую задачу')]");
    public SelenideElement typeBug = $x("//input[@id='issuetype-field']");
    public SelenideElement nameBug = $x("//input[@class='text long-field']");
    public SelenideElement nextButtonBug = $x("//input[@value='Создать']");
    public SelenideElement descriptionBug = $x("//iframe[contains(@id,'mce_')]");
    public ElementsCollection visualButtons = $$x("//div[@class='aui-navgroup-primary']//button[text()='Визуальный']");

    public void createNewBug(String bugType, String name, String description){
        createButtonBug.click();
        typeBug.click();
        typeBug.clear();
        typeBug.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        typeBug.sendKeys(Keys.BACK_SPACE);
        typeBug.setValue(bugType);
        if (!typeBug.getValue().equals(bugType)) {
            $x("//div[@id='issuetype-suggestions']//a[normalize-space(.)='" + bugType + "']").click();
        }
        nameBug.setValue(name);
        for (SelenideElement visualButton : visualButtons) {
            if (!visualButton.isSelected()) {
                visualButton.click();
            }
        }
        switchTo().frame(descriptionBug);
        $x("//body").setValue(description);
        switchTo().defaultContent();
        nextButtonBug.click();
    }
}
