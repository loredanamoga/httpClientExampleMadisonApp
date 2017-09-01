package com.madison.tests;

import com.madison.data.Constants;
import com.madison.steps.AdvancedSearchSteps;
import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import net.thucydides.junit.annotations.UseTestDataFrom;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Qualifier;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SerenityParameterizedRunner.class)
@UseTestDataFrom(value = Constants.CSV_FILES_PATH)
//@RunWith(SerenityRunner.class)
public class AdvancedSearchTestDDT {

    @Before
    public void maxiPage() {
        driver.manage().window().maximize();
    }

    @Managed(uniqueSession = false)
    public WebDriver driver;

    @Steps
    public AdvancedSearchSteps advancedSearchSteps;

    String keyword;

    @Qualifier
    public String qualifier() {
        return keyword;
    }

    @Test
    public void test01AdvancedSearchName() {
        advancedSearchSteps.navigateToHomePage();
        advancedSearchSteps.clickOnAdvancedSearch();
        advancedSearchSteps.inputName(keyword);
        advancedSearchSteps.clickOnSearchButton();
        advancedSearchSteps.checkIfContainsWord();
    }

    @Test
    public void test02EmptyNameAdvancedSearch() {
        advancedSearchSteps.navigateToHomePage();
        advancedSearchSteps.clickOnAdvancedSearch();
        advancedSearchSteps.inputName(keyword);
        advancedSearchSteps.clickOnSearchButton();
        advancedSearchSteps.checkIfResultsPageIsEmpty();
    }
}