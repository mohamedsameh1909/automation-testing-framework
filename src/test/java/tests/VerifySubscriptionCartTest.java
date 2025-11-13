/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tests;

/**
 *
 * @author HP
 */
import framework.BaseTest;
import framework.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.HomePage;
import pages.SubscriptionSection;

public class VerifySubscriptionCartTest extends BaseTest {

    @Test(description = "Test Case 11: Verify Subscription in Cart page")
    public void verifySubscriptionInCartPage() {
        HomePage home = new HomePage(driver);
        home.open();
        Assert.assertTrue(home.isHomeVisible(), "Home page not visible");

        // Navigate to Cart
        driver.findElement(By.linkText("Cart")).click();

        CartPage cartPage = new CartPage(driver);
        Assert.assertTrue(cartPage.isShoppingCartTitleVisible(), "Shopping Cart page not visible");

        // Scroll down to footer
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");

        SubscriptionSection subscription = new SubscriptionSection(driver);
        Assert.assertTrue(subscription.isSubscriptionTitleVisible(), "'SUBSCRIPTION' title not visible in Cart page");

        String email = Utils.generateRandomEmail();
        subscription.subscribe(email);

        Assert.assertTrue(subscription.isSuccessMessageVisible(), "Success message not visible after subscription in Cart page");
    }
}
