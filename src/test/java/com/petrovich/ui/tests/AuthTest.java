package com.petrovich.ui.tests;

import com.petrovich.ui.pages.HomePage;
import com.petrovich.ui.pages.LoginPage;
import com.petrovich.ui.pages.AccountPage;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Тест-класс для проверки сценария авторизации и выхода из системы.
 */
public class AuthTest extends BaseTest {

    /** Тестовый e-mail для входа в систему. */
    private static final String TEST_LOGIN    = "ULLI2019@yandex.ru";
    /** Тестовый пароль для входа в систему. */
    private static final String TEST_PASSWORD = "password";

    /**
     * Выполняет полный сценарий:
     * 1. Открытие главной страницы.
     * 2. Переход на страницу логина.
     * 3. Авторизация с заданными данными.
     * 4. Проверка успешного входа.
     * 5. Выход из аккаунта.
     * 6. Проверка возврата на главную.
     */
    @Test
    public void testLoginLogout() {
        // 1. Открываем главную страницу
        HomePage home = new HomePage().open();

        // 2. Переходим на страницу авторизации
        LoginPage login = home.goToLoginPage();

        // 3. Вводим логин и пароль, кликаем "Войти" и переходим в личный кабинет
        AccountPage account = login.login(TEST_LOGIN, TEST_PASSWORD);
        
        // 4. Проверяем, что кнопка "Выход" отображается, значит вход выполнен
        assertTrue(account.isLoggedIn(), "Пользователь не вошёл в систему");

        // 5. Нажимаем "Выход" и получаем главную страницу
        HomePage after = account.logout();

        // 6. Проверяем, что ссылка "Войти" снова видна на главной странице
        assertTrue(after.isLoginLinkVisible(), "Ссылка «Войти» не отображается после выхода");
    }
}
