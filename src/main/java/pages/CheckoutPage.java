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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutPage extends BasePage {

    @FindBy(xpath = "//h2[contains(text(),'Address Details')]")
    private WebElement addressDetailsTitle;

    @FindBy(xpath = "//h2[contains(text(),'Review Your Order')]")
    private WebElement reviewOrderTitle;

    @FindBy(name = "message")
    private WebElement commentTextArea;

    @FindBy(xpath = "//a[text()='Place Order']")
    private WebElement placeOrderButton;
    
    @FindBy(id = "address_delivery")
    private WebElement deliveryAddressBox;

    @FindBy(id = "address_invoice")
    private WebElement billingAddressBox;

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public boolean isAddressDetailsVisible() {
        return addressDetailsTitle.isDisplayed();
    }

    public boolean isReviewOrderVisible() {
        return reviewOrderTitle.isDisplayed();
    }

    public void enterComment(String comment) {
        commentTextArea.sendKeys(comment);
    }

    public void clickPlaceOrder() {
        placeOrderButton.click();
    }
    
    public String getDeliveryAddressText() {
    return deliveryAddressBox.getText();
    }

    public String getBillingAddressText() {
    return billingAddressBox.getText();
    }
}
