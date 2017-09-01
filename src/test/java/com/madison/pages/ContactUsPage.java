package com.madison.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ContactUsPage extends PageObject {

    @FindBy(css = "#name")
    private WebElement name;

    @FindBy(css = "#email")
    private WebElement email;

    @FindBy(css = "#comment")
    private WebElement comment;

    @FindBy(css = "div[class='buttons-set'] button>span>span")
    private WebElement submit;

    @FindBy(css = ".success-msg")
    private List<WebElement> checkContactSubmit;


    public void inputName(String keyword) {
        name.sendKeys(keyword);
    }

    public void inputEmail(String keyword) {
        email.sendKeys(keyword);
    }

    public void inputComment(String keyword) {
        comment.sendKeys(keyword);
    }

    public void clickOnSubmitButton() {
        submit.click();
    }

    public String checkContactSubmit() {
        if (checkContactSubmit.size() != 0)
            return checkContactSubmit.get(0).getText();
        return "";
    }
}