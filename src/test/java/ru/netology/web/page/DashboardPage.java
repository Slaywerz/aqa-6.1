package ru.netology.web.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
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


    public TransferPage TopUpFirstCard() {
        firstCardButton.click();
        return new TransferPage();
    }

    public TransferPage TopUpSecondCard() {
        secondCardButton.click();
        return new TransferPage();
    }
    private ElementsCollection cards = $$(".list__item");
    private final String  balanceStart = "баланс: ";
    private final String balanceEnd = " р.";

    public int getCardBalance(DataHelper.CardInfo cardInfo) {
        String id = cardInfo.getId();
        SelenideElement cardId = $(withText(id));
        var text = cardId.text();
        return extractBalance(text);
    }

    private int extractBalance(String text) {
        var start = text.indexOf(balanceStart);
        var finish = text.indexOf(balanceEnd);
        var value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }

//    private final SelenideElement updateButton = $("[data-test-id='action-reload']");
//
//    public void clickToUpdateButton() {
//        updateButton.click();
//    }

}
