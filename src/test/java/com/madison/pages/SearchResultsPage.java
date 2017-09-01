package com.madison.pages;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;

public class SearchResultsPage extends PageObject {

    @FindBy(css = ".products-grid .item.last")
    private List<WebElement> products;

    @FindBy(css = ".amount--has-pages")
    private List<WebElement> amountOfProducts1;

    @FindBy(css = ".amount--no-pages strong")
    private List<WebElement> amountOfProducts2;

    @FindBy(css = ".limiter option[selected='selected']")
    private WebElement limitPerPage;

    public void clickOnProduct(int productIndex) {
        products.get(productIndex).findElement(By.cssSelector("a")).click();
    }

    public void clickOnFirstProduct() {
        clickOnProduct(0);
    }

    public void clickOnLastProduct() {
        clickOnProduct(products.size() - 1);
    }

    public void clickOnRandomProduct() {
        Integer productNr = Math.abs(new Random().nextInt(products.size()));
        setProductTitleInSession(products.get(productNr).findElement(By.cssSelector(".product-name > a"))
                .getAttribute("title"));
        clickOnProduct(productNr);
    }

    public void clickOnCertainProduct(String productTitle) {
        for (WebElement product : products) {
            if (productTitle.toLowerCase().equals(product.findElement(By.cssSelector(".product-name > a"))
                    .getAttribute("title").toLowerCase())) {
                product.findElement(By.cssSelector("a")).click();
                break;
            }
        }
    }

    public void setAmountOfProductsInSession() {
        try {
            Serenity.setSessionVariable("amountOfProducts").to(amountOfProducts1.get(0)
                    .getText().split(" ")[2]);
        } catch (Exception ex) {
            Serenity.setSessionVariable("amountOfProducts").to(amountOfProducts2.get(0)
                    .getText().split(" ")[0]);
        }
    }

    public void setLimitPerPageInSession() {
        Serenity.setSessionVariable("limitPerPage").to(limitPerPage.getText().replace(" ", ""));
    }

    public String getProductToNavigateTo() {
        String[] split = Serenity.sessionVariableCalled("searchedTerm").toString().split(" ");
        String searchedTerm = "";
        for (String word : split) {
            searchedTerm += word;
            if (!word.equals(split[split.length - 1])) {
                searchedTerm += "+";
            }
        }

        return searchedTerm;
    }

    public void navigateToLastResultsPage() {
        if (getNumberOfResultPages() > 1) {
            navigateToResultsPage(getNumberOfResultPages());
        }
    }

    public void navigateToRandomResultsPage() {
        navigateToResultsPage(Math.abs(new Random().nextInt(getNumberOfResultPages() + 1)));
    }

    public void navigateToResultsPage(Integer pageNr) {
        getDriver().get("http://qa1.madison.com/catalogsearch/result/index/?p="
                + pageNr + "&q=" + getProductToNavigateTo());
    }

    public Integer getNumberOfResultPages() {
        setAmountOfProductsInSession();
        setLimitPerPageInSession();
        Integer productNumber = Integer.parseInt(Serenity.sessionVariableCalled("amountOfProducts").toString());
        Integer limitNumber = Integer.parseInt(Serenity.sessionVariableCalled("limitPerPage").toString());

        if (productNumber % limitNumber != 0) {
            return productNumber / limitNumber + 1;
        }

        return productNumber / limitNumber;
    }

    public void setProductTitleInSession(String title) {
        Serenity.setSessionVariable("productTitle").to(title);
    }

    public void setProductURLInSession(String url) {
        Serenity.setSessionVariable("productURL").to(url);
    }

    public void addRandomProductToWishlist() {
        Integer productNr = Math.abs(new Random().nextInt(products.size()));
        setProductTitleInSession(products.get(productNr).findElement(By.cssSelector(".product-name > a")).getAttribute("title"));
        setProductURLInSession(products.get(productNr).findElement(By.cssSelector(".product-name > a")).getAttribute("href"));
        products.get(productNr).findElement(By.cssSelector(".link-wishlist")).click();
    }

    public void addCertainProductToWishlist(String productTitle) {
        for (WebElement product : products) {
            if (productTitle.toLowerCase().equals(product.findElement(By.cssSelector(".product-name > a")).getAttribute("title").toLowerCase())) {
                product.findElement(By.cssSelector(".link-wishlist")).click();
                break;
            }
        }
    }

    public boolean checkIfProductsListIsEmpty() {
        return products.size() < 1;
    }

    public void addRandomProductToCompare() {
        Integer productNr = Math.abs(new Random().nextInt(products.size()));
        setProductTitleInSession(products.get(productNr).findElement(By.cssSelector(".product-name > a")).getAttribute("title"));
        setProductURLInSession(products.get(productNr).findElement(By.cssSelector(".product-name > a")).getAttribute("href"));
        products.get(productNr).findElement(By.cssSelector(".link-compare")).click();
    }
}
