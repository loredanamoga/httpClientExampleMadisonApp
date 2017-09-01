package com.madison.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Footer extends PageObject {

    @FindBy(css = ".block-subscribe")
    private WebElement newsletter;

    @FindBy(css = ".messages > li")
    private WebElement subscriptionMessage;

    public void subscribeToNewsletter(String email) {
        newsletter.findElement(By.cssSelector("#newsletter")).sendKeys(email);
        newsletter.findElement(By.cssSelector("button")).click();
    }

    public String getSubscriptionMessage() {
        return subscriptionMessage.getText();
    }
}