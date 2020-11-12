package cases;

import org.testng.annotations.Test;
import pages.MajorPage;
import pages.EventsPage;
import utils.BaseHooks;

import java.text.ParseException;
//import ;


public class CheckNextEvents extends BaseHooks {

    @Test(priority = 100)
    public void checkNextEventsEqualsToUpcomingEvents() {
        MajorPage majorPage = new MajorPage(driver);

        majorPage.open("https://events.epam.com/")
                 .clickEventsTab()
                 .clickUpcomingEventsButton()
                 .assertUpcomingEventsNumberIsNotNull()
                 .assertUpcomingEventsNumberEqualsCardsNumber();
    }

    @Test(priority = 200)
    public void checkUpcomingEventsCards() {
        MajorPage majorPage = new MajorPage(driver);

        majorPage.open("https://events.epam.com/")
                .clickEventsTab()
                .clickUpcomingEventsButton()
                .assertUpcomingEventsNumberIsNotNull()
                .checkCardsContent();
    }

    @Test(priority = 300)
    public void checkThisWeekUpcomingEvents() throws ParseException, InterruptedException {
        MajorPage majorPage = new MajorPage(driver);

        majorPage.open("https://events.epam.com/")
                .clickEventsTab()
                .clickUpcomingEventsButton()
                .assertThisWeekEventsWithInThisWeekDates();
    }

    @Test(priority = 400)
    public void checkPastEventsInCanada() throws InterruptedException {
        MajorPage majorPage = new MajorPage(driver);

        majorPage.open("https://events.epam.com/")
                .clickEventsTab()
                .clickPastEventsButton()
                .clickButtonFilterLocation()
                .clickLabelCanada()
                .assertPastEventsNumber();
    }


    @Test(priority = 500)
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


    @Test(priority = 600)
    public void checkFilteredVideoCards() {
        MajorPage majorPage = new MajorPage(driver);

        majorPage.open("https://events.epam.com/")
                .clickVideoTab()
                .clickMoreFilters()
                .selectFilters();

    }


    @Test(priority = 700)
    public void checkVideoCardsByKeyWord() {
        MajorPage majorPage = new MajorPage(driver);

        majorPage.open("https://events.epam.com/")
                .clickVideoTab()
                .inputQaToFilter()
                .assertFilteredCardsContainsQaString();

    }




}
