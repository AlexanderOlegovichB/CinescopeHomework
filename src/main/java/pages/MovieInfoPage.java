package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class MovieInfoPage {

    private SelenideElement reviewInput = $("[data-qa-id='movie_review_input']");
    private SelenideElement buyButton = $x("//button[.//p[text()='Купить билет']]");
    private SelenideElement sendReviewButton = $("[data-qa-id='movie_review_submit_button']");
    private SelenideElement ratingSelect = $("button[role='combobox']");
    private ElementsCollection ratingOption = $$("[role='option']");
    private SelenideElement reviewText = $("p.overflow-hidden.text-ellipsis");
    private SelenideElement reviewValue = $("span.underline");
    private SelenideElement reviewAuthor = $("h4.text-xl.w-fit");
    private SelenideElement reviewActionsButton = $("[data-qa-id='movie_review_actions_button']");
    private SelenideElement reviewActionsDeleteButton = $("[data-qa-id='movie_review_action_delete_button']");
    private SelenideElement genreInfo = $("p.text-lg.mt-5");
    private SelenideElement successReviewDeleteNotification = $x("//div[text()='Отзыв успешно удален']");


    @Step("Покупаем билет")
    public PaymentPage buyTicket() {
        buyButton.click();
        return new PaymentPage();
    }

    @Step("Вводим текст отзыва")
    public MovieInfoPage setReviewText(String value) {
        reviewInput.setValue(value);
        return this;
    }

    @Step("Выставить рейтинг")
    public MovieInfoPage setRatingValue(String value) {
        ratingSelect.click();
        ratingOption.findBy(text(value)).click();
        return this;
    }

    public String getGenreInfo() {
        return genreInfo.getText();
    }

    @Step("Отправить отзыв")
    public MovieInfoPage sendReview() {
        sendReviewButton.click();
        return this;
    }

    public String getReviewText() {
        return reviewText.getText();
    }

    public String getReviewValue() {
        return reviewValue.getText();
    }

    public String getReviewAuthor() {
        return reviewAuthor.getText();
    }

    public boolean isSuccessReviewDeleteNotification() {
        return successReviewDeleteNotification.is(visible, Duration.ofSeconds(5));
    }

    @Step("Удаление отзыва")
    public MovieInfoPage deleteReview() {
        reviewActionsButton.click();
        reviewActionsDeleteButton.click();
        return this;
    }
}
