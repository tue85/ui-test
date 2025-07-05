package com.petrovich.ui.pages;

import com.petrovich.ui.elements.Input;
import com.petrovich.ui.elements.Button;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EstimatePage extends BasePage {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    // Поля формы создания сметы
    private Input nameInput     = Input.byName("estimateName");
    private Button addBtn       = Button.byText("Добавить");
    private Input articleInput  = Input.byName("article");
    private Input quantityInput = Input.byName("quantity");
    private Button saveBtn      = Button.byText("Сохранить");

    /** Установить название сметы */
    public void setName(String name) {
        logger.info("Setting estimate name: {}", name);
        nameInput.setValue(name);
    }

    /** Добавить товар по артикулу и количеству */
    public void addProduct(String code, int qty) {
        logger.info("Adding {} x{} to estimate", code, qty);
        articleInput.setValue(code);
        addBtn.click();
        quantityInput.setValue(String.valueOf(qty));
    }

    /** Сохранить смету */
    public void save() {
        logger.info("Saving estimate");
        saveBtn.click();
    }

    /** Проверить, что в смете есть товар с указанным кодом и количеством */
    public boolean containsProduct(String code, int qty) {
        String xpath = String.format(
            "//*[contains(text(), '%s') and contains(text(), '%d')]", code, qty
        );
        return $x(xpath).exists();
    }

    /** Получить итоговую сумму сметы */
    public int getTotal() {
        String text = $("span.estimate-total").getText().replaceAll("\\D", "");
        return text.isEmpty() ? 0 : Integer.parseInt(text);
    }
}