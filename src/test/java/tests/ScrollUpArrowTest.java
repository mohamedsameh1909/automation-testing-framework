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

public class ScrollUpArrowTest extends BaseTest {

    @Test(description = "Test Case 25: Verify Scroll Up using 'Arrow' button and Scroll Down functionality")
    public void verifyScrollUpAndDown() throws InterruptedException {
        HomePage home = new HomePage(driver);
        home.open();
        Assert.assertTrue(home.isHomeVisible(), "Home page not visible");

        //Step 1: Scroll down to bottom
        home.scrollToBottom();
        Thread.sleep(2000); // just to let page settle visually

        //Step 2: Verify 'SUBSCRIPTION' is visible
        Assert.assertTrue(home.isSubscriptionVisible(), "'SUBSCRIPTION' title not visible at bottom of page");

        //Step 3: Click arrow to scroll up
        home.clickScrollUpArrow();
        Thread.sleep(2000);

        //Step 4: Verify top text is visible
        Assert.assertTrue(home.isFullFledgedTextVisible(), "'Full-Fledged practice website for Automation Engineers' text not visible after scrolling up");
    }
}
