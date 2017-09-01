package com.madison.pages;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AdvancedSearchPage extends PageObject {

    Random random = new Random();
    ProductViewPage productViewPage;

    @FindBy(css = "#name")
    private WebElement name;

    @FindBy(css = "div[class='buttons-set'] button>span>span")
    private WebElement search;



    @FindBy(css = ".error-msg")
    private WebElement errorMsg;

    @FindBy(id = "price")
    private WebElement priceFromField;

    @FindBy(id = "price_to")
    private WebElement priceToField;

    @FindBy(id = "color")
    private WebElementFacade color;

    @FindBy(id = "size")
    private WebElementFacade size;

    @FindBy(id = "gender")
    private WebElementFacade gender;


    public void setInputNameInSession(String keyword) {
        Serenity.setSessionVariable("inputName").to(keyword);
    }

    public void inputName(String keyword) {
        setInputNameInSession(keyword);
        name.sendKeys(keyword);
    }

    public String getName() {
        return name.getText();
    }

    public void clickOnSearchButton() {
        search.click();
    }

    public Boolean checkIfProductsListIsEmpty() {
        return getName().equals("") && errorMsg.isDisplayed();
    }

    public void inputPrice(String price) {
        priceFromField.sendKeys(price);
    }

    public void inputPriceTo(String priceTo) {
        priceToField.sendKeys(priceTo);
    }

    public void selectColor() {
        Select oSelect = new Select(color);
        List<WebElement> allColors = oSelect.getOptions();
        int rand = random.nextInt(allColors.size());
        Serenity.setSessionVariable("randomColor").to(allColors.get(rand).getText());
        allColors.get(rand).click();
    }

    public void selectSize() {
        Select oSelect = new Select(size);
        List<WebElement> allSizes = oSelect.getOptions();
        int rand = random.nextInt(allSizes.size());
        Serenity.setSessionVariable("randomSize").to(allSizes.get(rand).getText());
        allSizes.get(rand).click();
    }

    public void selectGender() {
        Select oSelect = new Select(gender);
        List<WebElement> allGenders = oSelect.getOptions();
        int rand = random.nextInt(allGenders.size());
        Serenity.setSessionVariable("randomGender").to(allGenders.get(rand).getText());
        allGenders.get(rand).click();
    }

}