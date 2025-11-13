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
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductsPage extends BasePage {

    @FindBy(xpath = "//h2[text()='All Products']")
    private WebElement allProductsTitle;

    @FindBy(css = ".features_items")
    private WebElement productsList;

    @FindBy(xpath = "(//a[text()='View Product'])[1]")
    private WebElement firstProductViewButton;
    
    @FindBy(id = "search_product")
    private WebElement searchInput;

    @FindBy(id = "submit_search")
    private WebElement searchButton;

    @FindBy(xpath = "//h2[text()='Searched Products']")
    private WebElement searchedProductsTitle;

    @FindBy(css = ".features_items .product-image-wrapper")
    private java.util.List<WebElement> searchedProductsList;
    
    @FindBy(xpath = "(//a[@class='btn btn-default add-to-cart'])[1]")
    private WebElement firstProductAddToCart;

    @FindBy(xpath = "(//a[@data-product-id='2'])")
    private WebElement secondProductAddToCart;

    @FindBy(xpath = "//button[text()='Continue Shopping']")
    private WebElement continueShoppingButton;

    @FindBy(xpath = "//u[text()='View Cart']")
    private WebElement viewCartButton;
    
    @FindBy(xpath = "//h2[text()='Brands']")
    private WebElement brandsSidebar;

    @FindBy(xpath = "//a[@href='/brand_products/Polo']")
    private WebElement poloBrandLink;

    @FindBy(xpath = "//a[@href='/brand_products/H&M']")
    private WebElement hnmBrandLink;

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public boolean isAllProductsTitleVisible() {
        return allProductsTitle.isDisplayed();
    }

    public boolean isProductsListVisible() {
        return productsList.isDisplayed();
    }

    public void clickFirstProductView() {
        firstProductViewButton.click();
    }
    
    public void searchProduct(String productName) {
        searchInput.clear();
        searchInput.sendKeys(productName);
        searchButton.click();
    }

    public boolean isSearchedProductsTitleVisible() {
        return searchedProductsTitle.isDisplayed();
    }

    public boolean areSearchedProductsVisible() {
        return !searchedProductsList.isEmpty();
    }
    
    public void addFirstProductToCart() {
        Actions actions = new Actions(driver);
        actions.moveToElement(firstProductAddToCart).perform();

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", firstProductAddToCart);

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOf(continueShoppingButton));
    }

    public void addSecondProductToCart() {
        Actions actions = new Actions(driver);
        actions.moveToElement(secondProductAddToCart).perform();

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", secondProductAddToCart);

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOf(viewCartButton));
    }

    public void clickContinueShopping() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", continueShoppingButton);
    }

    public void clickViewCart() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", viewCartButton);
    }
    
    
    public void addFirstProductAndGoToCart() {
    //Click "Add to cart" for the first visible product
    WebElement firstProductAdd = driver.findElement(By.xpath("(//a[contains(text(),'Add to cart')])[1]"));
    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", firstProductAdd);

    //Wait for modal
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    WebElement viewCartBtn = wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.xpath("//u[text()='View Cart']"))
    );

    //Click View Cart via JS
    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", viewCartBtn);

    //Ensure navigation happened
    wait.until(ExpectedConditions.urlContains("view_cart"));
    }
    
    
    public boolean isBrandsSidebarVisible() {
    return brandsSidebar.isDisplayed();
    }

    public void clickPoloBrand() {
    poloBrandLink.click();
    }

    public void clickHnmBrand() {
    hnmBrandLink.click();
    }
    
    /**
    * Adds all currently displayed searched products to the cart.
    * Returns the number of products added.
     * @return 
    */
    public int addAllSearchedProductsAndGoToCart() {
    //locator for the product cards inside searched results
    By cardLocator = By.cssSelector(".features_items .product-image-wrapper");

    List<WebElement> items = driver.findElements(cardLocator);
    int total = items.size();
    if (total == 0) {
        throw new NoSuchElementException("No searched product items found.");
    }

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(8));

    for (int i = 0; i < total; i++) {
        //re-fetch list (DOM can change after each add)
        items = driver.findElements(cardLocator);
        WebElement card = items.get(i);

        //scroll + hover
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", card);
        new Actions(driver).moveToElement(card).pause(Duration.ofMillis(300)).perform();

        //find only visible Add to Cart button inside this card
        List<WebElement> addBtns = card.findElements(By.cssSelector("a.add-to-cart"));
        WebElement addBtn = null;
        for (WebElement btn : addBtns) {
            if (btn.isDisplayed()) {
                addBtn = btn;
                break;
            }
        }

        if (addBtn == null) {
            throw new RuntimeException("No visible Add to cart button found for product index " + i);
        }

        //click it with JS to avoid overlays
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addBtn);

        //wait for modal
        wait.until(ExpectedConditions.or(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Continue Shopping']")),
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//u[text()='View Cart']"))
        ));

        if (i < total - 1) {
            //if not last product, click Continue Shopping
            WebElement modalContinue = driver.findElement(By.xpath("//button[text()='Continue Shopping']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", modalContinue);
            wait.until(ExpectedConditions.invisibilityOf(modalContinue));
        } else {
            //for last product, go to View Cart
            WebElement modalView = driver.findElement(By.xpath("//u[text()='View Cart']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", modalView);
            wait.until(ExpectedConditions.urlContains("view_cart"));
        }
    }

    return total;
    }
}
