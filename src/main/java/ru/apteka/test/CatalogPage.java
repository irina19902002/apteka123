package ru.apteka.test;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CatalogPage {
    SelenideElement header = $("h1");
    SelenideElement currentCat = $("[class='full has-child current opened m_line v_hover']");
    SelenideElement currentCatTop = $("[class='menu-item dropdown wide_menu   active']");
    ElementsCollection elCatalog = $$(".item_block");
    ElementsCollection nameElement = $$ (".item-title span");

    public ElementsCollection getSubCurrentCat(SelenideElement subCat) {
        return subCat.$$("ul li");
    }
    public ElementsCollection getSubCurrentCatTop(SelenideElement subCatTop) {
        return subCatTop.$$("ul li");
    }
}
