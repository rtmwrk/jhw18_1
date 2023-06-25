package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

// Страница входа в кабиинет пользователя
public class LoginPage {

  // Содержимое поля "Логин"
  private final SelenideElement loginField = $("[data-test-id=login] input");

  // Содержимое поля "Пароль"
  private final SelenideElement passwordField = $("[data-test-id=password] input");

  // Кнопка "Продолжить"
  private static SelenideElement loginButton = $("[data-test-id=action-login]");

  // Заполняем данными форму идентификации пользователя
  public VerificationPage validLogin(DataHelper.AuthInfo info) {
    // Логин
    loginField.setValue(info.getLogin());
    // Пароль
    passwordField.setValue(info.getPassword());
    // нажимаем кнопку "Продолжить"
    loginButton.click();
    // Возвращаем переформатированную страницу
    return new VerificationPage();
  }
}