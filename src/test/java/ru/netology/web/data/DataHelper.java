package ru.netology.web.data;

import com.github.javafaker.Faker;
import lombok.Value;
import ru.netology.web.page.CardsPage;

public class DataHelper {
    private DataHelper() {
    }

    public static Faker faker = new Faker();

    public static int getSumForTopUpFirstCard() {
        CardInfo secondCard = getSecondCard();
        int balance = new CardsPage().getCardBalance(secondCard);
        int randomSum = faker.random().nextInt(1, balance);
        return randomSum;
    }

    public static int getSumForTopUpSecondCard() {
        CardInfo firstCard = getFirstCard();
        int balance = new CardsPage().getCardBalance(firstCard);
        int randomSum = faker.random().nextInt(1, balance);
        return randomSum;
    }

    public static int getInvalidSumForFirstCard(){
        CardInfo secondCard = getSecondCard();
        int balance = new CardsPage().getCardBalance(secondCard);
        int randomSum = faker.random().nextInt(balance, 10000000);
    return randomSum;
    }

    public static int getInvalidSumForSecondCard(){
        CardInfo secondCard = getFirstCard();
        int balance = new CardsPage().getCardBalance(secondCard);
        int randomSum = faker.random().nextInt(balance, 10000000);
        return randomSum;
    }

    public static int getSumBelowZeroForFirstCard (){
        CardInfo secondCard = getSecondCard();
        int balance = new CardsPage().getCardBalance(secondCard);
        int randomSum = faker.random().nextInt(-1000, 0);
        return randomSum;
    }

    public static int getSumBelowZeroForSecondCard(){
        CardInfo secondCard = getFirstCard();
        int balance = new CardsPage().getCardBalance(secondCard);
        int randomSum = faker.random().nextInt(-1000, 0);
        return randomSum;
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
