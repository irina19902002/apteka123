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

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.logevents.SelenideLogger.step;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SearchTest extends WebTest{
    CityPopUp cityPopUp = new CityPopUp();
    MainPage mainPage = new MainPage();
    CatalogPage catalogPage = new CatalogPage();

    static int i = 0;

    @BeforeEach
    public void openBeforeEach() {


        //Selenide.webdriver().driver().getWebDriver().manage().addCookie(new Cookie("current_region", "119212"));
        open("https://aptekaeconom.com/");
        Selenide.webdriver().driver().getWebDriver().manage().addCookie(new Cookie("current_region", "119212"));
        //refresh();
        cityPopUp.modal.shouldNotBe(visible);
    }
    @AfterEach
    public void closeAfter(){
        Selenide.closeWebDriver();
    }


    @Test
    @DisplayName("Поиск по полному наименованию")
    @Feature("Поиск товаров")
    @Story("Результат поиска")
    public void shouldSearchFullName()  {

        step("Ввести полное наименование товара" , () -> {
            mainPage.searchInput.sendKeys("Нурофен" + Keys.ENTER);
        });
        step("Проверить, что в списке выведены только товары с полным названием из поиска",() ->{
            for (SelenideElement element : catalogPage.elCatalog) {
                element.shouldHave(text("Нурофен "));
            }
            ///  catalogPage.elCatalog.shouldHave(text("Нурофен "));
        });



    }
    //  @Test
    //  @DisplayName("Поиск по неполному наименованию")
    //  @Feature("Поиск товаров")
    // @Story("Результат поиска")
    //  public void shouldSearcShortName()  {
    //  step("Ввести полное наименование товара" , () -> {
    //      mainPage.searchInput.sendKeys("Нур" + Keys.ENTER);
    //  });
    //  step("Проверить, что в списке выведены только товары с полным названием из поиска",() ->{
    //   //   for (SelenideElement element : catalogPage.elCatalog) {
    //  element.shouldNotHave(text("Нур "));
    //    }
    //  });
    // }
    @Test
    @DisplayName("Количество позиций на странице поиска")
    @Feature("Поиск товаров")
    @Story("Результат поиска")
    public void shouldSearcQuantityElement() {


        step("Ввести полное наименование товара", () -> {
            mainPage.searchInput.sendKeys("Нурофен" + Keys.ENTER);
        });
        step("Проверить, что в списке выведено не меньши 5 позиций на странице", () -> {
                    int count = catalogPage.elCatalog.size();
                    assertTrue(count >= 5);

                }
        );
    }
}

