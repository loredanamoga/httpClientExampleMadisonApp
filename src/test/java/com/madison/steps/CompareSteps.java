package com.madison.steps;

import com.madison.pages.*;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.StepGroup;
import net.thucydides.core.steps.ScenarioSteps;

import static org.junit.Assert.assertTrue;

public class CompareSteps extends ScenarioSteps {

    private LoginPage loginPage;
    private ComparePage comparePage;
    private RightSideBar rightSideBar;
    private SearchResultsPage searchResultsPage;
    private Header header;
    private HomePage homePage;
    private ProductViewPage productViewPage;

    private String parentHandle;

    @Step
    public void navigateToHomePage() {
        homePage.open();
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
    public void navigateToRandomResultsPage() {
        searchResultsPage.navigateToRandomResultsPage();
    }

    @Step
    public void addRandomProductToCompare() {
        searchResultsPage.addRandomProductToCompare();
    }

    @Step
    public void checkByTitleIfProductIsInCompareList() {
        assertTrue(rightSideBar.checkByTitleIfProductIsInCompareList());
    }

    @Step
    public void checkByTitleIfProductIsInComparePage() {
        assertTrue(comparePage.checkByTitleIfProductIsInComparePage());
    }

    @Step
    public void clickOnRandomProduct() {
        searchResultsPage.clickOnRandomProduct();
    }

    @Step
    public void searchTerm(String searchTerm) {
        header.searchTerm(searchTerm);
    }

    @Step
    public void clickOnCompareButton() {
        rightSideBar.clickOnCompareButton();
    }

    @Step
    public void clickOnProductViewPageAddToCompare() {
        productViewPage.clickOnAddToCompare();
    }

    @Step
    public void switchToCompareWindow() {
        parentHandle = getDriver().getWindowHandle();

        for (String window : getDriver().getWindowHandles()) {
            getDriver().switchTo().window(window);
        }
    }

    @Step
    public void switchToMainWindow() {
        getDriver().close();
        getDriver().switchTo().window(parentHandle);
    }

    @StepGroup
    public void checkByTitleIfProductIsInCompare() {
        checkByTitleIfProductIsInCompareList();
        clickOnCompareButton();
        switchToCompareWindow();
        checkByTitleIfProductIsInComparePage();
        switchToMainWindow();
    }

}
