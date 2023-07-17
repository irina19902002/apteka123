package ru.apteka.test;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.logevents.SelenideLogger.step;


public class SearchTest extends WebTest {

    MainPage mainPage = new MainPage();
    CatalogPage catalogPage = new CatalogPage();


    @BeforeEach
    public void openBeforeEach() {
        Selenide.open("https://aptekaeconom.com/");
        Selenide.webdriver().driver().getWebDriver().manage().addCookie(new Cookie("current_region", "103006"));
        refresh();
    }

    @AfterEach
    void closeAfterEach() {
        closeWebDriver();
    }

    @Test
    @DisplayName("Поиск по полному наименованию")
    @Feature("Поиск товаров")
    @Story("Результат поиска")
    public void shouldSearchFullName() {

        step("Ввести полное наименование товара", () -> mainPage.searchInput.sendKeys("Нурофен" + Keys.ENTER)
        );
        step("Проверить, что в списке выведены только товары с полным названием из поиска", () -> {
            for (SelenideElement element : catalogPage.nameElement) {
                element.shouldHave(text("Нурофен "));
            }
        });
    }

    @Test
    @DisplayName("Количество позиций на странице поиска")
    @Feature("Поиск товаров")
    @Story("Результат поиска")
    public void shouldSearcQuantityElement() {


        step("Ввести полное наименование товара", () ->
                mainPage.searchInput.sendKeys("Нурофен" + Keys.ENTER)
        );
        step("Проверить, что в списке выведено не меньши 5 позиций на странице", () -> {
                    catalogPage.elCatalog.shouldHave(sizeGreaterThan(5));


                }
        );
    }
}

