package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import java.util.Objects;

import static com.codeborne.selenide.Selenide.$;

public class TransferPage {

    private final SelenideElement transferHeading = $("[data-test-id=amount] input");

    public TransferPage() {
        transferHeading.shouldBe(Condition.visible);
    }

    private final SelenideElement amount = $("[data-test-id=amount] input");
    private final SelenideElement from = $("[data-test-id=from] input");
    private final SelenideElement transferButton = $("[data-test-id=action-transfer]");
    private final SelenideElement to = $("[data-test-id=to] input");


    public DashboardPage Transfer(int randomSum) {
        amount.setValue(String.valueOf(randomSum));
        if ((Objects.requireNonNull(to.getAttribute("value"))).contains("0001")) {
            from.setValue(DataHelper.getSecondCard().getCardNumber());
        } else
            from.setValue(DataHelper.getFirstCard().getCardNumber());
        transferButton.click();
        return new DashboardPage();
    }

    private final SelenideElement errorNotification = $("[data-test-id='error-notification']");

    public SelenideElement InvalidFromCardNumber(int randomSum) {
        amount.setValue(String.valueOf(randomSum));
        from.setValue(DataHelper.getInvalidCard().getCardNumber());
        transferButton.click();
        return errorNotification.shouldBe(Condition.visible);
    }
}