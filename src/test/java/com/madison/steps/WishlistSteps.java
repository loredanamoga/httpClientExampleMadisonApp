package com.madison.steps;

import com.madison.pages.*;
import net.thucydides.core.annotations.Step;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class WishlistSteps {

    private Header header;
    private HomePage homePage;
    private LoginPage loginPage;
    private WishlistPage wishlistPage;
    private SearchResultsPage searchResultsPage;
    private ProductViewPage productViewPage;
    private RightSideBar rightSideBar;
    private ShareWishlistPage shareWishlistPage;

    @Step
    public void navigateToHomePage() {
        homePage.open();
    }

    @Step
    public void navigateToLoginPage() {
        loginPage.open();
    }

    @Step
    public void navigateToWishlist() {
        header.navigateToWishlist();
    }

    @Step
    public void searchTerm(String keyword) {
        header.searchTerm(keyword);
    }

    @Step
    public void login(String username, String password) {
        loginPage.login(username, password);
    }

    @Step
    public void logout() {
        header.logout();
    }

    @Step
    public void checkIfUserIsOnWishlistPage() {
        assertTrue(wishlistPage.checkIfOnCorrectPage());
    }

    @Step
    public void navigateToRandomResultsPage() {
        searchResultsPage.navigateToRandomResultsPage();
    }

    @Step
    public void clickOnRandomProduct() {
        searchResultsPage.clickOnRandomProduct();
    }

    @Step
    public void addRandomProductToWishlist() {
        searchResultsPage.addRandomProductToWishlist();
    }

    @Step
    public void checkByTitleIfProductIsInWishlist() {
        assertTrue(wishlistPage.checkIfProductIsInWishlist());
    }

    @Step
    public void clickOnAddToWishlistLink() {
        productViewPage.clickOnAddToWishlistLink();
    }

    @Step
    public void addCertainProductToWishlist(String productTitle) {
        searchResultsPage.addCertainProductToWishlist(productTitle);
    }

    @Step
    public void clickOnCertainProduct(String productTitle) {
        searchResultsPage.clickOnCertainProduct(productTitle);
    }

    @Step
    public void checkIfItemQuantityIsUpdatedAfterAddingTheSameProduct() {
        assertTrue(wishlistPage.checkIfProductQuantityIsUpdatedAfterAddingTheSameProduct());
    }

    @Step
    public void checkIfLastAddedProductIsDisplayed() {
        assertTrue(rightSideBar.checkIfLastAddedProductIsDisplayed());
    }

    @Step
    public void clearWishlist() {
        wishlistPage.clearWishlist();
    }

    @Step
    public void checkIfProductsWereRemoved() {
        assertTrue(wishlistPage.checkIfWishlistIsEmpty());
    }

    @Step
    public void checkIfOnlyOneProductIsUpdatedInWishlistWhenItsUpdateButtonIsClicked(String comment, String qty) {
        wishlistPage.changeCommentAndQuantityForAllProductsAndUpdateWishlistFromRandomProductUpdateButton(comment, qty);
        assertTrue(!wishlistPage.checkIfEveryProductCommentAndQuantityWasUpdated(comment, qty));
    }

    @Step
    public void checkIfAllProductsAreUpdatedInWishlistWhenUpdateAllButtonIsClicked(String comment, String qty) {
        wishlistPage.changeCommentAndQuantityForAllProductsAndUpdateWishlistFromRandomProductUpdateButton(comment, qty);
        assertTrue(wishlistPage.checkIfEveryProductCommentAndQuantityWasUpdated(comment, qty));
    }

    @Step
    public void checkIfWishlistLinkDisplaysCorrectNumberOfProducts() {
        assertEquals(header.getWishlistItemsNumber(), wishlistPage.getProductsNumber());
    }

    @Step
    public void checkIfUserCanShareWishlist() {
        wishlistPage.clickOnShareWishlistButton();
        assertTrue(shareWishlistPage.checkIfUserIsOnShareWishlistPage());
    }
}
