package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public abstract class BaseClass {
    WebDriver driver;

    public BaseClass(WebDriver driver){
        this.driver = driver;
    }

    protected WebElement waitForElement(WebElement element) {
        return new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(element));
    }

    protected List<WebElement> waitForElements(List<WebElement> element) {
        return new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfAllElements(element));
    }
}
