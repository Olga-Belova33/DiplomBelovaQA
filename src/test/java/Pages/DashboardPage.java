package Pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class DashboardPage {
    private final SelenideElement payButton = $$(".button")
            .find(exactText("Купить"));
    private SelenideElement creditButton = $$(".button")
            .find(text("Купить в кредит"));

    public PayPage clickPay() {
        payButton.click();
        return new PayPage();
    }

    public PayPage clickCredit() {
        creditButton.click();
        return new PayPage();
    }
}