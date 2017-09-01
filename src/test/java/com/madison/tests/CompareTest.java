package com.madison.tests;

import com.madison.steps.CompareSteps;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SerenityRunner.class)
public class CompareTest {

    @Managed(uniqueSession = true)
    private WebDriver driver;

    @Steps
    private CompareSteps compareSteps;

    @Test
    public void test01IfProductIsAddedFromSearchResultsPageAsGuest() {
        compareSteps.navigateToHomePage();
        compareSteps.searchTerm("c");
        compareSteps.navigateToRandomResultsPage();
        compareSteps.addRandomProductToCompare();
        compareSteps.checkByTitleIfProductIsInCompare();
    }

    @Test
    public void test02IfProductIsAddedFromProductViewPageAsGuest() {
        compareSteps.navigateToHomePage();
        compareSteps.searchTerm("c");
        compareSteps.navigateToRandomResultsPage();
        compareSteps.clickOnRandomProduct();
        compareSteps.clickOnProductViewPageAddToCompare();
        compareSteps.searchTerm("c");
        compareSteps.checkByTitleIfProductIsInCompare();
    }

    @Test
    public void test03IfProductIsAddedFromSearchResultsPageWhileLoggedIn() {
        compareSteps.navigateToLoginPage();
        compareSteps.login("flm.marius@yahoo.com", "aurora1432");
        test01IfProductIsAddedFromSearchResultsPageAsGuest();
    }

    @Test
    public void test04IfProductIsAddedFromProductViewPageWhileLoggedIn() {
        compareSteps.navigateToLoginPage();
        compareSteps.login("flm.marius@yahoo.com", "aurora1432");
        test02IfProductIsAddedFromProductViewPageAsGuest();
    }

    @Test
    public void test05IfProductAddedFromSearchResultsPageExistsAfterLogin() {
        test01IfProductIsAddedFromSearchResultsPageAsGuest();
        compareSteps.navigateToLoginPage();
        compareSteps.login("flm.marius@yahoo.com", "aurora1432");
        compareSteps.searchTerm("c");
        compareSteps.checkByTitleIfProductIsInCompare();
    }

    @Test
    public void test06IfProductAddedFromProductViewPageExistsAfterLogin() {
        test02IfProductIsAddedFromProductViewPageAsGuest();
        compareSteps.navigateToLoginPage();
        compareSteps.login("flm.marius@yahoo.com", "aurora1432");
        compareSteps.searchTerm("c");
        compareSteps.checkByTitleIfProductIsInCompare();
    }

}
