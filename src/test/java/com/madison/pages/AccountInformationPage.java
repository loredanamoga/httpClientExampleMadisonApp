package com.madison.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

public class AccountInformationPage extends PageObject {

    @FindBy(id = "firstname")
    private WebElementFacade firstNameField;

    @FindBy(id = "lastname")
    private WebElementFacade lastNameField;

    @FindBy(id = "email")
    private WebElementFacade emailField;

    @FindBy(id = "current_password")
    private WebElementFacade currentPasswordField;

    @FindBy(css = "[title=\"Save\"]")
    private WebElementFacade saveButton;

    @FindBy(className = "page-title")
    private WebElementFacade editAccountInfoMessage;

    @FindBy(id = "password")
    private WebElementFacade newPasswordField;

    @FindBy(id = "confirmation")
    private WebElementFacade passwordConfirmationField;

    public void changeFirstName(String firstName){
        typeInto(firstNameField, firstName);
    }

    public void changeLastName(String lastName){
        typeInto(lastNameField, lastName);
    }

    public void changeEmail(String email) {
        typeInto(emailField, email);
    }

    public void inputCurrentPassword(String password) {
        typeInto(currentPasswordField, password);
    }

    public void clickOnSaveButton() {
        clickOn(saveButton);
    }

    public String checkEditAccountInfoMessage() {
        return editAccountInfoMessage.getText();
    }

    public void inputNewPassword(String newPassword){
        typeInto(newPasswordField, newPassword);
    }

    public void inputConfirmationPassword(String confirmationPassword){
        typeInto(passwordConfirmationField, confirmationPassword);
    }

}
