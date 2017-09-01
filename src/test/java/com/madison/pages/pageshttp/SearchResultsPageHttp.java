package com.madison.pages.pageshttp;

import net.serenitybdd.core.Serenity;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.Random;

public class SearchResultsPageHttp {

    private Document pageContent;
    private Elements products;

    public void setPageContent(String content) {
        pageContent = Jsoup.parse(content);
        products = pageContent.select(".products-grid .item.last");
    }

    public HttpUriRequest clickOnProduct(Integer productIndex) {
        return RequestBuilder.get().setUri(products.get(productIndex).select("a")
                .attr("href")).build();
    }

    public HttpUriRequest clickOnFirstProduct() {
        return clickOnProduct(0);
    }

    public HttpUriRequest clickOnLastProduct() {
        return clickOnProduct(products.size() - 1);
    }

    public HttpUriRequest clickOnRandomProduct() {
        Integer productNr = Math.abs(new Random().nextInt(products.size()));
        setProductTitleInSession(products.get(productNr).select(".product-name > a").attr("title"));
        return clickOnProduct(productNr);
    }

    public HttpUriRequest clickOnCertainProduct(String productTitle) {
        for (int i = 0; i < products.size(); i++) {
            if (productTitle.toLowerCase().equals(products.get(i).select(".product-name > a").attr("title")
                    .toLowerCase())) {
                return clickOnProduct(i);
            }
        }
        return null;
    }

    public void setAmountOfProductsInSession() {
        try {
            Serenity.setSessionVariable("amountOfProducts").to(pageContent.select(".amount--has-pages")
                    .get(0).text().split(" ")[2]);
        } catch (Exception ex) {
            Serenity.setSessionVariable("amountOfProducts").to(pageContent.select(".amount--no-pages strong")
                    .get(0).text().split(" ")[0]);
        }
    }

    public void setLimitPerPageInSession() {
        Serenity.setSessionVariable("limitPerPage").to(pageContent.select(".limiter option[selected='selected']")
                .text().replace(" ", ""));

    }

    public String getProductToNavigateTo() {
        String[] split = Serenity.sessionVariableCalled("searchedTerm").toString().split(" ");
        String searchedTerm = "";
        for (String word : split) {
            searchedTerm += word;
            if (!word.equals(split[split.length - 1])) {
                searchedTerm += "+";
            }
        }

        return searchedTerm;
    }

    public void navigateToLastResultsPage() {
        if(getNumberOfResultsPages() > 1) {
            navigateToResultsPage(getNumberOfResultsPages());
        }
    }

    public void navigateToRandomResultsPage() {
        navigateToResultsPage(Math.abs(new Random().nextInt(getNumberOfResultsPages() + 1)));
    }

    public HttpUriRequest navigateToResultsPage(Integer pageNo) {
        return RequestBuilder.get().setUri("http://qa1.madison.com/catalogsearch/result/index/?p="
                + pageNo + "&q=" + getProductToNavigateTo()).build();

    }

    public Integer getNumberOfResultsPages() {
        setAmountOfProductsInSession();
        setLimitPerPageInSession();
        Integer productNumber = Integer.parseInt(Serenity.sessionVariableCalled("amountOfProducts").toString());
        Integer limitNumber = Integer.parseInt(Serenity.sessionVariableCalled("limitPerPage").toString());

        if (productNumber % limitNumber != 0) {
            return productNumber / limitNumber + 1;
        }

        return productNumber / limitNumber;
    }

    public void setProductTitleInSession(String title) {
        Serenity.setSessionVariable("productTitle").to(title);
    }

    public void setProductURLInSession(String url) {
        Serenity.setSessionVariable("productURL").to(url);
    }

    public HttpUriRequest addRandomProductToWishlist() {
        Integer productNr = Math.abs(new Random().nextInt(products.size()));
        setProductTitleInSession(products.get(productNr).select(".product-name > a").attr("title"));
        setProductURLInSession(products.get(productNr).select(".product-name > a").attr("href"));
        return RequestBuilder.get().setUri(products.get(productNr).select(".link-wishlist").attr("href")).build();
    }

    public HttpUriRequest addCertainProductToWishlist(String productTitle) {
        for (int i = 0; i < products.size(); i++) {
            if (productTitle.toLowerCase().equals(products.get(i).select(".product-name > a").attr("title")
                    .toLowerCase())) {
                return RequestBuilder.get().setUri(products.get(i).select(".link-wishlist").attr("href")).build();
            }
        }
        return null;
    }

    public Boolean checkIfProductListIsEmpty() {
        return products.size() < 1;
    }

    public HttpUriRequest addRandomProductToCompare() {
        Integer productNr = Math.abs(new Random().nextInt(products.size()));
        setProductTitleInSession(products.get(productNr).select(".product-name > a").attr("title"));
        setProductURLInSession(products.get(productNr).select(".product-name > a").attr("href"));
        return RequestBuilder.get().setUri(products.get(productNr).select(".link-compare").attr("href")).build();

    }
}
