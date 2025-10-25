package ifellowFinalTaskUIPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class TaskPage {

    public SelenideElement searchInput = $x("//input[@id='quickSearchInput']").as("Поиск");
    public SelenideElement headerTask = $x("//h1[@id='summary-val']").as("Название задачи");
    public SelenideElement statusTask = $x("//span[@id='status-val']").as("Статус задачи");
    public SelenideElement versionTask = $x("//span[@id='fixVersions-field']").as("Версия задачи");
    public SelenideElement moreDropdownButton = $x("//a[@id='opsbar-transitions_more']").as("Раскрытие списка статусов задачи");
    public SelenideElement sort = $x("//a[contains(@class,'order-options') and .//span[contains(text(),'Сортировать по')]]").as("Сортировка");
    public SelenideElement dropdownContainer = $x("//div[contains(@class,'aui-list-scroll')]").as("Список сортировок");
    public SelenideElement list = $x("//ol[contains(@class,'issue-list')]/li").as("Лист");
    public SelenideElement lastBug = $x("(//ol[contains(@class,'issue-list')]/li[1]/a)").as("Последний баг");
    public ElementsCollection visibleButtons = $$x("//div[@id='opsbar-opsbar-transitions']//a[contains(@class,'issueaction-workflow-transition')]").as("Видимые статусы");
    private final Duration timeout = Duration.ofSeconds(10);

    @Step("Открытие задачи {taskFullName}")
    public void openTask(String taskFullName){
        searchInput
                .shouldBe(visible).
                setValue(taskFullName).
                pressEnter();
        headerTask.shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    @Step("Открытие последней созданной ошибки")
    public void openLastBug() {
        sort
                .shouldBe(visible, timeout)
                .click();

        dropdownContainer.shouldBe(visible, timeout);

        dropdownContainer.$x(".//ul[contains(@class,'aui-list-section')]//li[1]")//ищет элемент относительно dropdownContainer
                .as("Первое поле (Создано)")
                .shouldBe(visible, timeout)
                .click();

        list.shouldBe(visible, timeout);
        lastBug.shouldBe(visible, timeout).click();
    }


    @Step("Смена статуса {targetStatus}")
    public void moveTaskToStatus(String targetStatus) {
        visibleButtons
                .filter(visible);
        SelenideElement button = visibleButtons.findBy(text(targetStatus));

        if (button.exists() && button.isEnabled()) {
            button.click();
        } else {
            moreDropdownButton
                    .shouldBe(enabled, timeout)
                    .click();

            SelenideElement targetButton = $x("//aui-dropdown-menu[@id='opsbar-transitions_more_drop']//span[text()='" + targetStatus + "']/parent::a")//передается динамическая переменная
                    .as("Не видимые статусы (в выпадающем списке)")
                    .shouldBe(visible, enabled);

            actions().moveToElement(targetButton).click().perform();
        }
    }
}

