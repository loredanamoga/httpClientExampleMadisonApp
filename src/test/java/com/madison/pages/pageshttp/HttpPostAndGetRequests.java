package com.madison.pages.pageshttp;

import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;

import java.util.HashMap;


/**
 * Created by loredanamoga on 8/9/2017.
 */
public class HttpPostAndGetRequests {

    public static HttpUriRequest httpPostRequest(String url, HashMap<String, String> postParameters) {
        RequestBuilder requestBuilder = RequestBuilder.post().setUri(url);
        for (String key : postParameters.keySet()) {
            requestBuilder.addParameter(key, postParameters.get(key));

        }
        return requestBuilder.build();
    }

    public static HttpUriRequest httpGetRequest(String url, HashMap<String, String> getParameters) {
        RequestBuilder requestBuilder = RequestBuilder.get().setUri(url);
        for (String key : getParameters.keySet()) {
            requestBuilder.addParameter(key, getParameters.get(key));

        }
        return requestBuilder.build();
    }

    public static HttpUriRequest saveProductPost(String url, HashMap<String, String> postParameters) {
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        for (String key : postParameters.keySet()) {
            builder.addTextBody("field1", "yes", ContentType.MULTIPART_FORM_DATA);
        }
        RequestBuilder requestBuilder = RequestBuilder.post().setUri(url);
        requestBuilder.setEntity(builder.build());

        return requestBuilder.setUri(url).build();
    }

}
