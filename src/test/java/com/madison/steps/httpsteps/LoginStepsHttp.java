package com.madison.steps.httpsteps;

import com.madison.pages.pageshttp.LoginPageHttp;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Step;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class LoginStepsHttp {

    private CloseableHttpClient httpClient;
    private LoginPageHttp loginPageHttp = new LoginPageHttp();

    public LoginStepsHttp(CloseableHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    @Step
    public void navigateToLoginPage() {
        try {
            loginPageHttp.setPageContent(EntityUtils.toString(httpClient.execute(loginPageHttp
                    .navigateToPage()).getEntity()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Step
    public void login(String username, String password) {
        try {
            HttpResponse response = httpClient.execute(loginPageHttp.login(username, password));
            if(response.getStatusLine().getStatusCode() == 302) {
                httpClient.execute(new HttpGet(response.getFirstHeader("Location").getValue()));
            }
            Serenity.setSessionVariable("response").to(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Step
    public void checkIfUserWasLoggedIn() {
        assertTrue(((HttpResponse)Serenity.sessionVariableCalled("response")).getFirstHeader("Location").getValue()
                .contains("qa1.madison.com/customer/account/"));
    }

}
