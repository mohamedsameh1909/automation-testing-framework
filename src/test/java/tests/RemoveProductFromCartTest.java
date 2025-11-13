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
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.HomePage;
import pages.ProductsPage;

public class RemoveProductFromCartTest extends BaseTest {

    @Test(description = "Test Case 17: Remove Products From Cart")
    public void removeProductsFromCart() {
        HomePage home = new HomePage(driver);
        home.open();
        Assert.assertTrue(home.isHomeVisible(), "Home page not visible");

        //Step 1: Add product to cart
        home.clickProducts();
        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.addFirstProductAndGoToCart();

        CartPage cartPage = new CartPage(driver);
        Assert.assertTrue(cartPage.isShoppingCartTitleVisible(), "Cart page not visible");

        //Step 2: Remove product
        cartPage.removeFirstProductAndWait();

        //Step 3: Verify cart is empty
        Assert.assertTrue(cartPage.isCartEmptyMessageVisible(), "Cart is not empty after removing product");
    }
}
