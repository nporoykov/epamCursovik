package cases;

import org.testng.annotations.Test;
import pages.MajorPage;
import pages.EventsPage;
import utils.BaseHooks;

import java.text.ParseException;
//import ;


public class CheckNextEvents extends BaseHooks {

    @Test
    public void checkNextEventsEqualsToUpcomingEvents() {
        MajorPage majorPage = new MajorPage(driver);

        majorPage.open("https://events.epam.com/")
                 .clickEventsTab()
                 .clickUpcomingEventsButton()
                 .assertUpcomingEventsNumberIsNotNull()
                 .assertUpcomingEventsNumberEqualsCardsNumber();
    }

    @Test
    public void checkUpcomingEventsCards() {
        MajorPage majorPage = new MajorPage(driver);

        majorPage.open("https://events.epam.com/")
                .clickEventsTab()
                .clickUpcomingEventsButton()
                .assertUpcomingEventsNumberIsNotNull()
                .checkCardsContent();
    }

    @Test
    public void checkThisWeekUpcomingEvents() throws ParseException, InterruptedException {
        MajorPage majorPage = new MajorPage(driver);

        majorPage.open("https://events.epam.com/")
                .clickEventsTab()
                .clickUpcomingEventsButton()
                .assertThisWeekEventsWithInThisWeekDates();
    }

    @Test
    public void checkPastEventsInCanada() throws InterruptedException {
        MajorPage majorPage = new MajorPage(driver);

        majorPage.open("https://events.epam.com/")
                .clickEventsTab()
                .clickPastEventsButton()
                .clickButtonFilterLocation()
                .clickLabelCanada()
                .assertPastEventsNumber();
    }


    @Test
    public void checkUpcomingEventsCardsDetails() {
        MajorPage majorPage = new MajorPage(driver);

        majorPage.open("https://events.epam.com/")
                .clickEventsTab()
                .clickUpcomingEventsButton()
                .assertUpcomingEventsNumberIsNotNull()
                .clickEventCard()
                .checkRegButton()
                .checkDetailedDate()
                .checkEventProgram();
    }


    @Test
    public void checkFilteredVideoCards() {
        MajorPage majorPage = new MajorPage(driver);

        majorPage.open("https://events.epam.com/")
                .clickVideoTab()
                .clickMoreFilters()
                .selectFilters();

    }


    @Test
    public void checkVideoCardsByKeyWord() {
        MajorPage majorPage = new MajorPage(driver);

        majorPage.open("https://events.epam.com/")
                .clickVideoTab()
                .inputQaToFilter()
                .assertFilteredCardsContainsQaString();

    }


}
