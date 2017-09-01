package com.madison.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.WebElement;

@DefaultUrl("http://qa1.madison.com")
public class HomePage extends PageObject {

    @FindBy(css = "li[class='item last']:first-child")
    private WebElement product;

    @FindBy(css = ".footer .links ul:nth-of-type(1) li:nth-of-type(2) a:first-child")
    private WebElement contactUs;

    @FindBy(css = ".footer .links .last a:first-child")
    private WebElement advancedSearch;

    public void clickOnProduct() {
        product.click();
    }

    public void clickOnContactUs() {
        contactUs.click();
    }

    public void clickOnAdvancedSearch() {
        advancedSearch.click();
    }
}