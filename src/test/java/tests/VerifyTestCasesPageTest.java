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
import pages.TestCasesPage;

public class VerifyTestCasesPageTest extends BaseTest {

    @Test(description = "Test Case 7: Verify Test Cases Page")
    public void verifyTestCasesPage() {
        HomePage home = new HomePage(driver);
        home.open();
        Assert.assertTrue(home.isHomeVisible(), "Home page not visible");

        //Click on Test Cases link
        driver.findElement(By.linkText("Test Cases")).click();

        TestCasesPage testCasesPage = new TestCasesPage(driver);
        Assert.assertTrue(testCasesPage.isTestCasesTitleVisible(), "Test Cases page not visible");
    }
}
