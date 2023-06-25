package ru.netology.web.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.web.data.DataHelper.*;

class MoneyTransferTest {
    LoginPage loginPage;
    DashboardPage dashboardPage;

    // Настройки перед каждым тестом
    @BeforeEach
    void setup() {
      loginPage = open("http://localhost:9999", LoginPage.class);
      var authInfo = getAuthInfo();
      var verificationPage = loginPage.validLogin(authInfo);
      var verificationCode = getVerificationCode();
      dashboardPage = verificationPage.validVerify(verificationCode);
    }

    // Функциональный позитивный тест
    @Test
    void shouldTransferMoneyFromFirstCardToSecondCard() {
      // Первая карта
      var firstCardInfo = getFirstCardInfo();
      // Вторая карты
      var secondCardInfo = getSecondCardInfo();
      // Баланс 1-й карты
      var firstCardBalance = dashboardPage.getCardBalance(firstCardInfo);
      // Баланс 2-й карты
      var secondCardBalance = dashboardPage.getCardBalance(secondCardInfo);
      // Формируем сумму перевода с 1-й карты на 2-ю
      var amount = generateValidAmount(firstCardBalance);
      // Уменьшаем баланс 1-й карты на сумму перевода
      var expectedBalanceFirstCard = firstCardBalance - amount;
      // Увеличиваем баланс 2-й карты на сумму перевода
      var expectedBalanceSecondCard = secondCardBalance + amount;
      // Осуществляем операцию "перевода"
      var transferPage = dashboardPage.selectCardToTransfer(secondCardInfo);
      dashboardPage = transferPage.makeValidTransfer(String.valueOf(amount), firstCardInfo);
      // Получаем балансы на картах после перевода
      var actualBalanceFirstCard = dashboardPage.getCardBalance(firstCardInfo);
      var actualbalanceSecondCard = dashboardPage.getCardBalance(secondCardInfo);
      // Сравниваем фактические и ожидаемые балансы на обеих картах
      assertEquals(expectedBalanceFirstCard, actualBalanceFirstCard);
      assertEquals(expectedBalanceSecondCard, actualbalanceSecondCard);
    }

  // Функциональный негативный тест
  // тест должен проходить
//  @Test
//  void shouldTransferMoneyFromFirstCardToSecondCardWithTooMatchValue() {
//    // Первая карта
//    var firstCardInfo = getFirstCardInfo();
//    // Вторая карты
//    var secondCardInfo = getSecondCardInfo();
//    // Баланс 1-й карты
//    var firstCardBalance = dashboardPage.getCardBalance(firstCardInfo);
//    // Баланс 2-й карты
//    var secondCardBalance = dashboardPage.getCardBalance(secondCardInfo);
//    // Формируем сумму перевода с 1-й карты на 2-ю
//    var amount = generateInvalidAmount(secondCardBalance);
//    // Пробуем перевести средства
//    var transferPage = dashboardPage.selectCardToTransfer(firstCardInfo);
//    transferPage.makeTransfer(String.valueOf(amount), secondCardInfo);
//    transferPage.findErrorMessage("Выполнена попытка перевода суммы, превыщающей остаток на карте списания");
//    // Получаем балансы на картах после перевода
//    var actualBalanceFirstCard = dashboardPage.getCardBalance(firstCardInfo);
//    var actualbalanceSecondCard = dashboardPage.getCardBalance(secondCardInfo);
//    // Сравниваем фактические и первоначальные балансы на обеих картах
//    assertEquals(firstCardBalance, actualBalanceFirstCard);
//    assertEquals(secondCardBalance, actualbalanceSecondCard);
//  }

}

