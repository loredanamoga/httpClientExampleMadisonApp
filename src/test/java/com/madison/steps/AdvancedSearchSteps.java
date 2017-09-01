package com.madison.steps;

import com.madison.pages.AdvancedSearchPage;
import com.madison.pages.AdvancedSearchResultsPage;
import com.madison.pages.HomePage;
import com.madison.pages.ProductViewPage;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertTrue;


public class AdvancedSearchSteps extends ScenarioSteps {
    private HomePage homePage;
    private AdvancedSearchPage advancedSearchPage;
    private AdvancedSearchResultsPage advancedSearchResultsPage;
    private ProductViewPage productViewPage;

    @Step
    public void navigateToHomePage() {
        homePage.open();
    }

    @Step
    public void clickOnAdvancedSearch() {
        homePage.clickOnAdvancedSearch();
    }

    @Step
    public void inputName(String keyword) {
        advancedSearchPage.inputName(keyword);
    }

    @Step
    public void clickOnSearchButton() {
        advancedSearchPage.clickOnSearchButton();
    }

    @Step
    public void checkIfContainsWord() {
        Assert.assertTrue("Cannot find the product by name.", advancedSearchResultsPage.checkIfProductIsInSearch());
    }

    @Step
    public void checkIfResultsPageIsEmpty() {
        Assert.assertTrue("Name field is not empty.", advancedSearchPage.checkIfProductsListIsEmpty());
    }

    @Step
    public void inputPrice(String price) {
        advancedSearchPage.inputPrice(price);
    }

    @Step
    public void inputPriceTo(String priceTo) {
        advancedSearchPage.inputPriceTo(priceTo);
    }

    @Step
    public void clickOnModifyLink() {
        advancedSearchResultsPage.clickOnModifySearchLink();
    }

    @Step
    public void findRandomProductWithAdvancedSearch() {
        List<String> selectedColors = new ArrayList<>();
        List<String> selectedSizes = new ArrayList<>();
        List<String> selectedGenders = new ArrayList<>();

        while (advancedSearchResultsPage.checkIfProductsListIsEmpty()) {
            advancedSearchPage.selectColor();
            selectedColors.add(Serenity.sessionVariableCalled("randomColor").toString().toLowerCase());

            advancedSearchPage.selectSize();
            selectedSizes.add(Serenity.sessionVariableCalled("randomSize").toString().toLowerCase());

            advancedSearchPage.selectGender();
            selectedGenders.add(Serenity.sessionVariableCalled("randomGender").toString().toLowerCase());

            clickOnSearchButton();
            try {
                advancedSearchResultsPage.clickOnRandomProduct();
                break;
            } catch (Exception ex) {
                getDriver().navigate().back();
            }
        }


        assertTrue(productViewPage.checkIfProductContainsCertainColors(selectedColors) &&
                productViewPage.checkIfProductContainsCertainSizes(selectedSizes));
    }
}