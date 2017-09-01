package com.madison.tests;

import com.madison.steps.AdvancedSearchSteps;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SerenityRunner.class)
public class AdvancedSearchTest {

    @Before
    public void maxiPage() {
        driver.manage().window().maximize();
    }

    @Managed(uniqueSession = true)
    public WebDriver driver;

    @Steps
    private AdvancedSearchSteps advancedSearchSteps;


    @Test
    public void test01CompleteAdvancedSearch() {
        advancedSearchSteps.navigateToHomePage();
        advancedSearchSteps.clickOnAdvancedSearch();
        advancedSearchSteps.inputPrice("100");
        advancedSearchSteps.inputPriceTo("500");
        advancedSearchSteps.findRandomProductWithAdvancedSearch();

    }

//    @Test
//    public void test02DeselctAdvancedSearch(){
//        advancedSearchSteps.navigateToHomePage();
//        advancedSearchSteps.clickOnAdvancedSearch();
//        advancedSearchSteps.selectColor(13);
//        advancedSearchSteps.selectSize(7);
//      //  advancedSearchSteps.selectSize(8);
//      //  advancedSearchSteps.selectSize(14);
//        advancedSearchSteps.selectGender(1);
//        advancedSearchSteps.clickOnSearchButton();
//        advancedSearchSteps.clickOnModifyLink();
//        advancedSearchSteps.selectSize(7);
//        advancedSearchSteps.clickOnSearchButton();
//    }
}
