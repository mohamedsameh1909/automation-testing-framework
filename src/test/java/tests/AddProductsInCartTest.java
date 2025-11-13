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
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.HomePage;
import pages.ProductsPage;

public class AddProductsInCartTest extends BaseTest {

    @Test(description = "Test Case 12: Add Products in Cart")
    public void addProductsToCart() {
        HomePage home = new HomePage(driver);
        home.open();
        Assert.assertTrue(home.isHomeVisible(), "Home page not visible");

        //Navigate to Products
        driver.findElement(By.cssSelector("div > ul > li:nth-child(2) > a")).click();

        ProductsPage productsPage = new ProductsPage(driver);
        Assert.assertTrue(productsPage.isAllProductsTitleVisible(), "ALL PRODUCTS page not visible");

        //Add first product
        productsPage.addFirstProductToCart();
        productsPage.clickContinueShopping();

        //Add second product
        productsPage.addSecondProductToCart();
        productsPage.clickViewCart();

        //Verify cart
        CartPage cartPage = new CartPage(driver);
        Assert.assertTrue(cartPage.isFirstProductVisible(), "First product not properly added to cart");
        Assert.assertTrue(cartPage.isSecondProductVisible(), "Second product not properly added to cart");
    }
}