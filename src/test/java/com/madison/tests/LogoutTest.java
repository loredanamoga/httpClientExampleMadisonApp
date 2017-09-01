package com.madison.tests;

import com.madison.steps.LogoutSteps;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SerenityRunner.class)
public class LogoutTest {

    @Managed(uniqueSession = true)
    public WebDriver driver;

    @Steps
    private LogoutSteps logoutSteps;

    @Test
    public void test01IfUserCanLogout() {
        logoutSteps.navigateToLoginPage();
        logoutSteps.login("flm.marius@yahoo.com", "aurora1432");
        logoutSteps.logout();
        logoutSteps.checkIfUserWasLoggedOut();
    }

}
