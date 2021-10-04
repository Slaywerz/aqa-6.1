package ru.netology.web.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CardsPage {
//    private final ElementsCollection cardsBalance = $$(".list__item");
    private final String balanceStart = "баланс: ";
    private final String balanceEnd = " р.";

    public int getCardBalance(DataHelper.CardInfo cardInfo) {
        String id = cardInfo.getId();
        SelenideElement cardId = $(withText(id));
        val text = cardId.text();
        return extractBalance(text);
    }

    private int extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceEnd);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }
}
