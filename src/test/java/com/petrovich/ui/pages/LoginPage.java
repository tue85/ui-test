package com.petrovich.ui.pages;

import com.petrovich.ui.elements.Input;
import com.petrovich.ui.elements.Button;
import static com.codeborne.selenide.Selenide.sleep;

/**
 * Страница логина пользователя.
 */
public class LoginPage extends BasePage {
    // Элементы страницы логина
    private Input usernameInput = Input.byXpath("/html/body/div[7]/div/div/div[2]/div[2]/div/div[2]/div[1]/form/div[1]/div/div/label/input");
    private Input passwordInput = Input.byXpath("/html/body/div[7]/div/div/div[2]/div[2]/div/div[2]/div[1]/form/div[2]/div/div/label/input");
    private Button loginButton  = Button.byXpath("/html/body/div[7]/div/div/div[2]/div[2]/div/div[2]/div[1]/form/button[1]");

    /**
     * Выполняет вход в систему и возвращает страницу аккаунта.
     * Добавлена задержка перед возвратом, чтобы дать странице время на обработку.
     * @param username логин пользователя
     * @param password пароль пользователя
     * @return AccountPage после успешного входа
     */
    public AccountPage login(String username, String password) {
        logger.info("Attempting to login with username: {}", username);
        usernameInput.setValue(username);
        passwordInput.setValue(password);
        loginButton.click();
        
        // Ждём 1 секунду, чтобы страница успела обновиться
        sleep(1000);

        return new AccountPage();
    }
}
