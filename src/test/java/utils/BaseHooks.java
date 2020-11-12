package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BaseHooks {
    public RemoteWebDriver driver;
    //   protected static WebDriver driver;
   // public RemoteWebDriver driver;

    @BeforeMethod
    public void setup() throws MalformedURLException {
        String selenoidURL = "http://localhost:4444/wd/hub";
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setBrowserName("chrome");
        caps.setVersion("85.0");
        caps.setCapability("enableVNC", true);
        caps.setCapability("screenResolution", "1280x1024");
        caps.setCapability("enableVideo", true);
        caps.setCapability("enableLog", true);

        driver = new RemoteWebDriver(new URL(selenoidURL), caps);
      //  driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);


        if (driver != null) {
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.manage().window().maximize();
        }
    }

    @AfterMethod
    public void teardown() {
       // driver.manage().deleteAllCookies();
        if (driver != null) {
            driver.quit();
        }
    }


//    @AfterMethod
//    public void cleanUp() {
//        driver.manage().deleteAllCookies();
//    }

}