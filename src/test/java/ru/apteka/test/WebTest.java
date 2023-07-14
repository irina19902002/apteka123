package ru.apteka.test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URI;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.setWebDriver;

public class WebTest {
    @BeforeAll
    static void setUp() throws MalformedURLException {
        Configuration.timeout = 6000;
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://aptekaeconom.com";
        boolean isRemote = true;
        if (isRemote) {
            ChromeOptions options = new ChromeOptions();
            RemoteWebDriver driver = new RemoteWebDriver(URI.create("http://localhost:4444/wd/hub").toURL(), options);
            setWebDriver(driver);
        } else {
            Configuration.browser = "chrome";
        }

        Selenide.open("/");
    }
}
