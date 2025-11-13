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
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.ProductDetailPage;

public class AddReviewOnProductTest extends BaseTest {

    @Test(description = "Test Case 21: Add Review on Product")
    public void addReviewOnProduct() {
        HomePage home = new HomePage(driver);
        home.open();
        Assert.assertTrue(home.isHomeVisible(), "Home page not visible");

        //Step 1: Go to Products page
        home.clickProducts();

        //Step 2: View first product
        driver.findElement(By.xpath("(//a[text()='View Product'])[1]")).click();

        ProductDetailPage detailPage = new ProductDetailPage(driver);
        Assert.assertTrue(detailPage.isProductNameVisible(), "Product detail page not visible");
        Assert.assertTrue(detailPage.isWriteReviewVisible(), "'Write Your Review' not visible");

        //Step 3: Enter review
        String email = Utils.generateRandomEmail();
        detailPage.enterReviewDetails("ReviewUser", email, "This product is excellent!");
        detailPage.submitReview();

        //Step 4: Verify success
        Assert.assertTrue(detailPage.isReviewSuccessVisible(), "Review success message not visible");
    }
}
