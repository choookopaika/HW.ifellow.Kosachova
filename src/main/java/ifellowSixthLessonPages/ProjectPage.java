package ifellowSixthLessonPages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class ProjectPage {

    public SelenideElement projectXpath = $x("//a[@id = 'browse_link']").as("Вкладка проекты");
    public SelenideElement projectTestXpath = $x("//a[@id='admin_main_proj_link_lnk']").as("Выбор проекта");
    public SelenideElement projectHeader = $x("//*[@title='Test']").as("Проект: тест");
    public SelenideElement projectCounter = $x("//div[@class='pager']//div[@class='showing']/span").as("Счетчик");

    @Step("Открытие проекта Test")
    public void openProject(){
        projectXpath.click();
        projectTestXpath.click();
        projectHeader.shouldBe(visible);
    }

    @Step("Счетчик созданных задач")
    public int getCountOfProject(){
        projectCounter.shouldBe(visible);
        String countOfTasks = projectCounter.getText();
        String total = countOfTasks.split("из")[1].trim();
        return Integer.parseInt(total.replaceAll("\\D", ""));
    }
}
