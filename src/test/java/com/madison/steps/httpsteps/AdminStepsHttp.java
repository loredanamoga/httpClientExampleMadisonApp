package com.madison.steps.httpsteps;

import com.madison.pages.pageshttp.*;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import org.apache.http.HttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.HashMap;


/**
 * Created by loredanamoga on 8/9/2017.
 */
public class AdminStepsHttp extends ScenarioSteps {

    private CloseableHttpClient httpClient;
    private LoginAdminPageHttp loginAdminPageHttp = new LoginAdminPageHttp();
    private HomePageAdminHttp homePageAdminHttp = new HomePageAdminHttp();
    private ManageProductsPageHttp manageProductsPageHttp = new ManageProductsPageHttp();
    private NewProductPageHttp newProductPageHttp = new NewProductPageHttp();
    private AddProductPageHttp addProductPageHttp = new AddProductPageHttp();

    public AdminStepsHttp(CloseableHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    @Step
    public void navigateToLoginAdminPage() {
        try {

            Serenity.setSessionVariable("httpResponse").to(httpClient.execute(loginAdminPageHttp
                    .navigateToAdminPage()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Step
    public void loginAdmin(String username, String password) {
        try {
            loginAdminPageHttp.setPageContent(EntityUtils.toString(((HttpResponse) Serenity
                    .sessionVariableCalled("httpResponse")).getEntity()));
            HttpResponse httpResponse = httpClient.execute(loginAdminPageHttp.loginAdmin(username, password));
            if (httpResponse.getStatusLine().getStatusCode() == 302) {
                httpResponse = httpClient.execute(HttpPostAndGetRequests.httpGetRequest(httpResponse
                        .getFirstHeader("Location").getValue(), new HashMap<>()));

            }
            Serenity.setSessionVariable("httpResponse").to(httpResponse);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Step
    public void navigateToManageProducts() {
        try {
            homePageAdminHttp.setPageContent(EntityUtils.toString(((HttpResponse) Serenity
                    .sessionVariableCalled("httpResponse")).getEntity()));
            HttpResponse response = httpClient.execute(homePageAdminHttp.goToManageProducts());
            Serenity.setSessionVariable("httpResponse").to(response);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Step
    public void goToAddProduct() {
        try {
            manageProductsPageHttp.setPageContent(EntityUtils.toString(((HttpResponse) Serenity
                    .sessionVariableCalled("httpResponse")).getEntity()));
            Serenity.setSessionVariable("httpResponse").to(httpClient.execute(manageProductsPageHttp.goToAddProduct()));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Step
    public void goToContinueButton() {
        try {
            newProductPageHttp.setPageContent(EntityUtils.toString(((HttpResponse) Serenity
                    .sessionVariableCalled("httpResponse")).getEntity()));
            Serenity.setSessionVariable("httpResponse").to(httpClient.execute(newProductPageHttp.goToContinueButton()));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Step
    public void addProductSpecifications(HashMap<String, String> params) {
        try {
            addProductPageHttp.setPageContent(EntityUtils.toString(((HttpResponse)Serenity.sessionVariableCalled("httpResponse")).getEntity()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            HttpResponse httpResponse = httpClient.execute(addProductPageHttp.addProductSpecifications(params));
            Serenity.setSessionVariable("httpResponse").to(httpResponse);
            System.out.println(((HttpResponse) Serenity.sessionVariableCalled("httpResponse")).getStatusLine());
            EntityUtils.consume(httpResponse.getEntity());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Step
    public void addProductSpecif(HashMap<String, String> params) {

        try {
            //second request
            HttpResponse httpResponse = httpClient.execute(addProductPageHttp.addProductSpecif(params));
            Serenity.setSessionVariable("httpResponse").to(httpResponse);
//            System.out.println((EntityUtils.toString(((HttpResponse) Serenity
//                    .sessionVariableCalled("httpResponse")).getEntity())).contains("class=\"scalable save\""));
            System.out.println(((HttpResponse) Serenity.sessionVariableCalled("httpResponse")).getFirstHeader("Location").getValue());
//            System.out.println("//////////////////////////////////////////" +(EntityUtils.toString(((HttpResponse) Serenity
//                   .sessionVariableCalled("httpResponse")).getEntity())));
//            System.out.println(((HttpResponse) Serenity.sessionVariableCalled("httpResponse")).getStatusLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
