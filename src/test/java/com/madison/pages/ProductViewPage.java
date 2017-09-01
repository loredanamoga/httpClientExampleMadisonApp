package com.madison.pages;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ProductViewPage extends PageObject {

    private Random random = new Random();

    @FindBy(css = ".link-wishlist")
    private WebElement addToWishlistLink;

    @FindBy(css = ".add-to-links li:last-of-type a")
    private WebElement addToCompareLink;

    @FindBy(css = ".h1")
    private WebElement productTitle;

    @FindBy(css = ".short-description>.std")
    private WebElement productShortDescription;

    @FindBy(css = ".tab-content>.std")
    private WebElement productDescription;

    @FindBy(css = ".input-box > #configurable_swatch_color li")
    private List<WebElement> productColors;

    @FindBy(css = ".input-box > #configurable_swatch_size li:not(.not-available)")
    private List<WebElement> clothesSizes;

    @FindBy(css = ".input-box > #configurable_swatch_shoe_size li:not(.not-available)")
    private List<WebElement> shoesSizes;

    @FindBy(css = "[type=\"checkbox\"]")
    private WebElement productRequiredCheckbox;

    @FindBy(css = "#super-product-table tr")
    private List<WebElement> productTypes;

    @FindBy(css = ".swatch-label")
    private WebElement color;

    @FindBy(css = ".option-l")
    private WebElement size;

    @FindBy(css = ".add-to-cart-buttons")
    private WebElement cartButton;

    @FindBy(css = ".toggle-tabs li:nth-child(3)")
    private WebElementFacade reviewsTab;

    @FindBy(className = "no-rating")
    private WebElementFacade leaveAReviewToAProductLink;


    public void clickOnColor() {
        color.click();
    }

    public void clickOnSize() {
        size.click();
    }

    public void clickOnAddToCart() {
        setProductTitleInSession();
        cartButton.click();
    }

    public void clickOnAddToCompare() {
        addToCompareLink.click();
    }

    public void selectRandomProductColor() {
        try {
            Integer randomColor = Math.abs(random.nextInt(productColors.size()));
            Serenity.setSessionVariable("productColor").to(randomColor);
            productColors.get(randomColor).click();
        } catch (Exception ex) {

        }

    }

    public void selectRandomProductSize() {
        try {
            Integer randomSize = Math.abs(random.nextInt(clothesSizes.size()));
            Serenity.setSessionVariable("productSize").to(randomSize);
            clothesSizes.get(randomSize).click();
        } catch (Exception ex) {
            try {
                Integer randomSize = Math.abs(random.nextInt(shoesSizes.size()));
                Serenity.setSessionVariable("productSize").to(randomSize);
                shoesSizes.get(randomSize).click();
            } catch (Exception e) {

            }
        }
    }

    public void selectProductRequiredCheckbox() {
        try {
            productRequiredCheckbox.click();
        } catch (Exception e) {

        }
    }

    public void selectProductQuantity(String qty) {
        try {
            Integer randomType = Math.abs(random.nextInt(productTypes.size()));
            Serenity.setSessionVariable("productTypes").to(randomType);
            productTypes.get(randomType).findElement(By.cssSelector(".qty-wrapper >.input-text.qty")).clear();
            productTypes.get(randomType).findElement(By.cssSelector(".qty-wrapper >.input-text.qty")).sendKeys(qty);
        } catch (Exception ex) {
            System.out.println("Error on ProductViewPage.selectProductQuantity");
        }

    }

    public void setCertainOptionsToProduct(String qty) {
        try {
            clothesSizes.get(Integer.parseInt(Serenity.sessionVariableCalled("productSize").toString())).click();
        } catch (Exception ex) {
            try {
                shoesSizes.get(Integer.parseInt(Serenity.sessionVariableCalled("productSize").toString())).click();
            } catch (Exception e) {

            }
        }

        try {
            productColors.get(Integer.parseInt(Serenity.sessionVariableCalled("productColor").toString())).click();
        } catch (Exception ex) {

        }

        selectProductRequiredCheckbox();
        selectProductQuantity(qty);

    }

    public void clickOnAddToWishlistLink() {
        addToWishlistLink.click();
    }

    public void setProductTitleInSession() {
        waitFor(productTitle);
        Serenity.setSessionVariable("productTitle").to(productTitle.getText());
    }

    public void setProductShortDescriptionInSession() {
        waitFor(productShortDescription);
        Serenity.setSessionVariable("productShortDescription").to(productShortDescription.getText());
    }

    public void setProductDescriptionInSession() {
        waitFor(productDescription);
        Serenity.setSessionVariable("productDescription").to(productDescription.getText());
    }

    public boolean verifyProductDetailsIfContainSpecificWord() {
        String searchTerm = Serenity.sessionVariableCalled("searchedTerm").toString();
        return (checkIfContainsWord(productTitle.getText(), searchTerm) || checkIfContainsWord(productDescription.
                getText(), searchTerm) || checkIfContainsWord(productShortDescription.getText(), searchTerm));
    }

    public void navigateToPreviousPage() {
        getDriver().navigate().back();
    }

    public boolean verifyProductIfContainsSpecificWordAndGoBack() {
        Boolean flag = verifyProductDetailsIfContainSpecificWord();
        navigateToPreviousPage();
        return flag;
    }

    private boolean checkIfContainsWord(String productToSearchIn, String searchTerm) {
        return productToSearchIn.toLowerCase().contains(searchTerm);
    }

    public void clickOnReviewsTab() {
        clickOn(reviewsTab);
    }

    public void clickOnAddAReviewLink() {
        try {
            clickOn(leaveAReviewToAProductLink);
        } catch (Exception ex) {
            System.out.println("Error on ProductViewPage.clickOnAddReviewLink()");
        }
    }

    public void addRandomProductToWishlist() {
        clickOn(addToWishlistLink);
    }

    public List<String> getColorNames() {
        List<String> colorNames = new ArrayList<>();
        for (WebElement color : productColors) {
            colorNames.add(color.findElement(By.cssSelector("a")).getAttribute("name"));
        }

        return colorNames;
    }

    public List<String> getSizes() {
        List<String> sizes = new ArrayList<>();
        for (WebElement size : shoesSizes) {
            sizes.add(size.findElement(By.cssSelector(".swatch-label")).getText());
        }
        for (WebElement size : clothesSizes) {
            sizes.add(size.findElement(By.cssSelector(".swatch-label")).getText());
        }

        return sizes;
    }

    public Boolean checkIfProductContainsCertainColors(List<String> colors) {
        for (String color : getColorNames()) {
            if (colors.contains(color)) {
                return true;
            }
        }

        return false;
    }

    public Boolean checkIfProductContainsCertainSizes(List<String> sizes) {
        for (String size : getSizes()) {
            if (sizes.contains(size)) {
                return true;
            }
        }

        return false;
    }


}
