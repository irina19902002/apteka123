package ru.apteka.test;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.logevents.SelenideLogger.step;

public class PostponeTest extends WebTest{
    CityPopUp cityPopUp = new CityPopUp();
    MainPage mainPage = new MainPage();
    BasketPage basketPage = new BasketPage();

    @BeforeEach
    public void openBeforeEach()  {
        //Selenide.webdriver().driver().getWebDriver().manage().addCookie(new Cookie("current_region", "119212"));
        open("https://aptekaeconom.com/");
        Selenide.webdriver().driver().getWebDriver().manage().addCookie(new Cookie("current_region", "119212"));
        //refresh();
        cityPopUp.modal.shouldNotBe(visible);
    }

    @AfterEach
    public void closeAfter(){
        ///Selenide.closeWebDriver();
    }



    @Test
    @DisplayName("Проверка корректности текста о сумме товаров в избранном")
    @Feature("Отложить")
    @Story("Выбранные товары")
    public void shouldDelayed() {
        step("Нажать кнопку отложить на иконке товара", () -> {
            mainPage.buttonLikeIcons.click();}
        );

        step("Проверить корректность текста о сумме товаров в избранном", () -> {

            mainPage.buttonDelayed.shouldBe(visible);

            mainPage.buttonDelayed.shouldHave(attribute("title","В отложенных товаров на " +mainPage.priceProduct.getText()+ " руб."));
        });

    };
    @Test
    @DisplayName("Проверка отложенного товара в корзине")
    @Feature("Отложить")
    @Story("Выбранные товары")
    public void shouldDelayedInBasket () {

        step("Нажать кнопку отложить на иконке товара", () -> {
            mainPage.buttonLikeIcons.click();
        });
        String stringName = mainPage.nameProduct.getText();

        step("Нажать кнопку оложенные товары", () -> {
            mainPage.buttonDelayed.click();
        });
        ElementsCollection nameHeader = basketPage.getHeader(basketPage.elBasket.filter(text(stringName)).get(0));
        step("Проверка, что отложенный товар отображается в корзине", () -> {
            nameHeader.shouldHave(CollectionCondition.texts(stringName));
        });

    }
    @Test
    @DisplayName("Проверка метки отложенного товара в корзине")
    @Feature("Отложить")
    @Story("Выбранные товары")
    public void shouldDelayedMetka () {

        step("Нажать кнопку отложить на иконке товара", () -> {
            mainPage.buttonLikeIcons.click();
        });
        String stringName = mainPage.nameProduct.getText();

        step("Нажать кнопку отложенные товары", () -> {
            mainPage.buttonDelayed.click();
        });

        step("Проверка, что отложенный товар отображается в корзине с пометкой отложен", () -> {
            basketPage.elBasket.filter(text(stringName)).get(0).shouldHave(text("Товар отложен"));
        });
    }
    @Test
    @DisplayName("Проверка, что отложенный товар не влияет на итоговую сумму ")
    @Feature("Отложить")
    @Story("Выбранные товары")
    public void shouldSumWithoutProdDelayed() {
        step("Нажать кнопку отложить на иконке товара", () -> {
            mainPage.buttonLikeIcons.click();}
        );
        step("Нажать кнопку отложенные товары, для перехода в корзину", () -> {
            mainPage.buttonDelayed.click();
        });
        step("Проверить, что отложенный товар виден в корзине", () -> {
            basketPage.elBasket.filter(text("Товар отложен")).get(0).shouldBe(visible);
        });
        step("Проверить корректность текста о сумме товаров в избранном", () -> {

            basketPage.currentPrice.shouldHave(text("0 руб."));
        });

    };

}
