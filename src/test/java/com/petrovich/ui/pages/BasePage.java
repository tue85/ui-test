package com.petrovich.ui.pages;

import com.codeborne.selenide.Selenide;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Базовый абстрактный класс для всех Page Object-страниц.
 * <p>
 * Содержит общие утилиты для:
 * <ul>
 *   <li>Получения текущего количества товаров в корзине и избранном.</li>
 *   <li>Открытия страниц по относительному URL c логгированием.</li>
 * </ul>
 * Каждый наследник автоматически получает настроенный {@link Logger}.
 */
public abstract class BasePage {

    /** Логгер конкретного класса-наследника. */
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * Возвращает количество товаров в корзине.
     *
     * <p>Метод читает текст из элемента <code>&lt;span class="cart-count"&gt;</code>.
     * Если текст пустой, считается, что товаров нет.</p>
     *
     * @return число товаров в корзине
     */
    public int getCartCount() {
        String text = Selenide.$("span.cart-count").getText();
        return text.isEmpty() ? 0 : Integer.parseInt(text.trim());
    }

    /**
     * Возвращает количество товаров в избранном.
     *
     * <p>Берёт текст из <code>&lt;span class="favorites-count"&gt;</code>.
     * Пустая строка трактуется как <code>0</code>.</p>
     *
     * @return число товаров в избранном
     */
    public int getFavoritesCount() {
        String text = Selenide.$("span.favorites-count").getText();
        return text.isEmpty() ? 0 : Integer.parseInt(text.trim());
    }

    /**
     * Открывает страницу по относительному URL.
     *
     * <p>Автоматически добавляет ведущий слэш при необходимости,
     * записывает действие в лог и использует {@link Selenide#open}.</p>
     *
     * @param relativeUrl путь вида <code>"/profile"</code> или <code>"profile"</code>
     */
    protected void openPage(String relativeUrl) {
        logger.info("Opening page: {}", relativeUrl);
        String url = relativeUrl.startsWith("/") ? relativeUrl : "/" + relativeUrl;
        Selenide.open(url);
    }
}
