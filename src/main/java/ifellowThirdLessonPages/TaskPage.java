package ifellowThirdLessonPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class TaskPage {

    public SelenideElement searchInput = $("#quickSearchInput");
    public SelenideElement headerTask = $x("//h1[@id='summary-val']");
    public SelenideElement statusTask = $x("//span[@id='status-val']");
    public SelenideElement versionTask = $x("//span[@id='fixVersions-field']");
    public SelenideElement moreDropdownButton = $x("//a[@id='opsbar-transitions_more']");

    public void openTask(String taskFullName){
        searchInput.shouldBe(visible).setValue(taskFullName).pressEnter();
        headerTask.shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    public void moveTaskToStatus(String targetStatus) {
        ElementsCollection visibleButtons = $$x("//div[@id='opsbar-opsbar-transitions']//a[contains(@class,'issueaction-workflow-transition')]")
                .filter(visible);
        SelenideElement button = visibleButtons.findBy(text(targetStatus));
        if (button.exists() && button.isEnabled()) {
            button.click();
        } else {
            moreDropdownButton.shouldBe(visible).click();
            SelenideElement dropdownMenu = $x("//aui-dropdown-menu[@id='opsbar-transitions_more_drop']")
                    .shouldBe(Condition.visible, Duration.ofSeconds(20));
            SelenideElement targetButton = dropdownMenu
                    .$$x(".//span[text()='" + targetStatus + "']")
                    .first()
                    .shouldBe(Condition.visible, Duration.ofSeconds(15))
                    .parent();
            targetButton.scrollIntoView(true).shouldBe(Condition.enabled, Duration.ofSeconds(15)).click();
        }
    }
}

