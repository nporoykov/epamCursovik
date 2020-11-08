package cases;

import org.testng.annotations.Test;
import pages.MajorPage;
import pages.EventsPage;
import utils.BaseHooks;
//import ;


public class CheckNextEvents extends BaseHooks {

    @Test(priority = 100)
    public void checkNextEvents() {
        MajorPage majorPage = new MajorPage(driver);

        majorPage.open("https://events.epam.com/")
                 .clickEventsTab()
                 .clickUpcomingEventsButton()
                 .assertUpcomingEventsNumberIsNotNull()
                 .assertUpcomingEventsNumber();
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


    @Test(priority = 400)
    public void checkPastEventsCards() {
        MajorPage majorPage = new MajorPage(driver);

        majorPage.open("https://events.epam.com/")
                .clickEventsTab()
                .clickPastEventsButton()
                .clickButtonFilterLocation()
                .clickLabelCanada()
                .assertPastEventsNumberIsNotNull();
    //            .assertPastEventsNumber();
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


    @Test(priority = 700)
    public void checkFilteredVideoCards() {
        MajorPage majorPage = new MajorPage(driver);

        majorPage.open("https://events.epam.com/")
                .clickVideoTab()
                .inputQaToFilter()
                .assertFilteredCardsNumberContainsQA();

    }




}
