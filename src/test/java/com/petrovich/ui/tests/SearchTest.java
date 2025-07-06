package com.petrovich.ui.tests;

import com.petrovich.ui.pages.HomePage;
import com.petrovich.ui.pages.SearchResultsPage;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Тесты, проверяющие функциональность поиска на сайте Petrovich.
 * В данном классе реализован один сценарий поиска с фильтрацией по цене и сортировкой.
 */
public class SearchTest extends BaseTest {

    /**
     * Проверяет поиск товаров по запросу с последующей фильтрацией по диапазону цен
     * и сортировкой результатов по возрастанию цены.
     */
    @Test
    public void testSearchWithFilterAndSort() {
        // Исходный поисковый запрос
        String query = "Краска акриловая белая";

        // Шаг 1: Открываем главную страницу сайта
        // Шаг 2: Выполняем поиск по заданному запросу через метод страницы HomePage
        SearchResultsPage page = new HomePage()
            .open()                // Навигация на главную страницу
            .searchFor(query);     // Ввод запроса и нажатие Enter

        // Шаг 3: Применяем фильтр по цене от 150 до 400 рублей
        // метод filterByPrice выставляет соответствующие значения в фильтрах и нажимает "Применить"
        page.filterByPrice(150, 400)
            // Шаг 4: Сортируем результаты по цене в порядке возрастания
            .sortByPriceAscending(); 

        // Шаг 5: Получаем массив цен всех отображённых товаров
        int[] prices = page.getAllProductPrices();

        // Убеждаемся, что результаты поиска не пусты
        assertTrue(prices.length > 0, 
                   "Search results should not be empty");

        // Шаг 6: Проверяем, что цены отсортированы по возрастанию
        // и при этом каждая цена находится в заданном диапазоне [150, 400]
        for (int i = 1; i < prices.length; i++) {
            // Проверяем порядок: текущая цена не меньше предыдущей
            assertTrue(prices[i] >= prices[i - 1],
                       String.format("Prices not sorted: %d >= %d?", 
                                     prices[i], prices[i - 1]));

            // Проверяем, что цена лежит в пределах фильтра
            assertTrue(prices[i] >= 150 && prices[i] <= 400,
                       String.format("Price %d is outside range [150,400]", 
                                     prices[i]));
        }

        // Шаг 7: Получаем список названий всех товаров
        List<String> titles = page.getAllProductTitles().texts();

        // Убеждаемся, что список названий получен и не пуст
        assertFalse(titles.isEmpty(), 
                    "Titles list should not be empty");

        // Шаг 8: Проверяем, что каждое название содержит исходный поисковый запрос
        for (String title : titles) {
            assertTrue(title.contains(query),
                       String.format("Title '%s' does not contain query", title));
        }
    }
}
