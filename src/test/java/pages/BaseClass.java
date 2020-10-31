package pages;

import org.openqa.selenium.WebDriver;

public abstract class BaseClass {
    WebDriver driver;

    public BaseClass(WebDriver driver){
        this.driver = driver;
    }
}
