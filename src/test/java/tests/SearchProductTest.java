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
import pages.ProductsPage;

public class SearchProductTest extends BaseTest {

    @Test(description = "Test Case 9: Search Product")
    public void searchProductFlow() {
        HomePage home = new HomePage(driver);
        home.open();
        Assert.assertTrue(home.isHomeVisible(), "Home page not visible");

        //Navigate to Products
        driver.findElement(By.cssSelector("ul > li:nth-child(2) > a")).click();

        ProductsPage productsPage = new ProductsPage(driver);
        Assert.assertTrue(productsPage.isAllProductsTitleVisible(), "ALL PRODUCTS page not visible");

        //Search for a known product (example: 'dress')
        productsPage.searchProduct("dress");

        Assert.assertTrue(productsPage.isSearchedProductsTitleVisible(), "'SEARCHED PRODUCTS' title not visible");
        Assert.assertTrue(productsPage.areSearchedProductsVisible(), "No searched products are visible");
    }
}
