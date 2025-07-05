package com.petrovich.ui.pages;

import com.petrovich.ui.elements.Button;

public class AccountPage extends BasePage {
    private final Button logoutButton = Button.byText("Выход");

    public HomePage logout() {
        logger.info("Logging out");
        logoutButton.click();
        return new HomePage();
    }

    public boolean isLoggedIn() {
        return logoutButton.isDisplayed();
    }
}
