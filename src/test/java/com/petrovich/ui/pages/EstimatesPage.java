package com.petrovich.ui.pages;

import com.petrovich.ui.elements.Button;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selectors.byText;

/**
 * Страница списка смет.
 */
public class EstimatesPage extends BasePage {
    // TODO: В DevTools найдите кнопку «Создать новую смету» и скопируйте её точный текст для локатора
    private final Button createBtn = Button.byText("Создать новую смету");

    /**
     * Открывает страницу со списком смет.
     * TODO: Проверьте в адресной строке браузера относительный путь (например "/estimates") и при необходимости скорректируйте.
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
        // TODO: при вызове передавайте сюда текст названия сметы точно так же, как он отображён на странице
        return $(byText(name)).exists();
    }
}
