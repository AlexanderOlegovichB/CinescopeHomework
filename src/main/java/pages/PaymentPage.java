package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.jspecify.annotations.NonNull;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class PaymentPage {

    private SelenideElement ticketCount = $("[data-qa-id=\"payment_amount_input\"]");
    private SelenideElement cardNumber = $("[data-qa-id=\"payment_card_number_input\"]");
    private SelenideElement cardHolder = $("[data-qa-id=\"payment_card_holder_input\"]");
    private SelenideElement cardExpMonth = $("[data-qa-id=\"payment_card_month_select\"]");
    private ElementsCollection cardExpMonthSelector = $$("[role='option']");
    private SelenideElement cardExpYear = $("[data-qa-id=\"payment_card_year_select\"]");
    private ElementsCollection cardExpYearSelector = $$("[role='option']");
    private SelenideElement cardCvv = $("[data-qa-id=\"payment_card_cvc_input\"]");
    private SelenideElement paymentButton = $("[data-qa-id=\"payment_submit_button\"]");
    private SelenideElement succesIcon = $("svg.lucide-circle-check-big.text-green-500");
    private SelenideElement succesText = $("p.text-xl");
    private SelenideElement returnToMainButton = $("svg.lucide lucide-circle-check-big.text-green-500");


    public void setTicketCount(String value) {
        ticketCount.setValue(value);
    }

    public void setCardNumber(String value) {
        cardNumber.setValue(value);
    }

    public void setCardHolder(String value) {
        cardHolder.setValue(value);
    }

    public void setExpMonth(String month) {
        cardExpMonth.click();
        cardExpMonthSelector.findBy(text(month)).click();
    }

    public void setExpYear(String year) {
        cardExpYear.click();
        cardExpYearSelector.findBy(text(year)).click();
    }

    public void setCvv(String value) {
        cardCvv.setValue(value);
    }

    public void buyTicket() {
        paymentButton.click();
    }

    public String getSuccesText() {
        return succesText.getText();
    }

    public SelenideElement getSuccesIcon() {
        return succesIcon;
    }
}
