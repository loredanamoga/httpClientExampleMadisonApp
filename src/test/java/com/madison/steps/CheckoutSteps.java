package com.madison.steps;

import com.madison.pages.*;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.StepGroup;
import org.junit.Assert;

public class CheckoutSteps {
    private HomePage homePage;
    private ProductViewPage productViewPage;
    private ShoppingCartPage shoppingCartPage;
    private CheckoutMethodPage checkoutMethodPage;
    private BillingInformationPage billingInformationPage;

    @Step
    public void navigateToHomePage() {
        homePage.open();
    }

    @Step
    public void clickOnProduct() {
        homePage.clickOnProduct();
    }

    @Step
    public void clickOnColor() {
        productViewPage.clickOnColor();
    }

    @Step
    public void clickOnSize() {
        productViewPage.clickOnSize();
    }

    @Step
    public void clickOnAddToCart() {
        productViewPage.clickOnAddToCart();
    }

    @Step
    public void clickOnProceedToCheckout() {
        shoppingCartPage.clickOnProceedToCheckout();
    }

    @Step
    public void clickOnContinueButton() {
        checkoutMethodPage.clickOnContinueButton();
    }

    @Step
    public void inputUsername(String keyword) {
        checkoutMethodPage.inputUsername(keyword);
    }

    @Step
    public void inputPassword(String keyword) {
        checkoutMethodPage.inputPassword(keyword);
    }

    @Step
    public void clickOnLoginButton() {
        checkoutMethodPage.clickOnLoginButton();
    }

    @StepGroup
    public void loginFlow(String username, String password) {
        inputUsername(username);
        inputPassword(password);
        clickOnLoginButton();
    }

    @Step
    public void selectAddress(String word) {
        billingInformationPage.selectAddress(word);
    }

    @Step
    public void inputFirstName(String word) {
        billingInformationPage.inputFirstName(word);
    }

    @Step
    public void inputLastName(String word) {
        billingInformationPage.inputLastName(word);
    }

    @Step
    public void inputEmailAddress(String word) {
        billingInformationPage.inputEmailAddress(word);
    }

    @Step
    public void inputAddress(String word) {
        billingInformationPage.inputAddress(word);
    }

    @Step
    public void inputCity(String word) {
        billingInformationPage.inputCity(word);
    }

    @Step
    public void inputPostalCode(String word) {
        billingInformationPage.inputPostalCode(word);
    }

    @Step
    public void selectCountry(String word) {
        billingInformationPage.selectCountry(word);
    }

    @Step
    public void inputTelephone(String word) {
        billingInformationPage.inputTelephone(word);
    }

    @StepGroup
    public void billingInformationCheckout(String firstname, String lastname, String emailaddress,
                                           String address, String city, String postalcode,
                                           String country, String telephone) {
        inputFirstName(firstname);
        inputLastName(lastname);
        inputEmailAddress(emailaddress);
        inputAddress(address);
        inputCity(city);
        inputPostalCode(postalcode);
        selectCountry(country);
        inputTelephone(telephone);
    }

    @StepGroup
    public void billingInformationCheckoutLogin(String firstname, String lastname,
                                                String address, String city, String postalcode,
                                                String country, String telephone) {
        inputFirstName(firstname);
        inputLastName(lastname);
        inputAddress(address);
        inputCity(city);
        inputPostalCode(postalcode);
        selectCountry(country);
        inputTelephone(telephone);
    }

    @Step
    public void clickOnContinue() {
        billingInformationPage.clickOnContinue();
    }

    @Step
    public void checkEmail(String expectedMessage) {
        String email = billingInformationPage.checkEmail();
        Assert.assertFalse("The email is invalid!", expectedMessage.contentEquals(email));
    }

    @Step
    public void clearFields() {
        billingInformationPage.clearInputFirstName();
        billingInformationPage.clearInputLastName();
        billingInformationPage.clearInputAddress();
        billingInformationPage.clearInputCity();
        billingInformationPage.clearInputPostalCode();
        billingInformationPage.clearInputTelephone();
    }

    @Step
    public void emptyCart() {
        shoppingCartPage.emptyCartAllAtOnce();
    }

    @Step
    public void navigateToCart() {
        shoppingCartPage.open();
    }

    @Step
    public void checkShippingInformation(String expectedLabel) {
        String label = billingInformationPage.checkShippingInformation();
        Assert.assertTrue("Shipping information does not working.", expectedLabel.contentEquals(label));
    }
}