package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static org.assertj.core.api.Assertions.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.WebDriverFactory;

import java.util.List;

public class EventsPage extends BaseClass{

    private Logger logger = LogManager.getLogger(MajorPage.class);

    @FindBy(xpath="//span[contains(text(),'Upcoming events')]") //локатор span Upcoming events
    private WebElement upcomingEventsButton;

    @FindBy(css="span.evnt-tab-counter.evnt-label.small.white") //локатор счетчика Upcoming events
    private WebElement eventsCount;

 //   @FindBys(@FindBy(css="div.evnt-events-column.cell-3")) //локатор списка карточек Upcoming events
 //   private List<WebElement> listCount;

    @FindBys(@FindBy(xpath="//div[@class='evnt-events-column cell-3']")) //локатор списка карточек Upcoming events
    private List<WebElement> listCount;

    String lang = ".//p[@class='language']/span";  //
    String site = ".//p[@class='online']/span";
    String name = ".//h1/span";
    String date = ".//p/span[@class='date']";
    String regInfo = ".//p/span[@class='status free-attend']";
    String speakersList = ".//div[@class='evnt-speaker']";  // data-name="Vasil Kasimov"
  //  String cardsList = "//div[@class='evnt-events-column cell-3']";


    public EventsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public EventsPage clickUpcomingEventsButton(){
        upcomingEventsButton.click();
        logger.info("Нажимаем на кнопку EVENTS");

        return this;
    }

    public String returnUpcomingEventsNumber(){

        return eventsCount.getText();
    }

    public EventsPage assertUpcomingEventsNumberIsNotNull(){
        assertThat(listCount.size()).isNotNull();
        logger.info("Проверяем, что количество карточек не null");

        return this;
    }

    public EventsPage assertUpcomingEventsNumber(){
        assertThat(listCount.size()).isEqualTo(Integer.parseInt(eventsCount.getText()));
        logger.info("Проверяем, что количество карточек " + listCount.size() + " равно счетчику на кнопке Upcoming Events " + Integer.parseInt(eventsCount.getText()));

        return this;
    }


    public EventsPage checkCardsContent(){
        for (Integer i = 0;i < listCount.size(); i++){

            assertThat(listCount.get(i).findElement(By.xpath(site)).getText()).isNotNull();
            logger.info("Проверяем, что в карточке "+(i+1)+" место проведения не null и = " + listCount.get(i).findElement(By.xpath(site)).getText());

            assertThat(listCount.get(i).findElement(By.xpath(lang)).getText()).isNotNull();
            logger.info("Проверяем, что в карточке "+(i+1)+" указанный язык не null и = " + listCount.get(i).findElement(By.xpath(lang)).getText());

            assertThat(listCount.get(i).findElement(By.xpath(name)).getText()).isNotNull();
            logger.info("Проверяем, что в карточке "+(i+1)+" название мероприятия не null и = " + listCount.get(i).findElement(By.xpath(name)).getText());

            assertThat(listCount.get(i).findElement(By.xpath(date)).getText()).isNotNull();
            logger.info("Проверяем, что в карточке "+(i+1)+" датта мероприятия не null и = " + listCount.get(i).findElement(By.xpath(date)).getText());

            assertThat(listCount.get(i).findElement(By.xpath(regInfo)).getText()).isNotNull();
            logger.info("Проверяем, что в карточке "+(i+1)+" информация о регистрации не null и = " + listCount.get(i).findElement(By.xpath(regInfo)).getText());

            assertThat(listCount.get(i).findElements(By.xpath(speakersList))).isNotNull();
            logger.info("Проверяем, что в карточке "+(i+1)+" количество спикеров не null");

            List<WebElement> speakers = (listCount.get(i)).findElements(By.xpath(speakersList));

            for(Integer s = 0;s < speakers.size(); s++){
                logger.info("Спикер №_" + (s+1) + " в карточке "+(i+1)+ " = " + speakers.get(s).getAttribute("data-name").toString());
            }

        }

        return this;
    }


}
