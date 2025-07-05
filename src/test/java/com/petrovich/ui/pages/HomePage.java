package com.petrovich.ui.pages;

import com.petrovich.ui.elements.Input;
import com.petrovich.ui.elements.Button;

import static com.codeborne.selenide.Selenide.open;

/**
 * Страница главного экрана сайта.
 */
public class HomePage extends BasePage {
    // TODO: Через DevTools скопируйте точный CSS-селектор поля поиска и при необходимости замените его здесь
    private final Input  searchInput = Input.byCss("#page-header-search > div > div > input");

    // TODO: Уточните точный текст кнопки «Войти» в UI (например, из ссылки в хедере)
    private final Button loginLink   = Button.byText("Войти");

    /**
     * Открывает главную страницу.
     * TODO: Проверьте, что относительный URL ("/") соответствует тому, что используется в приложении.
     */
    public HomePage open() {
        openPage("/");
        return this;
    }

    /**
     * Выполняет поиск по запросу и переходит на страницу результатов.
     * @param query текст для поиска, скопированный/введённый в поле поиска
     */
    public SearchResultsPage searchFor(String query) {
        logger.info("Searching for: {}", query);
        searchInput.setValue(query);
        searchInput.pressEnter();
        return new SearchResultsPage();
    }

    /**
     * Переходит на страницу логина.
     */
    public LoginPage goToLoginPage() {
        loginLink.click();
        return new LoginPage();
    }

    /**
     * Проверяет, видна ли ссылка «Войти».
     * @return true, если элемент отображается на странице
     */
    public boolean isLoginLinkVisible() {
        // TODO: Метод передаёт ту же строку, что и loginLink (Button.byText), убедитесь в её корректности
        return loginLink.isDisplayed();
    }
}
