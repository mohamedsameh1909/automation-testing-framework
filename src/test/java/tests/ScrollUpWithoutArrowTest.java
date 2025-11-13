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
import pages.HomePage;

public class ScrollUpWithoutArrowTest extends BaseTest {

    @Test(description = "Test Case 26: Verify Scroll Up without 'Arrow' button and Scroll Down functionality")
    public void verifyScrollUpWithoutArrow() throws InterruptedException {
        HomePage home = new HomePage(driver);
        home.open();
        Assert.assertTrue(home.isHomeVisible(), "Home page not visible");

        //Step 1: Scroll down to bottom
        home.scrollToBottom();
        Thread.sleep(2000); // allow scroll to finish

        //Step 2: Verify 'SUBSCRIPTION' is visible
        Assert.assertTrue(home.isSubscriptionVisible(), "'SUBSCRIPTION' section not visible at bottom of page");

        //Step 3: Scroll back up manually
        home.scrollToTop();
        Thread.sleep(2000);

        //Step 4: Verify top banner text is visible
        Assert.assertTrue(home.isFullFledgedTextVisible(), "'Full-Fledged practice website for Automation Engineers' text not visible after manual scroll up");
    }
}
