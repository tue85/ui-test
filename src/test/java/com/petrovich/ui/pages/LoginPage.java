package com.petrovich.ui.pages;

import com.petrovich.ui.elements.Input;
import com.petrovich.ui.elements.Button;

/**
 * Страница логина пользователя.
 */
public class LoginPage extends BasePage {
    // XPaths элементов страницы логина вынесены в константы
    private static final String XPATH_USERNAME_INPUT = "/html/body/div[6]/div/div/div[2]/div[2]/div/div[2]/div[1]/form/div[1]/div/div/label/input";
    private static final String XPATH_PASSWORD_INPUT = "/html/body/div[6]/div/div/div[2]/div[2]/div/div[2]/div[1]/form/div[2]/div/div/label/input";
    private static final String XPATH_LOGIN_BUTTON   = "/html/body/div[6]/div/div/div[2]/div[2]/div/div[2]/div[1]/form/button[1]";

    // Элементы страницы логина
    private final Input usernameInput = Input.byXpath(XPATH_USERNAME_INPUT);
    private final Input passwordInput = Input.byXpath(XPATH_PASSWORD_INPUT);
    private final Button loginButton  = Button.byXpath(XPATH_LOGIN_BUTTON);

    /**
     * Выполняет вход в систему и возвращает страницу аккаунта.
     * @param username логин пользователя
     * @param password пароль пользователя
     * @return AccountPage после успешного входа
     */
    public AccountPage login(String username, String password) {
        logger.info("Attempting to login with username: {}", username);
        usernameInput.setValue(username);
        passwordInput.setValue(password);
        loginButton.click();
        return new AccountPage();
    }
}
