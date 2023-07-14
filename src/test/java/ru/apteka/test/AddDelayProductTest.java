package ru.apteka.test;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.logevents.SelenideLogger.step;

public class AddDelayProductTest extends WebTest{
    CityPopUp cityPopUp = new CityPopUp();
    MainPage mainPage = new MainPage();
    BasketPage basketPage = new BasketPage();
    @BeforeEach
    public void openBeforeEach() {
     Selenide.open("https://aptekaeconom.com/");
        Selenide.webdriver().driver().getWebDriver().manage().addCookie(new Cookie("current_region", "103006"));
        //Selenide.webdriver().driver().getWebDriver().manage().addCookie(new Cookie("current_region", "119212"));
        refresh();
        //cityPopUp.modal.shouldNotBe(visible, Duration.ofSeconds(30));
    }
    @AfterEach void closeAfterEach(){
        closeWebDriver();
    }




    @Test
    @DisplayName("Проверка добавления отложенного товара к заказу")
    @Feature("Заказ")
    @Story("Заказ отложенного товара")
    public void shouldDelayed() {
        step("Нажать кнопку отложить на иконке товара", () -> {
            mainPage.buttonLikeIcons.click();
        }
        );
        String price = mainPage.priceProduct.getText();
      step("Нажать кнопку отложенные товары", () -> {
          //$(By.id("header")).$$(By.tagName("a")).filter(attribute("nofollow")).size();
        mainPage.buttonDelayed.click();
      });
        step("Нажать кнопку Добавитьк заказу?", () -> {
           basketPage.buttonAdd.shouldBe(visible);
           basketPage.buttonAdd.click();


      });
        step("Проверить. что товар добавился", () -> {

         basketPage.filterProduct.shouldHave(text("В корзине 1 товар"));
           basketPage.filterDelayed.shouldNotBe(visible);
       });
       step("Проверить. что стоимость корзины изменилась на цену отложенного товара", () -> {
            basketPage.currentPrice.shouldBe(text(price));
        });

    }
}
