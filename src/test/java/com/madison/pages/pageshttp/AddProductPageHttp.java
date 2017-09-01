package com.madison.pages.pageshttp;

import org.apache.http.client.methods.HttpUriRequest;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.HashMap;

/**
 * Created by loredanamoga on 8/10/2017.
 */
public class AddProductPageHttp {

    private Document pageContent;
    private String splittedLink;
    private String url, url2;

    public void setPageContent(String content) {
        pageContent = Jsoup.parse(content);
        splittedLink = pageContent.select("[title=\"Save and Continue Edit\"].scalable.save").attr("onclick")
                .split("'")[1];
        url = splittedLink.replace("{{attribute_set}}", "4").replace("{{type}}", "simple")
                .replace("{{tab_id}}", "/product_info_tabs_group_32");

        url2 = splittedLink.replace("save/back/edit/tab/{{tab_id}}", "validate");
    }

    public HttpUriRequest addProductSpecifications(HashMap<String, String> postParams) {
        String form_key = pageContent.select("[name=form_key]").attr("value");
        postParams.put("form_key", form_key);
//        HashMap<String, String> postParams = new HashMap<String, String>();

//        postParams.put("product[name]", productName);
//        postParams.put("product[description]", productDescription);
//        postParams.put("product[short_description]", productShortDescr);
//        postParams.put("product[sku]", SKU);
//        postParams.put("product[weight]", weight);
//        postParams.put("product[status]", status);
//        postParams.put("product[visibility]", visibility);
//        postParams.put("product[price]", price);
//        postParams.put("product[tax_class_id]", taxClass);
//        postParams.put("product[stock_data][qty]", quantity);
        return HttpPostAndGetRequests.httpPostRequest(url2, postParams);
    }

    public HttpUriRequest addProductSpecif(HashMap<String, String> postParams) {
        String form_key = pageContent.select("[name=form_key]").attr("value");
        postParams.put("form_key", form_key);
//        HashMap<String, String> postParams = new HashMap<String, String>();
//        postParams.put("product[name]", productName);
//        postParams.put("product[description]", productDescription);
//        postParams.put("product[short_description]", productShortDescr);
//        postParams.put("product[sku]", SKU);
//        postParams.put("product[weight]", weight);
//        postParams.put("product[status]", status);
//        postParams.put("product[visibility]", visibility);
//        postParams.put("product[price]", price);
//        postParams.put("product[tax_class_id]", taxClass);
//        postParams.put("product[stock_data][qty]", quantity);
        return HttpPostAndGetRequests.saveProductPost(url, postParams);
    }


}
