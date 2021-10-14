package ru.netology.web.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.LoginPageV2;
import ru.netology.web.page.TransferPage;

import static com.codeborne.selenide.Selenide.closeWindow;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MoneyTransferTest {

    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    @AfterEach
    void showDown() {
        closeWindow();
    }

    @Test
    @DisplayName("Transfer to the first card")
    void shouldDoneTransferToFirstCard() {
        var loginPage = new LoginPageV2();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
//        Получаем баланс карт до начала перевода для получения суммы перевода
        var firstCardBalance = dashboardPage.getCardBalance(DataHelper.getFirstCard());
        var secondCardBalance = dashboardPage.getCardBalance(DataHelper.getSecondCard());
        dashboardPage.FirstCardTransfer();
        var transferPage = new TransferPage();
//        Получаем сумму перевода для сравнения ожидаемого и фактического результата
        var transferSum = DataHelper.getValidSum(secondCardBalance);
//        Данный метод не используется в тесте из-за того, что он заполняет поля перевода и кликает на кнопку
        var topUpFirstCard = transferPage.transfer(transferSum, DataHelper.getSecondCard().getCardNumber());
//        Получаем ожидаемый результат
        var expectedFirstCardBalance = firstCardBalance + transferSum;
        var expectedSecondCardBalance = secondCardBalance - transferSum;
//        Получаем фактический результат на дашборде
        var actualFirstCardBalance = dashboardPage.getCardBalance(DataHelper.getFirstCard());
        var actualSecondCardBalance = dashboardPage.getCardBalance(DataHelper.getSecondCard());
//        Сравниваем ожидаемый и фактический результат
        assertEquals(expectedFirstCardBalance, actualFirstCardBalance);
        assertEquals(expectedSecondCardBalance, actualSecondCardBalance);
    }

    @Test
    @DisplayName("Transfer to the second card")
    void shouldDoneTransferToSecondCard() {
        var loginPage = new LoginPageV2();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        var firstCardBalance = dashboardPage.getCardBalance(DataHelper.getFirstCard());
        var secondCardBalance = dashboardPage.getCardBalance(DataHelper.getSecondCard());
        dashboardPage.SecondCardTransfer();
        var transferPage = new TransferPage();
        var transferSum = DataHelper.getValidSum(firstCardBalance);
        var topUpSecondCard = transferPage.transfer(transferSum, DataHelper.getFirstCard().getCardNumber());
        var expectedFirstCardBalance = firstCardBalance - transferSum;
        var expectedSecondCardBalance = secondCardBalance + transferSum;
        var actualFirstCardBalance = dashboardPage.getCardBalance(DataHelper.getFirstCard());
        var actualSecondCardBalance = dashboardPage.getCardBalance(DataHelper.getSecondCard());
        assertEquals(expectedFirstCardBalance, actualFirstCardBalance);
        assertEquals(expectedSecondCardBalance, actualSecondCardBalance);
    }

    @Test
    @DisplayName("Sum to the field do not be below zero")
//    В данном тесте мы проверяем возможность перевода отрицательной суммы для увеличения баланса
//    карты, с которой совершается перевод
    void shouldDoNotSetTransferSumBelowZero() {
        var loginPage = new LoginPageV2();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        var firstCardBalance = dashboardPage.getCardBalance(DataHelper.getFirstCard());
        var secondCardBalance = dashboardPage.getCardBalance(DataHelper.getSecondCard());
        dashboardPage.SecondCardTransfer();
        var transferPage = new TransferPage();
        var transferSum = DataHelper.getSumBelowZero(firstCardBalance);
        var topUpSecondCard = transferPage.transfer(transferSum, DataHelper.getFirstCard().getCardNumber());
//        Знаки операций сложения и вычитания инвертированны потому, что "+" на "-" дает "-", а "-" на "-" дает "+"
        var expectedFirstCardBalance = firstCardBalance + transferSum;
        var expectedSecondCardBalance = secondCardBalance - transferSum;
//        В случае, если перевод отрицательной суммы возможен, то баланс первой карты увеличится, а второй уменьшится
//        и тест упадёт
        var actualFirstCardBalance = dashboardPage.getCardBalance(DataHelper.getFirstCard());
        var actualSecondCardBalance = dashboardPage.getCardBalance(DataHelper.getSecondCard());
        assertEquals(expectedFirstCardBalance, actualFirstCardBalance);
        assertEquals(expectedSecondCardBalance, actualSecondCardBalance);
    }

    @Test
    @DisplayName("Transfer don't success because invalid card number")
    void shouldBeErrorBecauseOfInvalidCard() {
        var loginPage = new LoginPageV2();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
//        Узнаём баланс карты для того, чтобы в Transfer page была возможность ввести сумму
        var firstCardBalance = dashboardPage.getCardBalance(DataHelper.getFirstCard());
        dashboardPage.FirstCardTransfer();
        var transferPage = new TransferPage();
        var transferSum = DataHelper.getValidSum(firstCardBalance);
//        Берем некорректный номер карты для получения уведомления о невозможности перевода с этой карты
        var topUpSecondCard = transferPage.transfer(transferSum, DataHelper.getInvalidCard().getCardNumber());
        transferPage.errorMessage();
    }


//    @Test
//    @DisplayName("Transfer don't success because sum more than balance in card")
//    void shouldDoNotDoneTransferWithInvalidSum() {
//        var loginPage = new LoginPageV2();
//        var authInfo = DataHelper.getAuthInfo();
//        var verificationPage = loginPage.validLogin(authInfo);
//        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
//        var dashboardPage = verificationPage.validVerify(verificationCode);
//        var firstCardBalance = dashboardPage.getCardBalance(DataHelper.getFirstCard());
//        var secondCardBalance = dashboardPage.getCardBalance(DataHelper.getSecondCard());
//        dashboardPage.FirstCardTransfer();
//        var transferPage = new TransferPage();
//        var transferSum = DataHelper.getSumMoreThanBalance(secondCardBalance);
//        var topUpFirstCard = transferPage.transfer(transferSum, DataHelper.getSecondCard().getCardNumber());
//        transferPage.errorMessage();
//    }

}

