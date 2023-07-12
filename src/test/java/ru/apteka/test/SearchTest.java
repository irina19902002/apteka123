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

        open("https://aptekaeconom.com/");
        Selenide.webdriver().driver().getWebDriver().manage().addCookie(new Cookie("current_region", "119212"));
        refresh();
        cityPopUp.modal.shouldNotBe(visible);
    }
    @AfterEach
    public void closeAfter(){
        closeWebDriver();
    }


    @Test
    @DisplayName("����� �� ������� ������������")
    @Feature("����� �������")
    @Story("��������� ������")
    public void shouldSearchFullName()  {

        step("������ ������ ������������ ������" , () -> {
            mainPage.searchInput.sendKeys("�������" + Keys.ENTER);
        });
        step("���������, ��� � ������ �������� ������ ������ � ������ ��������� �� ������",() ->{
           for (SelenideElement element : catalogPage.elCatalog) {
               element.shouldHave(text("������� "));
         }
          ///  catalogPage.elCatalog.shouldHave(text("������� "));
        });



    }
    //  @Test
    //  @DisplayName("����� �� ��������� ������������")
    //  @Feature("����� �������")
    // @Story("��������� ������")
    //  public void shouldSearcShortName()  {
    //  step("������ ������ ������������ ������" , () -> {
    //      mainPage.searchInput.sendKeys("���" + Keys.ENTER);
    //  });
    //  step("���������, ��� � ������ �������� ������ ������ � ������ ��������� �� ������",() ->{
    //   //   for (SelenideElement element : catalogPage.elCatalog) {
    //  element.shouldNotHave(text("��� "));
    //    }
    //  });
    // }
    @Test
    @DisplayName("���������� ������� �� �������� ������")
    @Feature("����� �������")
    @Story("��������� ������")
    public void shouldSearcQuantityElement() {


        step("������ ������ ������������ ������", () -> {
            mainPage.searchInput.sendKeys("�������" + Keys.ENTER);
        });
        step("���������, ��� � ������ �������� �� ������ 5 ������� �� ��������", () -> {
                  int count = catalogPage.elCatalog.size();
                 assertTrue(count >= 5);

                }
        );
    }
}

