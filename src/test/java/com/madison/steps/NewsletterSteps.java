package com.madison.steps;

import com.madison.pages.Footer;
import com.madison.pages.HomePage;
import net.thucydides.core.steps.ScenarioSteps;

import static org.junit.Assert.assertTrue;

public class NewsletterSteps extends ScenarioSteps {

    private Footer footer;
    private HomePage homePage;

    public void navigateToHomePage() {
        homePage.open();
    }

    public void subscribeToNewsletter(String email) {
        footer.subscribeToNewsletter(email);
    }

    public void checkIfSubscriptionWasSuccessful() {
        assertTrue(footer.getSubscriptionMessage().equals("Thank you for your subscription."));
    }

}
