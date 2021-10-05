package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import java.util.Objects;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class TransferPage {
    //    private final ElementsCollection cardsBalance = $$(".list__item");

    private final SelenideElement transferHeading = $(byText("Пополнение карты"));

    public TransferPage() {
        transferHeading.shouldBe(Condition.visible);
    }

    private final SelenideElement amount = $("[data-test-id=amount] input");
    private final SelenideElement from = $("[data-test-id=from] input");
    private final SelenideElement transferButton = $("[data-test-id=action-transfer]");
    private final SelenideElement to = $("[data-test-id=to] input");


    public DashboardPage TopUp(DataHelper.RandomSum randomSum) {
        amount.setValue(String.valueOf(randomSum.getSum()));
        if (to.getAttribute("value").contains("0001")==true) {
            from.setValue(DataHelper.getSecondCard().getCardNumber());
        } else
            from.setValue(DataHelper.getFirstCard().getCardNumber());
        transferButton.click();
        return new DashboardPage();
    }

    public DashboardPage TopUpFirstCard(DataHelper.RandomSum randomSum) {
        from.setValue(DataHelper.getSecondCard().getCardNumber());
        amount.setValue(String.valueOf(randomSum.getSum()));
        transferButton.click();
        return new DashboardPage();
    }

}