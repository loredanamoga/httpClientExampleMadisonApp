package com.madison.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;

public class NewsletterPage extends PageObject {

    @FindBy(id = "subscription")
    private WebElementFacade newsletterSubscribtionCheckbox;

    @FindBy(css = "[title=\"Save\"]")
    private WebElementFacade saveButton;

    @FindBy(className = "success-msg")
    private WebElementFacade newsletterSubscribtionMessage;

    public void clickOnNewsletterSubscriptionCheckbox() {
        clickOn(newsletterSubscribtionCheckbox);
    }

    public void clickOnSaveButton() {
        clickOn(saveButton);
    }

    public boolean checkNewsletterSubscribtionMessage() {
        if (newsletterSubscribtionMessage.isDisplayed())
            return true;
        return false;
    }
}
