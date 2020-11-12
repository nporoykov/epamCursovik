package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
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

    public static WebDriver getDriver() {

        return webDriverThreadLocal.get();
    }


    @BeforeMethod
    public void runDriverThreadUp() throws MalformedURLException {
 //       WebDriver driver = WebDriverFactory.createDriver(WebDriverType.FIREFOX);
 //       driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        String selenoidURL = "http://localhost:4444/wd/hub";
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setBrowserName("chrome");
        caps.setVersion("85.0");
        caps.setCapability("enableVNC", true);
        caps.setCapability("screenResolution", "1280x1024");
        caps.setCapability("enableVideo", true);
        caps.setCapability("enableLog", true);

        RemoteWebDriver driver = new RemoteWebDriver(new URL(selenoidURL), caps);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        webDriverThreadLocal.set(driver);
    }


    @AfterMethod
    public void cleanDriverThreadUp() {
        getDriver().manage().deleteAllCookies();
        Optional.ofNullable(getDriver()).ifPresent(WebDriver::quit);
    }

}