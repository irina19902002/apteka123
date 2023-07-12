package ru.apteka.test;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.logevents.SelenideLogger.step;

public class AddDelayProductTest extends WebTest{
    CityPopUp cityPopUp = new CityPopUp();
    MainPage mainPage = new MainPage();
    BasketPage basketPage = new BasketPage();
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
    @DisplayName("�������� ���������� ����������� ������ � ������")
    @Feature("�����")
    @Story("����� ����������� ������")
    public void shouldDelayed() {
        step("������ ������ �������� �� ������ ������", () -> {
            mainPage.buttonLikeIcons.click();}
        );
        String price = mainPage.priceProduct.getText();

        step("������ ������ ���������� ������", () -> {
            mainPage.buttonDelayed.click();
        });
        step("������ ������ ��������� ������?", () -> {
            basketPage.buttonAdd.shouldBe(visible);
            basketPage.buttonAdd.click();


        });
        step("���������. ��� ����� ���������", () -> {

            basketPage.filterProduct.shouldHave(text("� ������� 1 �����"));
            basketPage.filterDelayed.shouldNotBe(visible);
        });
        step("���������. ��� ��������� ������� ���������� �� ���� ����������� ������", () -> {
            basketPage.currentPrice.shouldBe(text(price));
        });

    }
}
