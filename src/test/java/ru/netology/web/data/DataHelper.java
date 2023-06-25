package ru.netology.web.data;

import lombok.Value;

import java.util.Random;

public class DataHelper {
  private DataHelper() {}

  // Аутентификация пользователя
  public static AuthInfo getAuthInfo() {
    // Возвращаем логин и пароль для "зашитого" пользователя
    return new AuthInfo("vasya", "qwerty123");
  }

  // Передача кода верификации
  public static VerificationCode getVerificationCode() {
    // Возвращаем "зашитый" код верификации
    return new VerificationCode("12345");
  }

  // Создается первая карта
  public static CardInfo getFirstCardInfo() {
    return new CardInfo("5559 0000 0000 0001", "92df3f1c-a033-48e6-8390-206f6b1f56c0");
  }

  // Создается вторая карта
  public static CardInfo getSecondCardInfo() {
    return new CardInfo("5559 0000 0000 0002", "0f3f5c2a-249e-4c3d-8287-09f7a039391d");
  }

  // Возвращаем "зашитый" код верификации
  public static VerificationCode getVerificationCodeFor(AuthInfo authInfo) {
      return new VerificationCode("12345");
  }

  // Формируем баланс
  public static int generateValidAmount(int balance) {
      return new Random().nextInt(balance) + 1;
  }

  // Формируем ошибочный баланс
  public static int generateInvalidAmount(int balance) {
      return Math.abs(balance) + new Random().nextInt(10000);
  }

  @Value
  public static class VerificationCode {
    private String code;
  }

  @Value
  public static class CardInfo {
    String cardNumber;
    String testId;
  }

  @Value
  public static class AuthInfo {
    private String login;
    private String password;
  }
}
