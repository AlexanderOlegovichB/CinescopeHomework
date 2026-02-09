package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import junit.UITest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.MovieInfoPage;
import pages.StartPage;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@Epic("Общий функционал")
@Feature("Фильтры")
@UITest
@Tag("regress")
@DisplayName("Применение фильтров")
public class AddFiltersTest {

    private MovieInfoPage movieInfoPage = new MovieInfoPage();
    private StartPage startPage = new StartPage();

    @Test
    @Story("Пользователь применяет фильтры")
    @DisplayName("Применение фильтров")
    @Description("Валидный тест применения фильтров поиска фильмов на главной странице")

    public void correctAddFiltersTest() {
        String genre = "Триллер";

        startPage
                .clickAllMovies() // нажимаем "Все фильмы"
                .setGenreFilter(genre) // Выбираем жанр
                .clickMovieInfo(); // у найденного фильма жмем "Подробнее"

        String successGenreFilterMatch = movieInfoPage.getGenreInfo();
        assertThat(successGenreFilterMatch).contains(genre);
    }
}
