package ifellowThirdLessonPages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class ProjectPage {

    public SelenideElement projectXpath = $x("//a[@id = 'browse_link']");
    public SelenideElement projectTestXpath = $x("//a[@id='admin_main_proj_link_lnk']");
    public SelenideElement projectHeader = $x("//*[@title='Test']");

    public void openProject(){
        projectXpath.click();
        projectTestXpath.click();
        projectHeader.shouldBe(visible);
    }

    public int getCountOfProject(){
        SelenideElement projectCounter = $x("//div[@class='pager']//div[@class='showing']/span");
        projectCounter.shouldBe(visible);
        String countOfTasks = projectCounter.getText();
        String total = countOfTasks.split("из")[1].trim();
        return Integer.parseInt(total.replaceAll("\\D", ""));
    }
}
