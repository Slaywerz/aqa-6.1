package ru.netology.web.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;
import ru.netology.web.data.DataHelper;

import java.util.Objects;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class TransferPage {
//    private final ElementsCollection cardsBalance = $$(".list__item");

    public int getCardBalance(DataHelper.CardInfo cardInfo) {
        String id = cardInfo.getId();
        SelenideElement cardId = $(withText(id));
        val text = cardId.text();
        return extractBalance(text);
    }

    private int extractBalance(String text) {
        String balanceStart = "баланс: ";
        val start = text.indexOf(balanceStart);
        String balanceEnd = " р.";
        val finish = text.indexOf(balanceEnd);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }

    private final SelenideElement amount = $("[data-test-id=amount] input");
    private final SelenideElement from = $("[data-test-id=from] input");
    private final SelenideElement transferButton = $("[data-test-id=action-transfer]");
    private final SelenideElement to = $("[data-test-id=to] input");

    public void TopUp(int randomSum) {
        amount.setValue(String.valueOf(randomSum));
        if (Objects.requireNonNull(to.getAttribute("value")).contains("0001")) {
            from.setValue(DataHelper.getSecondCard().getCardNumber());
        } else
            from.setValue(DataHelper.getFirstCard().getCardNumber());
        transferButton.click();
    }
}
