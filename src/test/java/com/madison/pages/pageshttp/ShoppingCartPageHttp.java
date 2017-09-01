package com.madison.pages.pageshttp;

import net.serenitybdd.core.Serenity;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * Created by loredanamoga on 8/8/2017.
 */
public class ShoppingCartPageHttp {

    private Document pageContent;
    private Elements cartProducts;

    public void setPageContent(String content) {
        pageContent = Jsoup.parse(content);
        cartProducts = pageContent.select("#shopping-cart-table tbody tr");
    }

//    public HttpUriRequest emptyCartAllAtOnce() {
//        return RequestBuilder.get().setUri(pageContent.select("#empty_cart_button")).build();
//    }

    public HttpUriRequest emptyCartOneByOne() {
        for (int i = cartProducts.size() - 1; i >= 0; i--) {
            return RequestBuilder.get().setUri(cartProducts.get(i).select(".product-cart-remove a").attr("href")).build();
        }
        return null;
    }

    public Boolean checkIFCartProductsListIsEmpty() {
        return cartProducts.size() < 1;

    }

    public void clickOnProceedToCheckout() {

    }

    public Boolean checkIfProductIsInCart() {
        for (int i = 0; i < cartProducts.size(); i++) {
            if (cartProducts.get(i).select(".product-name a").attr("href").toLowerCase()
                    .equals(Serenity.setSessionVariable("productTitle").toString().toLowerCase())) {
                return true;
            }

        }
        return false;
    }

    public void navigateToPreviousPage() {


    }

    public Boolean checkIfProductQuantityIsUpdatedAfterAddingTheSameProduct() {
        for (int i = 0; i < cartProducts.size(); i++) {
            if (Serenity.sessionVariableCalled("productTitle").toString().toLowerCase()
                    .equals(cartProducts.get(i).select(".product-name a").attr("href").toLowerCase())) {
                return 2 == Integer.parseInt(cartProducts.get(i).select(".qty").attr("value"));
            }
        }
        return false;
    }

    public HttpUriRequest changeQuantityForCertainProduct(String quantity, Integer productIndex) {
        String action = cartProducts.get(productIndex).select(".qty").attr("value");
        //firstly you should clear the field...
        return RequestBuilder.post().setUri(action).addParameter("cart[3217][qty]", quantity).build();

    }

    public void updateRandomProductQuantityFromCartAndStoreItsInformationInSession() {

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
                Double.parseDouble(Serenity.sessionVariableCalled("randomProductQuantity").toString()) ==
                Double.parseDouble(Serenity.sessionVariableCalled("randomProductSubtotal").toString()));
    }

    public void applyCupon(String code) {

    }

    public boolean checkIfCuponWasApplied() {
        return false;
    }

    public Boolean checkIfHeaderNumberOfCartItemsIsCorrect() {
        return false;
    }

    public Boolean checkIfSubtotalIsCorrect() {
        return false;
    }
}
