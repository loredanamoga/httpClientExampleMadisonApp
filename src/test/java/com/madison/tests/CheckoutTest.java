package com.madison.tests;

import com.madison.data.Constants;
import com.madison.steps.CheckoutSteps;
import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import net.thucydides.junit.annotations.UseTestDataFrom;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Qualifier;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SerenityParameterizedRunner.class)
@UseTestDataFrom(value = Constants.CSV_PATH2)
public class CheckoutTest {

    @Before
    public void maxiPage() {
        driver.manage().window().maximize();
    }

    @Managed(uniqueSession = true)
    private WebDriver driver;

    @Steps
    private CheckoutSteps checkoutSteps;

    private String firstname, lastname, email, address, city, country, postalCode, telephone, username, password;

    @Qualifier
    public String qualifier() {
        return firstname;
    }

    @Test
    public void test01CheckoutGuestDDT() {
        checkoutSteps.navigateToHomePage();
        checkoutSteps.clickOnProduct();
        checkoutSteps.clickOnColor();
        checkoutSteps.clickOnSize();
        checkoutSteps.clickOnAddToCart();
        checkoutSteps.clickOnProceedToCheckout();
        checkoutSteps.clickOnContinueButton();
        checkoutSteps.billingInformationCheckout(firstname, lastname, email, address, city, country, postalCode, telephone);
        checkoutSteps.clickOnContinue();
        checkoutSteps.checkEmail("Please enter a valid email address. For example johndoe@domain.com.");
        checkoutSteps.checkShippingInformation("First Name");
    }

    @Test
    public void test02CheckoutLoginNewAddressTest() {
        String selectAddress = "New Address";

        checkoutSteps.navigateToHomePage();
        checkoutSteps.clickOnProduct();
        checkoutSteps.clickOnColor();
        checkoutSteps.clickOnSize();
        checkoutSteps.clickOnAddToCart();
        checkoutSteps.clickOnProceedToCheckout();
        checkoutSteps.loginFlow(username, password);
        checkoutSteps.selectAddress(selectAddress);
        checkoutSteps.clearFields();
        checkoutSteps.billingInformationCheckoutLogin(firstname, lastname, address, city, postalCode, country, telephone);
        checkoutSteps.clickOnContinue();
        checkoutSteps.checkShippingInformation("First Name");
    }

    @Test
    public void test03CheckoutLoginSameAddressTest() {
        checkoutSteps.navigateToHomePage();
        checkoutSteps.clickOnProduct();
        checkoutSteps.clickOnColor();
        checkoutSteps.clickOnSize();
        checkoutSteps.clickOnAddToCart();
        checkoutSteps.clickOnProceedToCheckout();
        checkoutSteps.loginFlow(username, password);
        checkoutSteps.clickOnContinue();
        checkoutSteps.checkShippingInformation("First Name");

    }

    @After
    public void tearDown() {
        checkoutSteps.navigateToCart();
        checkoutSteps.emptyCart();
    }
}
