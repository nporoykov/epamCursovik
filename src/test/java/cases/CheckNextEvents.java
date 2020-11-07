package cases;

import org.testng.annotations.Test;
import pages.MajorPage;
import pages.EventsPage;
import utils.BaseHooks;
//import ;


public class CheckNextEvents extends BaseHooks {

    @Test
    public void checkNextEvents() {
        MajorPage majorPage = new MajorPage(driver);

        majorPage.open("https://events.epam.com/")
                 .clickEventsTab()
                 .clickUpcomingEventsButton()
                 .assertUpcomingEventsNumberIsNotNull()
                 .assertUpcomingEventsNumber();
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
    public void checkPastEventsCards() {
        MajorPage majorPage = new MajorPage(driver);

        majorPage.open("https://events.epam.com/")
                .clickEventsTab()
                .clickPastEventsButton()
                .clickButtonFilterLocation()
                .clickLabelCanada()
                .assertPastEventsNumberIsNotNull();
     //           .assertPastEventsNumber();
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
                .inputQaToFilter()
                .getFilteredCardsNumber();

    }




}
