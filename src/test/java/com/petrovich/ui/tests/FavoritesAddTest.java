package com.petrovich.ui.tests;

import com.petrovich.ui.pages.ProductPage;
import com.petrovich.ui.pages.FavoritesPage;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selectors.byText;
import com.codeborne.selenide.Condition;
import java.time.Duration;

/**
 * Тесты, связанные с добавлением товара в избранное и проверкой счётчика.
 */
public class FavoritesAddTest extends BaseTest {
    private final String code = "104843";

    @Test
    public void testAddFavorite() {
        // Открываем страницу товара и получаем начальное значение счётчика
        ProductPage product = new ProductPage().open(code);
        int before = product.getFavoritesCount();

        // Добавляем в избранное и ждём обновления счётчика
        product.addToFavorites();
        $("span.favorites-count")
            .shouldHave(Condition.text(String.valueOf(before + 1)), Duration.ofSeconds(2));
        assertEquals(before + 1, product.getFavoritesCount());

        // Переходим в избранное и проверяем отображение товара
        FavoritesPage fav = new FavoritesPage().open();
        $(byText(code))
            .shouldBe(Condition.visible, Duration.ofSeconds(2));
        assertTrue(fav.containsProduct(code));
    }
}
