package com.madison.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

public class RegisterPage extends PageObject {

    @FindBy(className = "form-instructions")
    private WebElementFacade formInstructions;

    @FindBy(id = "firstname")
    private WebElementFacade firstNameField;

    @FindBy(id = "lastname")
    private WebElementFacade lastNameField;

    @FindBy(id = "email_address")
    private WebElementFacade emailAddressField;

    @FindBy(id = "password")
    private WebElementFacade passwordField;

    @FindBy(id = "confirmation")
    private WebElementFacade confirmPasswordField;

    @FindBy(css = ".buttons-set button")
    private WebElementFacade registerButton;

    @FindBy(css = ".messages span")
    private WebElementFacade registerMessage;

    public boolean verifyFormInstructions() {
        return formInstructions.getText().contains("create your account");
    }

    public void typeFirstName(String firstName) {
        firstNameField.sendKeys(firstName);
    }

    public boolean checkFirstNameFieldIsNotEmpty() {
        return firstNameField.getText().equals("");
    }

    public void typeLastName(String lastName) {
        typeInto(lastNameField, lastName);
    }

    public boolean checkLastNameFieldIsNotEmpty() {
        return lastNameField.getText().equals("");
    }

    public void typeEmailAddress(String email) {
        typeInto(emailAddressField, email);
    }

    public boolean checkEmailAddressFieldIsNotEmpty() {
        return emailAddressField.getText().equals("");
    }

    public void typePassword(String password) {
        typeInto(passwordField, password);
    }

    public boolean checkPasswordFieldIsNotEmpty() {
        return passwordField.getText().equals("");
    }

    public void typeConfirmationPassword(String password) {
        typeInto(confirmPasswordField, password);
    }

    public boolean checkPasswordConfirmationFieldIsNotEmpty() {
        return confirmPasswordField.getText().equals("");
    }

    public void clickOnRegisterButton() {
        clickOn(registerButton);
    }

    public String getRegisterMessage() {
        return registerMessage.getText();
    }
}
