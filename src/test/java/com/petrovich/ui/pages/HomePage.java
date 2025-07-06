package com.petrovich.ui.pages;

import com.petrovich.ui.elements.Input;
import com.petrovich.ui.elements.Button;

/**
 * Страница главного экрана сайта.
 */
public class HomePage extends BasePage {
    private static final String SEARCH_INPUT ="#page-header-search > div > div > input";
    private static final String LOGIN_LINK ="Войти";
    
    private final Input  searchInput = Input.byCss(SEARCH_INPUT);

    private final Button loginLink   = Button.byText(LOGIN_LINK);

    /**
     * Открывает главную страницу.
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
        return loginLink.isDisplayed();
    }
}
