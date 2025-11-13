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
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage extends BasePage {

    @FindBy(xpath = "//li[@class='active' and text()='Shopping Cart']")
    private WebElement shoppingCartTitle;
    
    @FindBy(xpath = "//*[@id=\"product-1\"]/td[3]/p")
    private WebElement firstProductPrice;

    @FindBy(css = "#product-1 > td.cart_quantity > button")
    private WebElement firstProductQuantity;

    @FindBy(css = "#product-1 > td.cart_total > p")
    private WebElement firstProductTotal;

    @FindBy(css = "#product-2 > td.cart_price > p")
    private WebElement secondProductPrice;

    @FindBy(css = "#product-2 > td.cart_quantity > button")
    private WebElement secondProductQuantity;

    @FindBy(css = "#product-2 > td.cart_total > p")
    private WebElement secondProductTotal;
    
    @FindBy(css = "#product-1 > td.cart_quantity > button")
    private WebElement cartQuantity;
    
    @FindBy(xpath = "//a[@class='btn btn-default check_out']")
    private WebElement proceedToCheckoutButton;

    @FindBy(xpath = "//u[text()='Register / Login']")
    private WebElement registerLoginButton;
    
    @FindBy(xpath = "//a[@class='cart_quantity_delete']")
    private WebElement removeProductButton;

    @FindBy(xpath = "//b[text()='Cart is empty!']")
    private WebElement emptyCartMessage;
    
    @FindBy(css = "table.table tbody tr, table.cart_info tbody tr")
    private List<WebElement> cartRows;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public boolean isShoppingCartTitleVisible() {
        return shoppingCartTitle.isDisplayed();
    }
    
    public boolean isFirstProductVisible() {
        return firstProductPrice.isDisplayed() &&
               firstProductQuantity.isDisplayed() &&
               firstProductTotal.isDisplayed();
    }

    public boolean isSecondProductVisible() {
        return secondProductPrice.isDisplayed() &&
               secondProductQuantity.isDisplayed() &&
               secondProductTotal.isDisplayed();
    }
    
    public int getFirstProductQuantity() {
        return Integer.parseInt(cartQuantity.getText().trim());
    }
    
    public void clickProceedToCheckout() {
    proceedToCheckoutButton.click();
    }

    public void clickRegisterLogin() {
    registerLoginButton.click();
    }
    
    public void removeFirstProduct() {
    removeProductButton.click();
    }

    public boolean isCartEmptyMessageVisible() {
    return emptyCartMessage.isDisplayed();
    }
    
    public void removeFirstProductAndWait() {
    removeProductButton.click();

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    wait.until(ExpectedConditions.visibilityOf(emptyCartMessage));
    }
    
    public int getCartItemsCount() {
        return (cartRows == null) ? 0 : cartRows.size();
    }

    public boolean hasAtLeastNItems(int n) {
        return getCartItemsCount() >= n;
    }
}
