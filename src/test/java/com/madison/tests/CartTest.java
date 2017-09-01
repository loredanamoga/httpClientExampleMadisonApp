package com.madison.tests;

import com.madison.steps.CartSteps;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;

/**
 * Created by loredanamoga on 8/1/2017.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SerenityRunner.class)
public class CartTest {

    @Before
    public void maxiPage() {
        driver.manage().window().maximize();
    }

    @Managed(uniqueSession = true)
    public WebDriver driver;

    @Steps
    private CartSteps cartSteps;


    @Test
    public void test01IfProductIsAddedFromProductViewPage() {
        cartSteps.navigateToHomePage();
        cartSteps.searchAndAddRandomProductToCart("c", "2");
        cartSteps.checkByTitleIfProductIsInCart();
    }

    @Test
    public void test02IfProductISAddedFromProductViewPageWhenLoggedIn() {
        cartSteps.navigateToLoginPage();
        cartSteps.login("loredana_mg@yahoo.co.uk", "qwerty");
        cartSteps.searchAndAddRandomProductToCart("c", "2");
        cartSteps.checkByTitleIfProductIsInCart();
        cartSteps.logout();
    }

    @Test
    public void test03IfCartDisplaysCorrectProductQuantity() {
        cartSteps.navigateToHomePage();
        cartSteps.searchAndAddRandomProductToCart("c", "2");
        cartSteps.navigateToPreviousPage();
        cartSteps.setCertainOptionsToProduct("2");
        cartSteps.clickOnAddProductToCart();
        cartSteps.checkIfItemQuantityIsUpdatedAfterAddingTheSameProduct();
    }

    @Test
    public void test04IfCartDisplaysCorrectProductQuantityWhenLoggedIn() {
        cartSteps.navigateToLoginPage();
        cartSteps.login("loredana_mg@yahoo.co.uk", "qwerty");
        cartSteps.searchAndAddRandomProductToCart("c", "2");
        cartSteps.navigateToPreviousPage();
        cartSteps.setCertainOptionsToProduct("2");
        cartSteps.clickOnAddProductToCart();
        cartSteps.checkIfItemQuantityIsUpdatedAfterAddingTheSameProduct();
        cartSteps.logout();
    }

    @Test
    public void test05IfCartDisplaysCorrectTotalAfterUpdatingQuantity() {
        cartSteps.navigateToHomePage();
        cartSteps.searchAndAddRandomProductToCart("c", "2");
        cartSteps.searchAndAddRandomProductToCart("c", "2");
        cartSteps.checkIfCartTotalIsCorrectAfterUpdatingQuantity("8");
    }

    @Test
    public void test06IfCartDisplaysCorrectTotalAfterUpdatingQuantityWhileLoggedIn() {
        cartSteps.navigateToLoginPage();
        cartSteps.login("loredana_mg@yahoo.co.uk", "qwerty");
        cartSteps.searchAndAddRandomProductToCart("c", "2");
        cartSteps.searchAndAddRandomProductToCart("c", "2");
        cartSteps.checkIfCartTotalIsCorrectAfterUpdatingQuantity("8");
    }

    @Test
    public void test07IfCanEmptyCartAllAtOnce() {
        cartSteps.navigateToHomePage();
        cartSteps.searchAndAddRandomProductToCart("c", "2");
        cartSteps.searchAndAddRandomProductToCart("c", "2");
        cartSteps.checkIfAnyoneCanEmptyCartAllAtOnce();
    }

    @Test
    public void test08IfCanEmptyCartAllAtOnceWhileLoggedIn() {
        cartSteps.navigateToLoginPage();
        cartSteps.login("loredana_mg@yahoo.co.uk", "qwerty");
        cartSteps.searchAndAddRandomProductToCart("c", "2");
        cartSteps.searchAndAddRandomProductToCart("c", "2");
        cartSteps.checkIfAnyoneCanEmptyCartAllAtOnce();
        cartSteps.logout();
    }

    @Test
    public void test09IfCanEmptyCartOneByOne() {
        cartSteps.navigateToHomePage();
        cartSteps.searchAndAddRandomProductToCart("c", "2");
        cartSteps.searchAndAddRandomProductToCart("c", "2");
        cartSteps.checkIfAnyoneCanEmptyCartOneByOne();
    }

    @Test
    public void test10IfCanEmptyCartOneByOneWhileLoggedIn() {
        cartSteps.navigateToLoginPage();
        cartSteps.login("loredana_mg@yahoo.co.uk", "qwerty");
        cartSteps.searchAndAddRandomProductToCart("c", "2");
        cartSteps.searchAndAddRandomProductToCart("c", "2");
        cartSteps.checkIfAnyoneCanEmptyCartOneByOne();
        cartSteps.logout();
    }

    @Test
    public void test11CheckIfCouponWasAppliedWhileLoggedIn() {
        cartSteps.navigateToLoginPage();
        cartSteps.login("loredana_mg@yahoo.co.uk", "qwerty");
        cartSteps.searchAndAddRandomProductToCart("c", "2");
        cartSteps.searchAndAddRandomProductToCart("c", "2");
        cartSteps.navigateToMyCart();
        cartSteps.applyCouponAndCheckIfItWasApplied("RTL25OFF", "%", "25");
    }

    @Test
    public void test12IfHeaderCartItemsNumberAreCorrectWhileLoggedIn() {
        cartSteps.navigateToLoginPage();
        cartSteps.login("loredana_mg@yahoo.co.uk", "qwerty");
        cartSteps.searchAndAddRandomProductToCart("c", "2");
        cartSteps.searchAndAddRandomProductToCart("c", "2");
        cartSteps.navigateToMyCart();
        cartSteps.checkIfHeaderCartItemsNumberAreCorrect();
    }

    @Test
    public void test13IfCartSubTotalIsCorrect() {
        cartSteps.navigateToHomePage();
        cartSteps.searchAndAddRandomProductToCart("c", "2");
        cartSteps.searchAndAddRandomProductToCart("c", "2");
        cartSteps.navigateToMyCart();
        cartSteps.checkIfSubTotalIsCorrect();
    }

    @Test
    public void test14IfCartSubTotalIsCorrectWhileLoggedIn() {
        cartSteps.navigateToLoginPage();
        cartSteps.login("loredana_mg@yahoo.co.uk", "qwerty");
        cartSteps.searchAndAddRandomProductToCart("c", "2");
        cartSteps.searchAndAddRandomProductToCart("c", "2");
        cartSteps.navigateToMyCart();
        cartSteps.checkIfSubTotalIsCorrect();
    }

    @After
    public void teardown() {
        cartSteps.navigateToMiniCart();
        cartSteps.emptyCart();
    }
}
