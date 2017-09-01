package com.madison.pages;


import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Random;

public class AddressBookPage extends PageObject {

    @FindBy(id = "telephone")
    private WebElementFacade telephoneField;

    @FindBy(id = "street_1")
    private WebElementFacade addressField;

    @FindBy(id = "city")
    private WebElementFacade cityField;

    @FindBy(id = "region_id")
    private WebElementFacade stateDropdown;

    @FindBy(id = "zip")
    private WebElementFacade zipCodeField;

    @FindBy(id = "country")
    private WebElementFacade countryDropdown;

    @FindBy(css = "[title=\"Save Address\"]")
    private WebElementFacade saveAddressButton;

    @FindBy(className = "success-msg")
    private WebElementFacade savedChangedBillingAddressMessage;

    @FindBy(id = "region")
    private WebElementFacade stateField;

    public void typeTelephone(String phone) {
        telephoneField.clear();
        telephoneField.sendKeys(phone);
    }

    public void typeAddress(String address) {
        typeInto(addressField, address);
    }

    public void typeCity(String city) {
        typeInto(cityField, city);
    }

    public void chooseState() {
        List<WebElement> allStates = new Select(countryDropdown).getOptions();
        allStates.get(new Random().nextInt(allStates.size())).click();
    }

    public void typeZipCode(String zip) {
        typeInto(zipCodeField, zip);
    }

    public void chooseCountry() {
        List<WebElement> allCountries = new Select(countryDropdown).getOptions();
        allCountries.get(new Random().nextInt(allCountries.size())).click();
    }

    public void clickOnSaveAddressButton() {
        clickOn(saveAddressButton);
    }

    public boolean checkSavedChangedBillingAddressMessage() {
        if (savedChangedBillingAddressMessage.isDisplayed())
            return true;
        return false;
    }

    public void typeState(String state) {
        typeInto(stateField, state);
    }

}
