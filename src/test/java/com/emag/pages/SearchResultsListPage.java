package com.emag.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;

public class SearchResultsListPage extends PageObject {
    
    @FindBy(css="[class~=emg-pagination-no]")
    public List<WebElement> pagesBigResolution;
    
    @FindBy(css="li.active > span")
    public List<WebElement> pagesSmallResolution;

    @FindBy(css=".lazy")
    public List<WebElement> productsBigResolution;

    @FindBy(css=".thumbnail.js-product-url")
    public List<WebElement> productsSmallResolution;

    public void redirectToRandomProductsListPage(String keyword) {
        Random random = new Random();
        Integer pageNr = 1;
        String[] split;

        if(pagesBigResolution.size() != 0) {
            while (true) {
                pageNr = Math.abs(random.nextInt()) % Integer.parseInt(pagesBigResolution.get(pagesBigResolution.size()-1).getText());
                if (pageNr != 0) {
                    break;
                }
            }
            //System.out.println("[class~=emg-pagination-no]");
        } else {
            if(pagesSmallResolution.size() != 0) {
                split = pagesSmallResolution.get(0).getText().split(" ");
                //System.out.println("Split[2]: " + split[2]);
                pageNr = Math.abs(random.nextInt()) % Integer.parseInt(split[2]);
                //System.out.println("li.active > span");
            }
        }

        //System.out.println("Page number: " + pageNr);

        if(pageNr != 1) {
            split = keyword.split(" ");
            keyword = "";
            for(int i = 0; i < split.length; i++) {
                keyword += split[i];
                if(i != split.length - 1) {
                    keyword += "+";
                }
            }

            getDriver().get("https://www.emag.ro/search/" + keyword + "/p" + pageNr);
            //System.out.println("Done");
        } 
    }

    public void clickOnRandomProduct() {
        Random random = new Random();

        if(productsBigResolution.isEmpty()) {
            Integer productsNr = Math.abs(random.nextInt()) % productsSmallResolution.size();
            WebElement product = productsSmallResolution.get(productsNr);

            product.click();
        } else {
            Integer productsNr = Math.abs(random.nextInt()) % productsBigResolution.size();
            WebElement product = productsBigResolution.get(productsNr);

            product.click();
        }
    }
}
