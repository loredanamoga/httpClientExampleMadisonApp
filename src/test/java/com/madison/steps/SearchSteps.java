package com.madison.steps;

import com.madison.pages.Header;
import com.madison.pages.HomePage;
import com.madison.pages.ProductViewPage;
import com.madison.pages.SearchResultsPage;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.StepGroup;

import static org.junit.Assert.assertTrue;

public class SearchSteps {

    private Header header;
    private ProductViewPage productViewPage;
    private SearchResultsPage searchResultsPage;
    private HomePage homePage;

    @Step
    public void navigateToHomePage() {
        homePage.open();
    }

    @Step
    public void searchTerm(String keyword) {
        header.searchTerm(keyword);
    }

    @Step
    public Boolean checkIfFirstProductIsRelevant() {
        searchResultsPage.clickOnFirstProduct();
        return productViewPage.verifyProductIfContainsSpecificWordAndGoBack();
    }

    @Step
    public Boolean checkIfLastProductIsRelevant() {
        searchResultsPage.navigateToLastResultsPage();
        searchResultsPage.clickOnLastProduct();
        return productViewPage.verifyProductIfContainsSpecificWordAndGoBack();
    }

    @Step
    public void clickOnRandomProduct() {
        searchResultsPage.clickOnRandomProduct();
    }

    @Step
    public Boolean checkIfRandomProductIsRelevant() {
        searchResultsPage.navigateToRandomResultsPage();
        searchResultsPage.clickOnRandomProduct();
        return productViewPage.verifyProductIfContainsSpecificWordAndGoBack();
    }

    @StepGroup
    public void ckeckFirstLastRandomIfRelevant() {
        assertTrue("The searched word doesn't appear in the first, last and random product from result list",
                checkIfFirstProductIsRelevant() && checkIfLastProductIsRelevant()
                        && checkIfRandomProductIsRelevant());
    }

    @Step
    public void checkIfResultsPageIsEmpty() {
        assertTrue(searchResultsPage.checkIfProductsListIsEmpty());
    }

}
