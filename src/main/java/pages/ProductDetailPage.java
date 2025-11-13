/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pages;

/**
 *
 * @author HP
 */
import framework.BasePage;
import java.time.Duration;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductDetailPage extends BasePage {

    @FindBy(xpath = "//div[@class='product-information']/h2")
    private WebElement productName;

    @FindBy(xpath = "//div[@class='product-information']/p[contains(text(),'Category')]")
    private WebElement category;

    @FindBy(xpath = "//div[@class='product-information']/span/span")
    private WebElement price;

    @FindBy(css = "div.col-sm-7 > div > p:nth-child(6)")
    private WebElement availability;

    @FindBy(css = "div.col-sm-7 > div > p:nth-child(7)")
    private WebElement condition;

    @FindBy(css = "div.col-sm-7 > div > p:nth-child(8)")
    private WebElement brand;

    @FindBy(id = "quantity")
    private WebElement quantityInput;

    @FindBy(xpath = "//button[@class='btn btn-default cart']")
    private WebElement addToCartButton;

    @FindBy(xpath = "//u[text()='View Cart']")
    private WebElement viewCartButton;
    
    //Modal buttons
    @FindBy(xpath = "//u[text()='View Cart']")
    private WebElement modalViewCartButton;

    @FindBy(xpath = "//button[text()='Continue Shopping']")
    private WebElement modalContinueButton;

    @FindBy(id = "name")
    private WebElement reviewNameInput;

    @FindBy(id = "email")
    private WebElement reviewEmailInput;

    @FindBy(id = "review")
    private WebElement reviewTextArea;

    @FindBy(id = "button-review")
    private WebElement submitReviewButton;

    @FindBy(xpath = "//span[contains(text(),'Thank you for your review.')]")
    private WebElement successMessage;

    @FindBy(xpath = "//a[text()='Write Your Review']")
    private WebElement writeYourReviewTitle;

    public ProductDetailPage(WebDriver driver) {
        super(driver);
    }

    public boolean isProductNameVisible() {
        return productName.isDisplayed();
    }

    public boolean isCategoryVisible() {
        return category.isDisplayed();
    }

    public boolean isPriceVisible() {
        return price.isDisplayed();
    }

    public boolean isAvailabilityVisible() {
        return availability.isDisplayed();
    }

    public boolean isConditionVisible() {
        return condition.isDisplayed();
    }

    public boolean isBrandVisible() {
        return brand.isDisplayed();
    }

    public void setQuantity(int qty) {
        quantityInput.clear();
        quantityInput.sendKeys(String.valueOf(qty));
    }

    public void clickAddToCart() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addToCartButton);
    }

    public void clickViewCart() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", viewCartButton);
    }
    
    public void clickAddToCartAndGoToCart() {
        //Click "Add to cart"
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addToCartButton);

        //Wait until modal appears
        new WebDriverWait(driver, Duration.ofSeconds(5))
            .until(ExpectedConditions.visibilityOf(modalViewCartButton));

        //Click "View Cart" in modal
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", modalViewCartButton);

        //wait for URL to contain 'view_cart'
        new WebDriverWait(driver, Duration.ofSeconds(5))
            .until(ExpectedConditions.urlContains("view_cart"));
    }

    public boolean isWriteReviewVisible() {
        return writeYourReviewTitle.isDisplayed();
    }

    public void enterReviewDetails(String name, String email, String review) {
        reviewNameInput.sendKeys(name);
        reviewEmailInput.sendKeys(email);
        reviewTextArea.sendKeys(review);
    }

    public void submitReview() {
        submitReviewButton.click();
    }

    public boolean isReviewSuccessVisible() {
        return successMessage.isDisplayed();
    }
}
