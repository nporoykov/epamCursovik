package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class BaseHooks {
//    protected static WebDriver driver;
//
//    @BeforeClass
//    public static void setup() {
//        driver = WebDriverFactory.createDriver(WebDriverType.OPERA);
//
//        if (driver != null) {
//            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//            driver.manage().window().maximize();
//        }
//    }
//
//    @AfterClass
//    public static void teardown() {
//        if (driver != null) {
//            driver.quit();
//        }
//    }

    private static ThreadLocal<WebDriver> webDriverThreadLocal = new ThreadLocal<>();

    public BaseHooks(){
    }

    public static WebDriver getWebDriver() {

        return webDriverThreadLocal.get();
    }


    @BeforeMethod
    public void runDriverThreadUp() {
        WebDriver driver = WebDriverFactory.createDriver(WebDriverType.FIREFOX);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        webDriverThreadLocal.set(driver);
    }


    @AfterMethod
    public void cleanDriverThreadUp() {
        getWebDriver().manage().deleteAllCookies();
        Optional.ofNullable(getWebDriver()).ifPresent(WebDriver::quit);
    }

}