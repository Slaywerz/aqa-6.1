package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class TransferPage {

    private final SelenideElement transferHeading = $("[data-test-id=amount] input");

    public TransferPage() {
        transferHeading.shouldBe(Condition.visible);
    }

    private final SelenideElement amount = $("[data-test-id=amount] input");
    private final SelenideElement from = $("[data-test-id=from] input");
    private final SelenideElement transferButton = $("[data-test-id=action-transfer]");


    public DashboardPage transfer(int randomSum, String cardNumber) {
        amount.setValue(String.valueOf(randomSum));
        from.setValue(cardNumber);
        transferButton.click();
        return new DashboardPage();
    }

    private final SelenideElement errorNotification = $("[data-test-id='error-notification']");

    public TransferPage errorMessage(){
        errorNotification.shouldBe(Condition.visible);
        return new TransferPage();
    }
}