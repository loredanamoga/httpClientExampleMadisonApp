package com.emag.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class CartPage extends PageObject {

    @FindBy(css=".main-product-title")
    private List<WebElement> cartProducts;

    public List<WebElement> getProductsFromCart() {
        return cartProducts;
    }

    public Boolean isProductInCart(String productTitle) {
        for(WebElement cartProduct : cartProducts) {
            if(productTitle.equals(cartProduct.getText())) {
                return true;
            }
        }

        return false;
    }

}
