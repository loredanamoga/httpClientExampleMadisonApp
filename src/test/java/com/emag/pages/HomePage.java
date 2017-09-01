package com.emag.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.WebElement;

@DefaultUrl("http://www.emag.ro")
public class HomePage extends PageObject {
    @FindBy(css="[class~=emg-fluid-search-field]")
    private WebElement searchBar;

    @FindBy(css="[class~=emag-fluid-search-btn")
    private WebElement searchButton;

    public void searchTerm(String keyword) {
        searchBar.sendKeys(keyword);
        searchButton.click();
    }

}
