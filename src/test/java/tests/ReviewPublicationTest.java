package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.*;
import junit.UITest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.MovieInfoPage;
import pages.PaymentPage;
import pages.StartPage;

import static com.codeborne.selenide.Condition.visible;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@Epic("Общий функционал")
@Feature("Отзывы")
@UITest
public class ReviewPublicationTest {

    private StartPage startPage = new StartPage();
    private MovieInfoPage movieInfoPage = new MovieInfoPage();

    @Test
    @Story("Пользователь публикует отзыв")
    @DisplayName("Публикация отзыва")
    @Description("Тест публикации отзыва с валидными данными")
    public void reviewPublicationTest() {

        String reviewExample = "Один фильм офигительней другого";
        String ratingValue = "4";
        String reviewAuthor = "Тестовый Юзер Юзерович";

        startPage.clickMovieInfo();

        movieInfoPage
                .setReviewText(reviewExample)
                .setRatingValue(ratingValue)
                .sendReview();


        String succesReviewPublicationTextAssert = movieInfoPage.getReviewText();
        assertThat(succesReviewPublicationTextAssert).isEqualTo(reviewExample);

        String succesReviewPublicationAuthorAssert = movieInfoPage.getReviewAuthor();
        assertThat(succesReviewPublicationAuthorAssert).isEqualTo(reviewAuthor);

        String succesReviewPublicationValueAssert = movieInfoPage.getReviewValue();
        assertThat(succesReviewPublicationValueAssert).contains(ratingValue);

    }

    @AfterEach
    public void cleanReview() {
        movieInfoPage.deleteReview();

        assertThat(movieInfoPage.isSuccesReviewDeleteNotif())
                .as("Должно отображаться уведомление об успешном удалении отзыва")
                .isTrue();
    }
}
