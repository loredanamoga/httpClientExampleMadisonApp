package com.madison.tests.httptests;

import com.madison.steps.httpsteps.LoginStepsHttp;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SerenityRunner.class)
public class LoginTestHttp {

    @Managed(uniqueSession = false)
    public WebDriver driver;

    private BasicCookieStore cookieStore = new BasicCookieStore();
    private CloseableHttpClient httpclient = HttpClients.custom().setDefaultCookieStore(cookieStore)
            .build();

    private LoginStepsHttp loginStepsHttp = new LoginStepsHttp(httpclient);

    @Test
    public void test01IfLoginFormValidationWorks() {
        loginStepsHttp.navigateToLoginPage();
        loginStepsHttp.login("flm.marius@yahoo.com", "aurora1432");
        loginStepsHttp.checkIfUserWasLoggedIn();
    }


}
