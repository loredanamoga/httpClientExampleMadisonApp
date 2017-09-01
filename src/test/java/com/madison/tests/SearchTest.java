package com.madison.tests;

import com.madison.data.Constants;
import com.madison.steps.SearchSteps;
import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import net.thucydides.junit.annotations.UseTestDataFrom;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

@RunWith(SerenityParameterizedRunner.class)
@UseTestDataFrom(value = Constants.CSV_FILES_PATH + "searchTest.csv")
public class SearchTest {

    @Managed(uniqueSession = false)
    private WebDriver driver;

    @Steps
    private SearchSteps searchSteps;

    private String keyword;

    @Before
    public void maximizeWindow() {
        driver.manage().window().maximize();
        Constants.SEARCHED_WORD = keyword;
    }

    @Test
    public void searchSpecificProductInResultPages() {
        searchSteps.navigateToHomePage();
        searchSteps.searchTerm(Constants.SEARCHED_WORD);
        searchSteps.checkIfResultsPageIsEmpty();
        searchSteps.ckeckFirstLastRandomIfRelevant();

    }

    @Test
    public void testIfThereAreNoResults() {
        searchSteps.navigateToHomePage();
        searchSteps.searchTerm("hfkjushfkjudhasfkjhds");
        searchSteps.checkIfResultsPageIsEmpty();
    }

}
