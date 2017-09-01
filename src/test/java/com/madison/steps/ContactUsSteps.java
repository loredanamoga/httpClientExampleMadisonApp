package com.madison.steps;

import com.madison.pages.ContactUsPage;
import com.madison.pages.HomePage;
import net.thucydides.core.annotations.Step;

import static org.junit.Assert.assertTrue;

public class ContactUsSteps {
    private HomePage homePage;
    private ContactUsPage contactUsPage;

    @Step
    public void navigateToHomePage() {
        homePage.open();
    }

    @Step
    public void clickOnContactUs() {
        homePage.clickOnContactUs();
    }

    @Step
    public void inputName(String keyword) {
        contactUsPage.inputName(keyword);
    }

    @Step
    public void inputEmail(String keyword) {
        contactUsPage.inputEmail(keyword);
    }

    @Step
    public void inputComment(String keyword) {
        contactUsPage.inputComment(keyword);
    }

    @Step
    public void clickOnSubmitButton() {
        contactUsPage.clickOnSubmitButton();
    }

    @Step
    public void checkContactSumbitMessage(String expectedMessage) {
        String message = contactUsPage.checkContactSubmit();
        assertTrue("The contact form was not send.", expectedMessage.contentEquals(message));
    }
}