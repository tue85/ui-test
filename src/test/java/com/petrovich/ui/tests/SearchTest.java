package com.petrovich.ui.tests;

import com.petrovich.ui.pages.HomePage;
import com.petrovich.ui.pages.SearchResultsPage;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import com.codeborne.selenide.Condition;
import java.time.Duration;
import static com.codeborne.selenide.Selenide.$;

public class SearchTest extends BaseTest {

    @Test
    public void testSearchWithFilterAndSort() {
        String query = "Краска акриловая белая";

        // Открываем главную страницу и выполняем поиск
        SearchResultsPage page = new HomePage().open()
            .searchFor(query);

        // Применяем фильтр по цене и сортировку
        page.filterByPrice(150, 400)
            .sortByPriceAscending();

        // Проверяем, что результаты не пустые и отсортированы по возрастанию цен в заданном диапазоне
        int[] prices = page.getAllProductPrices();
        assertTrue(prices.length > 0, "Search results should not be empty");
        for (int i = 1; i < prices.length; i++) {
            assertTrue(prices[i] >= prices[i - 1],
                       String.format("Prices not sorted: %d >= %d?", prices[i], prices[i - 1]));
            assertTrue(prices[i] >= 150 && prices[i] <= 400,
                       String.format("Price %d is outside range [150,400]", prices[i]));
        }

        // Проверяем, что названия товаров содержат поисковый запрос
        List<String> titles = page.getAllProductTitles().texts();
        assertFalse(titles.isEmpty(), "Titles list should not be empty");
        for (String title : titles) {
            assertTrue(title.contains(query),
                       String.format("Title '%s' does not contain query", title));
        }
    }
}
