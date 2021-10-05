package ru.netology.web.data;

import com.github.javafaker.Faker;
import lombok.Value;
import ru.netology.web.page.DashboardPage;

public class DataHelper {
    private DataHelper() {
    }

    public static Faker faker = new Faker();

    @Value
    public static class RandomSum {
        private int sum;
    }

    public static RandomSum getSumForTopUpFirstCard() {
        CardInfo secondCard = getSecondCard();
        int balance = new DashboardPage().getCardBalance(secondCard);
        int sumToTopUpFirstCard = faker.random().nextInt(1, balance);
        return new RandomSum(sumToTopUpFirstCard);
    }

    public static RandomSum getSumForTopUpSecondCard() {
        CardInfo firstCard = getFirstCard();
        int balance = new DashboardPage().getCardBalance(firstCard);
        int sumToTopUpSecondCard = faker.random().nextInt(1, balance);
        return new RandomSum(sumToTopUpSecondCard);
    }

    public static RandomSum getInvalidSumForFirstCard() {
        CardInfo secondCard = getSecondCard();
        int balance = new DashboardPage().getCardBalance(secondCard);
        int invalidSumToFirstCard = faker.random().nextInt(balance, 10000000);
        return new RandomSum(invalidSumToFirstCard);
    }

    public static RandomSum getInvalidSumForSecondCard() {
        CardInfo secondCard = getFirstCard();
        int balance = new DashboardPage().getCardBalance(secondCard);
        int invalidSumToSecondCard = faker.random().nextInt(balance, 10000000);
        return new RandomSum(invalidSumToSecondCard);
    }

    public static RandomSum getSumBelowZeroForFirstCard() {
        CardInfo secondCard = getSecondCard();
        int balance = new DashboardPage().getCardBalance(secondCard);
        int belowZeroToFirstCard = faker.random().nextInt(-1000, 0);
        return new RandomSum(belowZeroToFirstCard);
    }

    public static RandomSum getSumBelowZeroForSecondCard() {
        CardInfo secondCard = getFirstCard();
        int balance = new DashboardPage().getCardBalance(secondCard);
        int belowZeroToSecondCard = faker.random().nextInt(-1000, 0);
        return new RandomSum(belowZeroToSecondCard);
    }


    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static AuthInfo getOtherAuthInfo(AuthInfo original) {
        return new AuthInfo("petya", "123qwerty");
    }


    @Value
    public static class CardInfo {
        private String id;
        private String cardNumber;
    }

    public static CardInfo getFirstCard() {
        return new CardInfo("0001", "5559 0000 0000 0001");
    }

    public static CardInfo getSecondCard() {
        return new CardInfo("0002", "5559 0000 0000 0002");
    }

    @Value
    public static class VerificationCode {
        private String code;
    }

    public static VerificationCode getVerificationCodeFor(AuthInfo authInfo) {
        return new VerificationCode("12345");
    }
}
