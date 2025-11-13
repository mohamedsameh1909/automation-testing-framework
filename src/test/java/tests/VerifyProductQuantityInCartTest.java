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
import pages.ProductDetailPage;

public class VerifyProductQuantityInCartTest extends BaseTest {

    @Test(description = "Test Case 13: Verify Product Quantity in Cart")
    public void verifyProductQuantityInCart() {
        HomePage home = new HomePage(driver);
        home.open();
        Assert.assertTrue(home.isHomeVisible(), "Home page not visible");

        //Open first product detail page
        driver.findElement(By.cssSelector("div > ul > li:nth-child(2) > a")).click();
        driver.findElement(By.xpath("(//a[text()='View Product'])[1]")).click();

        ProductDetailPage detailPage = new ProductDetailPage(driver);
        Assert.assertTrue(detailPage.isProductNameVisible(), "Product detail page not visible");

        //Set quantity to 4 and add to cart
        detailPage.setQuantity(4);
        detailPage.clickAddToCartAndGoToCart();

        CartPage cartPage = new CartPage(driver);
        int actualQty = cartPage.getFirstProductQuantity();

        Assert.assertEquals(actualQty, 4, "Product quantity in cart does not match expected");
    }
}
