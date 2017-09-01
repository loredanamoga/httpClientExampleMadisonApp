package com.madison.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.WebElement;

public class ShareWishlistPage extends PageObject {

    @FindBy(css = ".page-title h1")
    private WebElement pageTitle;

    public Boolean checkIfUserIsOnShareWishlistPage() {
        return pageTitle.getText().toLowerCase().equals("share your wishlist");
    }

}