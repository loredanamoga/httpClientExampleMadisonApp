package com.madison.pages.pageshttp;

import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class LoginPageHttp {

    private Document pageContent;

    public HttpUriRequest navigateToPage() {
        return RequestBuilder.get().setUri("http://qa1.madison.com/customer/account/login/").build();
    }

    public HttpUriRequest login(String username, String password) {
        String form_key = pageContent.select("[name=form_key]").attr("value");
        String action = pageContent.select("#login-form").attr("action");
        return RequestBuilder.post().setUri(action)
                .addParameter("form_key", form_key)
                .addParameter("login[username]", username)
                .addParameter("login[password]", password)
                .addParameter("send", "").build();
    }


    public void setPageContent(String content) {
        pageContent = Jsoup.parse(content);
    }
}
