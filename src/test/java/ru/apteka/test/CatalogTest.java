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
    @DisplayName("������� �� ������������� � �������� �������")
    @Feature("������� �������")
    @Story("������������")
    public void shouldOpenCatalogTab() {
        SelenideElement tab = mainPage.tabs.filter(text("�������")).get(0);

        step("������� ������ �� �������", () -> {
            tab.hover();
        });
        step("�������� �� ����������� ������������", () -> {
            ElementsCollection subtabs = mainPage.getSubtabs(tab);
            subtabs.filter(text("�������� ������� �������")).get(0).click();
        });


        step("���������, ��� ��������� ������� �� �������� ������� ���������", () -> {
            catalogPage.header.shouldHave(text("�������� ������� �������"));
        });
    }
    @Test
    @DisplayName("������� �� ������������� � �������� �������")
    @Feature("������� ������������ � �������� �����")
    @Story("������������")
    public void shouldOpenCatalogTabLeft() {
        SelenideElement tab = mainPage.tabs.filter(text("�������")).get(0);


        step("������� ������ �� �������", () -> {
            tab.hover();
        });

        step("�������� �� ����������� ������������", () -> {
            ElementsCollection subtabs = mainPage.getSubtabs(tab);
            subtabs.filter(text("�������� ������� �������")).get(0).click();
        });

        step("���������, ��� � �������� � ����� ����� �������� ������� ��������� ������������ ���������", () -> {
            ElementsCollection subcategories = catalogPage.getSubCurrentCat(catalogPage.currentCat);
            catalogPage.currentCat.hover();
            subcategories.filter(text("�������� ������� �������")).get(0).shouldHave(cssClass("current"));

        });
    }
    @Test
     @DisplayName("������� �� ������������� � �������� �������")
    @Feature("������� ������������ � �������� ������")
    @Story("������������")
    public void shouldOpenCatalogTop() {
        SelenideElement tab = mainPage.tabs.filter(text("�������")).get(0);
        step("������� ������ �� �������", () -> {
            tab.hover();
        });

        step("�������� �� ����������� ������������", () -> {
            ElementsCollection subtabs = mainPage.getSubtabs(tab);
            subtabs.filter(text("�������� ������� �������")).get(0).click();
        });

        step("���������, ��� � �������� � ����� ����� �������� ������� ��������� ������������ ���������", () -> {
            ElementsCollection subcattop = catalogPage.getSubCurrentCat(catalogPage.currentCatTop);
            catalogPage.currentCatTop.hover();
            subcattop.filter(text("�������� ������� �������")).get(0).shouldHave(cssClass("active"));

        });
    }
}
