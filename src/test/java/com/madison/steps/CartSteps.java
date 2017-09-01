package com.madison.steps;

import com.madison.pages.*;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.StepGroup;

import static org.junit.Assert.assertTrue;

/**
 * Created by loredanamoga on 8/1/2017.
 */
public class CartSteps {

    private HomePage homePage;
    private Header header;
    private SearchResultsPage searchResultsPage;
    private ShoppingCartPage shoppingCartPage;
    private WishlistPage wishlistPage;
    private ProductViewPage productViewPage;
    private LoginPage loginPage;


    @Step
    public void navigateToHomePage() {
        homePage.open();
    }

    @Step
    public void navigateToRandomResultsPage() {
        searchResultsPage.navigateToRandomResultsPage();
    }

    @Step
    public void navigateToPreviousPage() {
        shoppingCartPage.navigateToPreviousPage();
    }

    @Step
    public void navigateToMyCart() {
        header.navigateToMyCart();
    }

    @Step
    public void navigateToMiniCart() {
        header.navigateToMiniCart();
    }

    @Step
    public void navigateToLoginPage() {
        loginPage.open();
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
    public void searchTerm(String keyword) {
        header.searchTerm(keyword);
    }


    @Step
    public void clickOnRandomProduct() {
        searchResultsPage.clickOnRandomProduct();
    }

    @Step
    public void selectRandomProductColor() {
        productViewPage.selectRandomProductColor();
    }

    @Step
    public void selectRandomProductSize() {
        productViewPage.selectRandomProductSize();
    }

    @Step
    public void selectProductRequiredCheckbox() {
        productViewPage.selectProductRequiredCheckbox();
    }

    @Step
    public void selectProductQuantity(String qty) {
        productViewPage.selectProductQuantity(qty);
    }


    @StepGroup
    public void selectColorAndSizeAndCheckboxAndQuantityOfProduct(String qty) {
        selectRandomProductColor();
        selectRandomProductSize();
        selectProductRequiredCheckbox();
        selectProductQuantity(qty);
    }

    @Step
    public void clickOnAddProductToCart() {
        productViewPage.clickOnAddToCart();
    }

    @Step
    public void checkByTitleIfProductIsInCart() {
        assertTrue(shoppingCartPage.checkIfProductIsInCart());
    }

    @Step
    public void setCertainOptionsToProduct(String qty) {
        productViewPage.setCertainOptionsToProduct(qty);
    }

    @Step
    public void checkIfItemQuantityIsUpdatedAfterAddingTheSameProduct() {
        assertTrue(shoppingCartPage.checkIfProductQuantityIsUpdatedAfterAddingTheSameProduct());
    }

    @Step
    public void checkIfCartTotalIsCorrectAfterUpdatingQuantity(String qty) {
        shoppingCartPage.updateRandomProductQuantityFromCartAndStoreItsInformationInSession(qty);
        assertTrue(shoppingCartPage.checkIfCartSubtotalIsCorrectAfterUpdatingQuantity());
    }

    @Step
    public void emptyCart() {
        shoppingCartPage.emptyCartAllAtOnce();
    }

    @StepGroup
    public void searchAndAddRandomProductToCart(String keyword, String qty) {
        searchTerm(keyword);
        navigateToRandomResultsPage();
        clickOnRandomProduct();
        selectColorAndSizeAndCheckboxAndQuantityOfProduct(qty);
        clickOnAddProductToCart();
    }

    @Step
    public void checkIfAnyoneCanEmptyCartAllAtOnce() {
        shoppingCartPage.emptyCartAllAtOnce();
        assertTrue(shoppingCartPage.checkIfCartProductsListIsEmpty());
    }

    @Step
    public void checkIfAnyoneCanEmptyCartOneByOne() {
        shoppingCartPage.emptyCartOneByOne();
        assertTrue(shoppingCartPage.checkIfCartProductsListIsEmpty());
    }

    @Step
    public void applyCouponAndCheckIfItWasApplied(String code, String type, String amount) {
        shoppingCartPage.applyCoupon(code);
        assertTrue(shoppingCartPage.checkIfCouponWasApplied(type, amount));
    }

    @Step
    public void checkIfHeaderCartItemsNumberAreCorrect() {
        header.setCartNumberInSession();
        assertTrue(shoppingCartPage.checkIfHeaderNumberOfCartItemsIsCorrect());
    }

    @Step
    public void checkIfSubTotalIsCorrect() {
        assertTrue(shoppingCartPage.checkIfSubTotalIsCorrect());
    }
}
