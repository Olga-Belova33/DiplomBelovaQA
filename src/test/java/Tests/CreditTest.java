package Tests;

import Pages.DashboardPage;
import Pages.PayPage;
import Data.DataHelper;
import Data.SQLHelper;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
import static Data.SQLHelper.cleanDatabase;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreditTest {
    PayPage payPage;

    @AfterAll
    static void tearDownAll() {
        cleanDatabase();
    }

    @BeforeEach
    void setUp() {
        String url = System.getProperty("app.url");
        open(url, DashboardPage.class);
        var dashboardPage = new DashboardPage();
        payPage = dashboardPage.clickCredit();
    }

    @Test
    void shouldPayByApprovedCard() {
        var approvedCard = DataHelper.validApprovedCard();
        payPage.login(approvedCard);
        payPage.payErrorNotificationHidden();
        payPage.payNotification("Успешно\n" +
                "Операция одобрена Банком.");
        var payStatus = SQLHelper.getCreditStatusEntity();
        assertEquals("APPROVED", payStatus.getStatus());
    }

    @Test
    void shouldntPayByDECLINEDCard() {
        var declinedCard = DataHelper.validDeclinedCard();
        payPage.login(declinedCard);
        payPage.payNotificationHidden();
        payPage.payErrorNotification("Ошибка\n" +
                "Ошибка! Банк отказал в проведении операции.");
        var payStatus = SQLHelper.getCreditStatusEntity();
        assertEquals("DECLINED", payStatus.getStatus());
    }

    @Test
    void shouldntPayByInvalidCard() {
        var invalidCard = DataHelper.invalidCard();
        payPage.login(invalidCard);
        payPage.checkInputSubCard();
    }

    @Test
    void shouldntPayByInvalidFormCard() {
        var invalidFormCard = DataHelper.invalidFormCard();
        payPage.login(invalidFormCard);
        payPage.checkCard("Неверный формат");
    }

    @Test
    void shouldntPayByExpiredMonthCard() {
        var expiredMonth = DataHelper.expiredMonth();
        payPage.login(expiredMonth);
        payPage.checkMonth("Истёк срок действия карты");
    }

    @Test
    void shouldntPayByInvalidMonthCard() {
        var invalidMonth = DataHelper.invalidMonth();
        payPage.login(invalidMonth);
        payPage.checkMonth("Неверно указан срок действия карты\n" +
                " \t ");
    }

    @Test
    void shouldntPayByInvalidFormMonthCard() {
        var invalidFormMonth = DataHelper.invalidFormMonth();
        payPage.login(invalidFormMonth);
        payPage.checkMonth("Неверный формат");
    }

    @Test
    void shouldntPayByZeroMonthCard() {
        var zeroMonth = DataHelper.zeroMonth();
        payPage.login(zeroMonth);
        payPage.checkMonth("Неверно указан срок действия карты\n" +
                " \t ");
    }

    @Test
    void shouldntPayByExpiredYearCard() {
        var expiredYear = DataHelper.expiredYear();
        payPage.login(expiredYear);
        payPage.checkYear("Истёк срок действия карты" +
                " \t ");
    }

    @Test
    void shouldntPayByInvalidYearCard() {
        var invalidYear = DataHelper.invalidYear();
        payPage.login(invalidYear);
        payPage.checkYear("Неверно указан срок действия карты\n" +
                " \t ");
    }

    @Test
    void shouldntPayByInvalidUserCard() {
        var invalidUser = DataHelper.invalidUser();
        payPage.login(invalidUser);
        payPage.checkOwner("Неверный формат");
    }

    @Test
    void shouldntPayByInvalidRusUserCard() {
        var invalidRusUser = DataHelper.invalidRusUser();
        payPage.login(invalidRusUser);
        payPage.checkOwner("Поле заполняется латинскими буквами");
    }

    @Test
    void shouldntPayByInvalidNumbUserCard() {
        var numberUser = DataHelper.numberUser();
        payPage.login(numberUser);
        payPage.checkOwner("Неверный формат");
    }

    @Test
    void shouldntPayByInvalidSignUserCard() {
        var signUser = DataHelper.signUser();
        payPage.login(signUser);
        payPage.checkOwner("Неверный формат");
    }

    @Test
    void shouldntPayByInvalidFormCVCCard() {
        var notFormCVC = DataHelper.notFormCVC();
        payPage.login(notFormCVC);
        payPage.checkCVC("Неверный формат");
    }
}