package com.madison.pages.pageshttp;

import org.apache.http.client.methods.HttpUriRequest;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.HashMap;

/**
 * Created by loredanamoga on 8/10/2017.
 */
public class NewProductPageHttp {

    private Document pageContent;
    private String splittedLink;
    private String url;

    public void setPageContent(String content) {
        pageContent = Jsoup.parse(content);
        splittedLink = pageContent.select("[title=\"Continue\"].scalable.save").attr("onclick")
                .split("'")[1];
        url = splittedLink.replace("{{type}}", "simple").replace("{{attribute_set}}", "11");



    }
    public HttpUriRequest goToContinueButton() {
        return HttpPostAndGetRequests.httpGetRequest(url, new HashMap<>());
    }


}
