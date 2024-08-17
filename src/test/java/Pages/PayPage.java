package Pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import Data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class PayPage {
    private final SelenideElement header = $$("h3")
            .find(or(("Оплата или Кредит"),
                    text("Оплата по карте"),
                    text("Кредит по данным карты")));

    private ElementsCollection input = $$(".input__control");
    private SelenideElement continueButton = $$(".button")
            .find(exactText(" Продолжить"));
    private SelenideElement notificationOk = $(".notification_status_ok");
    private SelenideElement notificationError = $(".notification_status_error");

    private SelenideElement checkCard = $("#root > div > form > " +
            "fieldset > div:nth-child(1) > span > span > span.input__sub");

    private SelenideElement checkMonth = $("#root > div > form > " +
            "fieldset > div:nth-child(2) > span > span:nth-child(1) > span > span > span.input__sub");

    private SelenideElement checkYear = $("#root > div > form > fieldset > div:nth-child(2) >" +
            " span > span:nth-child(2) > span > span > span.input__sub");

    private SelenideElement checkOwner = $("#root > div > form > fieldset > div:nth-child(3) >" +
            " span > span:nth-child(1) > span > span > span.input__sub");

    private SelenideElement checkCVC = $("#root > div > form > fieldset > div:nth-child(3) > " +
            "span > span:nth-child(2) > span > span > span.input__sub");

    public PayPage() {
        header.shouldBe(visible);
    }


    public void login(DataHelper.CardInfo cardInfo) {
        input.get(0).setValue(cardInfo.getCardNumber());
        input.get(1).setValue(cardInfo.getMonth());
        input.get(2).setValue(cardInfo.getYear());
        input.get(3).setValue(cardInfo.getOwner());
        input.get(4).setValue(cardInfo.getCVC());
        continueButton.click();
    }

    public void payNotification(String expectedText) {
        notificationOk.shouldHave(exactText(expectedText), Duration.ofSeconds(15)).shouldBe(visible);
    }

    public void payNotificationHidden() {
        notificationOk.shouldBe(hidden);
    }

    public void payErrorNotification(String expectedText) {
        notificationError.shouldHave(exactText(expectedText), Duration.ofSeconds(15)).shouldBe(visible);
    }

    public void payErrorNotificationHidden() {
        notificationError.shouldBe(hidden);
    }

    public void checkCard(String expectedText) {
        checkCard.shouldHave(text(expectedText)).shouldBe(visible);
    }

    public void checkInputSubCard() {
        checkCard.shouldBe(visible);
    }

    public void checkMonth(String expectedText) {
        checkMonth.shouldHave(text(expectedText)).shouldBe(visible);
    }

    public void checkYear(String expectedText) {
        checkYear.shouldHave(text(expectedText)).shouldBe(visible);
    }

    public void checkOwner(String expectedText) {
        checkOwner.shouldHave(text(expectedText)).shouldBe(visible);
    }

    public void checkCVC(String expectedText) {
        checkCVC.shouldHave(text(expectedText)).shouldBe(visible);
    }
}