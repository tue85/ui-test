package com.petrovich.ui.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.petrovich.ui.elements.Input;
import com.petrovich.ui.elements.Button;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/**
 * Страница результатов поиска.
 */
public class SearchResultsPage extends BasePage {

    // TODO: Скопируйте через DevTools селектор поля "Цена от" (placeholder или id) для точного совпадения
    private final Input priceFromInput     = Input.byXpath("/html/body/div[2]/main/div/div/div[2]/aside/div/div[4]/div/div/div[1]/div[1]/label/input");

    // TODO: Скопируйте через DevTools селектор поля "Цена до" (placeholder 'До' или другой уникальный атрибут)
    private final Input priceToInput       = Input.byXpath("/html/body/div[2]/main/div/div/div[2]/aside/div/div[4]/div/div/div[1]/div[2]/label/input");

    // TODO: Скопируйте через DevTools текст/селектор для кнопки сортировки по цене по возрастанию
    private final Button sortPriceAscButton = Button.byText("по цене");

    /**
     * Задать диапазон цен и применить фильтр.
     * @param min минимальная цена
     * @param max максимальная цена
     */
    public SearchResultsPage filterByPrice(int min, int max) {
        logger.info("Filtering by price {}–{}", min, max);
        
        priceFromInput.click();
        priceFromInput.setValue(String.valueOf(min));

        priceToInput.click();
        priceToInput.setValue(String.valueOf(max));

        // TODO: При необходимости поменяйте селектор "div.product-card" на тот, что появляется при загрузке результатов
        $("div.product-card")
            .should(Condition.appear, Duration.ofSeconds(2));

        return this;
    }

    /**
     * Отсортировать результаты по возрастанию цены.
     */
    public SearchResultsPage sortByPriceAscending() {
        logger.info("Sorting by price ascending");
        sortPriceAscButton.click();
        // TODO: Уточните селектор карточки товара для проверки появления после сортировки
        $("div.product-card")
            .should(Condition.appear, Duration.ofSeconds(2));
        return this;
    }

    /**
     * Считать все цены товаров на странице.
     * @return массив цен (int), без символов валюты
     */
    public int[] getAllProductPrices() {
        // Ищем все элементы span, класс которых содержит 'pt-price'
        ElementsCollection elems = $$("div.product-card span[class*='pt-price']");
        return elems.texts().stream()
                    .mapToInt(text -> Integer.parseInt(text.replaceAll("\\D", "")))
                    .toArray();
    }

    /**
     * Считать все заголовки товаров на странице.
     */
    public ElementsCollection getAllProductTitles() {
        // TODO: Уточните селектор заголовков, например "h2.product-title"
        return $$("div.product-card h2.product-title");
    }
}
