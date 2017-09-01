package com.madison.tests;

import com.madison.data.Constants;
import com.madison.steps.NewsletterSteps;
import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import net.thucydides.junit.annotations.UseTestDataFrom;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SerenityParameterizedRunner.class)
@UseTestDataFrom(value = Constants.NEWSLETTERCSV_PATH)
public class NewsletterTest {

    @Managed(uniqueSession = true)
    public WebDriver driver;

    private String email;

    @Steps
    private NewsletterSteps newsletterSteps;

    @Test
    public void test01IfUserCanSubscribe() {
        newsletterSteps.navigateToHomePage();
        newsletterSteps.subscribeToNewsletter(email);
        newsletterSteps.checkIfSubscriptionWasSuccessful();
    }

}
