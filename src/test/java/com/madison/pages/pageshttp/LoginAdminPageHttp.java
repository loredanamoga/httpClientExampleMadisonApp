package com.madison.pages.pageshttp;

import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.HashMap;

/**
 * Created by loredanamoga on 8/9/2017.
 */
public class LoginAdminPageHttp {

    private Document pageContent;

    public HttpUriRequest navigateToAdminPage() {
        return RequestBuilder.get().setUri("http://qa1.madison.com/admin/").build();
    }

    public HttpUriRequest loginAdmin(String username, String password) {
        String form_key = pageContent.select("[name=form_key]").attr("value");
        String url = "http://qa1.madison.com/admin";
        HashMap<String, String> postParams = new HashMap<String, String>();
        postParams.put("form_key", form_key);
        postParams.put("login[username]", username);
        postParams.put("dummy", "");
        postParams.put("login[password]", password);
        return HttpPostAndGetRequests.httpPostRequest(url, postParams);
    }


    public void setPageContent(String content) {
        pageContent = Jsoup.parse(content);
    }
}
