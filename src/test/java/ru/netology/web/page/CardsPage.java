package ru.netology.web.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$$;

public class CardsPage {
    private final ElementsCollection cardsButton = $$("[data-test-id='action-deposit']");
    private final SelenideElement firstCardButton = cardsButton.first();
    private final SelenideElement secondCardButton = cardsButton.last();

    public void TopUpFirstCard(){
        firstCardButton.click();
    }

    public void TopUpSecondCard(){
        secondCardButton.click();
    }
}
