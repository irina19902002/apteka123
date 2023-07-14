package ru.apteka.test;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import java.net.MalformedURLException;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.logevents.SelenideLogger.step;

public class CatalogTest extends WebTest{
    MainPage mainPage = new MainPage();
    CityPopUp cityPopUp = new CityPopUp();
    CatalogPage catalogPage = new CatalogPage();
    @BeforeEach
    public void openBeforeEach() throws MalformedURLException {

       // Selenide.webdriver().driver().getWebDriver().manage().addCookie(new Cookie("current_region", "119212"));
        Selenide.open("https://aptekaeconom.com/");
        Selenide.webdriver().driver().getWebDriver().manage().addCookie(new Cookie("current_region", "103006"));
        refresh();
        //cityPopUp.modal.shouldNotBe(visible);

    }
    @AfterEach void closeAfterEach(){
        closeWebDriver();
    }


    @Test
    @DisplayName("Переход по подкатегориям в каталоге товаров")
    @Feature("Каталог товаров")
    @Story("Подкатегории")
    public void shouldOpenCatalogTab() {
        SelenideElement tab = mainPage.tabs.filter(text("Гигиена")).get(0);

        step("Навести курсор на вкладку", () -> {
            tab.hover();
        });
        step("Кликнуть на появившуюся подкатегорию", () -> {
            ElementsCollection subtabs = mainPage.getSubtabs(tab);
            subtabs.filter(text("Предметы женской гигиены")).get(0).click();
        });


        step("Проверить, что произошел переход на страницу товаров категории", () -> {
            catalogPage.header.shouldHave(text("Предметы женской гигиены"));
        });
    }
    @Test
    @DisplayName("Переход по подкатегориям в каталоге товаров")
    @Feature("Текущая подкатегория в каталоге слева")
    @Story("Подкатегории")
    public void shouldOpenCatalogTabLeft() {
        SelenideElement tab = mainPage.tabs.filter(text("Гигиена")).get(0);


        step("Навести курсор на вкладку", () -> {
            tab.hover();
        });

        step("Кликнуть на появившуюся подкатегорию", () -> {
            ElementsCollection subtabs = mainPage.getSubtabs(tab);
            subtabs.filter(text("Предметы женской гигиены")).get(0).click();
        });

        step("Проверить, что в каталоге в левой части страницы текущая категория отображается корректно", () -> {
            ElementsCollection subcategories = catalogPage.getSubCurrentCat(catalogPage.currentCat);
            catalogPage.currentCat.hover();
            subcategories.filter(text("Предметы женской гигиены")).get(0).shouldHave(cssClass("current"));

        });
    }
    @Test
     @DisplayName("Переход по подкатегориям в каталоге товаров")
    @Feature("Текущая подкатегория в каталоге сверху")
    @Story("Подкатегории")
    public void shouldOpenCatalogTop() {
        SelenideElement tab = mainPage.tabs.filter(text("Гигиена")).get(0);
        step("Навести курсор на вкладку", () -> {
            tab.hover();
        });

        step("Кликнуть на появившуюся подкатегорию", () -> {
            ElementsCollection subtabs = mainPage.getSubtabs(tab);
            subtabs.filter(text("Предметы женской гигиены")).get(0).click();
        });

        step("Проверить, что в каталоге в левой части страницы текущая категория отображается корректно", () -> {
            ElementsCollection subcattop = catalogPage.getSubCurrentCat(catalogPage.currentCatTop);
            catalogPage.currentCatTop.hover();
            subcattop.filter(text("Предметы женской гигиены")).get(0).shouldHave(cssClass("active"));

        });
    }
}
