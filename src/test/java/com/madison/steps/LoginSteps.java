package com.madison.steps;

import com.madison.pages.LoginPage;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

import static org.junit.Assert.assertTrue;

public class LoginSteps extends ScenarioSteps {

    private LoginPage loginPage;

    @Step
    public void navigateToLoginPage() {
        loginPage.open();
    }

    @Step
    public void login(String username, String password) {
        loginPage.login(username, password);
    }

    @Step
    public void checkIfUserWasLoggedIn() {
        assertTrue(getDriver().getCurrentUrl().equals("http://qa1.madison.com/customer/account/"));
    }
}
