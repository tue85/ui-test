package com.petrovich.ui.pages;

import com.petrovich.ui.elements.Button;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selectors.byText;

/**
 * Страница списка смет.
 */
public class EstimatesPage extends BasePage {
    private static final String  CREATE_BTN = "Создать новую смету";
    
    private final Button createBtn = Button.byText(CREATE_BTN);

    /**
     * Открывает страницу со списком смет.
     */
    public EstimatesPage open() {
        openPage("/estimates");
        return this;
    }

    /**
     * Нажимает кнопку создания новой сметы.
     * Возвращает страницу создания сметы.
     */
    public EstimatePage clickCreateNewEstimate() {
        createBtn.click();
        return new EstimatePage();
    }

    /**
     * Проверяет, что на странице присутствует смета с указанным именем.
     * @param name точное название сметы, скопированное с UI (например, из заголовка или списка)
     */
    public boolean isEstimatePresent(String name) {
        return $(byText(name)).exists();
    }
}
