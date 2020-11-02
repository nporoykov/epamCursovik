package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class VideoPage extends BaseClass{
    private Logger logger = LogManager.getLogger(MajorPage.class);

    @FindBy(xpath="//input[@class='evnt-text-fields form-control evnt-search']") //локатор поля ввода поиска
    private WebElement inputField;

    @FindBys(@FindBy(xpath="//div[@class='evnt-card-wrapper']")) //локатор списка отфильтрованных карточек
    private List<WebElement> listFilteredCards;

    public VideoPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public VideoPage inputQaToFilter(){
        inputField.sendKeys("QA");
        logger.info("Вводим ключевое слово QA в поле поиска");

        return this;
    }

    public VideoPage getFilteredCardsNumber(){
        logger.info("На странице отображаются " +listFilteredCards.size()+ " докладов, содержащих в названии ключевое слово поиска QA");

        return this;
    }

}