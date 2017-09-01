package com.madison.tests;

import com.madison.data.Constants;
import com.madison.steps.AccountSteps;
import com.madison.steps.SearchSteps;
import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import net.thucydides.junit.annotations.UseTestDataFrom;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Qualifier;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@UseTestDataFrom(value = Constants.CSV_PATH2)
@RunWith(SerenityParameterizedRunner.class)
public class AccountTest {

    @Managed(uniqueSession = false)
    private WebDriver webDriver;

    @Steps
    private AccountSteps accountSteps;

    @Steps
    private SearchSteps searchSteps;

    private String firstName, lastName, username, password, changedfirstname, changedlastname, changedemail, telephone,
            city, address, state, postalcode;

    @Qualifier
    public String qualifier() {
        return firstName;
    }

    //This test will fail until the issue will be fixed
    @Test
    public void test01CreateUserAccountTest() {
        accountSteps.navigateToHomePage();
        accountSteps.navigateToMyAccount();
        accountSteps.clickOnCreateAnAccountButton();
        accountSteps.verifyFormInstructionsText();
        accountSteps.fillInRegisterForm(firstName, lastName, username, password, password);
        accountSteps.clickOnRegisterButton();
        accountSteps.checkIfRegistrationFailed(Constants.REGISTER_MESSAGE_CANNOT_CREATE_ACCOUNT);
    }

    //This test will fail until the issue will be fixed
    @Test
    public void test02EditUserContactInformation() {
        accountSteps.navigateToDashboardPage(username, password);
        accountSteps.clickToEditContactInformation();
        accountSteps.editAccountInformation(changedfirstname, changedlastname, changedemail, password, Constants.EDIT_ACCOUNT_INFORMATION_MESSAGE);
    }

    //This test will fail until the issue will be fixed
    @Test
    public void test03ChangeUserPassword() {
        accountSteps.navigateToDashboardPage(username, password);
        accountSteps.clickOnChangePassword();
        accountSteps.changePassword(password, password, password, Constants.EDIT_ACCOUNT_INFORMATION_MESSAGE);

    }

    @Test
    public void test04EditNewsletterDetails() {
        accountSteps.navigateToDashboardPage(username, password);
        accountSteps.clickOnEditNewsletterLink();
        accountSteps.subscribeToNewsletter(Constants.NEWSLETTER_SUBSCRIPTION_MESSAGE);
    }

    @Test
    public void test05EditBillingAddress() {
        accountSteps.navigateToDashboardPage(username, password);
        accountSteps.clickOnEditDefaultBillingAddressLink();
        accountSteps.fillInBillingAddressFields(telephone, address, city, postalcode, state);
    }

    @Test
    public void test06EditShippingAddress() {
        accountSteps.navigateToDashboardPage(username, password);
        accountSteps.clickOnEditDefaultShippingAddressLink();
        accountSteps.fillInShippingAddressFields(telephone, address, city, state, postalcode);
    }

    @Test
    public void test07checkProductsInWishlistFromMyAccountPage() {
        accountSteps.navigateToDashboardPage(username, password);
        searchSteps.searchTerm(Constants.SEARCHED_TERM);
        searchSteps.clickOnRandomProduct();
        accountSteps.addARandomProductToWishlist();
        accountSteps.checkIfProductWasAddedToWishlist();
    }

}


