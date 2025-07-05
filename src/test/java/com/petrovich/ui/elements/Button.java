package com.petrovich.ui.elements;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.Selectors;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

/**
 * Обёртка над SelenideElement для работы с кнопками.
 */
public class Button extends BaseElement {
    private Button(SelenideElement element) {
        super(element);
    }

    /**
     * Поиск кнопки по точному тексту.
     */
    public static Button byText(String text) {
        return new Button($(Selectors.byText(text)));
    }

    /**
     * Поиск кнопки по CSS-селектору.
     */
    public static Button byCss(String cssSelector) {
        return new Button($(cssSelector));
    }

    /**
     * Поиск кнопки по XPath-выражению.
     */
    public static Button byXpath(String xpath) {
        return new Button($x(xpath));
    }
}
