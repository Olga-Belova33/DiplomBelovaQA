package Data;

import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataHelper {

    private DataHelper() {
    }

    private static Faker fakerEng = new Faker(new Locale("en"));
    private static Faker fakerRus = new Faker(new Locale("ru"));

    public static String getCardApproved() {
        return "4444 4444 4444 4441";
    }

    public static String getCardDeclined() {
        return "4444 4444 4444 4442";
    }

    public static String getCardInvalid() {
        return "4444 4444 4444 4444";
    }

    public static String getCardNotForm() {
        return "4444 4444 4444 444";
    }

    private String dateNow(String pattern) {
        return LocalDate.now().format(DateTimeFormatter.ofPattern(pattern));
    }

    private static String generateDate(int addMonth, int addYears, String pattern) {
        return LocalDate.now()
                .plusMonths(addMonth)
                .plusYears(addYears)
                .format(DateTimeFormatter.ofPattern(pattern));
    }

    private static final String getValidMonth = generateDate(0, 0, "MM");
    private static final String getValidYear = generateDate(0, 1, "yy");
    private static final String getThisYear = generateDate(0, 0, "yy");
    private static final String getInvalidFormatMonth = generateDate(0, 0, "M");
    private static final String getExpiredMonth = generateDate(-1, 0, "MM");
    private static final String getInvalidMonth = "13";
    private static final String getZeroMonth = "00";

    private static final String getExpiredYear = generateDate(0, -1, "yy");

    private static final String getInvalidYear = generateDate(0, 6, "yy");

    private static String getValidUser() {
        return fakerEng.name().fullName();
    }

    private static String getInValidUser() {
        return fakerEng.name().firstName();
    }

    private static String getRusUser() {
        return fakerRus.name().fullName();
    }

    private static String getNumbUser() {
        return fakerEng.numerify("#######");
    }

    private static String getSignUser() {
        return "!@#$%^&*()";
    }

    private static String getValidCVC() {
        return fakerEng.numerify("###");
    }

    private static String getNotFormCVC() {
        return fakerEng.numerify("##");
    }

    @Data
    @AllArgsConstructor
    @Value
    public static class Status {
        String status;
    }

    @Value
    public static class CardInfo {
        String cardNumber;
        String month;
        String year;
        String owner;
        String CVC;
    }

    public static CardInfo validApprovedCard() {
        return new CardInfo(getCardApproved(), getValidMonth,
                getValidYear, getValidUser(), getValidCVC());
    }

    public static CardInfo validDeclinedCard() {
        return new CardInfo(getCardDeclined(), getValidMonth,
                getValidYear, getValidUser(), getValidCVC());
    }

    public static CardInfo invalidCard() {
        return new CardInfo(getCardInvalid(), getValidMonth,
                getValidYear, getValidUser(), getValidCVC());
    }

    public static CardInfo invalidFormCard() {
        return new CardInfo(getCardNotForm(), getValidMonth,
                getValidYear, getValidUser(), getValidCVC());
    }

    public static CardInfo expiredMonth() {
        return new CardInfo(getCardApproved(), getExpiredMonth,
                getThisYear, getValidUser(), getValidCVC());
    }

    public static CardInfo invalidFormMonth() {
        return new CardInfo(getCardApproved(), getInvalidFormatMonth,
                getValidYear, getValidUser(), getValidCVC());
    }

    public static CardInfo invalidMonth() {
        return new CardInfo(getCardApproved(), getInvalidMonth,
                getValidYear, getValidUser(), getValidCVC());
    }

    public static CardInfo zeroMonth() {
        return new CardInfo(getCardApproved(), getZeroMonth,
                getValidYear, getValidUser(), getValidCVC());
    }

    public static CardInfo expiredYear() {
        return new CardInfo(getCardApproved(), getValidMonth,
                getExpiredYear, getValidUser(), getValidCVC());
    }

    public static CardInfo invalidYear() {
        return new CardInfo(getCardApproved(), getValidMonth,
                getInvalidYear, getValidUser(), getValidCVC());
    }

    public static CardInfo invalidUser() {
        return new CardInfo(getCardApproved(), getValidMonth,
                getValidYear, getInValidUser(), getValidCVC());
    }

    public static CardInfo invalidRusUser() {
        return new CardInfo(getCardApproved(), getValidMonth,
                getValidYear, getRusUser(), getValidCVC());
    }

    public static CardInfo numberUser() {
        return new CardInfo(getCardApproved(), getValidMonth,
                getValidYear, getNumbUser(), getValidCVC());
    }

    public static CardInfo signUser() {
        return new CardInfo(getCardApproved(), getValidMonth,
                getValidYear, getSignUser(), getValidCVC());
    }

    public static CardInfo notFormCVC() {
        return new CardInfo(getCardApproved(), getValidMonth,
                getValidYear, getValidUser(), getNotFormCVC());
    }

}
