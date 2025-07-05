package com.petrovich.ui.elements;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
/**
 * Обёртка над SelenideElement для работы с полями ввода.
 */
public class Input extends BaseElement {
    private Input(SelenideElement element) {
        super(element);
    }

    /**
     * Поиск по XPath-выражению.
     */
    public static Input byXpath(String xpath) {
        return new Input($x(xpath));
    }

    /**
     * Поиск по значению атрибута id.
     */
    public static Input byId(String id) {
        return new Input($("#" + id));
    }

    /**
     * Поиск по значению атрибута name.
     */
    public static Input byName(String name) {
        return new Input($("input[name='" + name + "']"));
    }
    
    /**
     * Поиск по произвольному CSS-селектору.
     */
    public static Input byCss(String cssSelector) {
        return new Input($(cssSelector));
    }

    /**
     * Очистка и ввод текста в поле.
     */
    public void setValue(String text) {
        element.clear();
        element.setValue(text);
        logger.info("Input value set to: {}", text);
    }

    /**
     * Нажатие Enter в поле.
     */
    public void pressEnter() {
        element.pressEnter();
        logger.info("Pressed Enter in input");
    }
}
