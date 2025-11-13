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
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CategoryPage;
import pages.HomePage;

public class ViewCategoryProductsTest extends BaseTest {

    @Test(description = "Test Case 18: View Category Products")
    public void viewCategoryProducts() {
        HomePage home = new HomePage(driver);
        home.open();
        Assert.assertTrue(home.isHomeVisible(), "Home page not visible");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Step 1: Click on 'Women' category to expand it
        WebElement womenCategory = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[@href='#Women']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", womenCategory);
        womenCategory.click();

        // Step 2: Click on 'Dress' subcategory
        WebElement dressSubCategory = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[@href='/category_products/1']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", dressSubCategory);
        dressSubCategory.click();

        // Step 3: Verify navigation to category page
        WebElement categoryHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h2[contains(text(),'Women - Dress Products')]")));
        Assert.assertTrue(categoryHeader.isDisplayed(), "Category page not displayed");

        // Step 4: Now click on 'Men' category
        WebElement menCategory = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[@href='#Men']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", menCategory);
        menCategory.click();

        // Step 5: Click on 'Tshirts' subcategory
        WebElement tshirtSubCategory = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[@href='/category_products/3']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", tshirtSubCategory);
        tshirtSubCategory.click();

        // Step 6: Verify navigation to category page
        WebElement menCategoryHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h2[contains(text(),'Men - Tshirts Products')]")));
        Assert.assertTrue(menCategoryHeader.isDisplayed(), "Men category page not displayed");
    }
}