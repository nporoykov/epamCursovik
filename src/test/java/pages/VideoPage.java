package pages;

import io.qameta.allure.Step;
import io.qameta.allure.Story;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;


import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class VideoPage extends BaseClass{
    private Logger logger = LogManager.getLogger(MajorPage.class);

    @FindBy(xpath="//input[@class='evnt-text-fields form-control evnt-search']") //локатор поля ввода поиска
    private WebElement inputField;

    @FindBy(xpath="//span[text()='More Filters']") //локатор More Filters
    private WebElement moreFilters;

    @FindBy(xpath="//span[text()='Category']") //локатор category
    private WebElement category;

    @FindBy(xpath="//label[@data-value='Testing']") //локатор category -> Testing
    private WebElement categoryTesting;

    @FindBy(xpath="//span[text()='Location']") //локатор location
    private WebElement location;

    @FindBy(xpath="//label[@data-value='Belarus']") //локатор location –> Belarus
    private WebElement locationBelarus;

    @FindBy(xpath="//span[text()='Language']") //локатор language
    private WebElement language;

    @FindBy(xpath="//label[@data-value='ENGLISH']") //локатор language –> English
    private WebElement languageEnglish;

    @FindBys(@FindBy(xpath="//div[@class='evnt-card-wrapper']")) //локатор списка отфильтрованных карточек
    private List<WebElement> listFilteredCards;

    String lang = ".//p[@class='language']/span";  //
    String site = ".//p[@class='online']/span";
    String name = ".//h1/span";



    public VideoPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Step("Вводим ключевое слово QA в поле поиска")
    public VideoPage inputQaToFilter(){
        waitForElement(inputField).sendKeys("QA");
        logger.info("Вводим ключевое слово QA в поле поиска");

        return this;
    }

    @Step("Нажимаем на More Filters")
    public VideoPage clickMoreFilters(){
        moreFilters.click();
        logger.info("Нажимаем на More Filters");

        return this;
    }


    @Step("Выбираем: Category – Testing, Location – Belarus, Language – English")
    public VideoPage selectFilters(){
        category.click();
        categoryTesting.click();
        logger.info("Пользователь выбирает: Category – Testing");

        location.click();
        locationBelarus.click();
        logger.info("Пользователь выбирает: Location – Belarus");

        language.click();
        languageEnglish.click();
        logger.info("Пользователь выбирает: Language – English");

        return this;
    }

    @Story("Проверяем, что карточка +(i+1)+ содержит в названии +localCardName+ ключевое слово поиска QA")
    public VideoPage assertFilteredCardsContainsQaString(){
        for (Integer i = 0;i < waitForElements(listFilteredCards).size(); i++){
            String localCardName = waitForElement(listFilteredCards.get(i).findElement(By.xpath(name))).getText();
            Assert.assertTrue(localCardName.contains("QA"));

            logger.info("Проверяем, что карточка "+(i+1)+" содержит в названии \"" +listFilteredCards.get(i).findElement(By.xpath(name)).getText()+"\" ключевое слово поиска QA");
        }

        return this;
    }

}