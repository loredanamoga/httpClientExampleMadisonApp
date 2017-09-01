package com.emag.tests;

import com.emag.steps.AddProductToCartSteps;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

@RunWith(SerenityRunner.class)
public class MigEmagTest {

    @Managed(uniqueSession = true)
    private WebDriver driver;

    @Steps
    private AddProductToCartSteps addProductToCartSteps;



    @Test
    public void emagSearchTest() {
        addProductToCartSteps.navigateToHomePage();
        addProductToCartSteps.searchTermAndNavigateToRandomSearchPage("iphone 7");
        addProductToCartSteps.clickOnRandomProduct();
        addProductToCartSteps.getProductTitleAndClickOnAddToCartButton();
        addProductToCartSteps.clickOnShowCartDetails();
        addProductToCartSteps.checkByTitleIfProductWasAddedInCart();
    }

}
