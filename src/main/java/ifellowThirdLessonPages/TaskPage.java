package ifellowThirdLessonPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class TaskPage {

    public SelenideElement searchInput = $x("//input[@id='quickSearchInput']").as("Поиск");
    public SelenideElement headerTask = $x("//h1[@id='summary-val']").as("Название задачи");
    public SelenideElement statusTask = $x("//span[@id='status-val']").as("Статус задачи");
    public SelenideElement versionTask = $x("//span[@id='fixVersions-field']").as("Версия задачи");
    public SelenideElement moreDropdownButton = $x("//a[@id='opsbar-transitions_more']").as("Раскрытие списка статусов задачи");

    public void openTask(String taskFullName){
        searchInput.shouldBe(visible).setValue(taskFullName).pressEnter();
        headerTask.shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    public void openLastBug() {
        $x("//a[contains(@class,'order-options') and .//span[contains(text(),'Сортировать по')]]")
                .as("Сортировка")
                .shouldBe(visible, Duration.ofSeconds(10))
                .click();

        SelenideElement dropdownContainer = $x("//div[contains(@class,'aui-list-scroll')]")
                .as("Список сортировок")
                .shouldBe(visible, Duration.ofSeconds(10));

        dropdownContainer.$x(".//ul[contains(@class,'aui-list-section')]//li[1]")
                .as("Первое поле (Создано)")
                .shouldBe(visible, Duration.ofSeconds(10))
                .click();

        $x("//ol[contains(@class,'issue-list')]/li")
                .as("Лист")
                .shouldBe(visible, Duration.ofSeconds(10));

        $x("(//ol[contains(@class,'issue-list')]/li[1]/a)")
                .as("Последний баг")
                .shouldBe(visible, Duration.ofSeconds(10))
                .click();
    }


    public void moveTaskToStatus(String targetStatus) {
        ElementsCollection visibleButtons = $$x("//div[@id='opsbar-opsbar-transitions']//a[contains(@class,'issueaction-workflow-transition')]")
                .as("Видимые статусы")
                .filter(visible);
        SelenideElement button = visibleButtons.findBy(text(targetStatus));

        if (button.exists() && button.isEnabled()) {
            button.click();

        } else {
            moreDropdownButton
                    .shouldBe(enabled, Duration.ofSeconds(10))
                    .click();

            SelenideElement targetButton = $x("//aui-dropdown-menu[@id='opsbar-transitions_more_drop']//span[text()='" + targetStatus + "']/parent::a")
                    .as("Не видимые статусы (в выпадающем списке)")
                    .shouldBe(visible, Duration.ofSeconds(15));

            actions().moveToElement(targetButton).click().perform();
        }
    }
}

