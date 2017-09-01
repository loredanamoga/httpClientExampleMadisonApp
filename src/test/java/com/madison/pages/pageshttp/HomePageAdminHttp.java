package com.madison.pages.pageshttp;

import org.apache.http.client.methods.HttpUriRequest;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.HashMap;

/**
 * Created by loredanamoga on 8/9/2017.
 */
public class HomePageAdminHttp {

    private Document pageContent;
    private String url;

    public void setPageContent(String content) {
        pageContent = Jsoup.parse(content);
        url = pageContent.select("li:nth-of-type(3) ul li[class ='  level1']:nth-of-type(1) a").attr("href");
        //#nav> li:nth-of-type(3) ul:nth-of-type(1) li[class ='  level1']:nth-of-type(1) a

    }



    public HttpUriRequest goToManageProducts() {
        return HttpPostAndGetRequests.httpGetRequest(url, new HashMap<>());

    }

}
