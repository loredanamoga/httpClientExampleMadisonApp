package com.madison.tests.httptests;

import com.madison.steps.httpsteps.AdminStepsHttp;
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

/**
 * Created by loredanamoga on 8/9/2017.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SerenityRunner.class)
public class AdminTestHttp {

    @Managed(uniqueSession = false)
    public WebDriver driver;

    private BasicCookieStore cookieStore = new BasicCookieStore();
    private CloseableHttpClient httpclient = HttpClients.custom().setDefaultCookieStore(cookieStore)
            .build();
    private AdminStepsHttp adminStepsHttp = new AdminStepsHttp(httpclient);

    @Test
    public void test01() {
        adminStepsHttp.navigateToLoginAdminPage();
        adminStepsHttp.loginAdmin("admin", "parola11");
        adminStepsHttp.navigateToManageProducts();
        adminStepsHttp.goToAddProduct();
        adminStepsHttp.goToContinueButton();
        adminStepsHttp.addProductSpecifications(ReadCSV.csvRead());
        adminStepsHttp.addProductSpecif(ReadCSV.csvRead());

    }

}
