package com.petrovich.ui.elements;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;

/**
 * Обёртка над HTML-таблицей или любым контейнером с табличными данными.
 * Позволяет удобно получать количество строк и проверять,
 * содержит ли таблица заданный текст.
 */
public class Table extends BaseElement {

    /**
     * Создаёт {@code Table}, оборачивая переданный {@link SelenideElement}.
     *
     * @param element элемент, представляющий таблицу
     */
    public Table(SelenideElement element) {
        super(element);
    }

    /**
     * Возвращает количество строк таблицы.
     *
     * <p>Считает элементы с ролями {@code [role=row]} или {@code tr}.</p>
     *
     * @return число строк
     */
    public int getRowCount() {
        return element.$$("[role=row], tr").size();
    }

    /**
     * Проверяет, содержит ли таблица указанный текст.
     *
     * @param text искомая строка
     * @return {@code true}, если текст найден; иначе {@code false}
     */
    public boolean isTextContains(String text) {
        return element.getText().contains(text);
    }

    /**
     * Быстрый статический конструктор по CSS-селектору.
     *
     * @param cssSelector CSS-селектор таблицы
     * @return объект {@code Table}, привязанный к найденному элементу
     */
    public static Table byCss(String cssSelector) {
        return new Table($(cssSelector));
    }
}