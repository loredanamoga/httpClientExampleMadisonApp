package com.madison.pages;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Header extends PageObject {

    @FindBy(css = "#search")
    private WebElement searchBar;

    @FindBy(css = ".search-button")
    private WebElement searchButton;

    @FindBy(css = ".skip-account")
    private WebElement accountLink;

    @FindBy(css = "[href='http://qa1.madison.com/wishlist/']")
    private WebElement myWishlistLink;

    @FindBy(css = "[href='http://qa1.madison.com/checkout/cart/']")
    private WebElement miniCart;

    @FindBy(css = ".links [href='http://qa1.madison.com/checkout/cart/']")
    private WebElement myCartLink;

    @FindBy(css = "[href='http://qa1.madison.com/customer/account/logout/']")
    private WebElement logoutLink;

    @FindBy(css = "#header-account li ")
    private List<WebElement> accountDropdownOptions;

    public void searchTerm(String keyword) {
        searchBar.sendKeys(keyword);
        Serenity.setSessionVariable("searchedTerm").to(keyword);
        searchButton.click();
    }

    public void navigateToWishlist() {
        accountLink.click();
        waitFor(myWishlistLink);
        myWishlistLink.click();
    }

    public void navigateToMiniCart() {
        accountLink.click();
        waitFor(miniCart);
        miniCart.click();
    }

    public void navigateToMyCart() {
        accountLink.click();
        waitFor(myCartLink);
        myCartLink.click();
    }

    public void logout() {
        accountLink.click();
        waitFor(logoutLink);
        logoutLink.click();
    }

    public Integer getWishlistItemsNumber() {
        accountLink.click();
        waitFor(myWishlistLink);
        Integer itemsNumber = Integer.parseInt(myWishlistLink.getText().split("\\(")[1]
                .split(" ")[0]);
        accountLink.click();

        return itemsNumber;
    }

    public void navigateToMyAccount() {
        accountLink.click();
        accountDropdownOptions.get(0).click();
    }

    public void setCartNumberInSession() {
        accountLink.click();
        Serenity.setSessionVariable("myCartLinkNumber").to(myCartLink.getText().split("\\(")[1].split(" ")[0]);
        Serenity.setSessionVariable("miniCartNumber").to(miniCart.findElement(By.cssSelector(".count")).getText());
    }

}
