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
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    private final String url = "https://automationexercise.com/";

    @FindBy(css = "div.shop-menu.pull-right > ul > li:nth-child(4) > a")
    private WebElement signupLoginLink;

    @FindBy(css = "div.col-sm-9.padding-right > div.features_items > h2")  // just to verify home page loaded
    private WebElement homeContent;
    
    @FindBy(css = "div > ul > li:nth-child(2) > a")
    private WebElement productsLink;

    @FindBy(css = "div > ul > li:nth-child(3) > a")
    private WebElement cartLink;
    
    @FindBy(css = "div > ul > li:nth-child(4) > a")
    private WebElement logoutLink;
    
    @FindBy(xpath = "//h2[text()='Category']")
    private WebElement categorySidebar;

    @FindBy(xpath = "//a[@href='#Women']")
    private WebElement womenCategory;

    @FindBy(xpath = "//a[@href='/category_products/1']")
    private WebElement womenDressSubcategory;

    @FindBy(xpath = "//a[@href='#Men']")
    private WebElement menCategory;

    @FindBy(xpath = "//a[@href='/category_products/3']")
    private WebElement menTshirtsSubcategory;
    
    @FindBy(xpath = "//h2[text()='recommended items' or text()='RECOMMENDED ITEMS']")
    private WebElement recommendedItemsTitle;

    @FindBy(xpath = "(//div[@id='recommended-item-carousel']//a[contains(text(),'Add to cart')])[1]")
    private WebElement firstRecommendedAddToCart;

    @FindBy(xpath = "//u[text()='View Cart']")
    public WebElement modalViewCartButton;
    
    @FindBy(xpath = "//h2[text()='Subscription' or text()='SUBSCRIPTION']")
    private WebElement subscriptionTitle;

    @FindBy(id = "scrollUp")
    private WebElement scrollUpArrow;

    @FindBy(xpath = "//h2[contains(text(),'Full-Fledged practice website for Automation Engineers')]")
    private WebElement fullFledgedText;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get(url);
    }
    
    public boolean isHomeVisible() {
        return homeContent.isDisplayed();
    }

    

    public void clickSignupLogin() {
        signupLoginLink.click();
    }
    
    public void clickProducts() {
    productsLink.click();
    }

    public void clickCart() {
    cartLink.click();
    }
    
    public void clickLogout() {
    logoutLink.click();
    }
    
    public boolean isCategorySidebarVisible() {
    return categorySidebar.isDisplayed();
    }

    public void clickWomenCategory() {
    womenCategory.click();
    }

    public void clickWomenDress() {
    womenDressSubcategory.click();
    }

    public void clickMenCategory() {
    menCategory.click();
    }

    public void clickMenTshirts() {
    menTshirtsSubcategory.click();
    }
    
    public boolean isRecommendedItemsVisible() {
    return recommendedItemsTitle.isDisplayed();
    }

    public void addFirstRecommendedItemToCart() {
    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", recommendedItemsTitle);
    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", firstRecommendedAddToCart);
    }

    public void clickModalViewCart() {
    modalViewCartButton.click();
    }
    
    public boolean isSubscriptionVisible() {
    return subscriptionTitle.isDisplayed();
    }

    public void scrollToBottom() {
    ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    public void clickScrollUpArrow() {
    scrollUpArrow.click();
    }

    public boolean isFullFledgedTextVisible() {
    return fullFledgedText.isDisplayed();
    }
    
    public void scrollToTop() {
    ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0)");
    }
}
