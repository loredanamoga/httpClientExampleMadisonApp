package com.madison.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.WebElement;

@DefaultUrl("http://qa1.madison.com/customer/account/login/")
public class LoginPage extends PageObject {

    @FindBy(css = "[title=\"Create an Account\"]")
    private WebElementFacade createAnAccountButton;

    @FindBy(css = "#email")
    private WebElement inputEmail;

    @FindBy(css = "#pass")
    private WebElement inputPassword;

    @FindBy(css = "#send2")
    private WebElement loginButton;

    public void login(String username, String password) {
        inputEmail.sendKeys(username);
        inputPassword.sendKeys(password);
        loginButton.click();
    }

    public void clickOnCreateAnAccount() {
        clickOn(createAnAccountButton);
    }

}
