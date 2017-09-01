package com.madison.steps;

import com.madison.pages.*;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.StepGroup;
import net.thucydides.core.steps.ScenarioSteps;
import org.junit.Assert;

import static org.junit.Assert.assertTrue;

public class AccountSteps extends ScenarioSteps {

    private HomePage homePage;
    private LoginPage loginPage;
    private RegisterPage registerPage;
    private MyDashboardPage myDashboardPage;
    private AccountInformationPage accountInformationPage;
    private Header header;
    private NewsletterPage newsletterPage;
    private AddressBookPage addressBookPage;
    private ProductViewPage productViewPage;
    private WishlistPage wishlistPage;

    @Step
    public void navigateToHomePage() {
        homePage.open();
        homePage.getDriver().manage().window().maximize();
    }

    @Step
    public void navigateToMyAccount() {
        header.navigateToMyAccount();
    }

    @Step
    public void clickOnCreateAnAccountButton() {
        loginPage.clickOnCreateAnAccount();
    }

    @Step
    public void verifyFormInstructionsText() {
        registerPage.verifyFormInstructions();
    }

    @StepGroup
    public void fillInRegisterForm(String firstName, String lastName, String email, String password, String confirmedPassword) {

        registerPage.typeFirstName(firstName);
        registerPage.checkFirstNameFieldIsNotEmpty();

        registerPage.typeLastName(lastName);
        registerPage.checkLastNameFieldIsNotEmpty();

        registerPage.typeEmailAddress(email);
        registerPage.checkEmailAddressFieldIsNotEmpty();

        registerPage.typePassword(password);
        registerPage.checkPasswordFieldIsNotEmpty();

        registerPage.typeConfirmationPassword(confirmedPassword);
        registerPage.checkPasswordConfirmationFieldIsNotEmpty();

    }

    @Step
    public void clickOnRegisterButton() {
        registerPage.clickOnRegisterButton();
    }

    @Step
    public void checkIfRegistrationFailed(String message) {
        Assert.assertFalse(registerPage.getRegisterMessage().contentEquals(message));
    }

    @Step
    public void login(String username, String password) {
        loginPage.login(username, password);
    }


    @StepGroup
    public void navigateToDashboardPage(String username, String password) {
        navigateToHomePage();
        navigateToMyAccount();
        login(username, password);
    }

    @Step
    public void clickToEditContactInformation() {
        myDashboardPage.clickOnEditContactInformation();
    }

    @Step
    public void changeFirstName(String firstName) {
        accountInformationPage.changeFirstName(firstName);
    }

    @Step
    public void changeLastName(String lastName) {
        accountInformationPage.changeLastName(lastName);
    }

    @Step
    public void changeEmail(String email) {
        accountInformationPage.changeEmail(email);

    }

    @Step
    public void introduceCurrentPassword(String password) {
        accountInformationPage.inputCurrentPassword(password);
    }

    @Step
    public void clickOnSaveButton() {
        accountInformationPage.clickOnSaveButton();
    }

    @Step
    public void checkEditAccountInformationMessage(String message) {
        Assert.assertFalse(accountInformationPage.checkEditAccountInfoMessage().contentEquals(message));
    }

    @StepGroup
    public void editAccountInformation(String firstName, String lastName, String email, String currentPassword, String message) {
        changeFirstName(firstName);
        changeLastName(lastName);
        changeEmail(email);
        introduceCurrentPassword(currentPassword);
        clickOnSaveButton();
        checkEditAccountInformationMessage(message);
    }

    @Step
    public void clickOnChangePassword() {
        myDashboardPage.clickOnChangePasswordLink();
    }

    @Step
    public void typeNewPassword(String newPassword) {
        accountInformationPage.inputNewPassword(newPassword);
    }

    @Step
    void typeConfirmationPassword(String confirmationPassword) {
        accountInformationPage.inputConfirmationPassword(confirmationPassword);
    }

    @StepGroup
    public void changePassword(String newPassword, String confirmationPassword, String currentPassword, String message) {
        introduceCurrentPassword(currentPassword);
        typeNewPassword(newPassword);
        typeConfirmationPassword(confirmationPassword);
        clickOnSaveButton();
        checkEditAccountInformationMessage(message);
    }

    @Step
    public void clickOnEditNewsletterLink() {
        myDashboardPage.clickOnEditNewsletterDetails();
    }

    @Step
    public void clickOnNewsletterSubscriptionCheckbox() {
        newsletterPage.clickOnNewsletterSubscriptionCheckbox();
    }

    @Step
    public void clickOnSaveNewsletterChangesButton() {
        newsletterPage.clickOnSaveButton();
    }

    @Step
    public void checkNewsletterSubscritionMessage(String message) {
        assertTrue(newsletterPage.checkNewsletterSubscribtionMessage());
    }

    @Step
    public void subscribeToNewsletter(String message) {
        clickOnNewsletterSubscriptionCheckbox();
        clickOnSaveNewsletterChangesButton();
        checkNewsletterSubscritionMessage(message);
        getDriver().quit();

    }

    @Step
    public void clickOnEditDefaultBillingAddressLink() {
        myDashboardPage.clickOnEditDefaultBillingAddress();
    }

    @Step
    public void inputTelephone(String phone) {
        addressBookPage.typeTelephone(phone);
    }

    @Step
    public void inputAddress(String address) {
        addressBookPage.typeAddress(address);
    }

    @Step
    public void inputCity(String city) {
        addressBookPage.typeCity(city);
    }

    @Step
    public void chooseState() {
        addressBookPage.chooseState();
    }

    @Step
    public void inputZipCode(String zip) {
        addressBookPage.typeZipCode(zip);
    }

    @Step
    public void chooseCountry() {
        addressBookPage.chooseCountry();
    }

    @Step
    public void clickOnSaveAddressButton() {
        addressBookPage.clickOnSaveAddressButton();
    }

    @Step
    public void checkSavedChangedBillingAddressMessage() {
        assertTrue(addressBookPage.checkSavedChangedBillingAddressMessage());
    }

    @StepGroup
    public void fillInBillingAddressFields(String phone, String address, String city, String zip, String state) {
        inputTelephone(phone);
        inputAddress(address);
        inputCity(city);
        inputState(state);
        inputZipCode(zip);
        chooseCountry();
        clickOnSaveAddressButton();
        checkSavedChangedBillingAddressMessage();
    }

    @Step
    public void clickOnEditDefaultShippingAddressLink() {
        myDashboardPage.clickOnEditDefaultShippingAddress();
    }

    @Step
    public void inputState(String state) {
        addressBookPage.typeState(state);
    }

    @StepGroup
    public void fillInShippingAddressFields(String phone, String address, String city, String state, String zip) {
        inputTelephone(phone);
        inputAddress(address);
        inputCity(city);
        inputState(state);
        inputZipCode(zip);
        chooseCountry();
        clickOnSaveAddressButton();
        checkSavedChangedBillingAddressMessage();
    }

    @Step
    public void clickOnProductReviewsTab() {
        productViewPage.clickOnReviewsTab();
    }

    @Step
    public void clickToAddAReviewToAProduct() {
        productViewPage.clickOnAddAReviewLink();
    }

    @Step
    public void addARandomProductToWishlist() {
        productViewPage.addRandomProductToWishlist();
    }

    @Step
    public void checkIfProductWasAddedToWishlist() {
        assertTrue(wishlistPage.checkIfRandomProductWasAddedToWishlist());
    }
}
