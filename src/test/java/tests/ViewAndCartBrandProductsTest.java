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
import pages.BrandPage;
import pages.HomePage;
import pages.ProductsPage;

public class ViewAndCartBrandProductsTest extends BaseTest {

    @Test(description = "Test Case 19: View & Cart Brand Products")
    public void viewAndCartBrandProducts() {
        HomePage home = new HomePage(driver);
        home.open();
        Assert.assertTrue(home.isHomeVisible(), "Home page not visible");

        //Step 1: Navigate to Products
        home.clickProducts();

        ProductsPage productsPage = new ProductsPage(driver);
        Assert.assertTrue(productsPage.isBrandsSidebarVisible(), "Brands sidebar not visible");

        //Step 2: Click on Polo brand
        productsPage.clickPoloBrand();

        BrandPage brandPage = new BrandPage(driver);
        Assert.assertTrue(brandPage.getBrandTitle().contains("BRAND - POLO PRODUCTS"),
                "Incorrect brand page title for Polo");

        //Step 3: Click on H&M brand
        productsPage.clickHnmBrand();
        Assert.assertTrue(brandPage.getBrandTitle().contains("BRAND - H&M PRODUCTS"),
                "Incorrect brand page title for H&M");
    }
}
