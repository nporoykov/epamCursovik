package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class DetailedInfoEventPage extends BaseClass {

    private Logger logger = LogManager.getLogger(MajorPage.class);

    @FindBy(xpath="//button[contains(text(),'Register')]") //локатор buttonRegister
    private WebElement buttonRegister;

    @FindBy(xpath="//div[@class='evnt-panel-wrapper']//h4") //локатор detailedDate
    private WebElement detailedDate;

    @FindBys(@FindBy(xpath="//div[@class='evnt-card-wrapper']//h1/span")) //локатор списка h2 в карточке
    private List<WebElement> listEventProgram;


    public DetailedInfoEventPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        logger.info("Перешли на страницу с подробной информацией о мероприятии");
    }


    public DetailedInfoEventPage checkRegButton(){
        assertThat(buttonRegister.getText()).isNotNull();
        logger.info("На странице отображается блок с кнопкой для регистрации ="+buttonRegister.getText());

        return this;
    }

    public DetailedInfoEventPage checkDetailedDate(){
        assertThat(waitForElement(detailedDate).getText()).isNotNull();
        logger.info("На странице отображается дата и время ="+detailedDate.getText());

        return this;
    }

    public DetailedInfoEventPage checkEventProgram(){
        for (Integer i = 0;i < listEventProgram.size(); i++){

            assertThat(listEventProgram.get(i).getText()).isNotNull();
            logger.info("Проверяем, что программа мероприятия включает " + listEventProgram.get(i).getText());
        }

        return this;
    }


}
