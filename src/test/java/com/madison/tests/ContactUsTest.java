package com.madison.tests;

import com.madison.data.Constants;
import com.madison.steps.ContactUsSteps;
import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import net.thucydides.junit.annotations.UseTestDataFrom;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Qualifier;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SerenityParameterizedRunner.class)
@UseTestDataFrom(value = Constants.CSV_PATH3)
public class ContactUsTest {

    @Before
    public void maxiPage() {
        driver.manage().window().maximize();
    }

    @Managed(uniqueSession = true)
    public WebDriver driver;

    @Steps
    private ContactUsSteps contactUsSteps;

    private String name, email, comment;

    @Qualifier
    public String qualifier() {
        return name;
    }

    @Test
    public void test01ContactUsGuestDDT(){
        contactUsSteps.navigateToHomePage();
        contactUsSteps.clickOnContactUs();
        contactUsSteps.inputName(name);
        contactUsSteps.inputEmail(email);
        contactUsSteps.inputComment(comment);
        contactUsSteps.clickOnSubmitButton();
        contactUsSteps.checkContactSumbitMessage("Your inquiry was submitted and will be responded to as soon as possible. Thank you for contacting us.");
    }
}