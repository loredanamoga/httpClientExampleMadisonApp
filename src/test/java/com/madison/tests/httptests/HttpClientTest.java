package com.madison.tests.httptests;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.fluent.Request;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.*;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import org.openqa.selenium.WebDriver;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SerenityRunner.class)
public class HttpClientTest {

    @Managed(uniqueSession = false)
    public WebDriver driver;

    @Test
    public void main() {
        try {
            //System.out.println(Request.Get("http://qa1.madison.com").execute().returnContent());
            CookieStore httpCookieStore = new BasicCookieStore();
            HttpClientBuilder builder = HttpClientBuilder.create().setDefaultCookieStore(httpCookieStore);
            String form_key;
            CloseableHttpClient httpclient = builder.setRedirectStrategy(new LaxRedirectStrategy()).build(); //HttpClients.createDefault();
            HttpGet httpGet = new HttpGet("http://qa1.madison.com/customer/account/login/");
            CloseableHttpResponse response1 = httpclient.execute(httpGet);
            try {
                System.out.println(response1.getStatusLine());
                HttpEntity entity1 = response1.getEntity();
                Serenity.setSessionVariable("response").to(response1);
                form_key = EntityUtils.toString(((HttpResponse)Serenity.sessionVariableCalled("response"))
                        .getEntity()).split("<input name=\"form_key\" type=\"hidden\" value=\"")[1]
                        .split("\"")[0];
                EntityUtils.consume(entity1);
            } finally {
                response1.close();
            }


            HttpPost httpPost = new HttpPost("http://qa1.madison.com/customer/account/loginPost/");
            List<NameValuePair> nvps = new ArrayList<>();
            nvps.add(new BasicNameValuePair("form_key", form_key));
            nvps.add(new BasicNameValuePair("login[username]", "flm.marius@yahoo.com"));
            nvps.add(new BasicNameValuePair("login[password]", "aurora1432"));
            nvps.add(new BasicNameValuePair("send", ""));
            httpPost.setEntity(new UrlEncodedFormEntity(nvps));
            CloseableHttpResponse response2 = httpclient.execute(httpPost);
            try {
                System.out.println(response2.getStatusLine());
                HttpEntity entity2 = response2.getEntity();
                EntityUtils.consume(entity2);
            } finally {
                response2.close();
            }

            List<Cookie> cookies = httpCookieStore.getCookies();

            System.out.println("HttpClient cookies: ");
            driver.get("http://qa1.madison.com");
            for(Cookie cookie : cookies) {
                System.out.println(cookie);
                driver.manage().addCookie(new org.openqa.selenium.Cookie(cookie.getName(), cookie.getValue()));
            }
            System.out.println("Driver cookies: ");
            for(org.openqa.selenium.Cookie cookie : driver.manage().getCookies()) {
                System.out.println(cookie);
            }
            driver.get("http://qa1.madison.com");
            driver.get("http://qa1.madison.com/customer/account/index/");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
