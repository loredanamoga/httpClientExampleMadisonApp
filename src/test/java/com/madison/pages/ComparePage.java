package com.madison.pages;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

@DefaultUrl("http://qa1.madison.com/catalog/product_compare/index/")
public class ComparePage extends PageObject {

    @FindBy(css = ".compare-table .product-shop-row.top td")
    private List<WebElement> compareProducts;

    public Boolean checkByTitleIfProductIsInComparePage() {
        for (WebElement product : compareProducts) {
            if (product.findElement(By.cssSelector(".product-name a")).getText().toLowerCase()
                    .equals(Serenity.sessionVariableCalled(("productTitle")).toString().toLowerCase())) {
                return true;
            }
        }

        return false;
    }
}
