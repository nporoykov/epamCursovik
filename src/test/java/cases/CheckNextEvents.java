package cases;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Test;
import pages.MajorPage;
import pages.EventsPage;
import utils.BaseHooks;

import java.text.ParseException;
//import ;


public class CheckNextEvents extends BaseHooks {

    @Test
    @Epic("Проектная работа от EPAM")
    @Feature("Тестирование https://events.epam.com/")
    @Story("Тест - Просмотр предстоящих мероприятий")
    @Description("Проверяем, что на странице отображается столько карточек сколько на счетчике на кнопке Upcoming Events")
    public void checkNextEventsEqualsToUpcomingEvents() {
        MajorPage majorPage = new MajorPage(getWebDriver());

        majorPage.open("https://events.epam.com/")
                 .clickEventsTab()
                 .clickUpcomingEventsButton()
                 .assertUpcomingEventsNumberIsNotNull()
                 .assertUpcomingEventsNumberEqualsCardsNumber();
    }

    @Test
    @Epic("Проектная работа от EPAM")
    @Feature("Тестирование https://events.epam.com/")
    @Story("Тест - Просмотр карточек мероприятий")
    @Description("Проверка информации о мероприятии в карточках")
    public void checkUpcomingEventsCards() {
        MajorPage majorPage = new MajorPage(getWebDriver());

        majorPage.open("https://events.epam.com/")
                .clickEventsTab()
                .clickUpcomingEventsButton()
                .assertUpcomingEventsNumberIsNotNull()
                .checkCardsContent();
    }

    @Test
    @Epic("Проектная работа от EPAM")
    @Feature("Тестирование https://events.epam.com/")
    @Story("Тест - Валидация дат предстоящих мероприятий")
    @Description("Проверка, что в блоке This week даты проведения мероприятий(карточек) находятся в пределах текущей недели")
    public void checkThisWeekUpcomingEvents() throws ParseException, InterruptedException {
        MajorPage majorPage = new MajorPage(getWebDriver());

        majorPage.open("https://events.epam.com/")
                .clickEventsTab()
                .clickUpcomingEventsButton()
                .assertThisWeekEventsWithInThisWeekDates();
    }

    @Test
    @Epic("Проектная работа от EPAM")
    @Feature("Тестирование https://events.epam.com/")
    @Story("Тест - Просмотр прошедших мероприятий в Канаде")
    @Description("Проверка, что в блоке This week даты проведения мероприятий(карточек) находятся в пределах текущей недели")
    public void checkPastEventsInCanada() throws InterruptedException {
        MajorPage majorPage = new MajorPage(getWebDriver());

        majorPage.open("https://events.epam.com/")
                .clickEventsTab()
                .clickPastEventsButton()
                .clickButtonFilterLocation()
                .clickLabelCanada()
                .assertPastEventsNumber();
    }


    @Test
    @Epic("Проектная работа от EPAM")
    @Feature("Тестирование https://events.epam.com/")
    @Story("Тест - Просмотр детальной информации о мероприятии")
    @Description("Проверка, что на странице с информацией о мероприятии отображается блок с кнопкой для регистрации, дата и время, программа мероприятия")
    public void checkUpcomingEventsCardsDetails() {
        MajorPage majorPage = new MajorPage(getWebDriver());

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
    @Epic("Проектная работа от EPAM")
    @Feature("Тестирование https://events.epam.com/")
    @Story("Тест - Фильтрация докладов по категориям")
    @Description("Проверка, что на странице отображаются карточки соответствующие правилам выбранных фильтров")
    public void checkFilteredVideoCards() {
        MajorPage majorPage = new MajorPage(getWebDriver());

        majorPage.open("https://events.epam.com/")
                .clickVideoTab()
                .clickMoreFilters()
                .selectFilters();

    }


    @Test
    @Epic("Проектная работа от EPAM")
    @Feature("Тестирование https://events.epam.com/")
    @Story("Тест - Поиск докладов по ключевому слову")
    @Description("Проверка, что на странице отображаются доклады, содержащие в названии ключевое слово поиска")
    public void checkVideoCardsByKeyWord() throws InterruptedException {
        MajorPage majorPage = new MajorPage(getWebDriver());

        majorPage.open("https://events.epam.com/")
                .clickVideoTab()
                .inputQaToFilter()
                .assertFilteredCardsContainsQaString();//

    }


}
