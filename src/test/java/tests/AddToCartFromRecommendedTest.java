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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import pages.CartPage;
import pages.HomePage;

public class AddToCartFromRecommendedTest extends BaseTest {

    @Test(description = "Test Case 22: Add to Cart from Recommended Items")
    public void addToCartFromRecommendedItems() {
        HomePage home = new HomePage(driver);
        home.open();
        Assert.assertTrue(home.isHomeVisible(), "Home page not visible");

        //Step 1: Scroll to footer and verify Recommended Items
        Assert.assertTrue(home.isRecommendedItemsVisible(), "'RECOMMENDED ITEMS' not visible");

        //Step 2: Add first recommended product to cart
        home.addFirstRecommendedItemToCart();

        //Step 3: Wait for modal and click View Cart
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(home.modalViewCartButton));
        home.clickModalViewCart();

        //Step 4: Verify product is displayed in cart
        CartPage cartPage = new CartPage(driver);
        Assert.assertTrue(cartPage.hasAtLeastNItems(1), "Recommended product not added to cart");
    }
}
