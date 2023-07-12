package ru.apteka.test;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MainPage {
    ElementsCollection tabs = $$("nav[class='mega-menu sliced ovisible'] tr td");
    SelenideElement buttonGlass = $("button[class='top-btn inline-search-show twosmallfont']");
    SelenideElement searchInput = $("#title-search-input_fixed");
    SelenideElement buttonLikeIcons = $("#bx_3966226736_114168_HIT .like_icons");
    SelenideElement priceProduct = $("#bx_3966226736_114168_HIT .price_value");
    SelenideElement nameProduct = $ ("#bx_3966226736_114168_HIT .item-title span");
    SelenideElement buttonDelayed = $ ("div[class='wrap_icon inner-table-block baskets big-padding']  a[href='/basket/#delayed']");
    ElementsCollection buttonsHeader = $$ (".wrap_icon inner-table-block baskets big-padding");
    public ElementsCollection getSubtabs(SelenideElement tab) {

        return tab.$$("ul li");
    }
}
