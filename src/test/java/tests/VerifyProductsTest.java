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
import pages.HomePage;
import pages.ProductDetailPage;
import pages.ProductsPage;

public class VerifyProductsTest extends BaseTest {

    @Test(description = "Test Case 8: Verify All Products and Product Detail Page")
    public void verifyProductsAndDetailPage() {
        HomePage home = new HomePage(driver);
        home.open();
        Assert.assertTrue(home.isHomeVisible(), "Home page not visible");

        //Click on Products
        driver.findElement(By.cssSelector("ul > li:nth-child(2) > a")).click();

        ProductsPage productsPage = new ProductsPage(driver);
        Assert.assertTrue(productsPage.isAllProductsTitleVisible(), "ALL PRODUCTS page not visible");
        Assert.assertTrue(productsPage.isProductsListVisible(), "Products list is not visible");

        productsPage.clickFirstProductView();

        ProductDetailPage detailPage = new ProductDetailPage(driver);
        Assert.assertTrue(detailPage.isProductNameVisible(), "Product name not visible");
        Assert.assertTrue(detailPage.isCategoryVisible(), "Product category not visible");
        Assert.assertTrue(detailPage.isPriceVisible(), "Product price not visible");
        Assert.assertTrue(detailPage.isAvailabilityVisible(), "Product availability not visible");
        Assert.assertTrue(detailPage.isConditionVisible(), "Product condition not visible");
        Assert.assertTrue(detailPage.isBrandVisible(), "Product brand not visible");
    }
}
