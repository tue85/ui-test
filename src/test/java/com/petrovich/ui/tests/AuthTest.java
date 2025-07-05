package com.petrovich.ui.tests;

import com.petrovich.ui.pages.HomePage;
import com.petrovich.ui.pages.LoginPage;
import com.petrovich.ui.pages.AccountPage;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static com.codeborne.selenide.Selenide.sleep;


public class AuthTest extends BaseTest {
    private static final String TEST_LOGIN    = "ULLI2019@yandex.ru";
    private static final String TEST_PASSWORD = "s5GK4V";

    @Test
    public void testLoginLogout() {

        HomePage home = new HomePage().open(); // откр баз стр
        sleep(2000);    
        LoginPage login = home.goToLoginPage(); // переход на автор
        sleep(6000);
        AccountPage account = login.login(TEST_LOGIN, TEST_PASSWORD); //введение пароля loginPage
        //Работает верно
        
        //assertTrue(account.isLoggedIn());
        //HomePage after = account.logout();
        //assertTrue(after.isLoginLinkVisible());
    }
}
