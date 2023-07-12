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
    @DisplayName("�������� ������������ ������ � ����� ������� � ���������")
    @Feature("��������")
    @Story("��������� ������")
    public void shouldDelayed() {
        step("������ ������ �������� �� ������ ������", () -> {
            mainPage.buttonLikeIcons.click();}
        );

        step("��������� ������������ ������ � ����� ������� � ���������", () -> {

            mainPage.buttonDelayed.shouldBe(visible);

            mainPage.buttonDelayed.shouldHave(attribute("title","� ���������� ������� �� " +mainPage.priceProduct.getText()+ " ���."));
        });

    };
    @Test
    @DisplayName("�������� ����������� ������ � �������")
    @Feature("��������")
    @Story("��������� ������")
    public void shouldDelayedInBasket () {

        step("������ ������ �������� �� ������ ������", () -> {
            mainPage.buttonLikeIcons.click();
        });
        String stringName = mainPage.nameProduct.getText();

        step("������ ������ ��������� ������", () -> {
            mainPage.buttonDelayed.click();
        });
        ElementsCollection nameHeader = basketPage.getHeader(basketPage.elBasket.filter(text(stringName)).get(0));
        step("��������, ��� ���������� ����� ������������ � �������", () -> {
            nameHeader.shouldHave(CollectionCondition.texts(stringName));
        });

    }
    @Test
    @DisplayName("�������� ����� ����������� ������ � �������")
    @Feature("��������")
    @Story("��������� ������")
    public void shouldDelayedMetka () {

        step("������ ������ �������� �� ������ ������", () -> {
            mainPage.buttonLikeIcons.click();
        });
        String stringName = mainPage.nameProduct.getText();

        step("������ ������ ���������� ������", () -> {
            mainPage.buttonDelayed.click();
        });

        step("��������, ��� ���������� ����� ������������ � ������� � �������� �������", () -> {
            basketPage.elBasket.filter(text(stringName)).get(0).shouldHave(text("����� �������"));
        });
    }
    @Test
    @DisplayName("��������, ��� ���������� ����� �� ������ �� �������� ����� ")
    @Feature("��������")
    @Story("��������� ������")
    public void shouldSumWithoutProdDelayed() {
        step("������ ������ �������� �� ������ ������", () -> {
            mainPage.buttonLikeIcons.click();}
        );
        step("������ ������ ���������� ������, ��� �������� � �������", () -> {
            mainPage.buttonDelayed.click();
        });
        step("���������, ��� ���������� ����� ����� � �������", () -> {
            basketPage.elBasket.filter(text("����� �������")).get(0).shouldBe(visible);
        });
        step("��������� ������������ ������ � ����� ������� � ���������", () -> {

            basketPage.currentPrice.shouldHave(text("0 ���."));
        });

    };

}
