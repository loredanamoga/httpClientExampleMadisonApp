package com.madison.pages;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;

@DefaultUrl("http://qa1.madison.com/wishlist/index/index/")
public class WishlistPage extends PageObject {

    @FindBy(css = "[id^='item']")
    private List<WebElement> wishlistProducts;

    @FindBy(css = "[name='save_and_share']")
    private WebElement shareWishlistButton;

    public Boolean checkIfOnCorrectPage() {
        return (getDriver().getCurrentUrl().contains("http://qa1.madison.com/wishlist/index/index/"));
    }

    public Boolean checkIfProductIsInWishlist() {
        for (WebElement product : wishlistProducts) {
            if (product.findElement(By.cssSelector(".product-name a")).getText().toLowerCase()
                    .equals(Serenity.sessionVariableCalled("productTitle").toString().toLowerCase())) {
                return true;
            }
        }

        return false;
    }

    public Boolean checkIfProductQuantityIsUpdatedAfterAddingTheSameProduct() {
        for (WebElement product : wishlistProducts) {
            if (Serenity.sessionVariableCalled("productTitle").toString().toLowerCase()
                    .equals(product.findElement(By.cssSelector(".product-name a")).getText().toLowerCase())) {
                return 1 < Integer.parseInt(product.findElement(By.cssSelector(".qty")).getAttribute("value"));
            }
        }

        return false;
    }

    public void clearWishlist() {
        for (int i = wishlistProducts.size() - 1; i >= 0; i--) {
            wishlistProducts.get(i).findElement(By.cssSelector(".btn-remove")).click();
            getAlert().accept();
        }
    }

    public Boolean checkIfWishlistIsEmpty() {
        return wishlistProducts.size() == 0;
    }

    public Boolean checkIfEveryProductCommentAndQuantityWasUpdated(String comment, String qty) {
        for (WebElement product : wishlistProducts) {
            if (!product.findElement(By.cssSelector("[title='Comment']")).getAttribute("value").equals(comment)) {
                return false;
            }
            try {
                if (!product.findElement(By.cssSelector(".qty")).getAttribute("value").equals(qty)) {
                    return false;
                }
            } catch (Exception ex) {
                System.out.println("Quantity field not present on this product");
            }
        }

        return true;
    }

    public void changeCommentAndQuantityForAllProductsAndUpdateWishlistFromRandomProductUpdateButton(String comment, String qty) {
        for (WebElement product : wishlistProducts) {
            product.findElement(By.cssSelector("[title='Comment']")).clear();
            product.findElement(By.cssSelector("[title='Comment']")).sendKeys(comment);
            try {
                product.findElement(By.cssSelector(".qty")).clear();
                product.findElement(By.cssSelector(".qty")).sendKeys(qty);
            } catch (Exception ex) {

            }
        }

        wishlistProducts.get(Math.abs(new Random().nextInt(wishlistProducts.size())))
                .findElement(By.cssSelector(".btn-update")).click();
    }

    public Integer getProductsNumber() {
        return wishlistProducts.size();
    }

    public void clickOnShareWishlistButton() {
        shareWishlistButton.click();
    }

    public Boolean checkIfRandomProductWasAddedToWishlist() {
        for (WebElement product : wishlistProducts) {
            if (Serenity.sessionVariableCalled("productTitle").toString().toLowerCase()
                    .equals(product.findElement(By.cssSelector("h3")).getText().toLowerCase())) {
                return true;
            }
        }
        return false;
    }

}
