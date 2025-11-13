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
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.SubscriptionSection;

public class VerifySubscriptionHomeTest extends BaseTest {

    @Test(description = "Test Case 10: Verify Subscription in home page")
    public void verifySubscriptionInHomePage() {
        HomePage home = new HomePage(driver);
        home.open();
        Assert.assertTrue(home.isHomeVisible(), "Home page not visible");

        // Scroll to footer
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");

        SubscriptionSection subscription = new SubscriptionSection(driver);
        Assert.assertTrue(subscription.isSubscriptionTitleVisible(), "'SUBSCRIPTION' title not visible");

        String email = Utils.generateRandomEmail();
        subscription.subscribe(email);

        Assert.assertTrue(subscription.isSuccessMessageVisible(), "Success message not visible after subscription");
    }
}