package com.madison.pages;

import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Random;

@DefaultUrl("http://qa1.madison.com/checkout/cart/")
public class ShoppingCartPage extends PageObject {

    @FindBy(css = "#shopping-cart-table tbody tr")
    private List<WebElement> cartProducts;

    @FindBy(css = "#empty_cart_button")
    private WebElement emptyCartLink;

    @FindBy(css = "ul[class='checkout-types top'] button>span>span")
    private WebElement proceedButton;

    @FindBy(css = ".discount-form")
    private WebElement discountForm;

    @FindBy(css = "#shopping-cart-totals-table")
    private WebElement cartTotals;

    public void emptyCartAllAtOnce() {
        try {
            emptyCartLink.click();
        } catch (Exception ex) {

        }
    }

    public void emptyCartOneByOne() {
        for (Integer i = cartProducts.size() - 1; i >= 0; i--) {
            cartProducts.get(i).findElement(By.cssSelector(".product-cart-remove a")).click();
        }
    }

    public Boolean checkIfCartProductsListIsEmpty() {
        return cartProducts.size() < 1;
    }

    public void clickOnProceedToCheckout() {
        proceedButton.click();
    }

    public Boolean checkIfProductIsInCart() {
        for (WebElement product : cartProducts) {
            if (product.findElement(By.cssSelector(".product-name a")).getText().toLowerCase()
                    .equals(Serenity.sessionVariableCalled("productTitle").toString().toLowerCase())) {
                return true;
            }
        }

        return false;
    }

    public void navigateToPreviousPage() {
        getDriver().navigate().back();
    }

    public Boolean checkIfProductQuantityIsUpdatedAfterAddingTheSameProduct() {
        for (WebElement product : cartProducts) {
            if (Serenity.sessionVariableCalled("productTitle").toString().toLowerCase()
                    .equals(product.findElement(By.cssSelector(".product-name a")).getText().toLowerCase())) {
                return 2 == Integer.parseInt(product.findElement(By.cssSelector(".qty")).getAttribute("value"));
            }
        }

        return false;
    }


    public void changeQuantityForCertainProduct(String qty, Integer productIndex) {
        cartProducts.get(productIndex).findElement(By.cssSelector(".qty")).clear();
        cartProducts.get(productIndex).findElement(By.cssSelector(".qty")).sendKeys(qty);
    }

    public void updateRandomProductQuantityFromCartAndStoreItsInformationInSession(String qty) {
        Integer productNr = Math.abs(new Random().nextInt(cartProducts.size()));
        changeQuantityForCertainProduct(qty, productNr);
        Serenity.setSessionVariable("randomProductTitle").to(cartProducts.get(productNr)
                .findElement(By.cssSelector(".product-name > a")).getText());
        Serenity.setSessionVariable("randomProductPrice").to(cartProducts.get(productNr)
                .findElement(By.cssSelector(".product-cart-price .price")).getText().split("€")[1]);
        Serenity.setSessionVariable("randomProductQuantity").to(cartProducts.get(productNr)
                .findElement(By.cssSelector(".qty")).getAttribute("value"));
        cartProducts.get(productNr).findElement(By.cssSelector(".btn-update")).click();
        Serenity.setSessionVariable("randomProductSubtotal").to(splitTotalPrice(cartProducts.get(productNr)
                .findElement(By.cssSelector(".product-cart-total .price")).getText()));
    }

    public String splitTotalPrice(String amount) {
        String total = "";
        for (String number : amount.split("€")[1].split(",")) {
            total += number;
        }
        return total;
    }

    public Boolean checkIfCartSubtotalIsCorrectAfterUpdatingQuantity() {
        return (Double.parseDouble(Serenity.sessionVariableCalled("randomProductPrice").toString()) *
                Double.parseDouble(Serenity.sessionVariableCalled("randomProductQuantity").toString())) ==
                Double.parseDouble(Serenity.sessionVariableCalled("randomProductSubtotal").toString());
    }

    public void applyCoupon(String code) {
        discountForm.findElement(By.cssSelector("#coupon_code")).clear();
        discountForm.findElement(By.cssSelector("#coupon_code")).sendKeys(code);
        discountForm.findElement(By.cssSelector("[title='Apply']")).click();
    }

    public Boolean checkIfCouponWasApplied(String type, String amount) {
        Double grandTotal = Double.parseDouble(splitTotalPrice(cartTotals.findElement(By
                .cssSelector("strong .price")).getText()));
        Double subTotal = Double.parseDouble(splitTotalPrice(cartTotals.findElement(By
                .cssSelector("tbody tr:first-of-type .price")).getText()));

        switch (type) {
            case "%":
                return grandTotal == subTotal - (Double.parseDouble(amount) / 100 * subTotal);
            case "-":
                return grandTotal == subTotal - Double.parseDouble(amount);
        }

        return false;
    }

    public Boolean checkIfHeaderNumberOfCartItemsIsCorrect() {
        Integer numberOfItems = 0;
        for (WebElement product : cartProducts) {
            numberOfItems += Integer.parseInt(product.findElement(By.cssSelector(".qty"))
                    .getAttribute("value"));
        }

        Integer miniCartNumber = Integer.parseInt(Serenity.sessionVariableCalled("miniCartNumber").toString());
        Integer myCartLinkNumber = Integer.parseInt(Serenity.sessionVariableCalled("myCartLinkNumber").toString());
        return (myCartLinkNumber.equals(numberOfItems) && miniCartNumber.equals(numberOfItems));
    }

    public Boolean checkIfSubTotalIsCorrect() {
        Double sum = 0.0;
        for (WebElement product : cartProducts) {
            sum += Double.parseDouble(splitTotalPrice(product.findElement(By
                    .cssSelector(".product-cart-total .price")).getText()));
        }

        return sum == Double.parseDouble(splitTotalPrice(cartTotals.findElement(By
                .cssSelector("tbody tr:first-of-type .price")).getText()));
    }

}

