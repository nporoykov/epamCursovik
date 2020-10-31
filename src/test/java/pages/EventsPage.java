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

    @FindBys(@FindBy(css="div.evnt-events-column.cell-3")) //локатор списка карточек Upcoming events
    private List<WebElement> listCount;


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

    public EventsPage assertUpcomingEventsNumber(){
        assertThat(listCount.size()).isEqualTo(Integer.parseInt(eventsCount.getText()));
        logger.info("Проверяем, что количество карточек равно счетчику на кнопке Upcoming Events");
//Количество карточек равно счетчику на кнопке Upcoming Events
        return this;
    }

  //  List<WebElement> smartPhones = driver.findElements((By.xpath("//img[contains(@alt,'Смартфон ')]")));
  //  assertEquals(2, smartPhones.size());
}
