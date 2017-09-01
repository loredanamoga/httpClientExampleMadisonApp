//package com.madison.pages.pageshttp;
//
//import javax.net.ssl.HttpsURLConnection;
//import java.io.BufferedReader;
//import java.io.DataOutputStream;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.net.*;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * Created by loredanamoga on 8/14/2017.
// */
//public class PostAndGetNewHttp {
//
//    private List<String> cookies;
//    private HttpURLConnection conn;
//    private static final String USER_AGENT = "Mozilla/5.0";
//
//    public PostAndGetNewHttp() {
//        CookieHandler.setDefault(new CookieManager());
//    }
//
//    public int post(String url, HashMap<String,String> params) throws IOException {
//
//        URL obj = new URL(url);
//        if (obj.getProtocol().equals("https")) {
//            conn = (HttpsURLConnection) obj.openConnection();
//        } else {
//            conn = (HttpURLConnection) obj.openConnection();
//        }
//        StringBuilder postParams = new StringBuilder();
//        for (Map.Entry<String, String> e : params.entrySet()) {
//            postParams.append(e.getKey()).append("=").append(e.getValue());
//            postParams.append("&");
//        }
//
//        postParams.deleteCharAt(postParams.length() - 1);
//        // Acts like a browser
//        conn.setUseCaches(false);
//        conn.setRequestMethod("POST");
//        conn.setRequestProperty("User-Agent", PostAndGetNewHttp.USER_AGENT);
//        conn.setRequestProperty("Accept",
//                "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
//        conn.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
//        for (String cookie : this.cookies) {
//            conn.addRequestProperty("Cookie", cookie.split(";", 1)[0]);
//        }
//        conn.setRequestProperty("Connection", "keep-alive");
//        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
//        conn.setRequestProperty("Content-Length", Integer.toString(postParams.length()));
//
//        conn.setDoOutput(true);
//        conn.setDoInput(true);
//
//        // Send post request
//        DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
//
//
//        wr.writeBytes(postParams.toString());
//        wr.flush();
//        wr.close();
//
//        conn.getInputStream();
//
//        int responseCode = conn.getResponseCode();
//        System.out.println("\nSending 'POST' request to URL : " + url);
//        System.out.println("Response Code : " + responseCode);
//
//        return responseCode;
//
//    }
//
//
//    public int get(String url) throws MalformedURLException, IOException {
//
//        URL obj = new URL(url);
//        if (obj.getProtocol().equals("https")) {
//            conn = (HttpsURLConnection) obj.openConnection();
//        } else {
//            conn = (HttpURLConnection) obj.openConnection();
//        }
//
//        // default is GET
//        conn.setRequestMethod("GET");
//
//        conn.setUseCaches(false);
//
//        // act like a browser
//        conn.setRequestProperty("User-Agent", PostAndGetNewHttp.USER_AGENT);
//        conn.setRequestProperty("Accept",
//                "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
//        conn.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
//        if (cookies != null) {
//            for (String cookie : this.cookies) {
//                conn.addRequestProperty("Cookie", cookie.split(";", 1)[0]);
//            }
//        }
//        int responseCode = conn.getResponseCode();
//        System.out.println("\nSending 'GET' request to URL : " + url);
//        System.out.println("Response Code : " + responseCode);
//
//        BufferedReader in
//                = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//        String inputLine;
//        StringBuilder response = new StringBuilder();
//
//        while ((inputLine = in.readLine()) != null) {
//            response.append(inputLine);
//        }
//        in.close();
//
//        // Get the response cookies
//        setCookies(conn.getHeaderFields().get("Set-Cookie"));
//
//        return responseCode;
//
//    }
//
//    private List<String> getCookies() {
//        return cookies;
//    }
//
//    private void setCookies(List<String> cookies) {
//        this.cookies = cookies;
//    }
//
//}
