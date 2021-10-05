package ru.netology.web.test;

import lombok.val;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.LoginPageV2;
import ru.netology.web.page.TransferPage;

import static com.codeborne.selenide.Selenide.closeWindow;
import static com.codeborne.selenide.Selenide.open;

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
    void shouldTransferMoneyBetweenOwnCardsV2() {
        var loginPage = new LoginPageV2();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        var dashBoardPage = new DashboardPage();
        var balanceFirstCard = dashBoardPage.getCardBalance(DataHelper.getFirstCard());
        var balanceSecondCard = dashBoardPage.getCardBalance(DataHelper.getSecondCard());
        var transferPage = dashBoardPage.TopUpFirstCard();
        var sumForTopUp = DataHelper.getSumForTopUpFirstCard();
        var topUpFirstCard = transferPage.TopUpFirstCard(sumForTopUp);
        var dsfsdf = dashBoardPage.getCardBalance(DataHelper.getFirstCard());

    }
}

