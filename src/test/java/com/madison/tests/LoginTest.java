package com.madison.tests;

import com.madison.data.Constants;
import com.madison.steps.LoginSteps;
import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import net.thucydides.junit.annotations.UseTestDataFrom;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SerenityParameterizedRunner.class)
@UseTestDataFrom(value = Constants.LOGINCSV_PATH)
public class LoginTest {

      @Before
    public void maxiPage() {
        driver.manage().window().maximize();
    }

    @Managed(uniqueSession = true)
    public WebDriver driver;

    @Steps
    private LoginSteps loginSteps;

    private String username, password;

    @Test
    public void test01IfLoginFormValidationWorks() {
        loginSteps.navigateToLoginPage();
        loginSteps.login(username, password);
        loginSteps.checkIfUserWasLoggedIn();
    }
}
