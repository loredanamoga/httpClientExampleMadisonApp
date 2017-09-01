package com.madison.pages;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;

public class AdvancedSearchResultsPage extends PageObject {

    @FindBy(css = ".advanced-search-summary a")
    private WebElementFacade modify;

    @FindBy(css = ".products-grid .item.last")
    List<WebElement> products;


    public void clickOnModifySearchLink() {
        waitFor(modify);
        modify.click();
    }

    public Boolean checkIfProductIsInSearch() {
        for (WebElement product : products) {
            if (product.findElement(By.cssSelector(".product-name a")).getText().toLowerCase()
                    .contains(Serenity.sessionVariableCalled("inputName").toString().toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    public void clickOnRandomProduct() {
        products.get(new Random().nextInt(products.size())).click();
    }

    public Boolean checkIfProductsListIsEmpty() {
        try {
            return products.size() < 1;
        } catch (Exception ex) {

        }

        return false;
    }

}
