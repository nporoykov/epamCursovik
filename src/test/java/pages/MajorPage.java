package pages;


import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.WebDriverFactory;

public class MajorPage extends BaseClass{

    private Logger logger = LogManager.getLogger(MajorPage.class);

    @FindBy(xpath="//a[contains(text(),'Events')]") //локатор event tab'а
    private WebElement eventTab;

    @FindBy(xpath="//a[contains(text(),'Video')]") //локатор event tab'а
    private WebElement videoTab;

    public MajorPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Step("Открыта страница \"+url")
    public MajorPage open(String url) {
        driver.get(url);
        logger.info("Открыта страница "+url);

        return this;
    }

    @Step("Переходим на вкладку EVENTS")
    public EventsPage clickEventsTab(){
        eventTab.click();
        logger.info("Переходим на вкладку EVENTS");

        return new EventsPage(driver);
    }

    @Step("Переходим на вкладку VIDEO")
    public VideoPage clickVideoTab(){
        videoTab.click();
        logger.info("Переходим на вкладку VIDEO");

        return new VideoPage(driver);
    }
}
