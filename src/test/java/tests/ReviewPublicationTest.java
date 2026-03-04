package tests;


import api.client.MovieClient;
import api.dto.auth.AuthResponseDto;
import api.helper.AuthHelper;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import junit.UITest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.MovieInfoPage;
import pages.StartPage;
import utils.RoleCreds;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@Epic("Общий функционал")
@Tag("regress")
@Feature("Отзывы")
@UITest(loginRole = RoleCreds.USER)
@DisplayName("Тесты публикации отзыва")
public class ReviewPublicationTest {


    //    Инициализируем переменные
    private MovieClient movieClient;
    private AuthHelper authHelper;
    private StartPage startPage;
    private MovieInfoPage movieInfoPage;
    private RoleCreds testRole;

    private String userId;

    //    Назначаем переменные
    @BeforeEach
    void setUp() {
        movieClient = new MovieClient();
        authHelper = new AuthHelper();
        startPage = new StartPage();
        movieInfoPage = new MovieInfoPage();
        testRole = RoleCreds.USER;

        userId = null;
    }

    //    Сам тест
    @Test
    @Story("Пользователь публикует отзыв")
    @DisplayName("Публикация отзыва")
    @Description("Тест публикации отзыва с валидными данными")
    public void reviewPublicationTest() {

//        Логинимся чтобы плучить ид юзера
        AuthResponseDto auth = authHelper.login(testRole.getEmail(), testRole.getPassword());
        userId = auth.getUser().getId();

//        Переменные тела отзыва
        String reviewExample = "Один фильм офигительней другого";
        String ratingValue = "4";
        String reviewAuthor = "Тестовый Юзер Юзерович";

//        Нажимаем "Подробнее" у фильма
        startPage.clickMovieInfo();

//        Заполняем поля отзыва и отправляем его
        movieInfoPage
                .setReviewText(reviewExample)
                .setRatingValue(ratingValue)
                .sendReview()
                .waitForReviewWithText(reviewExample);

//        Проверки
        String successReviewPublicationTextAssert = movieInfoPage.getReviewText();
        assertThat(successReviewPublicationTextAssert)
                .as("Текст отзыва должен соответствовать указанному")
                .isEqualTo(reviewExample);

        String successReviewPublicationAuthorAssert = movieInfoPage.getReviewAuthor();
        assertThat(successReviewPublicationAuthorAssert)
                .as("Автор отзыва должен соответствовать указанному")
                .isEqualTo(reviewAuthor);

        String successReviewPublicationValueAssert = movieInfoPage.getReviewValue();
        assertThat(successReviewPublicationValueAssert)
                .as("Оценка должна соответствовать указанной")
                .contains(ratingValue);
    }

    //    Удаляем отзыв пользователя по апи от роли Админа
    @AfterEach
    void cleanUp() {
        if (userId == null) {
            return;
        }
        Integer movieId = movieInfoPage.getMovieId();
        String adminToken = authHelper.login(RoleCreds.ADMIN.getEmail(), RoleCreds.ADMIN.getPassword())
                .getAccessToken();
        movieClient.deleteReview(movieId, userId, adminToken);
    }
}
