package com.madison.pages;


import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MyDashboardPage extends PageObject {

    @FindBy(css = ".box-title h3+a")
    private List<WebElement> editOptions;

    @FindBy(css = ".block-content ul li")
    private List<WebElement> accountMenu;

    @FindBy(css = ".box-content p a")
    private WebElementFacade changePasswordLink;

    public void clickOnEditContactInformation() {
        clickOn(editOptions.get(0));
    }

    public void clickOnChangePasswordLink() {
        clickOn(changePasswordLink);
    }

    public void clickOnEditNewsletterDetails() {
        clickOn(editOptions.get(1));
    }

    public void clickOnEditDefaultBillingAddress() {
        clickOn(editOptions.get(2));
    }

    public void clickOnEditDefaultShippingAddress() {
        clickOn(editOptions.get(3));
    }

}
