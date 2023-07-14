package ru.apteka.test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.Objects;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.codeborne.selenide.WebDriverRunner.setWebDriver;

public class WebTest {
    @BeforeAll
    static void setUp() throws MalformedURLException {
        Configuration.remote = "http://localhost:4444/wd/hub";
         //String isRemote = System.getenv("IS_REMOTE");
       // if (Objects.equals(isRemote, "true")) {
        //    ChromeOptions chromeOptions = new ChromeOptions();
         //   chromeOptions.setCapability("enableVNC:", true);
         //   Configuration.browserSize= "1920x1080";
          //  WebDriver driver = new RemoteWebDriver(URI.create("http://localhost:4444/wd/hub").toURL(), chromeOptions);
           // setWebDriver(driver);
           //getWebDriver().manage().window().setSize(new Dimension(1920, 1080));
            //getWebDriver().manage().window().maximize();
            //getWebDriver().manage().window().setPosition(new Point(2,2));
      //  } else {
          //  Configuration.browser = "chrome";
      //  }

    }
}
