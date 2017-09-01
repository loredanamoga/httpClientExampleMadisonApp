package com.madison.steps;

import com.madison.pages.Header;
import com.madison.pages.LoginPage;
import net.thucydides.core.steps.ScenarioSteps;

import static org.junit.Assert.assertTrue;

public class LogoutSteps extends ScenarioSteps {

    private Header header;
    private LoginPage loginPage;

    public void navigateToLoginPage() {
        loginPage.open();
    }

    public void login(String username, String password) {
        loginPage.login(username, password);
    }

    public void logout() {
        header.logout();
    }

    public void checkIfUserWasLoggedOut() {
        assertTrue(getDriver().getCurrentUrl().equals("http://qa1.madison.com/customer/account/logoutSuccess/"));
    }

}
