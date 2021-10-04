package ru.netology.web.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private final SelenideElement heading = $("[data-test-id=dashboard]");


    public DashboardPage() {
        heading.shouldBe(visible);
    }

    private final ElementsCollection cardsButton = $$("[data-test-id='action-deposit']");
    private final SelenideElement firstCardButton = cardsButton.first();
    private final SelenideElement secondCardButton = cardsButton.last();


    public void TopUpFirstCard() {
        firstCardButton.click();
    }

    public void TopUpSecondCard() {
        secondCardButton.click();
    }

    private final SelenideElement updateButton = $("[data-test-id='action-reload']");

    public void clickToUpdateButton() {
        updateButton.click();
    }

}
