package ru.apteka.test;


import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class BasketPage {
    ElementsCollection elBasket = $$(".basket-items-list-item-container");
    SelenideElement currentPrice = $(".basket-coupon-block-total-price-current");
    SelenideElement buttonAdd = $("[class='alert alert-warning text-center'] [data-entity='basket-item-remove-delayed']");
    SelenideElement filterProduct = $(".basket-items-list-header-filter");
    SelenideElement filterDelayed = $("[class='basket-items-list-header-filter-item active']");

    public ElementsCollection getHeader(SelenideElement elHeader) {
        return elHeader.$$("h2");
    }
}
