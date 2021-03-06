package pages;

import io.qameta.allure.Step;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.TemporalField;
import java.time.temporal.TemporalQueries;
import java.time.temporal.WeekFields;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class EventsPage extends BaseClass{

    private static Logger logger = LogManager.getLogger(MajorPage.class);

    @FindBy(xpath="//span[text()='Upcoming events']") //локатор span Upcoming events
    private WebElement upcomingEventsButton;

    @FindBy(xpath="//span[text()='Past Events']") //локатор span Past Events   /////падение теста Past Events
    private WebElement pastEventsButton;

    @FindBy(xpath="//span[text()='Upcoming']/following-sibling::span") //локатор счетчика Upcoming events
    private WebElement eventsCount;

    @FindBy(xpath="//span[text()='Past']/following-sibling::span") //локатор счетчика Past events
    private WebElement pastEventsCount;

    @FindBy(xpath="//span[text()='Location']") //локатор buttonFilterLocation
    private WebElement buttonFilterLocation;

    @FindBy(xpath="//label[@data-value='Canada']") //локатор labelCanada
    private WebElement labelCanada;

    @FindBys(@FindBy(xpath="//h3[text()='This week']/following-sibling::div[@class='evnt-events-row']//div[@class='evnt-card-wrapper']")) //локатор блока This week
    private List<WebElement> thisWeekBlock;


    @FindBys(@FindBy(xpath="//div[@class='evnt-events-column cell-3']")) //локатор списка карточек Upcoming Events
    private List<WebElement> listCount;

//    @FindBys(@FindBy(xpath="//div[@class='evnt-event-name']")) //локатор списка карточек Past Events
//    private List<WebElement> listPastEventsCount;


    String cardTag = "//div[@class='evnt-cards-container']";  //evnt-cards-container    evnt-event-name
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

    @Step("Нажимаем на кнопку UPCOMING EVENTS")
    public EventsPage clickUpcomingEventsButton(){
        waitForElement(upcomingEventsButton).click();
        logger.info("Нажимаем на кнопку UPCOMING EVENTS");

        return this;
    }

    @Step("Нажимаем на кнопку PAST EVENTS")
    public EventsPage clickPastEventsButton(){
        waitForElement(pastEventsButton).click();
        logger.info("Нажимаем на кнопку PAST EVENTS");

        return this;
    }

    public String returnUpcomingEventsNumber(){

        return eventsCount.getText();
    }

    @Step("Нажимаем на Location в блоке фильтров")
    public EventsPage clickButtonFilterLocation() {
        buttonFilterLocation.click();
        logger.info("Нажимаем на Location в блоке фильтров");

        return this;
    }

    @Step("Выбираем Canada в выпадающем списке")
    public EventsPage clickLabelCanada() {
        labelCanada.click();
        logger.info("Выбираем Canada в выпадающем списке");

        return this;
    }

    @Step("Проверяем, что количество карточек на странице не 0")
    public EventsPage assertUpcomingEventsNumberIsNotNull(){
        assertThat(listCount.size()).isNotNull();
        logger.info("Проверяем, что количество карточек не null");

        return this;
    }

    @Step("Проверяем, что на странице отображается столько карточек сколько на счетчике на кнопке Past Events")
    public EventsPage assertPastEventsNumber() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 7);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(cardTag)));
        Thread.sleep(1000);

        Integer cardsCount = driver.findElements(By.xpath(cardTag)).size();
        logger.info("Проверяем, что количество карточек не null и = " + cardsCount);

        assertThat(cardsCount).isEqualTo(Integer.parseInt(pastEventsCount.getText()));
        logger.info("Проверяем, что количество карточек " + cardsCount + " равно счетчику на кнопке Past Events " + Integer.parseInt(pastEventsCount.getText()));

        return this;
    }


    @Step("Проверяем, что дата в карточках находится в пределах текущей недели")
    public EventsPage assertThisWeekEventsWithInThisWeekDates() throws InterruptedException, ParseException {
        logger.info("Находим количество карточек в блоке This Week = " + thisWeekBlock.size());

        for (Integer i = 0;i < thisWeekBlock.size(); i++){
            Date cardDate = new SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH).parse(thisWeekBlock.get(i).findElement(By.xpath(date)).getText());
            logger.info("Дата в карточке = " + cardDate);

            assertThat(cardDate.after(returnFirstDayOfWeek()) && cardDate.before(returnLastDayOfWeek())).isTrue();
            logger.info("Проверяем, что дата в карточке " +cardDate+ " >= первого дня недели " + returnFirstDayOfWeek() + " и <= последнего дня недели "+returnLastDayOfWeek());

        }
        return this;
    }

    public Date returnFirstDayOfWeek(){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
        Date firstDayOfTheWeek = cal.getTime();


        return firstDayOfTheWeek; // return Monday
    }

    public Date returnLastDayOfWeek(){
        Calendar cal = Calendar.getInstance();
        cal.setTime(returnFirstDayOfWeek());
        cal.add(Calendar.DATE, 6);
        Date lastDayOfTheWeek = cal.getTime();;

        return lastDayOfTheWeek; // return Sunday
    }

    @Step("Проверяем, что на странице отображается столько карточек сколько на счетчике на кнопке Upcoming Events")
    public EventsPage assertUpcomingEventsNumberEqualsCardsNumber(){
        assertThat(listCount.size()).isEqualTo(Integer.parseInt(eventsCount.getText()));
        logger.info("Проверяем, что количество карточек " + listCount.size() + " равно счетчику на кнопке Upcoming Events " + Integer.parseInt(eventsCount.getText()));

        return this;
    }


    @Step("Проверяем, что в карточках указана информация о мероприятии: место проведения, название мероприятия, ата мероприятия и т.д")
    public EventsPage checkCardsContent(){
        for (Integer i = 0;i < listCount.size(); i++){

            assertThat(listCount.get(i).findElement(By.xpath(site)).getText()).isNotNull();
            logger.info("Проверяем, что в карточке "+(i+1)+" место проведения не null и = " + listCount.get(i).findElement(By.xpath(site)).getText());

            assertThat(listCount.get(i).findElement(By.xpath(lang)).getText()).isNotNull();
            logger.info("Проверяем, что в карточке "+(i+1)+" указанный язык не null и = " + listCount.get(i).findElement(By.xpath(lang)).getText());

            assertThat(listCount.get(i).findElement(By.xpath(name)).getText()).isNotNull();
            logger.info("Проверяем, что в карточке "+(i+1)+" название мероприятия не null и = " + listCount.get(i).findElement(By.xpath(name)).getText());

            assertThat(listCount.get(i).findElement(By.xpath(date)).getText()).isNotNull();
            logger.info("Проверяем, что в карточке "+(i+1)+" дата мероприятия не null и = " + listCount.get(i).findElement(By.xpath(date)).getText());

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

    @Step("Нажимает на любую карточку")
    public DetailedInfoEventPage clickEventCard() {
        listCount.get(0).click();
        logger.info("Нажимает на любую карточку");

        return new DetailedInfoEventPage(driver);
    }




}
