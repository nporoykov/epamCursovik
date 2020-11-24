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
import java.net.URL;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class BaseHooks {

    private static ThreadLocal<WebDriver> webDriverThreadLocal = new ThreadLocal<>();
    String slenoidURL = "http://localhost:4444/wd/hub";


    public BaseHooks(){
    }

    public static WebDriver getWebDriver() {

        return webDriverThreadLocal.get();
    }


    @BeforeMethod
    public void runDriverThreadUp() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setBrowserName("chrome");
        caps.setVersion("85.0");
        caps.setCapability("enableVNC", true);
        caps.setCapability("screenResolution", "1980x2014");
        caps.setCapability("enableVideo", true);
        caps.setCapability("enableLog", true);

        RemoteWebDriver driver = new RemoteWebDriver(new URL(slenoidURL), caps);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        webDriverThreadLocal.set(driver);
    }


    @AfterMethod
    public void cleanDriverThreadUp() {
        getWebDriver().manage().deleteAllCookies();
        Optional.ofNullable(getWebDriver()).ifPresent(WebDriver::quit);
    }

}
