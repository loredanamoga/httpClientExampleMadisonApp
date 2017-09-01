package com.madison.tests;

import com.madison.steps.WishlistSteps;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.junit.After;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SerenityRunner.class)
public class WishlistTest {

    @Managed(uniqueSession = true)
    private WebDriver driver;

    @Steps
    private WishlistSteps wishlistSteps;

    @Test
    public void test01IfWishlistPageWorks() {
        wishlistSteps.navigateToHomePage();
        wishlistSteps.navigateToWishlist();
        wishlistSteps.login("flm.marius@yahoo.com", "aurora1432");
        wishlistSteps.checkIfUserIsOnWishlistPage();
    }

    @Test
    public void test02IfWishlistLinkDisplaysCorrectNumberOfProducts() {
        test05IfProductIsAddedFromSearchedResultsPageWhileLoggedIn();
        wishlistSteps.logout();
        test05IfProductIsAddedFromSearchedResultsPageWhileLoggedIn();
        wishlistSteps.navigateToWishlist();
        wishlistSteps.checkIfWishlistLinkDisplaysCorrectNumberOfProducts();
    }

    @Test
    public void test03IfProductIsAddedFromSearchedResultsPageAfterLoggingIn() {
        wishlistSteps.navigateToHomePage();
        wishlistSteps.searchTerm("c");
        wishlistSteps.navigateToRandomResultsPage();
        wishlistSteps.addRandomProductToWishlist();
        wishlistSteps.login("flm.marius@yahoo.com", "aurora1432");
        wishlistSteps.checkByTitleIfProductIsInWishlist();
    }

    @Test
    public void test04IfProductIsAddedFromProductViewPageAfterLoggingIn() {
        wishlistSteps.navigateToHomePage();
        wishlistSteps.searchTerm("c");
        wishlistSteps.navigateToRandomResultsPage();
        wishlistSteps.clickOnRandomProduct();
        wishlistSteps.clickOnAddToWishlistLink();
        wishlistSteps.login("flm.marius@yahoo.com", "aurora1432");
        wishlistSteps.checkByTitleIfProductIsInWishlist();
    }

    @Test
    public void test05IfProductIsAddedFromSearchedResultsPageWhileLoggedIn() {
        wishlistSteps.navigateToLoginPage();
        wishlistSteps.login("flm.marius@yahoo.com", "aurora1432");
        wishlistSteps.searchTerm("c");
        wishlistSteps.navigateToRandomResultsPage();
        wishlistSteps.addRandomProductToWishlist();
        wishlistSteps.checkByTitleIfProductIsInWishlist();
    }

    @Test
    public void test06IfProductIsAddedFromProductViewPageWhileLoggedIn() {
        wishlistSteps.navigateToLoginPage();
        wishlistSteps.login("flm.marius@yahoo.com", "aurora1432");
        wishlistSteps.searchTerm("c");
        wishlistSteps.navigateToRandomResultsPage();
        wishlistSteps.clickOnRandomProduct();
        wishlistSteps.clickOnAddToWishlistLink();
        wishlistSteps.checkByTitleIfProductIsInWishlist();
    }

    @Test
    public void test07IfProductCanBeAddedMultipleTimesFromSearchedResultsPage() {
        test05IfProductIsAddedFromSearchedResultsPageWhileLoggedIn();
        String productTitle = Serenity.sessionVariableCalled("productTitle").toString();
        wishlistSteps.searchTerm(productTitle);
        wishlistSteps.addCertainProductToWishlist(productTitle);
        wishlistSteps.checkIfItemQuantityIsUpdatedAfterAddingTheSameProduct();
    }

    @Test
    public void test08IfProductCanBeAddedMultipleTimesFromProductViewPage() {
        test06IfProductIsAddedFromProductViewPageWhileLoggedIn();
        String productTitle = Serenity.sessionVariableCalled("productTitle").toString();
        wishlistSteps.searchTerm(productTitle);
        wishlistSteps.clickOnCertainProduct(productTitle);
        wishlistSteps.clickOnAddToWishlistLink();
        wishlistSteps.checkIfItemQuantityIsUpdatedAfterAddingTheSameProduct();
    }

    @Test
    public void test09IfWishlistLastAddedItemsAreCorrectlyDisplayed() {
        test05IfProductIsAddedFromSearchedResultsPageWhileLoggedIn();
        wishlistSteps.searchTerm("c");
        wishlistSteps.checkIfLastAddedProductIsDisplayed();
    }

    @Test
    public void test10IfProductsCanBeRemovedFromWishlist() {
        test05IfProductIsAddedFromSearchedResultsPageWhileLoggedIn();
        wishlistSteps.clearWishlist();
        wishlistSteps.checkIfProductsWereRemoved();
    }

    @Test
    public void test11IfOnlyOneProductIsUpdatedInWishlistWhenItsUpdateButtonIsClicked() {
        test05IfProductIsAddedFromSearchedResultsPageWhileLoggedIn();
        wishlistSteps.logout();
        test05IfProductIsAddedFromSearchedResultsPageWhileLoggedIn();
        wishlistSteps.checkIfOnlyOneProductIsUpdatedInWishlistWhenItsUpdateButtonIsClicked("www", "2");
    }

    @Test
    public void test12IfAllProductsAreUpdatedInWishlistWhenUpdateAllButtonIsClicked() {
        test05IfProductIsAddedFromSearchedResultsPageWhileLoggedIn();
        wishlistSteps.logout();
        test05IfProductIsAddedFromSearchedResultsPageWhileLoggedIn();
        wishlistSteps.checkIfAllProductsAreUpdatedInWishlistWhenUpdateAllButtonIsClicked("www", "2");
    }

    @Test
    public void test13IfUserCanShareWishlist() {
        test05IfProductIsAddedFromSearchedResultsPageWhileLoggedIn();
        wishlistSteps.navigateToWishlist();
        wishlistSteps.checkIfUserCanShareWishlist();
    }

    //TODO Add to wishlist from Compare page

    @After
    public void teardown() {
        wishlistSteps.navigateToWishlist();
        wishlistSteps.clearWishlist();
    }
}