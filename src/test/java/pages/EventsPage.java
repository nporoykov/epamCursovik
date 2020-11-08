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

    @FindBy(xpath="//span[contains(text(),'Past Events')]") //локатор span Past Events
    private WebElement pastEventsButton;

    @FindBy(xpath="//span[text()='Upcoming']/following-sibling::span") //локатор счетчика Upcoming events
    private WebElement eventsCount;

    @FindBy(xpath="//span[text()='Past']/following-sibling::span") //локатор счетчика Past events
    private WebElement pastEventsCount;

    @FindBy(xpath="//span[text()='Location']") //локатор счетчика Past events     data-value="Canada"
    private WebElement buttonFilterLocation;

    @FindBy(xpath="//label[@data-value='Canada']") //локатор счетчика Past events     data-value="Canada"
    private WebElement labelCanada;


    @FindBys(@FindBy(xpath="//div[@class='evnt-events-column cell-3']")) //локатор списка карточек Upcoming Events
    private List<WebElement> listCount;

//    @FindBys(@FindBy(xpath="//div[@class='evnt-event-name']")) //локатор списка карточек Past Events
//    private List<WebElement> listPastEventsCount;


    String cardTag = "//div[@class='evnt-event-name']";
    String lang = ".//p[@class='language']/span";  //
    String site = ".//p[@class='online']/span";
    String name = ".//h1/span";
    String date = ".//p/span[@class='date']";
    String regInfo = ".//p/span[@class='status free-attend']";
    String speakersList = ".//div[@class='evnt-speaker']";



    public EventsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public EventsPage clickUpcomingEventsButton(){
        upcomingEventsButton.click();
        logger.info("Нажимаем на кнопку UPCOMING EVENTS");

        return this;
    }

    public EventsPage clickPastEventsButton(){
        pastEventsButton.click();
        logger.info("Нажимаем на кнопку PAST EVENTS");

        return this;
    }

    public String returnUpcomingEventsNumber(){

        return eventsCount.getText();
    }

    public EventsPage clickButtonFilterLocation() {
        buttonFilterLocation.click();
        logger.info("Нажимаем на Location в блоке фильтров");

        return this;
    }

    public EventsPage clickLabelCanada() {
        labelCanada.click();
        logger.info("Выбираем Canada в выпадающем списке");

        return this;
    }


    public EventsPage assertUpcomingEventsNumberIsNotNull(){
        assertThat(listCount.size()).isNotNull();
        logger.info("Проверяем, что количество карточек не null");

        return this;
    }

    public EventsPage assertPastEventsNumberIsNotNull(){
        WebDriverWait wait = new WebDriverWait(driver, 7);
        assertThat(wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(cardTag)))).isNotNull();

        Integer cardsCount = driver.findElements(By.xpath(cardTag)).size();
        logger.info("Проверяем, что количество карточек не null и = " + cardsCount);

        assertThat(cardsCount).isEqualTo(Integer.parseInt(pastEventsCount.getText()));
        logger.info("Проверяем, что количество карточек " + cardsCount + " равно счетчику на кнопке Past Events " + Integer.parseInt(pastEventsCount.getText()));

        return this;
    }


    public EventsPage assertUpcomingEventsNumber(){
        assertThat(listCount.size()).isEqualTo(Integer.parseInt(eventsCount.getText()));
        logger.info("Проверяем, что количество карточек " + listCount.size() + " равно счетчику на кнопке Upcoming Events " + Integer.parseInt(eventsCount.getText()));

        return this;
    }


//    public EventsPage assertPastEventsNumber(){
//        assertThat(listPastEventsCount.size()).isEqualTo(Integer.parseInt(pastEventsCount.getText()));
//        logger.info("Проверяем, что количество карточек " + listCount.size() + " равно счетчику на кнопке Past Events " + Integer.parseInt(pastEventsCount.getText()));
//
//        return this;
//    }

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

    public DetailedInfoEventPage clickEventCard() {
        listCount.get(0).click();
        logger.info("Нажимает на любую карточку");

        return new DetailedInfoEventPage(driver);
    }




}
