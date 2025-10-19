package tests;
import com.codeborne.selenide.Configuration;
import io.qameta.allure.*;
import junit.UITest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.MovieInfo;
import pages.StartPage;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Epic("Общий функционал")
@Feature("Фильтры")
@UITest
public class AddFiltersTest {
    @Test
    @Story("Пользователь применяет фильтры")
    @DisplayName("Применение фильтров")
    @Description("Валидный тест применения фильтров поиска фильмов на главной странице")

    public void correctAddFiltersTest() {
       // Configuration.holdBrowserOpen = true; //отладочная конфигурация
        String genre = "Триллер";


        Allure.step("Нажимаем 'Все фильмы'", () -> {
            StartPage startPage = new StartPage();
            startPage.clickMore();
        });

        Allure.step("Выбираем жанр", () -> {
            StartPage startPage = new StartPage();
            startPage.setGenreFilter(genre);
        });

        Allure.step("Нажимаем 'Подробнее' у первого фильма в выдаче", () -> {
            StartPage startPage = new StartPage();
            startPage.clickInfo();
        });

        Allure.step("Проверяем жанр на соответствие фильтру", () -> {
            MovieInfo movieInfo = new MovieInfo();

            String succesGenreFilterMatch = movieInfo.getGenreInfo();
            assertThat(succesGenreFilterMatch).contains(genre);
        });
    }
}
