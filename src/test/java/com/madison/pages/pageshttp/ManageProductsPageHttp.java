package com.madison.pages.pageshttp;

import org.apache.http.client.methods.HttpUriRequest;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.HashMap;

/**
 * Created by loredanamoga on 8/10/2017.
 */
public class ManageProductsPageHttp {

    private Document pageContent;
    private String url;

    public void setPageContent(String content) {
        pageContent = Jsoup.parse(content);
        url = pageContent.select(".scalable.add").attr("onclick")
                .split("'")[1];
    }

    public HttpUriRequest goToAddProduct() {
        return HttpPostAndGetRequests.httpGetRequest(url, new HashMap<>());
    }
}
