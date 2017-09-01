package com.madison.pages;

import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by razvansidra on 7/26/2017.
 */
public class CheckoutMethodPage extends PageObject {

    @FindBy(css = "#onepage-guest-register-button")
    private WebElement guest;

    @FindBy(css = "#login-email")
    private WebElement usernameInput;

    @FindBy(css = "#login-password")
    private WebElement passwordInput;

    @FindBy(css = ".buttons-set .button:first-child")
    private WebElement loginButton;

    public void clickOnContinueButton() {
        guest.click();
    }

    public void inputUsername(String keyword) {
        usernameInput.sendKeys(keyword);
    }

    public void inputPassword(String keyword) {
        passwordInput.sendKeys(keyword);
    }

    public void clickOnLoginButton() {
        loginButton.click();
    }
}