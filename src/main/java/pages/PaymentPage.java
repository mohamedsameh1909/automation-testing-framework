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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PaymentPage extends BasePage {

    @FindBy(name = "name_on_card")
    private WebElement nameOnCardInput;

    @FindBy(name = "card_number")
    private WebElement cardNumberInput;

    @FindBy(name = "cvc")
    private WebElement cvcInput;

    @FindBy(name = "expiry_month")
    private WebElement expiryMonthInput;

    @FindBy(name = "expiry_year")
    private WebElement expiryYearInput;

    @FindBy(id = "submit")
    private WebElement payAndConfirmButton;

    @FindBy(xpath = "//p[contains(text(),'Congratulations! Your order has been confirmed!')]")
    private WebElement orderPlacedMessage;
    
    @FindBy(xpath = "//a[contains(text(),'Download Invoice')]")
    private WebElement downloadInvoiceButton;

    public PaymentPage(WebDriver driver) {
        super(driver);
    }

    public void enterPaymentDetails(String name, String number, String cvc, String month, String year) {
        nameOnCardInput.sendKeys(name);
        cardNumberInput.sendKeys(number);
        cvcInput.sendKeys(cvc);
        expiryMonthInput.sendKeys(month);
        expiryYearInput.sendKeys(year);
    }

    public void clickPayAndConfirm() {
        payAndConfirmButton.click();
    }

    public boolean isOrderSuccessVisible() {
        return orderPlacedMessage.isDisplayed();
    }
    
    public boolean waitForOrderPlaced() {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    return wait.until(ExpectedConditions.visibilityOf(orderPlacedMessage)).isDisplayed();
    }
    
    public void clickDownloadInvoice() {
    downloadInvoiceButton.click();
    }
}
