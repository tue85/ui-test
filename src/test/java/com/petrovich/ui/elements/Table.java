package com.petrovich.ui.elements;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;

public class Table extends BaseElement {
    public Table(SelenideElement element) {
        super(element);
    }

    public static Table byCss(String cssSelector) {
        return new Table($(cssSelector));
    }

    public int getRowCount() {
        return element.$$("[role=row], tr").size();
    }

    public boolean contains(String text) {
        return element.getText().contains(text);
    }
}
