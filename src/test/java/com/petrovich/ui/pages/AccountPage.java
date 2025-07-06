package com.petrovich.ui.pages;

import com.petrovich.ui.elements.Button;

/**
 * Страница «Личный кабинет».
 * <p>
 * Предоставляет функции выхода из системы и проверки,
 * что пользователь авторизован.
 * </p>
 */
public class AccountPage extends BasePage {
    // Константа для текста кнопки выхода
    private static final String LOGOUT_BUTTON_TEXT = "Выход";

    // Элемент кнопки «Выход»
    private final Button logoutButton = Button.byText(LOGOUT_BUTTON_TEXT);

    /**
     * Выполняет выход пользователя из аккаунта.
     *
     * <p>Метод кликает по кнопке «Выход», пишет запись в лог
     * и возвращает объект главной страницы.</p>
     *
     * @return {@link HomePage} — объект главной страницы после выхода
     */
    public HomePage logout() {
        logger.info("Logging out");
        logoutButton.click();
        return new HomePage();
    }

    /**
     * Проверяет, отображается ли кнопка «Выход»,
     * тем самым косвенно подтверждая, что пользователь авторизован.
     *
     * @return {@code true}, если кнопка видна; иначе {@code false}
     */
    public boolean isLoggedIn() {
        return logoutButton.isDisplayed();
    }
}
