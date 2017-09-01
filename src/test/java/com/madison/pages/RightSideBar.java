package com.madison.pages;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class RightSideBar extends PageObject {

    @FindBy(css = ".block-wishlist .product-name a")
    private List<WebElement> wishlistProducts;

    @FindBy(css = "#compare-items .item")
    private List<WebElement> compareProducts;

    @FindBy(css = "[title='Compare']")
    private WebElement compareButton;

    public Boolean checkIfLastAddedProductIsDisplayed() {
        for (WebElement product : wishlistProducts) {
            if (Serenity.sessionVariableCalled("productTitle").toString()
                    .toLowerCase().equals(product.getText().toLowerCase())) {
                return true;
            }
        }

        return false;
    }

    public Boolean checkByTitleIfProductIsInCompareList() {
        for (WebElement product : compareProducts) {
            if (product.findElement(By.cssSelector("p a")).getText().toLowerCase()
                    .equals(Serenity.sessionVariableCalled(("productTitle")).toString().toLowerCase())) {
                return true;
            }
        }

        return false;
    }

    public void clickOnCompareButton() {
        compareButton.click();
    }

}
