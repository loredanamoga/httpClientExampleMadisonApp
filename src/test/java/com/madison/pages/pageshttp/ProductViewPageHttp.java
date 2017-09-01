package com.madison.pages.pageshttp;

import net.serenitybdd.core.Serenity;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class ProductViewPageHttp {

    private Document pageContent;
    private Element addToWishlistLink;
    private Element addToCompareLink;
    private Element productTitle;
    private Element productShortDescription;
    private Element productDescription;
    private Element productColors;
    private Element clothesSizes;
    private Element shoesSizes;
    private Element productRequiredCheckbox;
    private Element productType;
    private Element color;
    private Element size;
    private Element cartButton;
    private Element reviewsTab;
    private Element leaveAReviewToProductLink;


    public void setPageContent(String content) {
        pageContent = Jsoup.parse(content);
    }

    public void clickOnColor() {}

    public void clickOnSize() {}

    public void clickOnAddToCart() {

    }

    public void clickOnAddToCompare() {

    }

    public void selectRandomProductColor() {

    }

    public void selectRandomProductSize() {

    }

    public void selectProductRequiredCheckbox() {

    }

    public void selectProductQuantity() {

    }

    public void setCertainOptionsToProduct(String qty) {

    }

    public void clickOnAddToWishlistLink() {

    }

    public void setProductTitleInSession() {
        Serenity.setSessionVariable("productTitle").to(productTitle.text());
    }

}
