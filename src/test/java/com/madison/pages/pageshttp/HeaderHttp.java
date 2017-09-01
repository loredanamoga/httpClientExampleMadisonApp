package com.madison.pages.pageshttp;

import net.serenitybdd.core.Serenity;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HeaderHttp {

    private Document pageContent;
    private Elements links;
    private Element miniCart;

    public void setPageContent(String content) {
        pageContent = Jsoup.parse(content);
        links = pageContent.select(".links li");
        miniCart = pageContent.select(".skip-link.skip-cart").get(0);
    }

    public HttpUriRequest searchTerm(String keyword) {
        String action = pageContent.select("#search_mini_form").attr("action");
        return RequestBuilder.get().setUri(action).addParameter("q", keyword).build();
    }

    public HttpUriRequest navigateToWishlist() {
        return RequestBuilder.get().setUri(links.get(1).select("a").attr("href")).build();
    }

//    public HttpUriRequest navigateToMiniCart() {
//        return RequestBuilder.get().setUri(miniCart.attr("href")).build();
//    }

    public HttpUriRequest navigateToMyCart() {
        return RequestBuilder.get().setUri(links.get(2).select("a").attr("href")).build();
    }

    public HttpUriRequest logout() {
        return RequestBuilder.get().setUri(links.get(5).select("a").attr("href")).build();
    }

    public Integer getWishlistItemsNumber() {
        return Integer.parseInt(links.get(1).select("a").text().split("\\(")[1]
                .split(" ")[0]);
    }

    public HttpUriRequest navigateToMyAccount() {
        return RequestBuilder.get().setUri(links.get(0).select("a").attr("href")).build();
    }

    public void setCartNumberInSession() {
        Serenity.setSessionVariable("myCartLinkNumber").to(links.get(2).select("a").text().split("\\(")[1].split(" ")[0]);
        Serenity.setSessionVariable("miniCartNumber").to(miniCart.select(".count").text());
    }
}
